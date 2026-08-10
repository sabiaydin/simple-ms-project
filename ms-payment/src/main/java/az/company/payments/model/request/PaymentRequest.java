package az.company.payments.model.request;

import az.company.payments.model.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequest(
        @NotNull
        Long orderId,
        @NotNull
        PaymentType paymentType,
        @Positive
        BigDecimal amount
) {
}
