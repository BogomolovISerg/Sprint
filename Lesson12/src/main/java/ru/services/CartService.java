package ru.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.persistence.Cart;
import ru.persistence.entities.Product;
import ru.persistence.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Data
@AllArgsConstructor
public class CartService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Lookup
    public Cart getNewCart() {
        return null;
    }

    public void addProduct(Cart cart, Product product, Integer quantity) {
        if (product != null) cart.getCartMap().merge(product, quantity, Integer::sum);
        if (cart.getCartMap().get(product) < 1) cart.getCartMap().remove(product);
    }

    public void addProductById(Cart cart, Integer prodId, Integer quantity) {
        Product product = productService.findProductById(prodId).get();
        this.addProduct(cart, product, quantity);
    }

    public Float getSum(Cart cart) {
        Float sum = 0F;
        for (Map.Entry<Product, Integer> entry : cart.getCartMap().entrySet()) {
            sum += entry.getKey().getPrice();
        }
        return sum;
    }

    public int getProductQuantity(Cart cart, Product product) {
        if (cart.getCartMap().containsKey(product)) {
            return cart.getCartMap().get(product);
        }
        return 0;
    }

    public Integer getItemsAmount(Cart cart) {
        Integer amount = 0;
        for (Map.Entry<Product, Integer> entryMap : cart.getCartMap().entrySet()) {
            amount += entryMap.getValue();
        }
        return amount;
    }

    public int getProductQuantity(Cart cart, Integer prodId) {
        Product product = productService.findProductById(prodId).get();
        return this.getProductQuantity(cart, product);
    }

    public List<Product> getCartListSorted(Cart cart) {
        List<Product> cartList = new ArrayList<>(cart.getCartMap().keySet());
        Collections.sort(cartList, (p1, p2) -> {
            if (p1.getId() > p2.getId()) {
                return 1;
            } else if (p1.getId() < p2.getId()) {
                return -1;
            }
            return 0;
        });
        return cartList;
    }

}