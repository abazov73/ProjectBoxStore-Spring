package com.example.ipLab.StoreDataBase.Repositories;

import com.example.ipLab.StoreDataBase.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
