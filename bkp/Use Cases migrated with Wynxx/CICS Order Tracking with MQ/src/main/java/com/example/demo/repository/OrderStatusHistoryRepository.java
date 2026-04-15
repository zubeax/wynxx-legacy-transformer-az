package com.example.demo.repository;

import com.example.demo.entity.OrderStatusHistory;
import com.example.demo.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Long> {

    List<OrderStatusHistory> findByOrderIdOrderByCreatedAtAsc(Long orderId);

    Page<OrderStatusHistory> findByOrderId(Long orderId, Pageable pageable);

    @Query("SELECT h FROM OrderStatusHistory h WHERE h.order.id = :orderId ORDER BY h.createdAt DESC")
    List<OrderStatusHistory> findLatestHistoryByOrderId(@Param("orderId") Long orderId, Pageable pageable);

    Optional<OrderStatusHistory> findFirstByOrderIdOrderByCreatedAtDesc(Long orderId);

    @Query("SELECT h FROM OrderStatusHistory h WHERE h.order.id = :orderId AND h.newStatus = :status")
    List<OrderStatusHistory> findByOrderIdAndNewStatus(@Param("orderId") Long orderId,
                                                        @Param("status") OrderStatus status);

    long countByOrderId(Long orderId);
}
