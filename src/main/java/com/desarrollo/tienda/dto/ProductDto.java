package com.desarrollo.tienda.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;


    public ProductDto(Long id, String name, String description, Double price, Long user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductDto() {
    }
}
