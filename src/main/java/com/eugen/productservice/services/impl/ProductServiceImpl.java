package com.eugen.productservice.services.impl;

import com.eugen.productservice.models.Product;
import com.eugen.productservice.models.SearchResult;
import com.eugen.productservice.repositories.ProductRepository;
import com.eugen.productservice.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final String PRODUCT_COLOR_COLUMN_NAME = "color";
    private final String PRODUCT_MANUFACTURER_COLUMN_NAME = "manufacturer";
    private final String PRODUCT_TYPE_COLUMN_NAME = "type";
    private final String PRODUCT_YEAR_COLUMN_NAME = "year";

    private final ProductRepository productRepository;

    public Long saveProduct(Product product) {
        return productRepository.save(product).getId();
    }

    public Product findProductById(Long id){
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public SearchResult searchByModelName(String substring) {
        List<Product> foundProducts = productRepository.findByModelNameNative(substring.toLowerCase());
        Map<String, Set<String>> facetsForFoundProducts = generateFacetsForFoundProducts(foundProducts);

        return new SearchResult(foundProducts, facetsForFoundProducts);
    }

    /**
     * Генерирует фасеты для найденных продуктов: цвет, производителя, год и тип.
     *
     * @param products список продуктов для генерации фасетов
     * @return Map, содержащая имена типов фасетов в качестве ключа и множество значений фасетов данного типа
     */
    public Map<String, Set<String>> generateFacetsForFoundProducts(List<Product> products) {
        Map<String, Set<String>> resultFacets = new HashMap<>();

        resultFacets.put(PRODUCT_COLOR_COLUMN_NAME, generateFacet(products, Product::getColor));
        resultFacets.put(PRODUCT_MANUFACTURER_COLUMN_NAME, generateFacet(products, Product::getManufacturer));
        resultFacets.put(PRODUCT_YEAR_COLUMN_NAME, generateFacet(products, p -> p.getYear().toString()));
        resultFacets.put(PRODUCT_TYPE_COLUMN_NAME, generateFacet(products, Product::getType));

        return resultFacets;
    }

    /**
     * Генерирует фасеты для найденных продуктов на основе указанной функции отображения.
     *
     * @param products список продуктов для генерации фасетов
     * @param extractor функция отображения для извлечения значения фасета из каждого продукта
     * @return множество строк фасетов на основе функции отображения
     */
    public Set<String> generateFacet(List<Product> products, Function<Product, String> extractor) {
        return products.stream()
                .map(extractor)
                .collect(Collectors.toSet());
    }

}

