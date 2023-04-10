package com.example.ipLab.StoreDataBase.Model;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="store_fk")
    private Store store;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_fk")
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_fk")
    private Product product;
    @Column
    private int quantity;
    public Ordered(){}
    public Ordered(Store store, Product product, Customer customer, int quantity){
        this.store = store;
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ordered order = (Ordered) obj;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
