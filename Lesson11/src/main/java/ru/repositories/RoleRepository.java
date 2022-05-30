package ru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
