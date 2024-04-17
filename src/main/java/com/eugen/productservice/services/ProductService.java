package com.eugen.productservice.services;

import com.eugen.productservice.models.Product;
import com.eugen.productservice.models.SearchResult;

public interface ProductService {
    Long saveProduct(Product product);

    Product findProductById(Long id);

    SearchResult searchByModelName(String substring);

}
