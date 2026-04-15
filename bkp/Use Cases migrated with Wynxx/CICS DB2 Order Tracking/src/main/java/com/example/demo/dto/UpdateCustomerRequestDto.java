package com.example.demo.dto;

import com.example.demo.enums.CustomerStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for updating an existing customer")
public class UpdateCustomerRequestDto {

    @Size(max = 100, message = "First name must not exceed 100 characters")
    @Schema(description = "Customer's first name", example = "John")
    private String firstName;

    @Size(max = 100, message = "Last name must not exceed 100 characters")
    @Schema(description = "Customer's last name", example = "Doe")
    private String lastName;

    @Size(max = 30, message = "Phone number must not exceed 30 characters")
    @Schema(description = "Customer's phone number", example = "+1-555-123-4567")
    private String phoneNumber;

    @Size(max = 200, message = "Company name must not exceed 200 characters")
    @Schema(description = "Customer's company name", example = "Acme Corp")
    private String companyName;

    @Size(max = 50, message = "Tax ID must not exceed 50 characters")
    @Schema(description = "Customer's tax identification number", example = "12-3456789")
    private String taxId;

    @Size(max = 255, message = "Billing address line 1 must not exceed 255 characters")
    @Schema(description = "Billing address line 1", example = "123 Main Street")
    private String billingAddressLine1;

    @Size(max = 255, message = "Billing address line 2 must not exceed 255 characters")
    @Schema(description = "Billing address line 2", example = "Suite 100")
    private String billingAddressLine2;

    @Size(max = 100, message = "Billing city must not exceed 100 characters")
    @Schema(description = "Billing city", example = "New York")
    private String billingCity;

    @Size(max = 100, message = "Billing state must not exceed 100 characters")
    @Schema(description = "Billing state/province", example = "NY")
    private String billingState;

    @Size(max = 20, message = "Billing postal code must not exceed 20 characters")
    @Schema(description = "Billing postal/zip code", example = "10001")
    private String billingPostalCode;

    @Size(max = 100, message = "Billing country must not exceed 100 characters")
    @Schema(description = "Billing country", example = "United States")
    private String billingCountry;

    @Size(max = 255, message = "Shipping address line 1 must not exceed 255 characters")
    @Schema(description = "Shipping address line 1", example = "456 Oak Avenue")
    private String shippingAddressLine1;

    @Size(max = 255, message = "Shipping address line 2 must not exceed 255 characters")
    @Schema(description = "Shipping address line 2", example = "Apt 2B")
    private String shippingAddressLine2;

    @Size(max = 100, message = "Shipping city must not exceed 100 characters")
    @Schema(description = "Shipping city", example = "Brooklyn")
    private String shippingCity;

    @Size(max = 100, message = "Shipping state must not exceed 100 characters")
    @Schema(description = "Shipping state/province", example = "NY")
    private String shippingState;

    @Size(max = 20, message = "Shipping postal code must not exceed 20 characters")
    @Schema(description = "Shipping postal/zip code", example = "11201")
    private String shippingPostalCode;

    @Size(max = 100, message = "Shipping country must not exceed 100 characters")
    @Schema(description = "Shipping country", example = "United States")
    private String shippingCountry;

    @Schema(description = "Customer status", example = "ACTIVE")
    private CustomerStatus status;

    @DecimalMin(value = "0.00", message = "Credit limit must be non-negative")
    @Schema(description = "Customer's credit limit", example = "5000.00")
    private java.math.BigDecimal creditLimit;

    @Schema(description = "Additional notes about the customer", example = "VIP customer - priority handling")
    private String notes;
}
