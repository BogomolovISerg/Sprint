package ru.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.persistence.entities.Orders;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findOrdersByUserId(Integer userId);

    @Query(value = "SELECT o FROM Orders o WHERE o.id = :orderId")
    Orders findOrderByOrderId (Integer orderId);

}
