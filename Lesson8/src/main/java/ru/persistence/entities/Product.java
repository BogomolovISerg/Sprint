package ru.persistence.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "FROM Product p"),
        @NamedQuery(name = "Product.findAllSortedByName", query = "FROM Product p ORDER BY p.name ASC"),
        @NamedQuery(name = "Product.findById", query = "FROM Product p WHERE p.id = :id"),
        @NamedQuery(name = "Product.deleteById", query = "DELETE FROM Product p WHERE p.id = :id")
})

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @ManyToMany
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Orders> orders;

    @OneToMany(mappedBy = "product")
    private List<CartEntry> cartEntries;

    public Product() { }

    public Product(Integer id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Товар id = %s, наименование = %s, цена = %s", id, name, price);
    }
}
