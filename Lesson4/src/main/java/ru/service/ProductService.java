package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.persistence.Product;
import ru.persistence.ProductRepository;

import java.util.List;
@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductList() {
        return productRepository.listAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findProduct(id);
    }

    public void addUpdate(Product product) {
        productRepository.addProduct(product);
    }

    public void deleteById(Integer id){
        productRepository.deleteProduct(id);
    }
}
