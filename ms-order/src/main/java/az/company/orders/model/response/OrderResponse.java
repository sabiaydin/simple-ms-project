package az.company.orders.model.response;

import az.company.orders.model.enums.OrderStatus;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        Long productId,
        Integer quantity,
        BigDecimal amount,
        OrderStatus status,
        PaymentResponse paymentResponse
) {
}
