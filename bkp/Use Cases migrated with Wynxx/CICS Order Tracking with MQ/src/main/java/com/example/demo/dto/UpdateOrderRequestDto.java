package com.example.demo.dto;

import com.example.demo.enums.PaymentMethod;
import com.example.demo.enums.ShippingMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for updating an existing order")
public class UpdateOrderRequestDto {

    @Schema(description = "Payment method", example = "CREDIT_CARD")
    private PaymentMethod paymentMethod;

    @Schema(description = "Shipping method", example = "EXPRESS")
    private ShippingMethod shippingMethod;

    @Size(max = 255, message = "Shipping address line 1 must not exceed 255 characters")
    @Schema(description = "Shipping address line 1", example = "456 Oak Avenue")
    private String shippingAddressLine1;

    @Size(max = 255, message = "Shipping address line 2 must not exceed 255 characters")
    @Schema(description = "Shipping address line 2", example = "Suite 200")
    private String shippingAddressLine2;

    @Size(max = 100, message = "Shipping city must not exceed 100 characters")
    @Schema(description = "Shipping city", example = "Los Angeles")
    private String shippingCity;

    @Size(max = 100, message = "Shipping state must not exceed 100 characters")
    @Schema(description = "Shipping state", example = "CA")
    private String shippingState;

    @Size(max = 20, message = "Shipping postal code must not exceed 20 characters")
    @Schema(description = "Shipping postal code", example = "90001")
    private String shippingPostalCode;

    @Size(max = 100, message = "Shipping country must not exceed 100 characters")
    @Schema(description = "Shipping country", example = "United States")
    private String shippingCountry;

    @Schema(description = "Additional notes for the order")
    private String notes;
}
