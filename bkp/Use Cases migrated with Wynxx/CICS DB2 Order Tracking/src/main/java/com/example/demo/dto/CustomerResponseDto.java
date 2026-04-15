package com.example.demo.dto;

import com.example.demo.enums.CustomerStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for customer data")
public class CustomerResponseDto {

    @Schema(description = "Unique identifier of the customer", example = "1")
    private Long id;

    @Schema(description = "Customer's first name", example = "John")
    private String firstName;

    @Schema(description = "Customer's last name", example = "Doe")
    private String lastName;

    @Schema(description = "Customer's full name", example = "John Doe")
    private String fullName;

    @Schema(description = "Customer's display name (includes company if available)", example = "Acme Corp (John Doe)")
    private String displayName;

    @Schema(description = "Customer's email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Customer's phone number", example = "+1-555-123-4567")
    private String phoneNumber;

    @Schema(description = "Customer's company name", example = "Acme Corp")
    private String companyName;

    @Schema(description = "Customer's tax identification number", example = "12-3456789")
    private String taxId;

    @Schema(description = "Billing address line 1", example = "123 Main Street")
    private String billingAddressLine1;

    @Schema(description = "Billing address line 2", example = "Suite 100")
    private String billingAddressLine2;

    @Schema(description = "Billing city", example = "New York")
    private String billingCity;

    @Schema(description = "Billing state/province", example = "NY")
    private String billingState;

    @Schema(description = "Billing postal/zip code", example = "10001")
    private String billingPostalCode;

    @Schema(description = "Billing country", example = "United States")
    private String billingCountry;

    @Schema(description = "Shipping address line 1", example = "456 Oak Avenue")
    private String shippingAddressLine1;

    @Schema(description = "Shipping address line 2", example = "Apt 2B")
    private String shippingAddressLine2;

    @Schema(description = "Shipping city", example = "Brooklyn")
    private String shippingCity;

    @Schema(description = "Shipping state/province", example = "NY")
    private String shippingState;

    @Schema(description = "Shipping postal/zip code", example = "11201")
    private String shippingPostalCode;

    @Schema(description = "Shipping country", example = "United States")
    private String shippingCountry;

    @Schema(description = "Customer status", example = "ACTIVE")
    private CustomerStatus status;

    @Schema(description = "Customer status display name", example = "Active")
    private String statusDisplayName;

    @Schema(description = "Customer's credit limit", example = "5000.00")
    private BigDecimal creditLimit;

    @Schema(description = "Total number of orders placed", example = "15")
    private Integer totalOrders;

    @Schema(description = "Total amount spent by the customer", example = "12500.00")
    private BigDecimal totalSpent;

    @Schema(description = "Additional notes about the customer")
    private String notes;

    @Schema(description = "Whether the customer can place orders", example = "true")
    private Boolean canPlaceOrders;

    @Schema(description = "Timestamp when the customer was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the customer was last updated")
    private LocalDateTime updatedAt;
}
