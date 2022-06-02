package ru.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.persistence.Cart;
import ru.services.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private CartService cartService;
    private Cart cart;

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
        model.addAttribute("cartList", cartService.getCartListSorted(cart));
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
            cartService.addProductById(cart, id, quantity);
            response.sendRedirect(request.getHeader("referer"));
    }
}
