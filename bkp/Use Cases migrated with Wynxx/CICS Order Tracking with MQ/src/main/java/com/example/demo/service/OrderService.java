package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.enums.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderStatusHistoryRepository orderStatusHistoryRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderResponseDto createOrder(CreateOrderRequestDto request) {
        log.info("Creating new order for customer ID: {}", request.getCustomerId());

        // Validate customer
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + request.getCustomerId()));

        if (!customer.canPlaceOrder()) {
            throw new IllegalStateException("Customer with ID " + request.getCustomerId() +
                    " is not eligible to place orders. Status: " + customer.getStatus());
        }

        // Create order
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setPaymentMethod(request.getPaymentMethod());
        order.setShippingMethod(request.getShippingMethod());
        order.setShippingAddressLine1(request.getShippingAddressLine1() != null ?
                request.getShippingAddressLine1() : customer.getAddressLine1());
        order.setShippingAddressLine2(request.getShippingAddressLine2() != null ?
                request.getShippingAddressLine2() : customer.getAddressLine2());
        order.setShippingCity(request.getShippingCity() != null ?
                request.getShippingCity() : customer.getCity());
        order.setShippingState(request.getShippingState() != null ?
                request.getShippingState() : customer.getState());
        order.setShippingPostalCode(request.getShippingPostalCode() != null ?
                request.getShippingPostalCode() : customer.getPostalCode());
        order.setShippingCountry(request.getShippingCountry() != null ?
                request.getShippingCountry() : customer.getCountry());
        order.setCouponCode(request.getCouponCode());
        order.setDiscountAmount(request.getDiscountAmount() != null ? request.getDiscountAmount() : BigDecimal.ZERO);
        order.setCurrency(request.getCurrency() != null ? request.getCurrency() : "USD");
        order.setNotes(request.getNotes());

        // Calculate shipping cost based on method
        order.setShippingCost(calculateShippingCost(request.getShippingMethod()));

        // Save order first to get ID
        Order savedOrder = orderRepository.save(order);

        // Process order items
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequestDto itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + itemRequest.getProductId()));

            if (!product.isAvailableForSale()) {
                throw new IllegalStateException("Product '" + product.getName() + "' (SKU: " + product.getSku() + ") is not available for sale");
            }

            if (!product.hasEnoughStock(itemRequest.getQuantity())) {
                throw new IllegalStateException("Insufficient stock for product '" + product.getName() +
                        "'. Available: " + product.getAvailableQuantity() + ", Requested: " + itemRequest.getQuantity());
            }

            // Reserve stock
            product.reserveStock(itemRequest.getQuantity());
            productRepository.save(product);

            // Create order item
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setProductSku(product.getSku());
            orderItem.setProductName(product.getName());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setUnitPrice(product.getUnitPrice());
            orderItem.setDiscountPercent(itemRequest.getDiscountPercent() != null ? itemRequest.getDiscountPercent() : BigDecimal.ZERO);
            orderItem.setTaxRate(product.getTaxRate() != null ? product.getTaxRate() : BigDecimal.ZERO);
            orderItem.calculateAmounts();

            orderItems.add(orderItem);
        }

        orderItemRepository.saveAll(orderItems);
        savedOrder.setOrderItems(orderItems);

        // Calculate order totals
        savedOrder.calculateTotals();
        savedOrder = orderRepository.save(savedOrder);

        // Record initial status history
        OrderStatusHistory history = new OrderStatusHistory(savedOrder, null, OrderStatus.PENDING, "system", "Order created");
        orderStatusHistoryRepository.save(history);

        // Award loyalty points (1 point per dollar spent)
        int loyaltyPointsEarned = savedOrder.getTotalAmount().intValue();
        customer.addLoyaltyPoints(loyaltyPointsEarned);
        customerRepository.save(customer);

        log.info("Order created successfully. Order number: {}, Total: {} {}", savedOrder.getOrderNumber(),
                savedOrder.getTotalAmount(), savedOrder.getCurrency());
        return convertToResponse(savedOrder);
    }

    @Transactional(readOnly = true)
    public Optional<OrderResponseDto> getOrderById(Long id) {
        log.debug("Fetching order by ID: {}", id);
        return orderRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Optional<OrderResponseDto> getOrderByOrderNumber(String orderNumber) {
        log.debug("Fetching order by order number: {}", orderNumber);
        return orderRepository.findByOrderNumber(orderNumber).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        log.debug("Fetching all orders with pageable: {}", pageable);
        return orderRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrdersByCustomer(Long customerId, Pageable pageable) {
        log.debug("Fetching orders for customer ID: {}", customerId);
        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException("Customer not found with ID: " + customerId);
        }
        return orderRepository.findByCustomerId(customerId, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrdersByStatus(OrderStatus status, Pageable pageable) {
        log.debug("Fetching orders by status: {}", status);
        return orderRepository.findByStatus(status, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        log.debug("Fetching orders between {} and {}", startDate, endDate);
        return orderRepository.findByDateRange(startDate, endDate, pageable).map(this::convertToResponse);
    }

    @Transactional
    public OrderResponseDto updateOrder(Long id, UpdateOrderRequestDto request) {
        log.info("Updating order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (order.getStatus().isTerminal()) {
            throw new IllegalStateException("Cannot update order in terminal status: " + order.getStatus());
        }

        if (request.getPaymentMethod() != null) order.setPaymentMethod(request.getPaymentMethod());
        if (request.getShippingMethod() != null) {
            order.setShippingMethod(request.getShippingMethod());
            order.setShippingCost(calculateShippingCost(request.getShippingMethod()));
            order.calculateTotals();
        }
        if (request.getShippingAddressLine1() != null) order.setShippingAddressLine1(request.getShippingAddressLine1());
        if (request.getShippingAddressLine2() != null) order.setShippingAddressLine2(request.getShippingAddressLine2());
        if (request.getShippingCity() != null) order.setShippingCity(request.getShippingCity());
        if (request.getShippingState() != null) order.setShippingState(request.getShippingState());
        if (request.getShippingPostalCode() != null) order.setShippingPostalCode(request.getShippingPostalCode());
        if (request.getShippingCountry() != null) order.setShippingCountry(request.getShippingCountry());
        if (request.getNotes() != null) order.setNotes(request.getNotes());

        Order updatedOrder = orderRepository.save(order);
        log.info("Order updated successfully with ID: {}", updatedOrder.getId());
        return convertToResponse(updatedOrder);
    }

    @Transactional
    public OrderResponseDto confirmOrder(Long id, String confirmedBy) {
        log.info("Confirming order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Order can only be confirmed from PENDING status. Current status: " + order.getStatus());
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(OrderStatus.CONFIRMED);
        Order updatedOrder = orderRepository.save(order);

        // Record status history
        OrderStatusHistory history = new OrderStatusHistory(updatedOrder, previousStatus, OrderStatus.CONFIRMED,
                confirmedBy, "Order confirmed");
        orderStatusHistoryRepository.save(history);

        log.info("Order confirmed successfully. Order number: {}", updatedOrder.getOrderNumber());
        return convertToResponse(updatedOrder);
    }

    @Transactional
    public OrderResponseDto processOrder(Long id, String processedBy) {
        log.info("Processing order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Order can only be processed from CONFIRMED status. Current status: " + order.getStatus());
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(OrderStatus.PROCESSING);
        Order updatedOrder = orderRepository.save(order);

        // Record status history
        OrderStatusHistory history = new OrderStatusHistory(updatedOrder, previousStatus, OrderStatus.PROCESSING,
                processedBy, "Order is being processed");
        orderStatusHistoryRepository.save(history);

        log.info("Order processing started. Order number: {}", updatedOrder.getOrderNumber());
        return convertToResponse(updatedOrder);
    }

    @Transactional
    public OrderResponseDto shipOrder(Long id, ShipOrderRequestDto request) {
        log.info("Shipping order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (!order.canBeShipped()) {
            throw new IllegalStateException("Order cannot be shipped from status: " + order.getStatus() +
                    ". Order must be in CONFIRMED or PROCESSING status.");
        }

        // Deduct stock from products (stock was reserved during order creation)
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.deductStock(item.getQuantity());
            productRepository.save(product);
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(OrderStatus.SHIPPED);
        order.setTrackingNumber(request.getTrackingNumber());
        order.setShippedAt(LocalDateTime.now());
        if (request.getShippingMethod() != null) order.setShippingMethod(request.getShippingMethod());
        if (request.getEstimatedDeliveryDate() != null) order.setEstimatedDeliveryDate(request.getEstimatedDeliveryDate());

        Order updatedOrder = orderRepository.save(order);

        // Record status history
        OrderStatusHistory history = new OrderStatusHistory(updatedOrder, previousStatus, OrderStatus.SHIPPED,
                request.getShippedBy(), "Order shipped. Tracking: " + request.getTrackingNumber());
        orderStatusHistoryRepository.save(history);

        log.info("Order shipped successfully. Order number: {}, Tracking: {}", updatedOrder.getOrderNumber(), request.getTrackingNumber());
        return convertToResponse(updatedOrder);
    }

    @Transactional
    public OrderResponseDto deliverOrder(Long id, String deliveredBy) {
        log.info("Marking order as delivered with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (!order.canBeDelivered()) {
            throw new IllegalStateException("Order cannot be delivered from status: " + order.getStatus() +
                    ". Order must be in SHIPPED status.");
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(OrderStatus.DELIVERED);
        order.setDeliveredAt(LocalDateTime.now());
        order.setPaymentStatus(PaymentStatus.PAID);

        Order updatedOrder = orderRepository.save(order);

        // Record status history
        OrderStatusHistory history = new OrderStatusHistory(updatedOrder, previousStatus, OrderStatus.DELIVERED,
                deliveredBy, "Order delivered successfully");
        orderStatusHistoryRepository.save(history);

        log.info("Order delivered successfully. Order number: {}", updatedOrder.getOrderNumber());
        return convertToResponse(updatedOrder);
    }

    @Transactional
    public OrderResponseDto cancelOrder(Long id, CancelOrderRequestDto request) {
        log.info("Cancelling order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (!order.canBeCancelled()) {
            throw new IllegalStateException("Order cannot be cancelled from status: " + order.getStatus() +
                    ". Only PENDING, CONFIRMED, or PROCESSING orders can be cancelled.");
        }

        // Release reserved stock
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.releaseReservedStock(item.getQuantity());
            productRepository.save(product);
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(OrderStatus.CANCELLED);
        order.setCancelledAt(LocalDateTime.now());
        order.setCancellationReason(request.getReason());
        order.setPaymentStatus(PaymentStatus.CANCELLED);

        Order updatedOrder = orderRepository.save(order);

        // Record status history
        OrderStatusHistory history = new OrderStatusHistory(updatedOrder, previousStatus, OrderStatus.CANCELLED,
                request.getRequestedBy(), "Order cancelled. Reason: " + request.getReason());
        orderStatusHistoryRepository.save(history);

        // Reverse loyalty points earned when order was placed
        Customer customer = order.getCustomer();
        int loyaltyPointsToReverse = order.getTotalAmount().intValue();
        if (customer.getLoyaltyPoints() >= loyaltyPointsToReverse) {
            customer.deductLoyaltyPoints(loyaltyPointsToReverse);
            customerRepository.save(customer);
        }

        log.info("Order cancelled successfully. Order number: {}", updatedOrder.getOrderNumber());
        return convertToResponse(updatedOrder);
    }

    @Transactional
    public OrderResponseDto updateOrderStatus(Long id, OrderStatusUpdateRequestDto request) {
        log.info("Updating status for order ID: {} to {}", id, request.getNewStatus());

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (order.getStatus().isTerminal()) {
            throw new IllegalStateException("Cannot update status of order in terminal status: " + order.getStatus());
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(request.getNewStatus());

        Order updatedOrder = orderRepository.save(order);

        // Record status history
        OrderStatusHistory history = new OrderStatusHistory(updatedOrder, previousStatus, request.getNewStatus(),
                request.getChangedBy(), request.getComment());
        orderStatusHistoryRepository.save(history);

        log.info("Order status updated from {} to {} for order ID: {}", previousStatus, request.getNewStatus(), id);
        return convertToResponse(updatedOrder);
    }

    @Transactional
    public void deleteOrder(Long id) {
        log.info("Deleting order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (!order.getStatus().isTerminal()) {
            throw new IllegalStateException("Cannot delete an active order. Cancel the order first.");
        }

        orderRepository.deleteById(id);
        log.info("Order deleted successfully with ID: {}", id);
    }

    @Transactional(readOnly = true)
    public List<OrderStatusHistoryResponseDto> getOrderStatusHistory(Long orderId) {
        log.debug("Fetching status history for order ID: {}", orderId);
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
        return orderStatusHistoryRepository.findByOrderIdOrderByCreatedAtAsc(orderId)
                .stream()
                .map(this::convertHistoryToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BigDecimal calculateRevenue(LocalDateTime startDate, LocalDateTime endDate) {
        log.debug("Calculating revenue between {} and {}", startDate, endDate);
        return orderRepository.calculateTotalRevenue(startDate, endDate);
    }

    private String generateOrderNumber() {
        String datePrefix = "ORD-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-";
        Long maxId = orderRepository.findMaxOrderIdByPrefix(datePrefix);
        long nextSequence = (maxId != null ? maxId : 0L) + 1;
        return datePrefix + String.format("%05d", nextSequence);
    }

    private BigDecimal calculateShippingCost(ShippingMethod shippingMethod) {
        if (shippingMethod == null) return new BigDecimal("9.99");
        return switch (shippingMethod) {
            case STANDARD -> new BigDecimal("9.99");
            case EXPRESS -> new BigDecimal("19.99");
            case OVERNIGHT -> new BigDecimal("39.99");
            case PICKUP -> BigDecimal.ZERO;
            case FREE -> BigDecimal.ZERO;
        };
    }

    private OrderResponseDto convertToResponse(Order order) {
        OrderResponseDto response = new OrderResponseDto();
        response.setId(order.getId());
        response.setOrderNumber(order.getOrderNumber());
        response.setCustomerId(order.getCustomer() != null ? order.getCustomer().getId() : null);
        response.setCustomerName(order.getCustomer() != null ? order.getCustomer().getFullName() : null);
        response.setCustomerEmail(order.getCustomer() != null ? order.getCustomer().getEmail() : null);
        response.setStatus(order.getStatus());
        response.setStatusDisplayName(order.getStatus() != null ? order.getStatus().getDisplayName() : null);
        response.setPaymentStatus(order.getPaymentStatus());
        response.setPaymentStatusDisplayName(order.getPaymentStatus() != null ? order.getPaymentStatus().getDisplayName() : null);
        response.setPaymentMethod(order.getPaymentMethod());
        response.setPaymentMethodDisplayName(order.getPaymentMethod() != null ? order.getPaymentMethod().getDisplayName() : null);
        response.setShippingMethod(order.getShippingMethod());
        response.setShippingMethodDisplayName(order.getShippingMethod() != null ? order.getShippingMethod().getDisplayName() : null);
        response.setSubtotal(order.getSubtotal());
        response.setDiscountAmount(order.getDiscountAmount());
        response.setTaxAmount(order.getTaxAmount());
        response.setShippingCost(order.getShippingCost());
        response.setTotalAmount(order.getTotalAmount());
        response.setCurrency(order.getCurrency());
        response.setCouponCode(order.getCouponCode());
        response.setShippingAddressLine1(order.getShippingAddressLine1());
        response.setShippingAddressLine2(order.getShippingAddressLine2());
        response.setShippingCity(order.getShippingCity());
        response.setShippingState(order.getShippingState());
        response.setShippingPostalCode(order.getShippingPostalCode());
        response.setShippingCountry(order.getShippingCountry());
        response.setTrackingNumber(order.getTrackingNumber());
        response.setEstimatedDeliveryDate(order.getEstimatedDeliveryDate());
        response.setShippedAt(order.getShippedAt());
        response.setDeliveredAt(order.getDeliveredAt());
        response.setCancelledAt(order.getCancelledAt());
        response.setCancellationReason(order.getCancellationReason());
        response.setNotes(order.getNotes());
        response.setTotalItemCount(order.getTotalItemCount());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());

        if (order.getOrderItems() != null) {
            response.setItems(order.getOrderItems().stream()
                    .map(this::convertItemToResponse)
                    .collect(Collectors.toList()));
        }

        return response;
    }

    private OrderItemResponseDto convertItemToResponse(OrderItem item) {
        OrderItemResponseDto response = new OrderItemResponseDto();
        response.setId(item.getId());
        response.setProductId(item.getProduct() != null ? item.getProduct().getId() : null);
        response.setProductSku(item.getProductSku());
        response.setProductName(item.getProductName());
        response.setQuantity(item.getQuantity());
        response.setUnitPrice(item.getUnitPrice());
        response.setDiscountPercent(item.getDiscountPercent());
        response.setDiscountAmount(item.getDiscountAmount());
        response.setTaxRate(item.getTaxRate());
        response.setTaxAmount(item.getTaxAmount());
        response.setLineTotal(item.getLineTotal());
        response.setLineTotalWithTax(item.getLineTotalWithTax());
        response.setCreatedAt(item.getCreatedAt());
        return response;
    }

    private OrderStatusHistoryResponseDto convertHistoryToResponse(OrderStatusHistory history) {
        OrderStatusHistoryResponseDto response = new OrderStatusHistoryResponseDto();
        response.setId(history.getId());
        response.setOrderId(history.getOrder() != null ? history.getOrder().getId() : null);
        response.setPreviousStatus(history.getPreviousStatus());
        response.setPreviousStatusDisplayName(history.getPreviousStatus() != null ? history.getPreviousStatus().getDisplayName() : null);
        response.setNewStatus(history.getNewStatus());
        response.setNewStatusDisplayName(history.getNewStatus() != null ? history.getNewStatus().getDisplayName() : null);
        response.setChangedBy(history.getChangedBy());
        response.setComment(history.getComment());
        response.setCreatedAt(history.getCreatedAt());
        return response;
    }
}
