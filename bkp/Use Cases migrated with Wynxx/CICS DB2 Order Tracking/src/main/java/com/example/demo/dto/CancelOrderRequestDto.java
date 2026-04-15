package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for cancelling an order")
public class CancelOrderRequestDto {

    @NotBlank(message = "Cancellation reason is required")
    @Size(max = 500, message = "Cancellation reason must not exceed 500 characters")
    @Schema(description = "Reason for cancelling the order", example = "Customer requested cancellation", required = true)
    private String reason;

    @Size(max = 100, message = "Cancelled by must not exceed 100 characters")
    @Schema(description = "Name or ID of the person cancelling the order", example = "customer@example.com")
    private String cancelledBy;

    @Schema(description = "Whether to restore product stock upon cancellation", example = "true")
    private Boolean restoreStock = true;
}
