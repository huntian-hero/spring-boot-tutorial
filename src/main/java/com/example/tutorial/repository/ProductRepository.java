package com.example.tutorial.repository;

import com.example.tutorial.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findByActiveTrue();

  @Query("SELECT p FROM Product p WHERE p.price > :price")
  List<Product> findByPriceGreaterThan(Double price);
}
