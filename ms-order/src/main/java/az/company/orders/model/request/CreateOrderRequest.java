package az.company.orders.model.request;

import az.company.orders.model.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderRequest(
        @NotNull
        Long productId,
        @Positive
        Integer quantity,
        @NotNull
        PaymentType paymentType
) {
}
