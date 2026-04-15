package com.example.demo.dto;

import com.example.demo.enums.ShippingMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for shipping an order")
public class ShipOrderRequestDto {

    @Size(max = 100, message = "Tracking number must not exceed 100 characters")
    @Schema(description = "Shipment tracking number", example = "1Z999AA10123456784")
    private String trackingNumber;

    @Size(max = 100, message = "Carrier must not exceed 100 characters")
    @Schema(description = "Shipping carrier name", example = "UPS")
    private String carrier;

    @Schema(description = "Shipping method used", example = "STANDARD")
    private ShippingMethod shippingMethod;

    @Schema(description = "Estimated delivery date")
    private LocalDateTime estimatedDeliveryDate;

    @Schema(description = "Additional notes about the shipment")
    private String notes;
}
