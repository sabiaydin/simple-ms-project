package az.company.orders.service.impl;

import az.company.orders.client.PaymentClient;
import az.company.orders.client.ProductClient;
import az.company.orders.dao.entity.OrderEntity;
import az.company.orders.dao.repository.OrderRepository;
import az.company.orders.exception.OrderNotFoundException;
import az.company.orders.exception.PaymentServiceException;
import az.company.orders.mapper.OrderMapper;
import az.company.orders.model.enums.OrderStatus;
import az.company.orders.model.enums.PaymentType;
import az.company.orders.model.request.CreateOrderRequest;
import az.company.orders.model.request.PaymentRequest;
import az.company.orders.model.response.OrderResponse;
import az.company.orders.model.response.PaymentResponse;
import az.company.orders.model.response.ProductResponse;
import az.company.orders.service.OrderClientService;
import az.company.orders.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal=true)
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    ProductClient  productClient;
    PaymentClient paymentClient;
    OrderClientService  orderClientService;
    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {

        ProductResponse product =
                orderClientService.getProduct(request.productId());

        BigDecimal amount = product.price()
                .multiply(BigDecimal.valueOf(request.quantity()));

        orderClientService.reduceQuantity(
                request.productId(),
                request.quantity());

        OrderEntity order = orderMapper.toEntity(request);

        order.setAmount(amount);
        order.setStatus(OrderStatus.CREATED);

        order = orderRepository.save(order);

        PaymentResponse paymentResponse;

        try {

            paymentResponse = orderClientService.pay(
                    new PaymentRequest(
                            order.getId(),
                            request.paymentType(),
                            amount
                    )
            );

            order.setStatus(OrderStatus.PAID);

        } catch (PaymentServiceException ex) {

            order.setStatus(OrderStatus.FAILED);

            orderRepository.save(order);

            throw ex;
        }

        orderRepository.save(order);

        return buildOrderResponse(order, paymentResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {

        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found"));

        PaymentResponse paymentResponse = null;

        if (order.getStatus() == OrderStatus.PAID) {
            paymentResponse =
                    orderClientService.getPayment(order.getId());
        }

        return buildOrderResponse(order, paymentResponse);
    }
    private OrderResponse buildOrderResponse(OrderEntity order,
                                             PaymentResponse paymentResponse) {

        return new OrderResponse(
                order.getId(),
                order.getProductId(),
                order.getQuantity(),
                order.getAmount(),
                order.getStatus(),
                paymentResponse
        );
    }
    }
