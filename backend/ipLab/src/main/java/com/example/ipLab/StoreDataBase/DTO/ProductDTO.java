package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Product;

import java.util.List;

public class ProductDTO {
    public final Long id;
    public final String name;
    public final StoreDTO store;
    public final List<OrderedDTO> orders;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.store = new StoreDTO(product.getStore());
        this.orders = product.getOrders().stream().map(OrderedDTO::new).toList();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StoreDTO getStore() {
        return store;
    }

    public List<OrderedDTO> getOrders() {
        return orders;
    }
}
