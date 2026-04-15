package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.enums.OrderStatus;
import com.example.demo.service.OrderService;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Order Management", description = "APIs for managing orders and order processing workflows")
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Get all orders", description = "Retrieve a paginated list of all orders with optional filtering by status or date range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of orders"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> getAllOrders(
            @Parameter(description = "Filter by order status") @RequestParam(required = false) OrderStatus status,
            @Parameter(description = "Filter by start date (ISO format)") @RequestParam(required = false)
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @Parameter(description = "Filter by end date (ISO format)") @RequestParam(required = false)
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @PageableDefault(size = 20) Pageable pageable) {

        Page<OrderResponseDto> orders;
        if (startDate != null && endDate != null) {
            orders = orderService.getOrdersByDateRange(startDate, endDate, pageable);
        } else if (status != null) {
            orders = orderService.getOrdersByStatus(status, pageable);
        } else {
            orders = orderService.getAllOrders(pageable);
        }
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "Get order by ID", description = "Retrieve a specific order by its unique ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of order"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(
            @Parameter(description = "Order ID", required = true) @PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get order by order number", description = "Retrieve a specific order by its human-readable order number")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of order"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-number/{orderNumber}")
    public ResponseEntity<OrderResponseDto> getOrderByOrderNumber(
            @Parameter(description = "Order number (e.g., ORD-20240101-00001)", required = true) @PathVariable String orderNumber) {
        return orderService.getOrderByOrderNumber(orderNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get orders by customer", description = "Retrieve all orders for a specific customer")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of customer orders"),
        @ApiResponse(responseCode = "404", description = "Customer not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<OrderResponseDto>> getOrdersByCustomer(
            @Parameter(description = "Customer ID", required = true) @PathVariable Long customerId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId, pageable));
    }

    @Operation(summary = "Create a new order", description = "Place a new order for a customer with specified items")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Order created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data, customer not eligible, or insufficient stock"),
        @ApiResponse(responseCode = "404", description = "Customer or product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @Valid @RequestBody CreateOrderRequestDto request) {
        OrderResponseDto response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update an existing order", description = "Update order details (shipping address, payment method, etc.) for non-terminal orders")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data or order is in terminal status"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable Long id,
            @Valid @RequestBody UpdateOrderRequestDto request) {
        OrderResponseDto response = orderService.updateOrder(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete an order", description = "Delete a cancelled or delivered order by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Order deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Cannot delete an active order"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Confirm an order", description = "Confirm a pending order, moving it to CONFIRMED status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order confirmed successfully"),
        @ApiResponse(responseCode = "400", description = "Order is not in PENDING status"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/confirm")
    public ResponseEntity<OrderResponseDto> confirmOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable Long id,
            @Parameter(description = "User confirming the order") @RequestParam(required = false, defaultValue = "system") String confirmedBy) {
        OrderResponseDto response = orderService.confirmOrder(id, confirmedBy);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Process an order", description = "Start processing a confirmed order, moving it to PROCESSING status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order processing started successfully"),
        @ApiResponse(responseCode = "400", description = "Order is not in CONFIRMED status"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/process")
    public ResponseEntity<OrderResponseDto> processOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable Long id,
            @Parameter(description = "User processing the order") @RequestParam(required = false, defaultValue = "system") String processedBy) {
        OrderResponseDto response = orderService.processOrder(id, processedBy);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Ship an order", description = "Mark an order as shipped with tracking information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order shipped successfully"),
        @ApiResponse(responseCode = "400", description = "Order cannot be shipped from current status"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/ship")
    public ResponseEntity<OrderResponseDto> shipOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable Long id,
            @Valid @RequestBody ShipOrderRequestDto request) {
        OrderResponseDto response = orderService.shipOrder(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Mark order as delivered", description = "Mark a shipped order as delivered")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order marked as delivered successfully"),
        @ApiResponse(responseCode = "400", description = "Order is not in SHIPPED status"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/deliver")
    public ResponseEntity<OrderResponseDto> deliverOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable Long id,
            @Parameter(description = "User marking the order as delivered") @RequestParam(required = false, defaultValue = "system") String deliveredBy) {
        OrderResponseDto response = orderService.deliverOrder(id, deliveredBy);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cancel an order", description = "Cancel a pending, confirmed, or processing order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order cancelled successfully"),
        @ApiResponse(responseCode = "400", description = "Order cannot be cancelled from current status"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDto> cancelOrder(
            @Parameter(description = "Order ID", required = true) @PathVariable Long id,
            @Valid @RequestBody CancelOrderRequestDto request) {
        OrderResponseDto response = orderService.cancelOrder(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update order status", description = "Manually update the status of an order (admin operation)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order status updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid status transition or order is in terminal status"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(
            @Parameter(description = "Order ID", required = true) @PathVariable Long id,
            @Valid @RequestBody OrderStatusUpdateRequestDto request) {
        OrderResponseDto response = orderService.updateOrderStatus(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get order status history", description = "Retrieve the complete status change history for an order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of order status history"),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}/status-history")
    public ResponseEntity<List<OrderStatusHistoryResponseDto>> getOrderStatusHistory(
            @Parameter(description = "Order ID", required = true) @PathVariable Long id) {
        List<OrderStatusHistoryResponseDto> history = orderService.getOrderStatusHistory(id);
        return ResponseEntity.ok(history);
    }

    @Operation(summary = "Calculate revenue", description = "Calculate total revenue for a given date range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Revenue calculated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid date range"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/revenue")
    public ResponseEntity<BigDecimal> calculateRevenue(
            @Parameter(description = "Start date (ISO format)", required = true)
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @Parameter(description = "End date (ISO format)", required = true)
                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            return ResponseEntity.badRequest().build();
        }
        BigDecimal revenue = orderService.calculateRevenue(startDate, endDate);
        return ResponseEntity.ok(revenue);
    }
}
