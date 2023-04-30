package com.example.ipLab.StoreDataBase.Repositories;

import com.example.ipLab.StoreDataBase.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.store <> null")
    Collection<Product> findAllProductsWithStores();
    @Query("SELECT p FROM Product p WHERE p.store = null")
    Collection<Product> findAllProductsWithoutStores();
}
