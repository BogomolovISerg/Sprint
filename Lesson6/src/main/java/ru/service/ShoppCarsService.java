package ru.service;

import ru.persistence.ShoppCars;
import ru.persistence.entities.CartEntry;
import ru.persistence.entities.Product;
import ru.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ShoppCarsService{

    private final EntityManager em;

    private final ProductRepository productRepository;

    public ShoppCarsService(EntityManager em, ProductRepository productRepository) {
        this.em = em;
        this.productRepository = productRepository;
    }

    @Lookup
    public ShoppCars getNewCart() {
        return null;
    }

    public List<CartEntry> findAllProductsById(String nomerId) {
        List<CartEntry> cartEntryList = em.createQuery("FROM CartEntry c WHERE c.nomer_order = ':nomerId'")
                .setParameter("nomerId", nomerId)
                .getResultList();
        return cartEntryList;
    }

    public void addProduct(ShoppCars cart, Product product, Integer quantity) {
        cart.addProduct(product, quantity);
    }

     public void addProduct(ShoppCars cart, Integer prodId, Integer quantity) {
        Product product = productRepository.findProduct(prodId);
        this.addProduct(cart, product, quantity);
    }

    public Float getSum(ShoppCars cart) {
        return cart.getSum();
    }

    public void printCart(ShoppCars cart) {
        float summa = 0.0f;
        for (Map.Entry<Product, Integer> entryMap : cart.getCartMap().entrySet()) {
            Product product = entryMap.getKey();
            float intermediate = entryMap.getValue() * product.getPrice();
            System.out.println(String.format(product.toString() + "| кол = %-5s | Сумма = %-10s", entryMap.getValue(), intermediate));
            summa += intermediate;
        }
        System.out.println("Сумма корзины: " + summa);
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
        Product product = productRepository.findProduct(prodId);
        return this.getProductQuantity(cart, product);
    }

    public List<Product> getCartList(ShoppCars cart) {
        List<Product> cartList = new ArrayList<>(cart.getCartMap().keySet());
        return cartList;
    }

}