package ru.service;

import ru.persistence.entities.Product;
import ru.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Page<Product> getProductsFiltered(Float minPrice, Float maxPrice, String partName, Integer pageNum, Integer productsPerPage) {
        Pageable pageRequest = PageRequest.of(pageNum - 1, productsPerPage);
        Page<Product> productPage = productRepository.findProductsByPriceBetweenAndNameLike(minPrice, maxPrice, "%"+partName+"%", pageRequest);
        return productPage;
    }

      public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    public void addUpdate(Product product) {
        productRepository.save(product);
    }

        public void deleteById(Integer id) {
            productRepository.deleteById(id);
    }

}
