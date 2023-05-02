package com.example.ipLab.StoreDataBase.DTO;

import com.example.ipLab.StoreDataBase.Model.Customer;
import com.example.ipLab.StoreDataBase.Model.Ordered;
import com.example.ipLab.StoreDataBase.Model.Product;

public class OrderedDTO {
    public Long id;
    public int quantity;
    public String productName;
    public String customerFIO;
    public String storeName;
    public Long customerId;
    public Long productId;

    public OrderedDTO(){

    }

    public OrderedDTO(Ordered ordered){
        this.id = ordered.getId();
        this.quantity = ordered.getQuantity();
        this.productName = ordered.getProduct().getName();
        this.storeName = ordered.getProduct().getStore().getStoreName();
        this.customerFIO = ordered.getCustomer().getLastName() + " " + ordered.getCustomer().getFirstName() + " " + ordered.getCustomer().getMiddleName();
        this.customerId = ordered.getProduct().getId();
        this.productId = ordered.getProduct().getId();
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }

    public String getcustomerFIO() {
        return customerFIO;
    }

    public void setcustomerFIO(String customerFIO) {
        this.customerFIO = customerFIO;
    }

    public String getstoreName() {
        return storeName;
    }

    public void setstoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getProductId() {
        return productId;
    }
}
