package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Ordered;

public class OrderedDTO {
    public final Long id;
    public final int quantity;
    public final String productName;
    public final String customerFIO;
    public final String storeName;

    public OrderedDTO(Ordered ordered){
        this.id = ordered.getId();
        this.quantity = ordered.getQuantity();
        this.productName = ordered.getProduct().getName();
        this.storeName = ordered.getProduct().getStore().getStoreName();
        this.customerFIO = ordered.getCustomer().getLastName() + " " + ordered.getCustomer().getFirstName() + " " + ordered.getCustomer().getMiddleName();
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getCustomerFIO() {
        return customerFIO;
    }

    public String getStoreName() {
        return storeName;
    }
}
