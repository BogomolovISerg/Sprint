package ru.service;

import java.math.BigDecimal;

public class Product {

    private int id;
    private String name;
    private float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public int getId(){
        return id;
    }

    public float getPrice(){
        return price;
    }

    public String toString() {
        return String.format("Товар id = %-2s | название = %-15s | цена = %-8s", id, name, price);
    }
}
