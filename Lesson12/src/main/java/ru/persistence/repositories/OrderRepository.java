package ru.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.persistence.entities.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrdersByUserId(Long userId);

    @Query(value = "SELECT o FROM Order o WHERE o.id = :orderId")
    Order findOrderByOrderId (Integer orderId);

}
