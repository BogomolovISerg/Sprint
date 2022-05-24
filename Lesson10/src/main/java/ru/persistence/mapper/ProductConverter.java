package ru.persistence.mapper;

import org.springframework.stereotype.Component;
import ru.persistence.dtos.ProductDto;
import ru.persistence.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    public ProductDto fromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return product;
    }
}