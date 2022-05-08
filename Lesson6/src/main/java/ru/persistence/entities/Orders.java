package ru.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {

    public Orders(Сlients user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @OneToMany
    @JoinColumn (name = "clients_id")
    private Сlients user;

    @OneToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @OneToMany(mappedBy = "order")
    private List<CartEntry> cartEntries;

    @Override
    public String toString() {
        return String.format("Order {id = %s, user_id = %s}", id, user.getId());
    }
}
