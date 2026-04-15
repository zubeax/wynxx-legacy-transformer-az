package com.example.demo.dto;

import com.example.demo.enums.PaymentMethod;
import com.example.demo.enums.ShippingMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for creating a new order")
public class CreateOrderRequestDto {

    @NotNull(message = "Customer ID is required")
    @Schema(description = "ID of the customer placing the order", example = "1", required = true)
    private Long customerId;

    @NotNull(message = "At least one order item is required")
    @Size(min = 1, message = "Order must contain at least one item")
    @Valid
    @Schema(description = "List of items to order", required = true)
    private List<OrderItemRequestDto> items;

    @Schema(description = "Payment method", example = "CREDIT_CARD")
    private PaymentMethod paymentMethod;

    @Schema(description = "Shipping method", example = "STANDARD")
    private ShippingMethod shippingMethod;

    @Size(max = 255, message = "Shipping address line 1 must not exceed 255 characters")
    @Schema(description = "Shipping address line 1", example = "123 Main Street")
    private String shippingAddressLine1;

    @Size(max = 255, message = "Shipping address line 2 must not exceed 255 characters")
    @Schema(description = "Shipping address line 2", example = "Apt 4B")
    private String shippingAddressLine2;

    @Size(max = 100, message = "Shipping city must not exceed 100 characters")
    @Schema(description = "Shipping city", example = "New York")
    private String shippingCity;

    @Size(max = 100, message = "Shipping state must not exceed 100 characters")
    @Schema(description = "Shipping state", example = "NY")
    private String shippingState;

    @Size(max = 20, message = "Shipping postal code must not exceed 20 characters")
    @Schema(description = "Shipping postal code", example = "10001")
    private String shippingPostalCode;

    @Size(max = 100, message = "Shipping country must not exceed 100 characters")
    @Schema(description = "Shipping country", example = "United States")
    private String shippingCountry;

    @Size(max = 50, message = "Coupon code must not exceed 50 characters")
    @Schema(description = "Coupon code for discount", example = "SAVE10")
    private String couponCode;

    @DecimalMin(value = "0.0", message = "Discount amount must be non-negative")
    @Schema(description = "Manual discount amount", example = "5.00")
    private BigDecimal discountAmount;

    @Size(max = 3, min = 3, message = "Currency must be a 3-letter ISO code")
    @Schema(description = "Currency code (ISO 4217)", example = "USD")
    private String currency = "USD";

    @Schema(description = "Additional notes for the order")
    private String notes;
}
