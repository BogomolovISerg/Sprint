package ru.persistence;

public class Product {

    private Integer id;
    private String name;
    private Float price;

    public Product(Integer id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId(){
        return id;
    }
    public String getName() { return name; }
    public float getPrice(){
        return price;
    }

    public String toString() {
        return String.format("Товар id = %-2s | название = %-15s | цена = %-8s", id, name, price);
    }
}
