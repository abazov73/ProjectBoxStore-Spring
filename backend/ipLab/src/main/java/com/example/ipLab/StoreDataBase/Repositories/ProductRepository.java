package com.example.ipLab.StoreDataBase.Repositories;

import com.example.ipLab.StoreDataBase.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
