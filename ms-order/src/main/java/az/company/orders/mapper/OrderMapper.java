package az.company.orders.mapper;

import az.company.orders.dao.entity.OrderEntity;
import az.company.orders.model.request.CreateOrderRequest;
import az.company.orders.model.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toEntity(CreateOrderRequest request);
}
