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

    @Schema(description = "Order number (human-readable)", example = "ORD-2024-000001")
    private String orderNumber;

    @Schema(description = "Customer ID", example = "1")
    private Long customerId;

    @Schema(description = "Customer full name", example = "John Doe")
    private String customerName;

    @Schema(description = "Customer email", example = "john.doe@example.com")
    private String customerEmail;

    @Schema(description = "Order status", example = "CONFIRMED")
    private OrderStatus status;

    @Schema(description = "Order status display name", example = "Confirmed")
    private String statusDisplayName;

    @Schema(description = "Payment status", example = "PAID")
    private PaymentStatus paymentStatus;

    @Schema(description = "Payment status display name", example = "Paid")
    private String paymentStatusDisplayName;

    @Schema(description = "Payment method", example = "CREDIT_CARD")
    private PaymentMethod paymentMethod;

    @Schema(description = "Payment method display name", example = "Credit Card")
    private String paymentMethodDisplayName;

    @Schema(description = "Shipping method", example = "STANDARD")
    private ShippingMethod shippingMethod;

    @Schema(description = "Shipping method display name", example = "Standard Shipping")
    private String shippingMethodDisplayName;

    @Schema(description = "Order subtotal (before discount, tax, shipping)", example = "299.98")
    private BigDecimal subtotal;

    @Schema(description = "Discount amount applied", example = "20.00")
    private BigDecimal discountAmount;

    @Schema(description = "Tax amount", example = "24.65")
    private BigDecimal taxAmount;

    @Schema(description = "Shipping cost", example = "9.99")
    private BigDecimal shippingCost;

    @Schema(description = "Total order amount", example = "314.62")
    private BigDecimal totalAmount;

    @Schema(description = "Amount already paid", example = "314.62")
    private BigDecimal paidAmount;

    @Schema(description = "Outstanding balance", example = "0.00")
    private BigDecimal outstandingBalance;

    @Schema(description = "Currency code", example = "USD")
    private String currencyCode;

    @Schema(description = "Shipping address line 1")
    private String shippingAddressLine1;

    @Schema(description = "Shipping address line 2")
    private String shippingAddressLine2;

    @Schema(description = "Shipping city")
    private String shippingCity;

    @Schema(description = "Shipping state/province")
    private String shippingState;

    @Schema(description = "Shipping postal/zip code")
    private String shippingPostalCode;

    @Schema(description = "Shipping country")
    private String shippingCountry;

    @Schema(description = "Billing address line 1")
    private String billingAddressLine1;

    @Schema(description = "Billing address line 2")
    private String billingAddressLine2;

    @Schema(description = "Billing city")
    private String billingCity;

    @Schema(description = "Billing state/province")
    private String billingState;

    @Schema(description = "Billing postal/zip code")
    private String billingPostalCode;

    @Schema(description = "Billing country")
    private String billingCountry;

    @Schema(description = "Shipment tracking number", example = "1Z999AA10123456784")
    private String trackingNumber;

    @Schema(description = "Shipping carrier", example = "UPS")
    private String carrier;

    @Schema(description = "Estimated delivery date")
    private LocalDateTime estimatedDeliveryDate;

    @Schema(description = "Actual delivery date")
    private LocalDateTime actualDeliveryDate;

    @Schema(description = "Date/time the order was shipped")
    private LocalDateTime shippedAt;

    @Schema(description = "Date/time the order was confirmed")
    private LocalDateTime confirmedAt;

    @Schema(description = "Date/time the order was cancelled")
    private LocalDateTime cancelledAt;

    @Schema(description = "Reason for cancellation")
    private String cancellationReason;

    @Schema(description = "Coupon/promo code applied", example = "SAVE10")
    private String couponCode;

    @Schema(description = "Payment reference/transaction ID", example = "TXN-123456789")
    private String paymentReference;

    @Schema(description = "Customer notes for the order")
    private String customerNotes;

    @Schema(description = "Internal notes for the order")
    private String internalNotes;

    @Schema(description = "Whether the order can be cancelled", example = "true")
    private Boolean canBeCancelled;

    @Schema(description = "Whether the order can be shipped", example = "false")
    private Boolean canBeShipped;

    @Schema(description = "Whether the order is fully paid", example = "true")
    private Boolean fullyPaid;

    @Schema(description = "List of order items")
    private List<OrderItemResponseDto> items;

    @Schema(description = "Timestamp when the order was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the order was last updated")
    private LocalDateTime updatedAt;
}
