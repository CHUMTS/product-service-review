package com.eugen.productservice.controllers;

import com.eugen.productservice.models.Product;
import com.eugen.productservice.models.SearchResult;
import org.springframework.http.ResponseEntity;

public interface ProductController {

    ResponseEntity<Long> saveProduct(Product requestProduct);

    ResponseEntity<Product> getProductById(Long id);

    ResponseEntity<SearchResult> getProductsBySubstring(String searchQuery);
}
