package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Store;

import java.util.List;

public class StoreDTO {
    public Long id;
    public String storeName;
    public List<ProductDTO> products;

    public StoreDTO(){

    }

    public StoreDTO(Store store){
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.products = store.getProducts().stream().map(ProductDTO::new).toList();
    }

    public Long getId() {
        return id;
    }

    public String getstoreName() {
        return storeName;
    }

    public void setstoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
}
