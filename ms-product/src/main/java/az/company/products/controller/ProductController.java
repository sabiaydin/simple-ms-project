package az.company.products.controller;

import az.company.products.model.request.CreateProductRequest;
import az.company.products.model.response.ProductResponse;
import az.company.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody CreateProductRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createProduct(request));
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {

        log.info("Get product request. id={}", id);

        ProductResponse response = service.getProductById(id);

        log.info("Product found. id={}", id);

        return response;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable Long id,
            @RequestParam Integer reduceQuantity) {

        service.reduceQuantity(id, reduceQuantity);

        return ResponseEntity.ok().build();
    }
}
