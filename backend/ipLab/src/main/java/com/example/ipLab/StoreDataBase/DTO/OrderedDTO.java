package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Ordered;

public class OrderedDTO {
    public final Long id;
    public final int quantity;
    public final ProductDTO product;
    public final CustomerDTO customer;

    public OrderedDTO(Ordered ordered){
        this.id = ordered.getId();
        this.quantity = ordered.getQuantity();
        this.product = new ProductDTO(ordered.getProduct());
        this.customer = new CustomerDTO(ordered.getCustomer());
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }
}
