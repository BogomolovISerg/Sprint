package ru.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.persistence.ShoppCars;
import ru.persistence.entities.Product;
import ru.persistence.repositories.CartEntryRepository;
import ru.persistence.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppCarsService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Lookup
    public ShoppCars getNewCart() {
        return null;
    }

    public void addProduct(ShoppCars cart, Product product, Integer quantity) {
        if (product != null) cart.getCartMap().merge(product, quantity, Integer::sum);
        if (cart.getCartMap().get(product) < 1) cart.getCartMap().remove(product);
    }

    public void addProduct(ShoppCars cart, Integer prodId, Integer quantity) {
        Product product = productService.findById(prodId).get();
        this.addProduct(cart, product, quantity);
    }

    public Float getSum(ShoppCars cart) {
        return cart.getSum();
    }

    public int getProductQuantity(ShoppCars cart, Product product) {
        if (cart.getCartMap().containsKey(product)) {
            return cart.getCartMap().get(product);
        }
        return 0;
    }

    public Integer getItemsAmount(ShoppCars cart) {
        Integer amount = 0;
        for (Map.Entry<Product, Integer> entryMap : cart.getCartMap().entrySet()) {
            amount += entryMap.getValue();
        }
        return amount;
    }

    public int getProductQuantity(ShoppCars cart, Integer prodId) {
        Product product = productService.findById(prodId).get(); // TODO
        return this.getProductQuantity(cart, product);
    }

    public List<Product> getCartList(ShoppCars cart) {
        List<Product> cartList = new ArrayList<>(cart.getCartMap().keySet());
        return cartList;
    }

}