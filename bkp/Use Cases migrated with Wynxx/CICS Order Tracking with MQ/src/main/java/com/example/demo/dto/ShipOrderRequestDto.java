package com.example.demo.dto;

import com.example.demo.enums.ShippingMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for shipping an order")
public class ShipOrderRequestDto {

    @NotBlank(message = "Tracking number is required")
    @Schema(description = "Shipment tracking number", example = "1Z999AA10123456784", required = true)
    private String trackingNumber;

    @Schema(description = "Shipping method used", example = "STANDARD")
    private ShippingMethod shippingMethod;

    @Schema(description = "Estimated delivery date")
    private LocalDateTime estimatedDeliveryDate;

    @Schema(description = "User or system marking the order as shipped", example = "warehouse@example.com")
    private String shippedBy;
}
