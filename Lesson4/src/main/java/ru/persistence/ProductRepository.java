package ru.persistence;

import org.springframework.stereotype.Component;
import ru.persistence.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProductRepository {
    private Map<Integer, Product> productList  = new ConcurrentHashMap<>();

    {
        productList.put(1, new Product(1, "Кофе", 200.0f));
        productList.put(2, new Product(2, "Чай", 100.0f));
        productList.put(3, new Product(3, "Какао", 150.0f));
        productList.put(4, new Product(4, "Лимонад", 70.0f));
        productList.put(5, new Product(5, "Сидор", 125.0f));
    }

    public Product findProduct(Integer id){
        return productList.get(id);
    }
    public void deleteProduct(Integer id){
        productList.remove(id);
    }
    public void addProduct(Product product) {
        productList.put(product.getId(), product);
    }
    public List<Product> listAll(){
        return new ArrayList<>(productList.values());
    }
}
