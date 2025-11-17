package com.example.tutorial.service;

import com.example.tutorial.dto.ProductDTO;
import com.example.tutorial.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();
    List<Product> getActiveProducts();
    List<Product> getProductsByPriceGreaterThan(Double price);
    Optional<Product> getProductById(Long id);
    Product saveProduct(ProductDTO productDTO);
    void deleteProduct(Long id);
}
