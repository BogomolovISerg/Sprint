package ru.persistence.dtos;

import lombok.Data;

@Data
public class ProductDto {

    private Integer id;
    private String name;
    private Float price;

}