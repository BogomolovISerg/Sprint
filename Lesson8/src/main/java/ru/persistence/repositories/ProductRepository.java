package ru.persistence.repositories;

import ru.persistence.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductByName(String name);
    List<Product> findProductByPriceBetween(Float minPrice, Float maxPrice);
    List<Product> findProductByPriceLessThan(Float maxPrice);
    List<Product> findProductByPriceGreaterThan(Float minPrice);

    Page<Product> findProductsByPriceBetweenAndNameLike(Float minPrice, Float maxPrice, String partName, Pageable varPageSort);
}
