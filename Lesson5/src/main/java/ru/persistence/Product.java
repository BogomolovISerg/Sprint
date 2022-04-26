package ru.persistence;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "FROM Product p"),
        @NamedQuery(name = "Product.findAllSortedByName", query = "FROM Product p ORDER BY p.name"),
        @NamedQuery(name = "Product.findById", query = "FROM Product p WHERE p.id = :id"),
        @NamedQuery(name = "Product.deleteById", query = "DELETE FROM Product p WHERE p.id = :id")
})

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column private String name;
    @Column private Float price;

    public Product() { }

    public Product(Integer id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return String.format("Товар id = %-2s | название = %-15s | цена = %-8s", id, name, price);
    }
}
