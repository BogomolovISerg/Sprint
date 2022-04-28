package ru.persistence;

import ru.persistence.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class ShoppCars{

    private final Map<Product, Integer> cart = new HashMap<>();

    public Map<Product, Integer> getCartMap() {
        return cart;
    }

    public void addProduct(Product product, Integer quantity) {
        cart.merge(product, quantity, Integer::sum);
    }

    public void delProduct(Product product, Integer quantity) {
        if (cart.containsKey(product)) {
            if (quantity != null && cart.get(product).compareTo(quantity) > 0) {
                cart.put(product, cart.get(product) - quantity);
            } else
                cart.remove(product);
        }
    }

    public float getSum() {
        float sum = 0.0f;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            sum += entry.getKey().getPrice();
        }
        return sum;
    }

}