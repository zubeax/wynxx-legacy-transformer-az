package com.example.demo.dto;

import com.example.demo.enums.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for updating an existing product")
public class UpdateProductRequestDto {

    @Size(max = 255, message = "Product name must not exceed 255 characters")
    @Schema(description = "Product name", example = "Wireless Bluetooth Headphones Pro")
    private String name;

    @Schema(description = "Full product description")
    private String description;

    @Size(max = 500, message = "Short description must not exceed 500 characters")
    @Schema(description = "Short product description")
    private String shortDescription;

    @Size(max = 100, message = "Category must not exceed 100 characters")
    @Schema(description = "Product category", example = "Electronics")
    private String category;

    @Size(max = 100, message = "Brand must not exceed 100 characters")
    @Schema(description = "Product brand", example = "SoundMax")
    private String brand;

    @DecimalMin(value = "0.01", message = "Unit price must be greater than 0")
    @Schema(description = "Unit price of the product", example = "159.99")
    private BigDecimal unitPrice;

    @DecimalMin(value = "0.00", message = "Cost price must be non-negative")
    @Schema(description = "Cost price of the product", example = "80.00")
    private BigDecimal costPrice;

    @DecimalMin(value = "0.00", message = "Tax rate must be non-negative")
    @DecimalMax(value = "100.00", message = "Tax rate must not exceed 100%")
    @Schema(description = "Tax rate percentage", example = "8.50")
    private BigDecimal taxRate;

    @DecimalMin(value = "0.00", message = "Discount percentage must be non-negative")
    @DecimalMax(value = "100.00", message = "Discount percentage must not exceed 100%")
    @Schema(description = "Discount percentage", example = "15.00")
    private BigDecimal discountPercentage;

    @Min(value = 0, message = "Minimum stock level must be non-negative")
    @Schema(description = "Minimum stock level before reorder alert", example = "10")
    private Integer minimumStockLevel;

    @DecimalMin(value = "0.00", message = "Weight must be non-negative")
    @Schema(description = "Product weight in kilograms", example = "0.350")
    private BigDecimal weightKg;

    @Size(max = 50, message = "Unit of measure must not exceed 50 characters")
    @Schema(description = "Unit of measure", example = "piece")
    private String unitOfMeasure;

    @Size(max = 100, message = "Barcode must not exceed 100 characters")
    @Schema(description = "Product barcode", example = "1234567890123")
    private String barcode;

    @Schema(description = "Product status", example = "ACTIVE")
    private ProductStatus status;

    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    @Schema(description = "Product image URL", example = "https://example.com/images/product-001.jpg")
    private String imageUrl;

    @Schema(description = "Additional notes about the product")
    private String notes;
}
