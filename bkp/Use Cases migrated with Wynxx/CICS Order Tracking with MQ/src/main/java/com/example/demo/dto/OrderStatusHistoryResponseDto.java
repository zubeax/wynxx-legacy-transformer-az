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

    @Schema(description = "ID of the order", example = "1")
    private Long orderId;

    @Schema(description = "Previous order status", example = "PENDING")
    private OrderStatus previousStatus;

    @Schema(description = "Previous status display name", example = "Pending")
    private String previousStatusDisplayName;

    @Schema(description = "New order status", example = "CONFIRMED")
    private OrderStatus newStatus;

    @Schema(description = "New status display name", example = "Confirmed")
    private String newStatusDisplayName;

    @Schema(description = "User or system that made the change", example = "admin@example.com")
    private String changedBy;

    @Schema(description = "Comment about the status change", example = "Order confirmed by warehouse")
    private String comment;

    @Schema(description = "Timestamp when the status change occurred")
    private LocalDateTime createdAt;
}
