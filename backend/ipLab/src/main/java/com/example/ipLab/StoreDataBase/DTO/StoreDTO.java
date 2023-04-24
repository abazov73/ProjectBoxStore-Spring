package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Store;

import java.util.List;

public class StoreDTO {
    public final Long id;
    public final String storeName;
    public final List<ProductDTO> products;

    public StoreDTO(Store store){
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.products = store.getProducts().stream().map(ProductDTO::new).toList();
    }

    public Long getId() {
        return id;
    }

    public String getStoreName() {
        return storeName;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
}
