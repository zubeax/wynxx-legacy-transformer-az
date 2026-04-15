package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.PaymentStatus;
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
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

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

        if (!customer.canPlaceOrders()) {
            throw new IllegalStateException("Customer '" + customer.getFullName() + "' is not allowed to place orders. Status: " + customer.getStatus());
        }

        // Create order
        Order order = new Order();
        order.setOrderNumber(generateOrderNumber());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setPaymentMethod(request.getPaymentMethod());
        order.setShippingMethod(request.getShippingMethod());
        order.setShippingCost(request.getShippingCost() != null ? request.getShippingCost() : BigDecimal.ZERO);
        order.setDiscountAmount(request.getDiscountAmount() != null ? request.getDiscountAmount() : BigDecimal.ZERO);
        order.setCurrencyCode(request.getCurrencyCode() != null ? request.getCurrencyCode() : "USD");
        order.setShippingAddressLine1(request.getShippingAddressLine1());
        order.setShippingAddressLine2(request.getShippingAddressLine2());
        order.setShippingCity(request.getShippingCity());
        order.setShippingState(request.getShippingState());
        order.setShippingPostalCode(request.getShippingPostalCode());
        order.setShippingCountry(request.getShippingCountry());
        order.setBillingAddressLine1(request.getBillingAddressLine1());
        order.setBillingAddressLine2(request.getBillingAddressLine2());
        order.setBillingCity(request.getBillingCity());
        order.setBillingState(request.getBillingState());
        order.setBillingPostalCode(request.getBillingPostalCode());
        order.setBillingCountry(request.getBillingCountry());
        order.setCouponCode(request.getCouponCode());
        order.setCustomerNotes(request.getCustomerNotes());
        order.setInternalNotes(request.getInternalNotes());
        order.setPaidAmount(BigDecimal.ZERO);

        // Process order items
        for (OrderItemRequestDto itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + itemRequest.getProductId()));

            if (!product.isOrderable()) {
                throw new IllegalStateException("Product '" + product.getName() + "' (SKU: " + product.getSku() + ") is not available for ordering. Status: " + product.getStatus());
            }

            if (product.getAvailableQuantity() < itemRequest.getQuantity()) {
                throw new IllegalStateException("Insufficient stock for product '" + product.getName() + "'. Available: " + product.getAvailableQuantity() + ", Requested: " + itemRequest.getQuantity());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setProductSku(product.getSku());
            orderItem.setProductName(product.getName());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setUnitPrice(product.getEffectivePrice());
            orderItem.setTaxRate(product.getTaxRate() != null ? product.getTaxRate() : BigDecimal.ZERO);
            orderItem.setDiscountPercentage(itemRequest.getDiscountPercentage() != null ? itemRequest.getDiscountPercentage() : BigDecimal.ZERO);
            orderItem.setDiscountAmount(BigDecimal.ZERO);
            orderItem.setNotes(itemRequest.getNotes());
            orderItem.calculateTotals();

            order.addOrderItem(orderItem);

            // Reserve stock
            product.reserveStock(itemRequest.getQuantity());
            productRepository.save(product);
        }

        // Calculate order totals
        order.recalculateTotals();

        Order savedOrder = orderRepository.save(order);

        // Record initial status history
        OrderStatusHistory history = new OrderStatusHistory(savedOrder, null, OrderStatus.PENDING, "system", "Order created");
        orderStatusHistoryRepository.save(history);

        // Update customer stats
        customer.incrementOrderStats(savedOrder.getTotalAmount());
        customerRepository.save(customer);

        log.info("Order created successfully with number: {}", savedOrder.getOrderNumber());
        return convertToResponse(savedOrder);
    }

    @Transactional(readOnly = true)
    public Optional<OrderResponseDto> getOrderById(Long id) {
        log.debug("Fetching order with ID: {}", id);
        return orderRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Optional<OrderResponseDto> getOrderByOrderNumber(String orderNumber) {
        log.debug("Fetching order with number: {}", orderNumber);
        return orderRepository.findByOrderNumber(orderNumber).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getAllOrders(Pageable pageable) {
        log.debug("Fetching all orders, page: {}", pageable.getPageNumber());
        return orderRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrdersByCustomer(Long customerId, Pageable pageable) {
        if (!customerRepository.existsById(customerId)) {
            throw new IllegalArgumentException("Customer not found with ID: " + customerId);
        }
        return orderRepository.findByCustomerId(customerId, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrdersByStatus(OrderStatus status, Pageable pageable) {
        return orderRepository.findByStatus(status, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> searchOrders(String searchTerm, Pageable pageable) {
        return orderRepository.findBySearchTerm(searchTerm, pageable).map(this::convertToResponse);
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
        if (request.getShippingMethod() != null) order.setShippingMethod(request.getShippingMethod());
        if (request.getShippingCost() != null) {
            order.setShippingCost(request.getShippingCost());
            order.recalculateTotals();
        }
        if (request.getDiscountAmount() != null) {
            order.setDiscountAmount(request.getDiscountAmount());
            order.recalculateTotals();
        }
        if (request.getShippingAddressLine1() != null) order.setShippingAddressLine1(request.getShippingAddressLine1());
        if (request.getShippingAddressLine2() != null) order.setShippingAddressLine2(request.getShippingAddressLine2());
        if (request.getShippingCity() != null) order.setShippingCity(request.getShippingCity());
        if (request.getShippingState() != null) order.setShippingState(request.getShippingState());
        if (request.getShippingPostalCode() != null) order.setShippingPostalCode(request.getShippingPostalCode());
        if (request.getShippingCountry() != null) order.setShippingCountry(request.getShippingCountry());
        if (request.getBillingAddressLine1() != null) order.setBillingAddressLine1(request.getBillingAddressLine1());
        if (request.getBillingAddressLine2() != null) order.setBillingAddressLine2(request.getBillingAddressLine2());
        if (request.getBillingCity() != null) order.setBillingCity(request.getBillingCity());
        if (request.getBillingState() != null) order.setBillingState(request.getBillingState());
        if (request.getBillingPostalCode() != null) order.setBillingPostalCode(request.getBillingPostalCode());
        if (request.getBillingCountry() != null) order.setBillingCountry(request.getBillingCountry());
        if (request.getPaymentReference() != null) order.setPaymentReference(request.getPaymentReference());
        if (request.getCustomerNotes() != null) order.setCustomerNotes(request.getCustomerNotes());
        if (request.getInternalNotes() != null) order.setInternalNotes(request.getInternalNotes());

        Order updatedOrder = orderRepository.save(order);
        log.info("Order updated successfully with ID: {}", updatedOrder.getId());
        return convertToResponse(updatedOrder);
    }

    @Transactional
    public OrderResponseDto confirmOrder(Long id, String confirmedBy) {
        log.info("Confirming order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (!order.canBeConfirmed()) {
            throw new IllegalStateException("Order cannot be confirmed in current status: " + order.getStatus());
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        OrderStatusHistory history = new OrderStatusHistory(savedOrder, previousStatus, OrderStatus.CONFIRMED,
                confirmedBy, "Order confirmed");
        orderStatusHistoryRepository.save(history);

        log.info("Order confirmed successfully with ID: {}", id);
        return convertToResponse(savedOrder);
    }

    @Transactional
    public OrderResponseDto processOrder(Long id, String processedBy) {
        log.info("Processing order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Order must be CONFIRMED before processing. Current status: " + order.getStatus());
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(OrderStatus.PROCESSING);

        Order savedOrder = orderRepository.save(order);

        OrderStatusHistory history = new OrderStatusHistory(savedOrder, previousStatus, OrderStatus.PROCESSING,
                processedBy, "Order is being processed");
        orderStatusHistoryRepository.save(history);

        log.info("Order processing started for ID: {}", id);
        return convertToResponse(savedOrder);
    }

    @Transactional
    public OrderResponseDto shipOrder(Long id, ShipOrderRequestDto request) {
        log.info("Shipping order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (!order.canBeShipped()) {
            throw new IllegalStateException("Order cannot be shipped in current status: " + order.getStatus());
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(OrderStatus.SHIPPED);
        order.setShippedAt(LocalDateTime.now());
        order.setTrackingNumber(request.getTrackingNumber());
        order.setCarrier(request.getCarrier());
        if (request.getShippingMethod() != null) order.setShippingMethod(request.getShippingMethod());
        order.setEstimatedDeliveryDate(request.getEstimatedDeliveryDate());

        // Deduct stock from products
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.deductStock(item.getQuantity());
            productRepository.save(product);
        }

        Order savedOrder = orderRepository.save(order);

        OrderStatusHistory history = new OrderStatusHistory(savedOrder, previousStatus, OrderStatus.SHIPPED,
                "system", "Order shipped. Tracking: " + request.getTrackingNumber());
        orderStatusHistoryRepository.save(history);

        log.info("Order shipped successfully with ID: {}", id);
        return convertToResponse(savedOrder);
    }

    @Transactional
    public OrderResponseDto deliverOrder(Long id, String deliveredBy) {
        log.info("Marking order as delivered with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new IllegalStateException("Order must be SHIPPED before marking as delivered. Current status: " + order.getStatus());
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(OrderStatus.DELIVERED);
        order.setActualDeliveryDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        OrderStatusHistory history = new OrderStatusHistory(savedOrder, previousStatus, OrderStatus.DELIVERED,
                deliveredBy, "Order delivered successfully");
        orderStatusHistoryRepository.save(history);

        log.info("Order delivered successfully with ID: {}", id);
        return convertToResponse(savedOrder);
    }

    @Transactional
    public OrderResponseDto cancelOrder(Long id, CancelOrderRequestDto request) {
        log.info("Cancelling order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (!order.canBeCancelled()) {
            throw new IllegalStateException("Order cannot be cancelled in current status: " + order.getStatus());
        }

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(OrderStatus.CANCELLED);
        order.setCancelledAt(LocalDateTime.now());
        order.setCancellationReason(request.getReason());

        // Restore stock if requested
        if (Boolean.TRUE.equals(request.getRestoreStock())) {
            for (OrderItem item : order.getOrderItems()) {
                Product product = item.getProduct();
                product.releaseReservedStock(item.getQuantity());
                product.addStock(item.getQuantity());
                productRepository.save(product);
            }
        }

        Order savedOrder = orderRepository.save(order);

        // Update customer stats
        Customer customer = order.getCustomer();
        customer.decrementOrderStats(order.getTotalAmount());
        customerRepository.save(customer);

        OrderStatusHistory history = new OrderStatusHistory(savedOrder, previousStatus, OrderStatus.CANCELLED,
                request.getCancelledBy(), request.getReason());
        orderStatusHistoryRepository.save(history);

        log.info("Order cancelled successfully with ID: {}", id);
        return convertToResponse(savedOrder);
    }

    @Transactional
    public OrderResponseDto updateOrderStatus(Long id, OrderStatusUpdateRequestDto request) {
        log.info("Updating order status for ID: {} to {}", id, request.getNewStatus());

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        OrderStatus previousStatus = order.getStatus();
        order.setStatus(request.getNewStatus());

        // Handle specific status transitions
        switch (request.getNewStatus()) {
            case CONFIRMED -> order.setConfirmedAt(LocalDateTime.now());
            case SHIPPED -> order.setShippedAt(LocalDateTime.now());
            case DELIVERED -> order.setActualDeliveryDate(LocalDateTime.now());
            case CANCELLED -> {
                order.setCancelledAt(LocalDateTime.now());
                order.setCancellationReason(request.getReason());
            }
            default -> { /* no additional action */ }
        }

        Order savedOrder = orderRepository.save(order);

        OrderStatusHistory history = new OrderStatusHistory(savedOrder, previousStatus, request.getNewStatus(),
                request.getChangedBy(), request.getReason());
        history.setNotes(request.getNotes());
        orderStatusHistoryRepository.save(history);

        log.info("Order status updated for ID: {} from {} to {}", id, previousStatus, request.getNewStatus());
        return convertToResponse(savedOrder);
    }

    @Transactional
    public OrderResponseDto recordPayment(Long id, BigDecimal amount, String paymentReference) {
        log.info("Recording payment for order ID: {}, amount: {}", id, amount);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }

        BigDecimal currentPaid = order.getPaidAmount() == null ? BigDecimal.ZERO : order.getPaidAmount();
        BigDecimal newPaidAmount = currentPaid.add(amount);

        order.setPaidAmount(newPaidAmount);
        if (paymentReference != null) order.setPaymentReference(paymentReference);

        if (newPaidAmount.compareTo(order.getTotalAmount()) >= 0) {
            order.setPaymentStatus(PaymentStatus.PAID);
        } else {
            order.setPaymentStatus(PaymentStatus.PARTIALLY_PAID);
        }

        Order savedOrder = orderRepository.save(order);
        log.info("Payment recorded for order ID: {}. Total paid: {}", id, newPaidAmount);
        return convertToResponse(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderStatusHistoryResponseDto> getOrderStatusHistory(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Order not found with ID: " + id);
        }
        return orderStatusHistoryRepository.findByOrderIdOrderByCreatedAtAsc(id)
                .stream()
                .map(this::convertHistoryToResponse)
                .toList();
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

    private String generateOrderNumber() {
        String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = orderRepository.count() + 1;
        return String.format("ORD-%s-%06d", datePart, count);
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
        response.setPaidAmount(order.getPaidAmount());
        response.setOutstandingBalance(order.getOutstandingBalance());
        response.setCurrencyCode(order.getCurrencyCode());
        response.setShippingAddressLine1(order.getShippingAddressLine1());
        response.setShippingAddressLine2(order.getShippingAddressLine2());
        response.setShippingCity(order.getShippingCity());
        response.setShippingState(order.getShippingState());
        response.setShippingPostalCode(order.getShippingPostalCode());
        response.setShippingCountry(order.getShippingCountry());
        response.setBillingAddressLine1(order.getBillingAddressLine1());
        response.setBillingAddressLine2(order.getBillingAddressLine2());
        response.setBillingCity(order.getBillingCity());
        response.setBillingState(order.getBillingState());
        response.setBillingPostalCode(order.getBillingPostalCode());
        response.setBillingCountry(order.getBillingCountry());
        response.setTrackingNumber(order.getTrackingNumber());
        response.setCarrier(order.getCarrier());
        response.setEstimatedDeliveryDate(order.getEstimatedDeliveryDate());
        response.setActualDeliveryDate(order.getActualDeliveryDate());
        response.setShippedAt(order.getShippedAt());
        response.setConfirmedAt(order.getConfirmedAt());
        response.setCancelledAt(order.getCancelledAt());
        response.setCancellationReason(order.getCancellationReason());
        response.setCouponCode(order.getCouponCode());
        response.setPaymentReference(order.getPaymentReference());
        response.setCustomerNotes(order.getCustomerNotes());
        response.setInternalNotes(order.getInternalNotes());
        response.setCanBeCancelled(order.canBeCancelled());
        response.setCanBeShipped(order.canBeShipped());
        response.setFullyPaid(order.isFullyPaid());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());

        if (order.getOrderItems() != null) {
            response.setItems(order.getOrderItems().stream()
                    .map(this::convertItemToResponse)
                    .toList());
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
        response.setDiscountPercentage(item.getDiscountPercentage());
        response.setDiscountAmount(item.getDiscountAmount());
        response.setTaxRate(item.getTaxRate());
        response.setTaxAmount(item.getTaxAmount());
        response.setLineTotal(item.getLineTotal());
        response.setSubtotal(item.getSubtotal());
        response.setNotes(item.getNotes());
        response.setCreatedAt(item.getCreatedAt());
        response.setUpdatedAt(item.getUpdatedAt());
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
        response.setReason(history.getReason());
        response.setNotes(history.getNotes());
        response.setCreatedAt(history.getCreatedAt());
        return response;
    }
}
