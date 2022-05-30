package ru;

import ru.persistence.*;
import ru.persistence.entities.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class Lesson6{

    private EntityManager em;
    private final ProductRepository productRepository;
    private final СlientsRepository userRepository;

    public Lesson6(EntityManager em, СlientsRepository userRepository, ProductRepository productRepository) {
        this.em = em;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @PostConstruct
    private void homework() {
        Сlients user;
        Product product;
        int numSelected;
        String str = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(!str.equals("exit")) {
            System.out.println("\n----------------------------------------");
            System.out.println("1. Список товаров по ID покупателя");
            System.out.println("2. Список покупателей по ID товара");
            System.out.println("3. Завершить");
            System.out.print("Выберите действие: ");
            try {
                str = in.readLine();
                numSelected = Integer.parseInt(str);
                if (numSelected < 1 || numSelected > 3) throw new NumberFormatException();
            } catch (Exception e) {
                System.out.println("Неверное значение.");
                continue;
            }

            switch (numSelected) {
                case 1:

                    System.out.print("Введите id покупателя: ");
                    try {
                        str = in.readLine();
                        Long userId = Long.parseLong(str);
                        user = userRepository.findById(userId);
                    } catch (Exception e) {
                        System.out.println("ID покупателя неверен");
                        continue;
                    }

                    if (user == null) {
                        System.out.println("Покупателя с указанным ID не найден!");
                        continue;
                    }
                    detailsByUser(user);
                    break;

                case 2:
                    System.out.print("Введите id товара: ");
                    try {
                        str = in.readLine();
                        Integer prodId = Integer.parseInt(str);
                        product = productRepository.findProduct(prodId);
                    } catch (Exception e) {
                        System.out.println("ID товара неверен!");
                        continue;
                    }

                    if (product == null) {
                        System.out.println("Товара с таким ID нет найден");
                        continue;
                    }

                    detailsByProduct(product);
                    break;

                case 3:
                    str = "exit";
                    break;
            }
        }
    }

    private void detailsByUser(Сlients client) {
        System.out.println("Пользователь: " + client);
        System.out.println("Количество заказов: " + client.getOrders().size());
        System.out.println("Список заказов: \n" + client.getOrders());
        System.out.println("Список заказанных товаров с детализацией на момент покупки:");
        for (Orders order : client.getOrders()) {
            for (CartEntry cartEntry : order.getCartEntries()) {
                System.out.println("Товар: id = " + cartEntry.getProduct().getId() +
                        ", name = " + cartEntry.getProduct().getName() +
                        ", количество: " + cartEntry.getQuantity() +
                        ", цена на момент покупки: " + cartEntry.getAcquirePrice());
            }
        }
    }

    private void detailsByProduct(Product product) {
        System.out.println("Товар: " + product);
        System.out.println("Количество заказов: " + product.getOrders().size());
        Set<Сlients> userList = new HashSet<>();
        for (Orders order : product.getOrders()) {
            userList.add(order.getUser());
        }
        System.out.println("Количество покупателей: " + userList.size());
        System.out.println("Список покупателей: \n" + userList);
    }
}
