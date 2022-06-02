package ru.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.persistence.Cart;
import ru.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartRestController {

    private CartService cartService;
    private Cart cart;

}
