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

    @Schema(description = "Product name", example = "Wireless Headphones")
    private String name;

    @Schema(description = "Product description")
    private String description;

    @Schema(description = "Product category", example = "Electronics")
    private String category;

    @Schema(description = "Product brand", example = "TechBrand")
    private String brand;

    @Schema(description = "Selling price per unit", example = "99.99")
    private BigDecimal unitPrice;

    @Schema(description = "Cost price per unit", example = "45.00")
    private BigDecimal costPrice;

    @Schema(description = "Tax rate percentage", example = "8.5")
    private BigDecimal taxRate;

    @Schema(description = "Price including tax", example = "108.49")
    private BigDecimal priceWithTax;

    @Schema(description = "Total stock quantity", example = "100")
    private Integer stockQuantity;

    @Schema(description = "Reserved stock quantity", example = "10")
    private Integer reservedQuantity;

    @Schema(description = "Available stock quantity (stock - reserved)", example = "90")
    private Integer availableQuantity;

    @Schema(description = "Reorder level threshold", example = "10")
    private Integer reorderLevel;

    @Schema(description = "Whether stock is at or below reorder level", example = "false")
    private Boolean lowStock;

    @Schema(description = "Product weight in kilograms", example = "0.350")
    private BigDecimal weightKg;

    @Schema(description = "URL to product image", example = "https://example.com/images/product.jpg")
    private String imageUrl;

    @Schema(description = "Product status", example = "ACTIVE")
    private ProductStatus status;

    @Schema(description = "Product status display name", example = "Active")
    private String statusDisplayName;

    @Schema(description = "Whether the product is available for sale", example = "true")
    private Boolean availableForSale;

    @Schema(description = "Timestamp when the product was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the product was last updated")
    private LocalDateTime updatedAt;
}
