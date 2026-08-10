package az.company.orders.model.request;

import az.company.orders.model.enums.PaymentType;

import java.math.BigDecimal;

public record PaymentRequest(
        Long orderId,
        PaymentType paymentType,
        BigDecimal amount

) {
}
