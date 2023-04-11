package com.example.ipLab.StoreDataBase.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_fk")
    private Store store;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private List<Ordered> orders;
    public Product(){}
    public Product(String name){
        this.name = name;
        this.orders = new ArrayList<Ordered>();
    }
    public Product(Store store, String name){
        this.store = store;
        this.name = name;
        this.orders = new ArrayList<Ordered>();
    }
    public void AddOrdered(Ordered ordered){
        this.orders.add(ordered);
        if (ordered.getProduct() != this){
            ordered.setProduct(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Store getStore() {
        return store;
    }

    public List<Ordered> getOrders() {
        return orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
