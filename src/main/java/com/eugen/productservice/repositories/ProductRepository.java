package com.eugen.productservice.repositories;

import com.eugen.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p WHERE p.modelName LIKE %:substring% OR p.manufacturer LIKE %:substring%")
    List<Product> findByModelNameNative(@Param("substring")String searchSubstring);
}
