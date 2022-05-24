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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "clients_id")
    private Clients user;

    @OneToMany
    @JoinColumn (name = "products_id")
    private List<Product> products;

    @OneToMany(mappedBy = "id")
    private List<CartEntry> cartEntries;

    public String toString() {
        return String.format("Счет {id = %s, клиент = %s}", id, user.getId());
    }
}
