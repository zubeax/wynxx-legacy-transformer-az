package com.example.demo.dto;

import com.example.demo.enums.CustomerStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Schema(description = "Customer's email address", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Customer's phone number", example = "+1-555-123-4567")
    private String phoneNumber;

    @Schema(description = "Primary address line", example = "123 Main Street")
    private String addressLine1;

    @Schema(description = "Secondary address line", example = "Apt 4B")
    private String addressLine2;

    @Schema(description = "City", example = "New York")
    private String city;

    @Schema(description = "State or province", example = "NY")
    private String state;

    @Schema(description = "Postal or ZIP code", example = "10001")
    private String postalCode;

    @Schema(description = "Country", example = "United States")
    private String country;

    @Schema(description = "Full formatted address")
    private String fullAddress;

    @Schema(description = "Customer status", example = "ACTIVE")
    private CustomerStatus status;

    @Schema(description = "Customer status display name", example = "Active")
    private String statusDisplayName;

    @Schema(description = "Loyalty points balance", example = "150")
    private Integer loyaltyPoints;

    @Schema(description = "Additional notes about the customer")
    private String notes;

    @Schema(description = "Timestamp when the customer was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the customer was last updated")
    private LocalDateTime updatedAt;
}
