package ru.controllers;

import ru.utils.ProductFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.persistence.entities.Product;
import ru.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/products")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @ModelAttribute("activePage")
    String activePage() {
        return "products";
    }

    @GetMapping({"", "/{pageIndex}"})
    public String showAll(Model model,
                          @PathVariable(required = false) Integer pageIndex,
                          @RequestParam(defaultValue = "0") Float minPrice,
                          @RequestParam(defaultValue = "1000000") Float maxPrice,
                          @RequestParam(defaultValue = "") String partTitle,
                          @RequestParam(defaultValue = "5") Integer productsPerPage) {


        model.addAttribute("productFilter", new ProductFilter(minPrice, maxPrice, partTitle));

        if (minPrice < 0f) minPrice = 0f;
        if (maxPrice > 1000000f) maxPrice = 1000000f;
        if (pageIndex == null || pageIndex < 1) pageIndex = 1;
        Page<Product> products = productService.findAllFilteredPaged(minPrice, maxPrice, partTitle, pageIndex, productsPerPage);
        model.addAttribute("productList", products.getContent());
        final int currentPage = products.getPageable().getPageNumber();
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("previousPage", products.getPageable().hasPrevious() ? currentPage : null);
        model.addAttribute("nextPage", products.getTotalPages() > currentPage + 1 ? currentPage + 2 : null);
        model.addAttribute("productsPerPage", productsPerPage);

        return "products";
    }

    @GetMapping("edit")
    public String editProduct(@RequestParam(required = false) Integer id,
                              @RequestParam(required = false) Boolean view,
                              HttpServletRequest request,
                              Model model) {
        Product product;
        if (id == null) {
            product = new Product();
        } else {
            product = productService.findById(id).get();
        }
        model.addAttribute("product", product);
        model.addAttribute("disabled", view);
        model.addAttribute("referer", request.getHeader("referer"));
        return "product_form";
    }

    @PostMapping("/edit/save")
    public String mergeProduct(@ModelAttribute Product product) {
        productService.addUpdate(product);
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public void deleteProduct(@RequestParam Integer id, Model model,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        logger.debug("Product deleted: " + productService.findById(id).toString());
        productService.deleteById(id);
        model.addAttribute("productList", productService.findAll());
        response.sendRedirect(request.getHeader("referer"));
    }

    @PostMapping("/filter")
    public String setFilter(@ModelAttribute("productFilter") ProductFilter productFilter, Model model) {

        String resp = "redirect:/products/1";
        resp = resp + "?minPrice=";
        if (productFilter.getMinPrice() != null) resp = resp + productFilter.getMinPrice();
        resp = resp + "&maxPrice=";
        if (productFilter.getMaxPrice() != null) resp = resp + productFilter.getMaxPrice();
        resp = resp + "&partTitle=" + productFilter.getPartTitle();

        return resp;
    }
}
