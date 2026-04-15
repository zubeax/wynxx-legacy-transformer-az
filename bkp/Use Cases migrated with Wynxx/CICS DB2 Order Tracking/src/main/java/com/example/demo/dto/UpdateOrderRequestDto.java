package com.example.demo.dto;

import com.example.demo.enums.PaymentMethod;
import com.example.demo.enums.ShippingMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for updating an existing order")
public class UpdateOrderRequestDto {

    @Schema(description = "Payment method", example = "CREDIT_CARD")
    private PaymentMethod paymentMethod;

    @Schema(description = "Shipping method", example = "EXPRESS")
    private ShippingMethod shippingMethod;

    @DecimalMin(value = "0.00", message = "Shipping cost must be non-negative")
    @Schema(description = "Shipping cost", example = "19.99")
    private BigDecimal shippingCost;

    @DecimalMin(value = "0.00", message = "Discount amount must be non-negative")
    @Schema(description = "Order-level discount amount", example = "25.00")
    private BigDecimal discountAmount;

    @Size(max = 255, message = "Shipping address line 1 must not exceed 255 characters")
    @Schema(description = "Shipping address line 1", example = "456 Oak Avenue")
    private String shippingAddressLine1;

    @Size(max = 255, message = "Shipping address line 2 must not exceed 255 characters")
    @Schema(description = "Shipping address line 2", example = "Apt 2B")
    private String shippingAddressLine2;

    @Size(max = 100, message = "Shipping city must not exceed 100 characters")
    @Schema(description = "Shipping city", example = "Brooklyn")
    private String shippingCity;

    @Size(max = 100, message = "Shipping state must not exceed 100 characters")
    @Schema(description = "Shipping state/province", example = "NY")
    private String shippingState;

    @Size(max = 20, message = "Shipping postal code must not exceed 20 characters")
    @Schema(description = "Shipping postal/zip code", example = "11201")
    private String shippingPostalCode;

    @Size(max = 100, message = "Shipping country must not exceed 100 characters")
    @Schema(description = "Shipping country", example = "United States")
    private String shippingCountry;

    @Size(max = 255, message = "Billing address line 1 must not exceed 255 characters")
    @Schema(description = "Billing address line 1", example = "123 Main Street")
    private String billingAddressLine1;

    @Size(max = 255, message = "Billing address line 2 must not exceed 255 characters")
    @Schema(description = "Billing address line 2", example = "Suite 100")
    private String billingAddressLine2;

    @Size(max = 100, message = "Billing city must not exceed 100 characters")
    @Schema(description = "Billing city", example = "New York")
    private String billingCity;

    @Size(max = 100, message = "Billing state must not exceed 100 characters")
    @Schema(description = "Billing state/province", example = "NY")
    private String billingState;

    @Size(max = 20, message = "Billing postal code must not exceed 20 characters")
    @Schema(description = "Billing postal/zip code", example = "10001")
    private String billingPostalCode;

    @Size(max = 100, message = "Billing country must not exceed 100 characters")
    @Schema(description = "Billing country", example = "United States")
    private String billingCountry;

    @Size(max = 200, message = "Payment reference must not exceed 200 characters")
    @Schema(description = "Payment reference/transaction ID", example = "TXN-123456789")
    private String paymentReference;

    @Schema(description = "Customer notes for the order", example = "Please leave at the door")
    private String customerNotes;

    @Schema(description = "Internal notes for the order (staff only)")
    private String internalNotes;
}
