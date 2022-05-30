package ru.controllers.rest;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.controllers.ProductController;
import ru.persistence.dtos.ProductDto;
import ru.persistence.entities.Product;
import ru.persistence.mapper.ProductConverter;
import ru.service.ProductService;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(@RequestParam(defaultValue = "1") Integer pageIndex,
                                         @RequestParam(defaultValue = "0") Float minPrice,
                                         @RequestParam(defaultValue = "1000000") Float maxPrice,
                                         @RequestParam(defaultValue = "") String partTitle,
                                         @RequestParam(defaultValue = "5") Integer productsPerPage) {
        Page<Product> page = productService.findAllFilteredPaged(minPrice, maxPrice, partTitle, pageIndex, productsPerPage);
        if (page.getContent().size() > 0) {
            return ResponseEntity.ok(page.getContent());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        if (productService.findById(id).isPresent()) {
            Product product = productService.findById(id).get();
            return ResponseEntity.ok(new ProductConverter().fromProduct(product));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        if (productDto.getId() == null) {
            Product product = new ProductConverter().toProduct(productDto);
            return ResponseEntity.ok(productService.addUpdate(product));
        }
        return new ResponseEntity<>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto) {
        if (productDto.getId() != null) {
            Product product = new ProductConverter().toProduct(productDto);
            return ResponseEntity.ok(productService.addUpdate(product));
        }
        return new ResponseEntity<>(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Integer id) {
        if (productService.findById(id).isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
