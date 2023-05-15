package com.example.ipLab.StoreDataBase.Repositories;

import com.example.ipLab.StoreDataBase.Model.Ordered;
import com.example.ipLab.StoreDataBase.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface OrderedRepository extends JpaRepository<Ordered, Long> {
    @Query("SELECT o FROM Ordered o WHERE o.customer.id = ?1")
    Collection<Ordered> findOrdersByCustomerId(Long clientId);
}
