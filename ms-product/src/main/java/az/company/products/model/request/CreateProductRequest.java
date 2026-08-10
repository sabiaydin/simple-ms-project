package az.company.products.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public record CreateProductRequest(
    @NotBlank
    String name,
    String description,
    @Positive
    @NotNull
    BigDecimal price,
    @NotNull
    @PositiveOrZero
    Integer quantity
)
{}
