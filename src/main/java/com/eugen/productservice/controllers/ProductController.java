package com.eugen.productservice.controllers;

import com.eugen.productservice.models.Product;
import com.eugen.productservice.models.SearchResult;
import com.eugen.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Long> saveProduct(@RequestBody Product requestProduct) {
        Long savedProductId = productService.saveProduct(requestProduct);
        return ResponseEntity.ok().body(savedProductId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product responseProduct = productService.findProductById(id);
        return ResponseEntity.ok().body(responseProduct);
    }

    @GetMapping(value = "/search/{searchQuery}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResult> getProductsBySubstring(@PathVariable String searchQuery) {
        SearchResult productsWithFacets = productService.searchByModelName(searchQuery);
        return ResponseEntity.ok().body(productsWithFacets);
    }
}
