package ru.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.persistence.entities.Order;

public interface CartEntryRepository extends JpaRepository<Order, Integer> {
}
