package ru.persistence;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.persistence.entities.CartEntry;
import ru.persistence.entities.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Component
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class ShoppCars {

    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private final int cartId = COUNTER.incrementAndGet();

    private final Map<Product, Integer> cart = new HashMap<>();
    public Map<Product, Integer> getCartMap() {
        return cart;
    }

    public float getSum() {
        float sum = 0.0f;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            sum += entry.getKey().getPrice();
        }
        return sum;
    }

}