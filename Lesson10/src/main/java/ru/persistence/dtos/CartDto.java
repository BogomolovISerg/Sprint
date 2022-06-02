package ru.persistence.dtos;

import lombok.Data;
import ru.persistence.entities.Product;

import java.util.HashMap;
import java.util.Map;

@Data
public class CartDto {

    private Map<Product, Integer> cartMap = new HashMap<>();
    private Float totalSum;

}
