package az.company.orders.model.response;

import az.company.orders.model.enums.PaymentType;

import java.math.BigDecimal;

public record PaymentResponse(
        Long id,
        Long orderId,
        PaymentType paymentType,
        BigDecimal amount,
        String referenceNumber

) {
}
