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

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "unit_price", nullable = false, precision = 19, scale = 4)
    private BigDecimal unitPrice;

    @Column(name = "cost_price", precision = 19, scale = 4)
    private BigDecimal costPrice;

    @Column(name = "tax_rate", precision = 5, scale = 2)
    private BigDecimal taxRate = BigDecimal.ZERO;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity = 0;

    @Column(name = "reorder_level")
    private Integer reorderLevel = 0;

    @Column(name = "weight_kg", precision = 10, scale = 3)
    private BigDecimal weightKg;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private ProductStatus status = ProductStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    public int getAvailableQuantity() {
        return stockQuantity - reservedQuantity;
    }

    public boolean isAvailableForSale() {
        return status != null && status.isAvailableForSale() && getAvailableQuantity() > 0;
    }

    public boolean hasEnoughStock(int requestedQuantity) {
        return getAvailableQuantity() >= requestedQuantity;
    }

    public void reserveStock(int quantity) {
        if (!hasEnoughStock(quantity)) {
            throw new IllegalArgumentException("Insufficient stock for product: " + sku);
        }
        this.reservedQuantity += quantity;
    }

    public void releaseReservedStock(int quantity) {
        this.reservedQuantity = Math.max(0, this.reservedQuantity - quantity);
    }

    public void deductStock(int quantity) {
        if (this.stockQuantity < quantity) {
            throw new IllegalArgumentException("Cannot deduct more stock than available for product: " + sku);
        }
        this.stockQuantity -= quantity;
        this.reservedQuantity = Math.max(0, this.reservedQuantity - quantity);
    }

    public void addStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Stock quantity to add must be positive");
        }
        this.stockQuantity += quantity;
        if (this.status == ProductStatus.OUT_OF_STOCK && this.stockQuantity > 0) {
            this.status = ProductStatus.ACTIVE;
        }
    }

    public BigDecimal getPriceWithTax() {
        if (taxRate == null || taxRate.compareTo(BigDecimal.ZERO) == 0) {
            return unitPrice;
        }
        return unitPrice.multiply(BigDecimal.ONE.add(taxRate.divide(new BigDecimal("100"))));
    }

    public boolean isLowStock() {
        return reorderLevel != null && stockQuantity <= reorderLevel;
    }
}
