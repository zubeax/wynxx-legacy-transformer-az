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
    @Size(min = 5, max = 500, message = "Cancellation reason must be between 5 and 500 characters")
    @Schema(description = "Reason for cancelling the order", example = "Customer requested cancellation", required = true)
    private String reason;

    @Schema(description = "User or system requesting the cancellation", example = "customer@example.com")
    private String requestedBy;
}
