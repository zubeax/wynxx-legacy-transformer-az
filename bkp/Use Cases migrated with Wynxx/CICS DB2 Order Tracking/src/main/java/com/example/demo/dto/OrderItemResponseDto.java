package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for order item data")
public class OrderItemResponseDto {

    @Schema(description = "Unique identifier of the order item", example = "1")
    private Long id;

    @Schema(description = "ID of the product", example = "5")
    private Long productId;

    @Schema(description = "Product SKU", example = "PROD-001")
    private String productSku;

    @Schema(description = "Product name at time of order", example = "Wireless Bluetooth Headphones")
    private String productName;

    @Schema(description = "Quantity ordered", example = "2")
    private Integer quantity;

    @Schema(description = "Unit price at time of order", example = "149.99")
    private BigDecimal unitPrice;

    @Schema(description = "Discount percentage applied", example = "5.00")
    private BigDecimal discountPercentage;

    @Schema(description = "Total discount amount", example = "15.00")
    private BigDecimal discountAmount;

    @Schema(description = "Tax rate applied", example = "8.50")
    private BigDecimal taxRate;

    @Schema(description = "Tax amount", example = "24.65")
    private BigDecimal taxAmount;

    @Schema(description = "Line total (quantity × unit price - discount + tax)", example = "314.63")
    private BigDecimal lineTotal;

    @Schema(description = "Subtotal before discount and tax", example = "299.98")
    private BigDecimal subtotal;

    @Schema(description = "Additional notes for this item")
    private String notes;

    @Schema(description = "Timestamp when the item was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the item was last updated")
    private LocalDateTime updatedAt;
}
