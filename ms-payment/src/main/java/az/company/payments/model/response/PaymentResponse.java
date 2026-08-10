package az.company.payments.model.response;

import az.company.payments.model.enums.PaymentType;

import java.math.BigDecimal;

public record PaymentResponse(
        Long id,
        Long orderId,
        PaymentType paymentType,
        BigDecimal amount,
        String referenceNumber
) {
}
