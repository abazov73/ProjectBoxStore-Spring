package com.example.ipLab.StoreDataBase.Repositories;

import com.example.ipLab.StoreDataBase.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
