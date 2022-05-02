package ru.persistence.repositories;

import ru.persistence.entities.Orders;
import ru.persistence.entities.Сlients;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public interface СlientRepository extends JpaRepository<Сlients, Integer> {
}
