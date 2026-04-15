package com.example.demo.dto;

import com.example.demo.enums.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for product data")
public class ProductResponseDto {

    @Schema(description = "Unique identifier of the product", example = "1")
    private Long id;

    @Schema(description = "Stock Keeping Unit", example = "PROD-001")
    private String sku;

    @Schema(description = "Product name", example = "Wireless Bluetooth Headphones")
    private String name;

    @Schema(description = "Full product description")
    private String description;

    @Schema(description = "Short product description", example = "Wireless headphones with ANC")
    private String shortDescription;

    @Schema(description = "Product category", example = "Electronics")
    private String category;

    @Schema(description = "Product brand", example = "SoundMax")
    private String brand;

    @Schema(description = "Unit price of the product", example = "149.99")
    private BigDecimal unitPrice;

    @Schema(description = "Cost price of the product", example = "75.00")
    private BigDecimal costPrice;

    @Schema(description = "Tax rate percentage", example = "8.50")
    private BigDecimal taxRate;

    @Schema(description = "Discount percentage", example = "10.00")
    private BigDecimal discountPercentage;

    @Schema(description = "Effective price after discount", example = "134.99")
    private BigDecimal effectivePrice;

    @Schema(description = "Price including tax", example = "145.94")
    private BigDecimal priceWithTax;

    @Schema(description = "Current stock quantity", example = "85")
    private Integer stockQuantity;

    @Schema(description = "Reserved quantity (in pending orders)", example = "5")
    private Integer reservedQuantity;

    @Schema(description = "Available quantity for ordering", example = "80")
    private Integer availableQuantity;

    @Schema(description = "Minimum stock level before reorder alert", example = "10")
    private Integer minimumStockLevel;

    @Schema(description = "Whether the product is in stock", example = "true")
    private Boolean inStock;

    @Schema(description = "Whether the product is low on stock", example = "false")
    private Boolean lowStock;

    @Schema(description = "Whether the product can be ordered", example = "true")
    private Boolean orderable;

    @Schema(description = "Product weight in kilograms", example = "0.350")
    private BigDecimal weightKg;

    @Schema(description = "Unit of measure", example = "piece")
    private String unitOfMeasure;

    @Schema(description = "Product barcode", example = "1234567890123")
    private String barcode;

    @Schema(description = "Product status", example = "ACTIVE")
    private ProductStatus status;

    @Schema(description = "Product status display name", example = "Active")
    private String statusDisplayName;

    @Schema(description = "Product image URL")
    private String imageUrl;

    @Schema(description = "Additional notes about the product")
    private String notes;

    @Schema(description = "Timestamp when the product was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the product was last updated")
    private LocalDateTime updatedAt;
}
