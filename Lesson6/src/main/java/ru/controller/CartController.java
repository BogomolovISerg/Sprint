package ru.controller;

import ru.persistence.ShoppCars;
import ru.service.ShoppCarsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ShoppCarsService cartService;
    private ShoppCars cart;

    @Autowired
    private void cartController(ShoppCarsService cartService) {
        this.cartService = cartService;
        cart = cartService.getNewCart();
    }

    @ModelAttribute("activePage")
    String activePage() {
        return "cart";
    }

    @ModelAttribute("cartItemsBadge")
    Integer cartItemsBadge() {
        return cartService.getItemsAmount(cart);
    }

    @GetMapping
    public String cartList (Model model) {
        model.addAttribute("cartList", cartService.getCartList(cart));
        model.addAttribute("cartService", cartService);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/add/{product_id}")
    public void addToCart (
            @PathVariable(name = "product_id") Integer id,
            @RequestParam(required = false, name = "q") Integer quantity,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
            cartService.addProduct(cart, id, quantity);
            response.sendRedirect(request.getHeader("referer"));
    }
}
