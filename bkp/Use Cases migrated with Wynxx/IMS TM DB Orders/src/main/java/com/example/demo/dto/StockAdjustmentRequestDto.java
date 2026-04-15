package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for adjusting product stock quantity")
public class StockAdjustmentRequestDto {

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Schema(description = "Quantity to add to stock", example = "50", required = true)
    private Integer quantity;

    @NotBlank(message = "Reason is required")
    @Schema(description = "Reason for stock adjustment", example = "Received new shipment", required = true)
    private String reason;
}
