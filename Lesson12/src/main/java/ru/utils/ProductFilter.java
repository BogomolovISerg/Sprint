package ru.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ProductFilter {

    Float minPrice;
    Float maxPrice;
    String partTitle;

}
