package ru.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.persistence.entities.CartEntry;
import ru.persistence.entities.Orders;

import java.util.List;

public interface CartEntryRepository extends JpaRepository<Orders, Integer> {

}
