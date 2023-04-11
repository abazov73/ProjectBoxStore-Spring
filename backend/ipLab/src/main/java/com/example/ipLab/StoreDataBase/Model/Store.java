package com.example.ipLab.StoreDataBase.Model;

import com.example.ipLab.StoreDataBase.Service.ProductService;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String storeName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "store", cascade = CascadeType.ALL)
    private List<Ordered> orders;
    public Store(){}
    public Store(String storeName){
        this.storeName = storeName;
        this.orders = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getStoreName() {
        return storeName;
    }

    public List<Ordered> getOrders() {
        return orders;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void AddProduct(Product product){
        this.products.add(product);
        if (product.getStore() != this){
            product.setStore(this);
        }
    }
    public void AddOrdered(Ordered ordered){
        this.orders.add(ordered);
        if (ordered.getStore() != this){
            ordered.setStore(this);
        }
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Store store = (Store) obj;
        return Objects.equals(id, store.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
