package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for creating a new product")
public class CreateProductRequestDto {

    @NotBlank(message = "SKU is required")
    @Size(max = 100, message = "SKU must not exceed 100 characters")
    @Schema(description = "Stock Keeping Unit - unique product identifier", example = "PROD-001", required = true)
    private String sku;

    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must not exceed 255 characters")
    @Schema(description = "Product name", example = "Wireless Bluetooth Headphones", required = true)
    private String name;

    @Schema(description = "Full product description", example = "High-quality wireless headphones with noise cancellation")
    private String description;

    @Size(max = 500, message = "Short description must not exceed 500 characters")
    @Schema(description = "Short product description", example = "Wireless headphones with ANC")
    private String shortDescription;

    @Size(max = 100, message = "Category must not exceed 100 characters")
    @Schema(description = "Product category", example = "Electronics")
    private String category;

    @Size(max = 100, message = "Brand must not exceed 100 characters")
    @Schema(description = "Product brand", example = "SoundMax")
    private String brand;

    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.01", message = "Unit price must be greater than 0")
    @Schema(description = "Unit price of the product", example = "149.99", required = true)
    private BigDecimal unitPrice;

    @DecimalMin(value = "0.00", message = "Cost price must be non-negative")
    @Schema(description = "Cost price of the product", example = "75.00")
    private BigDecimal costPrice;

    @DecimalMin(value = "0.00", message = "Tax rate must be non-negative")
    @DecimalMax(value = "100.00", message = "Tax rate must not exceed 100%")
    @Schema(description = "Tax rate percentage", example = "8.50")
    private BigDecimal taxRate = BigDecimal.ZERO;

    @DecimalMin(value = "0.00", message = "Discount percentage must be non-negative")
    @DecimalMax(value = "100.00", message = "Discount percentage must not exceed 100%")
    @Schema(description = "Discount percentage", example = "10.00")
    private BigDecimal discountPercentage = BigDecimal.ZERO;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity must be non-negative")
    @Schema(description = "Initial stock quantity", example = "100", required = true)
    private Integer stockQuantity = 0;

    @Min(value = 0, message = "Minimum stock level must be non-negative")
    @Schema(description = "Minimum stock level before reorder alert", example = "10")
    private Integer minimumStockLevel = 0;

    @DecimalMin(value = "0.00", message = "Weight must be non-negative")
    @Schema(description = "Product weight in kilograms", example = "0.350")
    private BigDecimal weightKg;

    @Size(max = 50, message = "Unit of measure must not exceed 50 characters")
    @Schema(description = "Unit of measure", example = "piece")
    private String unitOfMeasure;

    @Size(max = 100, message = "Barcode must not exceed 100 characters")
    @Schema(description = "Product barcode", example = "1234567890123")
    private String barcode;

    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    @Schema(description = "Product image URL", example = "https://example.com/images/product-001.jpg")
    private String imageUrl;

    @Schema(description = "Additional notes about the product")
    private String notes;
}
