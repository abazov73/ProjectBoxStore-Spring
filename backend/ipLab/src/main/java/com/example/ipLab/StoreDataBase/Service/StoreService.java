package com.example.ipLab.StoreDataBase.Service;

import com.example.ipLab.StoreDataBase.Model.Product;
import com.example.ipLab.StoreDataBase.Model.Store;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {
    @PersistenceContext
    private EntityManager em;
    private ProductService productService;

    public StoreService(ProductService productService){
        this.productService = productService;
    }

    @Transactional
    public Store addStore(String storeName){
        if (!StringUtils.hasText(storeName)){
            throw new IllegalArgumentException("Store name is null or empty");
        }
        final Store store = new Store(storeName);
        em.persist(store);
        return store;
    }

    @Transactional()
    public Store getStore(Long id){
        Store store = em.find(Store.class, id);
        if (store == null){
            throw new EntityNotFoundException(String.format("Store with id = %s isn't found", id));
        }
        return store;
    }

    @Transactional
    public List<Store> getAllStores(){
        return em.createQuery("get s from Store s", Store.class).getResultList();
    }

    @Transactional
    public Store updateStore(Long id, String storeName){
        if (!StringUtils.hasText(storeName)){
            throw new IllegalArgumentException("Store name is null or empty");
        }
        final Store store = getStore(id);
        store.setStoreName(storeName);
        return em.merge(store);
    }

    @Transactional
    public Store deleteStore(Long id){
        final Store store = getStore(id);
        em.remove(store);
        return store;
    }
    @Transactional
    public void deleteAllStores(){
        em.createQuery("delete from Store");
    }

    //product section
    @Transactional
    public Product addProduct(Long storeId, Long productId){
        Store store = getStore(storeId);
        Product product = productService.getProduct(productId);
        store.AddProduct(product);
        em.persist(store);
        return product;
    }

    @Transactional()
    public Product getProductFromStore(Long productId, Long storeId){
        Store store = getStore(storeId);
        var prFind = store.getProducts().stream().filter(pr -> pr.getId().equals(productId)).findFirst();
        if (prFind.isPresent()) {
            return prFind.get();
        }
        else throw new EntityNotFoundException(String.format("Product with id = %s isn't found in store with id = %s", productId, storeId));
    }

    @Transactional
    public List<Product> getAllProductsFromStore(Long storeId) {
        Store store = getStore(storeId);
        return store.getProducts();
    }

    @Transactional
    public Product deleteProductFromStore(Long storeId, Long productId){
        Store store = getStore(storeId);
        Product product = getProductFromStore(productId, storeId);
        store.getProducts().remove(product);
        em.remove(product);
        return product;
    }
    @Transactional
    public void deleteAllProducts(Long storeId){
        Store store = getStore(storeId);
        List<Product> storeProducts = store.getProducts();
        for (Product pr:
             storeProducts) {
            pr.setStore(null);
            store.getProducts().remove(pr);
            em.remove(pr);
        }
    }
}
