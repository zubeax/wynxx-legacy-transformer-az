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
@Schema(description = "Request DTO for an order line item")
public class OrderItemRequestDto {

    @NotNull(message = "Product ID is required")
    @Schema(description = "ID of the product to order", example = "1", required = true)
    private Long productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Schema(description = "Quantity to order", example = "2", required = true)
    private Integer quantity;

    @DecimalMin(value = "0.00", message = "Discount percentage must be non-negative")
    @DecimalMax(value = "100.00", message = "Discount percentage must not exceed 100%")
    @Schema(description = "Item-level discount percentage override", example = "5.00")
    private BigDecimal discountPercentage;

    @Schema(description = "Additional notes for this item", example = "Gift wrap requested")
    private String notes;
}
