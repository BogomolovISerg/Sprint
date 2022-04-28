package ru.persistence.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "FROM products p"),
        @NamedQuery(name = "Product.findAllSortedByName", query = "FROM products p ORDER BY p.name"),
        @NamedQuery(name = "Product.findById", query = "FROM products p WHERE p.id = :id"),
        @NamedQuery(name = "Product.deleteById", query = "DELETE FROM products p WHERE p.id = :id")
})

public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @OneToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Orders> orders;

    @OneToMany(mappedBy = "products")
    private List<CartEntry> cartEntries;

    public Product() { }

    public Product(Integer id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product id = %s, name = %s, price = %s", id, name, price);
    }
}
