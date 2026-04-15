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

    @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters")
    @Schema(description = "Customer's first name", example = "John")
    private String firstName;

    @Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters")
    @Schema(description = "Customer's last name", example = "Doe")
    private String lastName;

    @Pattern(regexp = "^[+]?[0-9\\s\\-().]{7,20}$", message = "Phone number format is invalid")
    @Schema(description = "Customer's phone number", example = "+1-555-123-4567")
    private String phoneNumber;

    @Size(max = 255, message = "Address line 1 must not exceed 255 characters")
    @Schema(description = "Primary address line", example = "123 Main Street")
    private String addressLine1;

    @Size(max = 255, message = "Address line 2 must not exceed 255 characters")
    @Schema(description = "Secondary address line", example = "Apt 4B")
    private String addressLine2;

    @Size(max = 100, message = "City must not exceed 100 characters")
    @Schema(description = "City", example = "New York")
    private String city;

    @Size(max = 100, message = "State must not exceed 100 characters")
    @Schema(description = "State or province", example = "NY")
    private String state;

    @Size(max = 20, message = "Postal code must not exceed 20 characters")
    @Schema(description = "Postal or ZIP code", example = "10001")
    private String postalCode;

    @Size(max = 100, message = "Country must not exceed 100 characters")
    @Schema(description = "Country", example = "United States")
    private String country;

    @Schema(description = "Customer status", example = "ACTIVE")
    private CustomerStatus status;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    @Schema(description = "Additional notes about the customer")
    private String notes;
}
