package ru.persistence;

import ru.persistence;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class ShoppCars {

    private final Map<Product, Integer> cart = new HashMap<>();

    public Map<Product, Integer> getCartMap() {
        return cartMap;
    }

    public void addProduct(Product product, Integer quantity) {
        if (product != null) cartMap.merge(product, quantity, Integer::sum);
        if (cartMap.get(product) < 1) cartMap.remove(product);
    }

    public float getSumCart() {
        float sum = 0.0f;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            sum += entry.getKey().getPrice();
        }
        return sum;
    }

}