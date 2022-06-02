package ru.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.persistence.entities.Product;
import ru.persistence.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> findAllFilteredPaged(Float minPrice, Float maxPrice, String partTitle, Integer pageIndex, Integer productsPerPage) {
        Pageable pageRequest = PageRequest.of(pageIndex - 1, productsPerPage);
        Page<Product> productPage = productRepository.findProductsByPriceBetweenAndTitleLike(minPrice, maxPrice, "%"+partTitle+"%", pageRequest);
        return productPage;
    }

    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

}
