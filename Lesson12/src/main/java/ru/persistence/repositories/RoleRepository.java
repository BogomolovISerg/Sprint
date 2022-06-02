package ru.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.persistence.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
