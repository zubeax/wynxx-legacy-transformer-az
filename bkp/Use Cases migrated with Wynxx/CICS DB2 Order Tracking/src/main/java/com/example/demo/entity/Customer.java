package com.example.demo.entity;

import com.example.demo.enums.CustomerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false, length = 255)
    private String email;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Column(name = "company_name", length = 200)
    private String companyName;

    @Column(name = "tax_id", length = 50)
    private String taxId;

    @Column(name = "billing_address_line1", length = 255)
    private String billingAddressLine1;

    @Column(name = "billing_address_line2", length = 255)
    private String billingAddressLine2;

    @Column(name = "billing_city", length = 100)
    private String billingCity;

    @Column(name = "billing_state", length = 100)
    private String billingState;

    @Column(name = "billing_postal_code", length = 20)
    private String billingPostalCode;

    @Column(name = "billing_country", length = 100)
    private String billingCountry;

    @Column(name = "shipping_address_line1", length = 255)
    private String shippingAddressLine1;

    @Column(name = "shipping_address_line2", length = 255)
    private String shippingAddressLine2;

    @Column(name = "shipping_city", length = 100)
    private String shippingCity;

    @Column(name = "shipping_state", length = 100)
    private String shippingState;

    @Column(name = "shipping_postal_code", length = 20)
    private String shippingPostalCode;

    @Column(name = "shipping_country", length = 100)
    private String shippingCountry;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private CustomerStatus status = CustomerStatus.ACTIVE;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "credit_limit", precision = 15, scale = 2)
    private java.math.BigDecimal creditLimit;

    @Column(name = "total_orders")
    private Integer totalOrders = 0;

    @Column(name = "total_spent", precision = 15, scale = 2)
    private java.math.BigDecimal totalSpent = java.math.BigDecimal.ZERO;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDisplayName() {
        if (companyName != null && !companyName.isBlank()) {
            return companyName + " (" + getFullName() + ")";
        }
        return getFullName();
    }

    public boolean canPlaceOrders() {
        return status != null && status.canPlaceOrders();
    }

    public void incrementOrderStats(java.math.BigDecimal orderAmount) {
        this.totalOrders = (this.totalOrders == null ? 0 : this.totalOrders) + 1;
        this.totalSpent = (this.totalSpent == null ? java.math.BigDecimal.ZERO : this.totalSpent).add(orderAmount);
    }

    public void decrementOrderStats(java.math.BigDecimal orderAmount) {
        this.totalOrders = Math.max(0, (this.totalOrders == null ? 0 : this.totalOrders) - 1);
        this.totalSpent = (this.totalSpent == null ? java.math.BigDecimal.ZERO : this.totalSpent).subtract(orderAmount);
        if (this.totalSpent.compareTo(java.math.BigDecimal.ZERO) < 0) {
            this.totalSpent = java.math.BigDecimal.ZERO;
        }
    }
}
