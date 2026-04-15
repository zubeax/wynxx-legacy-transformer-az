package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.enums.CustomerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<Customer> findByStatus(CustomerStatus status, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(c.companyName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Customer> findBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.companyName IS NOT NULL AND LOWER(c.companyName) LIKE LOWER(CONCAT('%', :companyName, '%'))")
    Page<Customer> findByCompanyNameContaining(@Param("companyName") String companyName, Pageable pageable);

    List<Customer> findByStatusIn(List<CustomerStatus> statuses);

    @Query("SELECT c FROM Customer c WHERE c.totalSpent >= :minSpent ORDER BY c.totalSpent DESC")
    List<Customer> findTopCustomersBySpending(@Param("minSpent") BigDecimal minSpent, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.totalOrders >= :minOrders ORDER BY c.totalOrders DESC")
    List<Customer> findCustomersByMinOrders(@Param("minOrders") Integer minOrders, Pageable pageable);

    long countByStatus(CustomerStatus status);

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.status = :status")
    long countActiveCustomers(@Param("status") CustomerStatus status);
}
