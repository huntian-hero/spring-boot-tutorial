package com.example.spring_boot_tutorial.repository;

import com.example.spring_boot_tutorial.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByActiveTrue();

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findByPriceCreaterThan(Double price);
}
