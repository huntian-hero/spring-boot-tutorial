package com.example.tutorial.service;

import com.example.tutorial.dto.ProductDTO;
import com.example.tutorial.entity.Product;
import com.example.tutorial.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired private ProductRepository productRepository;

  @Autowired private ModelMapper modelMapper;

  @Override
  public List<Product> getAllProducts() {

    return productRepository.findAll();
  }

  @Override
  public List<Product> getActiveProducts() {

    return productRepository.findByActiveTrue();
  }

  @Override
  public List<Product> getProductsByPriceGreaterThan(Double price) {

    return productRepository.findByPriceGreaterThan(price);
  }

  @Override
  public Optional<Product> getProductById(Long id) {

    return productRepository.findById(id);
  }

  @Override
  public Product saveProduct(ProductDTO productDTO) {

    Product product = modelMapper.map(productDTO, Product.class);
    return productRepository.save(product);
  }

  @Override
  public void deleteProduct(Long id) {

    productRepository.deleteById(id);
  }
}
