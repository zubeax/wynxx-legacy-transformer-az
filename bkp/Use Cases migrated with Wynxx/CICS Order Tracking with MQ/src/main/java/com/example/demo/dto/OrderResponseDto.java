package com.example.demo.dto;

import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.PaymentMethod;
import com.example.demo.enums.PaymentStatus;
import com.example.demo.enums.ShippingMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for order data")
public class OrderResponseDto {

    @Schema(description = "Unique identifier of the order", example = "1")
    private Long id;

    @Schema(description = "Human-readable order number", example = "ORD-20240101-00001")
    private String orderNumber;

    @Schema(description = "ID of the customer who placed the order", example = "1")
    private Long customerId;

    @Schema(description = "Full name of the customer", example = "John Doe")
    private String customerName;

    @Schema(description = "Email of the customer", example = "john.doe@example.com")
    private String customerEmail;

    @Schema(description = "Order status", example = "PENDING")
    private OrderStatus status;

    @Schema(description = "Order status display name", example = "Pending")
    private String statusDisplayName;

    @Schema(description = "Payment status", example = "PENDING")
    private PaymentStatus paymentStatus;

    @Schema(description = "Payment status display name", example = "Pending")
    private String paymentStatusDisplayName;

    @Schema(description = "Payment method", example = "CREDIT_CARD")
    private PaymentMethod paymentMethod;

    @Schema(description = "Payment method display name", example = "Credit Card")
    private String paymentMethodDisplayName;

    @Schema(description = "Shipping method", example = "STANDARD")
    private ShippingMethod shippingMethod;

    @Schema(description = "Shipping method display name", example = "Standard Shipping")
    private String shippingMethodDisplayName;

    @Schema(description = "Order subtotal before tax and shipping", example = "199.98")
    private BigDecimal subtotal;

    @Schema(description = "Discount amount applied", example = "10.00")
    private BigDecimal discountAmount;

    @Schema(description = "Tax amount", example = "16.99")
    private BigDecimal taxAmount;

    @Schema(description = "Shipping cost", example = "9.99")
    private BigDecimal shippingCost;

    @Schema(description = "Total order amount", example = "216.96")
    private BigDecimal totalAmount;

    @Schema(description = "Currency code", example = "USD")
    private String currency;

    @Schema(description = "Coupon code applied", example = "SAVE10")
    private String couponCode;

    @Schema(description = "Shipping address line 1", example = "123 Main Street")
    private String shippingAddressLine1;

    @Schema(description = "Shipping address line 2", example = "Apt 4B")
    private String shippingAddressLine2;

    @Schema(description = "Shipping city", example = "New York")
    private String shippingCity;

    @Schema(description = "Shipping state", example = "NY")
    private String shippingState;

    @Schema(description = "Shipping postal code", example = "10001")
    private String shippingPostalCode;

    @Schema(description = "Shipping country", example = "United States")
    private String shippingCountry;

    @Schema(description = "Shipment tracking number", example = "1Z999AA10123456784")
    private String trackingNumber;

    @Schema(description = "Estimated delivery date")
    private LocalDateTime estimatedDeliveryDate;

    @Schema(description = "Timestamp when the order was shipped")
    private LocalDateTime shippedAt;

    @Schema(description = "Timestamp when the order was delivered")
    private LocalDateTime deliveredAt;

    @Schema(description = "Timestamp when the order was cancelled")
    private LocalDateTime cancelledAt;

    @Schema(description = "Reason for cancellation")
    private String cancellationReason;

    @Schema(description = "Additional notes")
    private String notes;

    @Schema(description = "Total number of items in the order", example = "3")
    private Integer totalItemCount;

    @Schema(description = "List of order items")
    private List<OrderItemResponseDto> items;

    @Schema(description = "Timestamp when the order was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the order was last updated")
    private LocalDateTime updatedAt;
}
