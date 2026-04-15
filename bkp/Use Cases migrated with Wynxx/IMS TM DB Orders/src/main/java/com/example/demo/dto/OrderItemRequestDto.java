package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for an order line item")
public class OrderItemRequestDto {

    @NotNull(message = "Product ID is required")
    @Schema(description = "ID of the product to order", example = "1", required = true)
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 9999, message = "Quantity must not exceed 9999")
    @Schema(description = "Quantity to order", example = "2", required = true)
    private Integer quantity;

    @DecimalMin(value = "0.0", message = "Discount percent must be non-negative")
    @DecimalMax(value = "100.0", message = "Discount percent must not exceed 100%")
    @Schema(description = "Discount percentage for this item", example = "10.0")
    private BigDecimal discountPercent = BigDecimal.ZERO;
}
