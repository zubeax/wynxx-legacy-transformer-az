package com.example.demo.repository;

import com.example.demo.entity.OrderStatusHistory;
import com.example.demo.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Long> {

    List<OrderStatusHistory> findByOrderIdOrderByCreatedAtAsc(Long orderId);

    List<OrderStatusHistory> findByOrderIdOrderByCreatedAtDesc(Long orderId);

    @Query("SELECT osh FROM OrderStatusHistory osh WHERE osh.order.id = :orderId AND osh.newStatus = :status ORDER BY osh.createdAt DESC")
    Optional<OrderStatusHistory> findLatestStatusChangeForOrder(@Param("orderId") Long orderId,
                                                                 @Param("status") OrderStatus status);

    @Query("SELECT osh FROM OrderStatusHistory osh WHERE osh.order.id = :orderId ORDER BY osh.createdAt DESC")
    List<OrderStatusHistory> findLatestHistoryByOrderId(@Param("orderId") Long orderId);

    long countByOrderId(Long orderId);
}
