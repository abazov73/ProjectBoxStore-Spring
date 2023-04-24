package com.example.ipLab.StoreDataBase.Repositories;

import com.example.ipLab.StoreDataBase.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedRepository extends JpaRepository<Ordered, Long> {
}
