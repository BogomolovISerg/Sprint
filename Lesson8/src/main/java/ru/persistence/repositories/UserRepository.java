package ru.persistence.repositories;

import ru.persistence.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Clients, Integer> {
}
