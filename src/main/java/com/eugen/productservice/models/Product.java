package com.eugen.productservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products", schema = "public")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    private String color;

    private String manufacturer;

    private Integer year;

    private String type;

    @Column(name = "model_name")
    private String modelName;

}
