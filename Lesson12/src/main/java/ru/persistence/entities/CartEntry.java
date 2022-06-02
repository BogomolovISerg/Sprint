package ru.persistence.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_products")
public class CartEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @Column (name = "quantity")
    private Integer quantity;

    @Column (name = "acquire_price")
    private Float acquirePrice;

    @Override
    public String toString() {
        return String.format("\nCartEntry {id = %s, order_id = %s, product_id = %s, quantity = %s, acquire_price = %s}",
                id, order.getId(), product.getId(), quantity, acquirePrice);
    }
}
