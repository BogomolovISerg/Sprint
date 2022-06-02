package ru.persistence.repositories;

import ru.persistence.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductByPriceBetween(Float minPrice, Float maxPrice);

    List<Product> findProductByPriceLessThan(Float maxPrice);

    List<Product> findProductByPriceGreaterThan(Float minPrice);

    List<Product> findProductByName(String name);
}
