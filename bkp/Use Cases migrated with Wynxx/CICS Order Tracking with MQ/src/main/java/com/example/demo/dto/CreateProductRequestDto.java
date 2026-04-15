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
    @Size(min = 1, max = 100, message = "SKU must be between 1 and 100 characters")
    @Schema(description = "Stock Keeping Unit - unique product identifier", example = "PROD-001", required = true)
    private String sku;

    @NotBlank(message = "Product name is required")
    @Size(min = 1, max = 255, message = "Product name must be between 1 and 255 characters")
    @Schema(description = "Product name", example = "Wireless Headphones", required = true)
    private String name;

    @Schema(description = "Product description")
    private String description;

    @Size(max = 100, message = "Category must not exceed 100 characters")
    @Schema(description = "Product category", example = "Electronics")
    private String category;

    @Size(max = 100, message = "Brand must not exceed 100 characters")
    @Schema(description = "Product brand", example = "TechBrand")
    private String brand;

    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.0001", message = "Unit price must be greater than 0")
    @Digits(integer = 15, fraction = 4, message = "Unit price format is invalid")
    @Schema(description = "Selling price per unit", example = "99.99", required = true)
    private BigDecimal unitPrice;

    @DecimalMin(value = "0.0", message = "Cost price must be non-negative")
    @Digits(integer = 15, fraction = 4, message = "Cost price format is invalid")
    @Schema(description = "Cost price per unit", example = "45.00")
    private BigDecimal costPrice;

    @DecimalMin(value = "0.0", message = "Tax rate must be non-negative")
    @DecimalMax(value = "100.0", message = "Tax rate must not exceed 100%")
    @Schema(description = "Tax rate percentage", example = "8.5")
    private BigDecimal taxRate = BigDecimal.ZERO;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity must be non-negative")
    @Schema(description = "Initial stock quantity", example = "100", required = true)
    private Integer stockQuantity = 0;

    @Min(value = 0, message = "Reorder level must be non-negative")
    @Schema(description = "Minimum stock level before reorder alert", example = "10")
    private Integer reorderLevel = 0;

    @DecimalMin(value = "0.0", message = "Weight must be non-negative")
    @Schema(description = "Product weight in kilograms", example = "0.350")
    private BigDecimal weightKg;

    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    @Schema(description = "URL to product image", example = "https://example.com/images/product.jpg")
    private String imageUrl;
}
