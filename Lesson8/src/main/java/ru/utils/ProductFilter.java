package ru.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ProductFilter {

    private Float minPrice;
    private Float maxPrice;
    private String partName;

}
