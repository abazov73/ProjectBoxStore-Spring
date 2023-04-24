package com.example.ipLab.StoreDataBase.Service;

import com.example.ipLab.StoreDataBase.Exceptions.StoreNotFoundException;
import com.example.ipLab.StoreDataBase.Model.Product;
import com.example.ipLab.StoreDataBase.Model.Store;
import com.example.ipLab.StoreDataBase.Repositories.StoreRepository;
import com.example.ipLab.StoreDataBase.util.validation.ValidatorUtil;
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
    private final StoreRepository storeRepository;
    private final ValidatorUtil validatorUtil;
    private ProductService productService;

    public StoreService(StoreRepository storeRepository, ValidatorUtil validatorUtil, ProductService productService){
        this.storeRepository = storeRepository;
        this.validatorUtil = validatorUtil;
        this.productService = productService;
    }

    @Transactional
    public Store addStore(String storeName){
        final Store store = new Store(storeName);
        validatorUtil.validate(store);
        storeRepository.save(store);
        return store;
    }

    @Transactional()
    public Store getStore(Long id){
        return storeRepository.findById(id).orElseThrow(() -> new StoreNotFoundException(id));
    }

    @Transactional
    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    @Transactional
    public Store updateStore(Long id, String storeName){
        final Store store = getStore(id);
        store.setStoreName(storeName);
        validatorUtil.validate(store);
        return storeRepository.save(store);
    }

    @Transactional
    public Store deleteStore(Long id){
        final Store store = getStore(id);
        storeRepository.delete(store);
        return store;
    }
    @Transactional
    public void deleteAllStores(){
        storeRepository.deleteAll();
    }

    //product section
    @Transactional
    public Product addProduct(Long storeId, Long productId){
        Store store = getStore(storeId);
        Product product = productService.getProduct(productId);
        store.AddProduct(product);
        storeRepository.save(store);
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
        product.setStore(null);
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
        }
    }
}
