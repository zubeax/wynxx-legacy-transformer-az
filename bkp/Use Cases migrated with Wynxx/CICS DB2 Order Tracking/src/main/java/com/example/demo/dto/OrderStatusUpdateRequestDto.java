package com.example.demo.dto;

import com.example.demo.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for updating order status")
public class OrderStatusUpdateRequestDto {

    @NotNull(message = "New status is required")
    @Schema(description = "New order status", example = "CONFIRMED", required = true)
    private OrderStatus newStatus;

    @Size(max = 500, message = "Reason must not exceed 500 characters")
    @Schema(description = "Reason for status change", example = "Payment verified")
    private String reason;

    @Schema(description = "Additional notes about the status change")
    private String notes;

    @Size(max = 100, message = "Changed by must not exceed 100 characters")
    @Schema(description = "Name or ID of the person making the change", example = "admin@example.com")
    private String changedBy;
}
