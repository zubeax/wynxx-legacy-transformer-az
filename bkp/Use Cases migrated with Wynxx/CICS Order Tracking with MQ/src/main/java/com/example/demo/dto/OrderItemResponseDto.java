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

    @Schema(description = "Product name at time of order", example = "Wireless Headphones")
    private String productName;

    @Schema(description = "Quantity ordered", example = "2")
    private Integer quantity;

    @Schema(description = "Unit price at time of order", example = "99.99")
    private BigDecimal unitPrice;

    @Schema(description = "Discount percentage applied", example = "10.0")
    private BigDecimal discountPercent;

    @Schema(description = "Discount amount", example = "20.00")
    private BigDecimal discountAmount;

    @Schema(description = "Tax rate percentage", example = "8.5")
    private BigDecimal taxRate;

    @Schema(description = "Tax amount", example = "15.30")
    private BigDecimal taxAmount;

    @Schema(description = "Line total before tax", example = "179.98")
    private BigDecimal lineTotal;

    @Schema(description = "Line total including tax", example = "195.28")
    private BigDecimal lineTotalWithTax;

    @Schema(description = "Timestamp when the item was created")
    private LocalDateTime createdAt;
}
