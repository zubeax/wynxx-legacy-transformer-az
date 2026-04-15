package com.example.demo.controller;

import com.example.demo.dto.CreateCustomerRequestDto;
import com.example.demo.dto.CustomerResponseDto;
import com.example.demo.dto.UpdateCustomerRequestDto;
import com.example.demo.enums.CustomerStatus;
import com.example.demo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Customer Management", description = "APIs for managing customers in the order processing system")
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Get all customers", description = "Retrieve a paginated list of all customers with optional filtering")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of customers"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomers(
            @Parameter(description = "Filter by customer status") @RequestParam(required = false) CustomerStatus status,
            @Parameter(description = "Search term for name or email") @RequestParam(required = false) String search,
            @PageableDefault(size = 20) Pageable pageable) {
        Page<CustomerResponseDto> customers;
        if (search != null && !search.isBlank()) {
            customers = customerService.searchCustomers(search, status, pageable);
        } else if (status != null) {
            customers = customerService.getCustomersByStatus(status, pageable);
        } else {
            customers = customerService.getAllCustomers(pageable);
        }
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Get customer by ID", description = "Retrieve a specific customer by their unique ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of customer"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get customer by email", description = "Retrieve a specific customer by their email address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of customer"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-email")
    public ResponseEntity<CustomerResponseDto> getCustomerByEmail(
            @Parameter(description = "Customer email address", required = true) @RequestParam String email) {
        return customerService.getCustomerByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new customer", description = "Register a new customer in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Customer created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data or email already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(
            @Valid @RequestBody CreateCustomerRequestDto request) {
        CustomerResponseDto response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update an existing customer", description = "Update customer details by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequestDto request) {
        CustomerResponseDto response = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a customer", description = "Delete a customer by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update customer status", description = "Change the status of a customer (ACTIVE, INACTIVE, SUSPENDED, PENDING_VERIFICATION)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer status updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid status value"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<CustomerResponseDto> updateCustomerStatus(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long id,
            @Parameter(description = "New customer status", required = true) @RequestParam CustomerStatus status) {
        CustomerResponseDto response = customerService.updateCustomerStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add loyalty points", description = "Add loyalty points to a customer's account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Loyalty points added successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid points value"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/loyalty-points/add")
    public ResponseEntity<CustomerResponseDto> addLoyaltyPoints(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long id,
            @Parameter(description = "Number of points to add", required = true) @RequestParam int points) {
        if (points <= 0) {
            return ResponseEntity.badRequest().build();
        }
        CustomerResponseDto response = customerService.addLoyaltyPoints(id, points);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deduct loyalty points", description = "Deduct loyalty points from a customer's account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Loyalty points deducted successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid points value or insufficient balance"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/loyalty-points/deduct")
    public ResponseEntity<CustomerResponseDto> deductLoyaltyPoints(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long id,
            @Parameter(description = "Number of points to deduct", required = true) @RequestParam int points) {
        if (points <= 0) {
            return ResponseEntity.badRequest().build();
        }
        CustomerResponseDto response = customerService.deductLoyaltyPoints(id, points);
        return ResponseEntity.ok(response);
    }
}
