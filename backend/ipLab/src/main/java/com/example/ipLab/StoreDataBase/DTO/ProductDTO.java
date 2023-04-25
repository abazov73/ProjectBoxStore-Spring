package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Product;

import java.util.List;

public class ProductDTO {
    public final Long id;
    public final String name;
    public final String storeName;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.storeName = product.getStore().getStoreName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStoreName() {
        return storeName;
    }
}
