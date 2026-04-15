package com.example.demo.dto;

import com.example.demo.enums.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for order status history entry")
public class OrderStatusHistoryResponseDto {

    @Schema(description = "Unique identifier of the history entry", example = "1")
    private Long id;

    @Schema(description = "Order ID", example = "1")
    private Long orderId;

    @Schema(description = "Previous order status", example = "PENDING")
    private OrderStatus previousStatus;

    @Schema(description = "Previous status display name", example = "Pending")
    private String previousStatusDisplayName;

    @Schema(description = "New order status", example = "CONFIRMED")
    private OrderStatus newStatus;

    @Schema(description = "New status display name", example = "Confirmed")
    private String newStatusDisplayName;

    @Schema(description = "Who made the change", example = "admin@example.com")
    private String changedBy;

    @Schema(description = "Reason for the status change", example = "Payment verified")
    private String reason;

    @Schema(description = "Additional notes about the change")
    private String notes;

    @Schema(description = "Timestamp when the status was changed")
    private LocalDateTime createdAt;
}
