package ru.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.persistence.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findProductsByPriceBetweenAndTitleLike(Float minPrice, Float maxPrice, String partTitle, Pageable varPageSort);
}
