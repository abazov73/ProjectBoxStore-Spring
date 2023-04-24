package com.example.ipLab.StoreDataBase.Service;

import com.example.ipLab.StoreDataBase.Exceptions.ProductNotFoundException;
import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Model.Ordered;
import com.example.ipLab.StoreDataBase.Model.Product;
import com.example.ipLab.StoreDataBase.Model.Store;
import com.example.ipLab.StoreDataBase.Repositories.ProductRepository;
import com.example.ipLab.StoreDataBase.util.validation.ValidatorUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ValidatorUtil validatorUtil;

    public ProductService(ProductRepository productRepository, ValidatorUtil validatorUtil){
        this.productRepository = productRepository;
        this.validatorUtil = validatorUtil;
    }
    @Transactional
    public Product addProduct(String productName){
        final Product product = new Product(productName);
        validatorUtil.validate(product);
        productRepository.save(product);
        return product;
    }

    @Transactional()
    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Transactional
    public Product updateProduct(Long id, String productName){
        final Product product = getProduct(id);
        product.setName(productName);
        validatorUtil.validate(product);
        return productRepository.save(product);
    }

    @Transactional
    public Product deleteProduct(Long id){
        final Product product = getProduct(id);
        Store store = product.getStore();
        if (store != null) store.getProducts().remove(product);
        productRepository.delete(product);
        return product;
    }
    @Transactional
    public void deleteAllProducts(){
        productRepository.deleteAll();
    }
}
