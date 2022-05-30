package ru.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.persistence.entities.Clients;

public interface UserRepository extends JpaRepository<Clients, Integer> {
}
