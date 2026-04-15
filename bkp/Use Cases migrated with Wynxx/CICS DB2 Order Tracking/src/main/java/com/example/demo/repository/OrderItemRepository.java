package com.example.demo.repository;

import com.example.demo.entity.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(Long orderId);

    List<OrderItem> findByProductId(Long productId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId ORDER BY oi.createdAt ASC")
    List<OrderItem> findByOrderIdOrderByCreatedAt(@Param("orderId") Long orderId);

    @Query("SELECT oi.product.id, SUM(oi.quantity) as totalQty FROM OrderItem oi " +
           "WHERE oi.order.status NOT IN ('CANCELLED', 'REFUNDED') " +
           "GROUP BY oi.product.id ORDER BY totalQty DESC")
    List<Object[]> findTopSellingProducts(Pageable pageable);

    @Query("SELECT SUM(oi.quantity) FROM OrderItem oi WHERE oi.product.id = :productId " +
           "AND oi.order.status NOT IN ('CANCELLED', 'REFUNDED')")
    Long sumQuantitySoldByProduct(@Param("productId") Long productId);

    void deleteByOrderId(Long orderId);
}
