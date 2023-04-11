package com.example.ipLab.StoreDataBase.Service;

import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Model.Ordered;
import com.example.ipLab.StoreDataBase.Model.Product;
import com.example.ipLab.StoreDataBase.Model.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class ProductService {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    public Product addProduct(String productName){
        if (!StringUtils.hasText(productName)){
            throw new IllegalArgumentException("Product name is null or empty");
        }
        final Product product = new Product(productName);
        em.persist(product);
        return product;
    }

    @Transactional()
    public Product getProduct(Long id){
        Product product = em.find(Product.class, id);
        if (product == null){
            throw new EntityNotFoundException(String.format("Product with id = %s isn't found", id));
        }
        em.persist(product);
        return product;
    }

    @Transactional
    public List<Product> getAllProducts(){
        return em.createQuery("get p from Product p", Product.class).getResultList();
    }

    @Transactional
    public Product updateProduct(Long id, String productName){
        if (!StringUtils.hasText(productName)){
            throw new IllegalArgumentException("Product name is null or empty");
        }
        final Product product = getProduct(id);
        if (product == null){
            throw new EntityNotFoundException(String.format("Product with id = %s isn't found", id));
        }
        product.setName(productName);
        return em.merge(product);
    }

    @Transactional
    public Product deleteProduct(Long id){
        final Product product = getProduct(id);
        if (product == null){
            throw new EntityNotFoundException(String.format("Product with id = %s isn't found", id));
        }
        Store store = product.getStore();
        if (store != null) store.getProducts().remove(product);
        em.remove(product);
        return product;
    }
    @Transactional
    public void deleteAllProducts(){
        em.createQuery("delete from Customer");
    }
}
