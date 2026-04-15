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
@Tag(name = "Customer Management", description = "APIs for managing customers in the order management system")
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Get all customers", description = "Retrieve a paginated list of all customers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of customers"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomers(
            @PageableDefault(size = 20) Pageable pageable,
            @Parameter(description = "Filter by customer status") @RequestParam(required = false) CustomerStatus status,
            @Parameter(description = "Search term for name, email, or company") @RequestParam(required = false) String search) {

        Page<CustomerResponseDto> customers;
        if (search != null && !search.isBlank()) {
            customers = customerService.searchCustomers(search, pageable);
        } else if (status != null) {
            customers = customerService.getCustomersByStatus(status, pageable);
        } else {
            customers = customerService.getAllCustomers(pageable);
        }
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Get customer by ID", description = "Retrieve a specific customer by their ID")
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
    @GetMapping("/by-email/{email}")
    public ResponseEntity<CustomerResponseDto> getCustomerByEmail(
            @Parameter(description = "Customer email address", required = true) @PathVariable String email) {
        return customerService.getCustomerByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new customer", description = "Create a new customer in the system")
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

    @Operation(summary = "Delete a customer", description = "Delete a customer by ID (only if no orders exist)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Customer has existing orders"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Activate a customer", description = "Set customer status to ACTIVE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer activated successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{id}/activate")
    public ResponseEntity<CustomerResponseDto> activateCustomer(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long id) {
        CustomerResponseDto response = customerService.activateCustomer(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deactivate a customer", description = "Set customer status to INACTIVE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer deactivated successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<CustomerResponseDto> deactivateCustomer(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long id) {
        CustomerResponseDto response = customerService.deactivateCustomer(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Suspend a customer", description = "Set customer status to SUSPENDED")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer suspended successfully"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{id}/suspend")
    public ResponseEntity<CustomerResponseDto> suspendCustomer(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long id,
            @Parameter(description = "Reason for suspension") @RequestParam(required = false) String reason) {
        CustomerResponseDto response = customerService.suspendCustomer(id, reason);
        return ResponseEntity.ok(response);
    }
}
