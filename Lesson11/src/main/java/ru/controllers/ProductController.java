package ru.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.entities.Product;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    @GetMapping({"", "/"})
    public String getProductsPage() {
        return "Cписок товаров";
    }

    @GetMapping("/edit")
    public String editProductList() {
        return "Редактирование и создание новых товаров";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer productId) {
        return "Редактирование товара";
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createProduct(@RequestBody Product product) {
        return "Сохранить товар";
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateProduct(@RequestBody Product product) {
        return "Изменить товар";
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id) {
        return "Удалить товар";
    }

}
