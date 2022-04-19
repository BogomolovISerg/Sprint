package org.web.service;

import org.web.model.Product;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductService {
    public List<Product> getAllProduct(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Чай", 100));
        productList.add(new Product(2, "Кофе", 200));
        productList.add(new Product(3, "Печеньки", 50));
        return productList;
    }
}
