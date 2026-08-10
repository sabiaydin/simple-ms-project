package az.company.products.service.impl;

import az.company.products.dao.entity.ProductEntity;
import az.company.products.dao.repository.ProductRepository;
import az.company.products.exception.InsufficientProductException;
import az.company.products.exception.ProductNotFoundException;
import az.company.products.mapper.ProductMapper;
import az.company.products.model.request.CreateProductRequest;
import az.company.products.model.response.ProductResponse;
import az.company.products.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        ProductEntity product = productMapper.toEntity(request);
        productRepository.save(product);
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
        return productMapper.toResponse(product);
    }

    @Override
    public void reduceQuantity(Long id, Integer quantity) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
        if(product.getQuantity() < quantity) {
            throw new InsufficientProductException("Insufficient product quantity");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
}
