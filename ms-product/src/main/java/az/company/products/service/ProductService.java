package az.company.products.service;

import az.company.products.model.request.CreateProductRequest;
import az.company.products.model.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest request);

    ProductResponse getProductById(Long id);

    void reduceQuantity(Long id, Integer quantity);
}
