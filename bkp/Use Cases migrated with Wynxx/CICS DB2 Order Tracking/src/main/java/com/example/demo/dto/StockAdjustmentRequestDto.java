package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for adjusting product stock quantity")
public class StockAdjustmentRequestDto {

    @NotNull(message = "Quantity is required")
    @Schema(description = "Quantity to add (positive) or remove (negative)", example = "50", required = true)
    private Integer quantity;

    @Size(max = 500, message = "Reason must not exceed 500 characters")
    @Schema(description = "Reason for stock adjustment", example = "Received new shipment from supplier")
    private String reason;
}
