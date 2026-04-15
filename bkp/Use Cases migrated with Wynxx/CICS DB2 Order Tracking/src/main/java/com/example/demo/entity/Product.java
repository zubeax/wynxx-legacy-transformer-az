package com.example.demo.entity;

import com.example.demo.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku", unique = true, nullable = false, length = 100)
    private String sku;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "short_description", length = 500)
    private String shortDescription;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "cost_price", precision = 15, scale = 2)
    private BigDecimal costPrice;

    @Column(name = "tax_rate", precision = 5, scale = 2)
    private BigDecimal taxRate = BigDecimal.ZERO;

    @Column(name = "discount_percentage", precision = 5, scale = 2)
    private BigDecimal discountPercentage = BigDecimal.ZERO;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @Column(name = "reserved_quantity")
    private Integer reservedQuantity = 0;

    @Column(name = "minimum_stock_level")
    private Integer minimumStockLevel = 0;

    @Column(name = "weight_kg", precision = 10, scale = 3)
    private BigDecimal weightKg;

    @Column(name = "unit_of_measure", length = 50)
    private String unitOfMeasure;

    @Column(name = "barcode", length = 100)
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private ProductStatus status = ProductStatus.ACTIVE;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    public int getAvailableQuantity() {
        int reserved = reservedQuantity == null ? 0 : reservedQuantity;
        return Math.max(0, stockQuantity - reserved);
    }

    public boolean isInStock() {
        return getAvailableQuantity() > 0;
    }

    public boolean isLowStock() {
        return stockQuantity <= (minimumStockLevel == null ? 0 : minimumStockLevel);
    }

    public boolean isOrderable() {
        return status != null && status.isOrderable() && isInStock();
    }

    public BigDecimal getEffectivePrice() {
        if (discountPercentage != null && discountPercentage.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal discount = unitPrice.multiply(discountPercentage).divide(BigDecimal.valueOf(100));
            return unitPrice.subtract(discount);
        }
        return unitPrice;
    }

    public BigDecimal getPriceWithTax() {
        BigDecimal effectivePrice = getEffectivePrice();
        if (taxRate != null && taxRate.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal tax = effectivePrice.multiply(taxRate).divide(BigDecimal.valueOf(100));
            return effectivePrice.add(tax);
        }
        return effectivePrice;
    }

    public void reserveStock(int quantity) {
        this.reservedQuantity = (this.reservedQuantity == null ? 0 : this.reservedQuantity) + quantity;
    }

    public void releaseReservedStock(int quantity) {
        this.reservedQuantity = Math.max(0, (this.reservedQuantity == null ? 0 : this.reservedQuantity) - quantity);
    }

    public void deductStock(int quantity) {
        this.stockQuantity = Math.max(0, this.stockQuantity - quantity);
        releaseReservedStock(quantity);
        if (this.stockQuantity == 0) {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
        if (this.status == ProductStatus.OUT_OF_STOCK && this.stockQuantity > 0) {
            this.status = ProductStatus.ACTIVE;
        }
    }
}
