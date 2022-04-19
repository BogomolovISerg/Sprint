package ru;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.config.AppConfig;
import ru.service.ShoppCars;
import ru.service.Product;
import ru.service.ShoppCarsService;
import ru.service.ProductService;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class StartApp {

    private ProductService productService;
    private ShoppCarsService cartService;

    public StartApp(ProductService productService, ShoppCarsService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @PostConstruct
    private void shoppcartWorks() throws IOException {
        ShoppCars cart = cartService.getNewCart();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        listCommand();

        while (true) {
            System.out.print("Команда: ");
            String str = in.readLine();

            if (!str.isEmpty()) {
                String[] p = str.split("\\s");
                String command = p[0];

                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Завершение работы.");
                    break;
                } else if (command.equalsIgnoreCase("help")) {
                    listCommand();
                } else if (command.equalsIgnoreCase("listProduct")) {
                    List<Product> listProduct = productService.getProductList();
                    for (Product product:
                            listProduct) {
                        System.out.println(product.toString());
                    }
                } else if (command.equalsIgnoreCase("new")) {
                    cart = cartService.getNewCart();
                    System.out.println("Создана новая корзина");
                }else if (command.startsWith("add")) {
                    if(p.length == 3 ) {
                        Product product = productService.getProductById(Integer.parseInt(p[1]));
                        if (product != null) {
                            cartService.addProduct(cart, product, Integer.parseInt(p[2]));
                            System.out.println("Товар добавлен в корзину");
                        }else
                            System.out.println("Товар не найден");
                    }else
                        System.out.println("Некорректная команда");
                }
                else if (command.startsWith("del")) {
                    if(p.length == 3 ) {
                        Product product = productService.getProductById(Integer.parseInt(p[1]));
                        if (product != null) {
                            cartService.delProduct(cart, product, Integer.parseInt(p[2]));
                            System.out.println("Товар удален из корзины");
                        }else
                            System.out.println("Товар не найден");
                    }else
                        System.out.println("Некорректная команда");
                }else if (command.equalsIgnoreCase("listCars")) {
                    cartService.printCart(cart);
                }
                else
                    System.out.println("Некорректная команда");
            }else
                    System.out.println("Некорректная команда");
            }
        }

    private static void listCommand() {
        System.out.println("Команды:");
        System.out.println("Cписок продуктов: listProduct");
        System.out.println("Добавить продукт: add id продукта количество");
        System.out.println("Удалить продукт: del id продукта количество");
        System.out.println("Cодержимое корзины: listCars");
        System.out.println("Удалить, создать новую корзину: new");
        System.out.println("Список команд: help");
        System.out.println("Завершить: exit");
        System.out.println();
    }
}
