package ru.persistence.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class CartEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @OneToMany
    @JoinColumn (name = "products_id")
    private Product product;

    @Column (name = "quantity")
    private Integer quantity;

    @Column (name = "nomer_order")
    private String nomer_order;

    @Column (name = "data_order")
    private Data data_order;

    public String toString() {
        return String.format("\nCartEntry {order_id = %s, product_id = %s, количество = %s, номер = %s, дата = %s}",
                id, product.getId(), quantity, nomer_order, data_order);
    }
}
