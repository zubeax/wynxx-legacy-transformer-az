package com.example.demo.dto;

import com.example.demo.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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

    @Schema(description = "Comment about the status change", example = "Order confirmed by warehouse")
    private String comment;

    @Schema(description = "User or system making the change", example = "admin@example.com")
    private String changedBy;
}
