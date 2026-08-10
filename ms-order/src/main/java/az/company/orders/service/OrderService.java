package az.company.orders.service;

import az.company.orders.model.request.CreateOrderRequest;
import az.company.orders.model.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse getOrderById(Long id);
}
