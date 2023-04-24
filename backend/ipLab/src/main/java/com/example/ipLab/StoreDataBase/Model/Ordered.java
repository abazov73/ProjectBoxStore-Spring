package com.example.ipLab.StoreDataBase.Model;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_fk")
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_fk")
    private Product product;
    @Column
    private int quantity;
    public Ordered(){}
    public Ordered(int quantity){
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }


    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (!product.getOrders().contains(this)){
            product.AddOrdered(this);
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (!customer.getOrders().contains(this)){
            customer.AddOrdered(this);
        }
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
