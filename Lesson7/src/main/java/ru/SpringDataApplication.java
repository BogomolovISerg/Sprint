package ru;

import ru.persistence.entities.Product;
import ru.persistence.repositories.ProductRepository;
import ru.persistence.repositories.СlientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.NoResultException;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final СlientRepository userRepository;
    private final ProductRepository productRepository;

    public SpringDataApplication(СlientRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("\nПродукт: " + productRepository.findById(1).orElseThrow(() ->
                new NoResultException("Товар с указанным id не существует!")));

        System.out.println("\nСписок всех продуктов: ");
        productRepository.findAll().forEach(System.out::println);

        int prodPerPage = 5;
        PageRequest pageRequest = PageRequest.of(0, prodPerPage);
        Page<Product> products = productRepository.findAll(pageRequest);

        System.out.println("\n");
        while (true) {
            System.out.println(products);
            products.getContent().forEach(System.out::println);
            if (products.hasNext()) {
                products = productRepository.findAll(products.nextPageable());
            } else {
                break;
            }
        }

        System.out.println("\n");

        Product product = null;

        System.out.println("Обновление товара id = 2");
        product = productRepository.findById(2).orElseThrow(() ->
                new NoResultException("Товар с указанным id (2) не существует!"));
        product.setName("Чай листовой");
        product.setPrice(250f);
        productRepository.save(product);
        System.out.println("\nТовар: " + productRepository.findById(2).orElseThrow(() ->
                new NoResultException("Товар с указанным id (2) не существует!")));

        System.out.println("\n");

        String prodName = "Печенье";
        System.out.println("Добавлен новый товар " + prodName);
        product = new Product(null, prodName, 100f);
        productRepository.save(product);
        product = productRepository.findProductByName(prodName).get(0);
        System.out.println("\nТовар: " + product);

        System.out.println("\n");

        Integer prodId = product.getId();
        System.out.println("Удаление товара id = " + prodId);
        productRepository.deleteById(prodId);
        System.out.println("\nТовар: " + productRepository.findById(prodId).orElse(null));

        System.out.println("\n");

        System.out.println("\nПродукты с ценой до 100: ");
        productRepository.findProductByPriceLessThan(100f).forEach(System.out::println);

        System.out.println("\n");

        System.out.println("\nПродукты с ценой более 100: ");
        productRepository.findProductByPriceGreaterThan(100f).forEach(System.out::println);

        System.out.println("\nПродукты в диапазоне цены от 100 до 200: ");
        productRepository.findProductByPriceBetween(100f, 200f).forEach(System.out::println);

    }
}
