package com.eugen.productservice.services;

import com.eugen.productservice.models.Product;
import com.eugen.productservice.models.SearchResult;
import com.eugen.productservice.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    // возвращать айди/объект/boolean/void - по ситуации
    public Long saveProduct(Product product) {
        return productRepository.save(product).getId();
    }
    //todo Возвращать сохранённый продукт без айди
    public Product findProductById(Long id){
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public SearchResult searchByModelName(String substring) {
        List<Product> foundProducts = productRepository.findByModelNameNative(substring.toLowerCase());
        Map<String, Set<String>> facetsForFoundProducts = generateFacetsForFoundProducts(foundProducts);

        return new SearchResult(foundProducts, facetsForFoundProducts);
    }
    // Да, копипаста есть, но так понятнее и быстро. В другом случае нужно пилить
    // иное решение, мудрёное, с дженериками, чтобы можно было для разных таблиц
    // с разными полями его применять. Оно будет переиспользуемым и более лаконичным, да.
    private Map<String, Set<String>> generateFacetsForFoundProducts(List<Product> products) {
        Map<String, Set<String>> resultFacets = new HashMap<>();

        Set<String> colorFacets = products.stream()
                .map(Product::getColor).collect(Collectors.toSet());
        Set<String> manufacturerFacets = products.stream()
                .map(Product::getManufacturer).collect(Collectors.toSet());
        Set<String> yearsFacets = products.stream()
                .map(p -> p.getYear().toString()).collect(Collectors.toSet());
        Set<String> typeFacets = products.stream()
                .map(Product::getType).collect(Collectors.toSet());

        resultFacets.put("color", colorFacets);
        resultFacets.put("manufacturer", manufacturerFacets);
        resultFacets.put("years", yearsFacets);
        resultFacets.put("types", typeFacets);
        return resultFacets;
    }


}

