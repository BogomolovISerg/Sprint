package ru.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.persistence.ShoppCars;
import ru.persistence.Product;
import ru.persistence.ProductRepository;
import java.util.Map;


@Service
public class ShoppCarsService {

    private ProductRepository productRepository;

    public ShoppCarsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Lookup
    public ShoppCars getNewCart() {
        return null;
    }

    public void addProduct(ShoppCars cart, Product product, Integer quantity) {
        cart.addProduct(product, quantity);
    }

    public void addProduct(ShoppCars cart, Integer prodId, Integer quantity) {
        Product product = productRepository.findProduct(prodId);
        this.addProduct(cart, product, quantity);
    }

    public void delProduct(ShoppCars cart, Product product, Integer quantity) {
        cart.delProduct(product, quantity);
    }

    public void printCart(ShoppCars cart) {
        float summa = 0.0f;
        for (Map.Entry<Product, Integer> entryMap : cart.getCartMap().entrySet()) {
            Product product = entryMap.getKey();
            float intermediate = entryMap.getValue() * product.getPrice();
            System.out.println(String.format(product.toString() + "| кол = %-5s | Сумма = %-10s",entryMap.getValue(),intermediate));
            summa += intermediate;
        }
        System.out.println("Сумма корзины: " + summa);
    }
}
