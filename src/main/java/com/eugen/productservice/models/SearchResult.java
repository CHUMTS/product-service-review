package com.eugen.productservice.models;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class SearchResult {
    private final List<Product> products;

    private final Map<String, Set<String>> facets;
}
