package com.example.tutorial.controller;

import com.example.tutorial.dto.ProductDTO;
import com.example.tutorial.entity.Product;
import com.example.tutorial.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product management APIs")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active products")
    public ResponseEntity<List<Product>> getActiveProducts() {

        List<Product> products = productService.getActiveProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/price/{price}")
    @Operation(summary = "Get products by price greater than")
    public ResponseEntity<List<Product>> getProductsByPriceGreaterThan(@PathVariable Double price) {

        List<Product> products = productService.getProductsByPriceGreaterThan(price);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Optional<Product> product = productService.getProductById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new product", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {

        Product product = productService.saveProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update an existing product", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {

        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productDTO.setId(id);
        Product updatedProduct = productService.saveProduct(productDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a product", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        Optional<Product> product = productService.getProductById(id);
        if (product.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
