package az.company.products.mapper;

import az.company.products.dao.entity.ProductEntity;
import az.company.products.model.request.CreateProductRequest;
import az.company.products.model.response.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity toEntity(CreateProductRequest request);
    ProductResponse toResponse(ProductEntity product);
}
