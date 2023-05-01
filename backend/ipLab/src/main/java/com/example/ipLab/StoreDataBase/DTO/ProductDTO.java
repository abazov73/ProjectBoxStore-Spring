package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Product;

import java.util.List;

public class ProductDTO {
    public Long id;
    public String productName;
    public String storeName;
    public ProductDTO(){

    }

    public ProductDTO(Product product){
        this.id = product.getId();
        this.productName = product.getName();
        this.storeName = product.getStore() == null ? null : product.getStore().getStoreName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return productName;
    }

    public String getStoreName() {
        return storeName;
    }
}
