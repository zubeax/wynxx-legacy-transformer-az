package com.example.demo.repository;

import com.example.demo.entity.DataPipeline;
import com.example.demo.enums.PipelineStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DataPipelineRepository extends JpaRepository<DataPipeline, Long> {

    Optional<DataPipeline> findByName(String name);

    boolean existsByName(String name);

    Page<DataPipeline> findByStatus(PipelineStatus status, Pageable pageable);

    List<DataPipeline> findByStatusAndIsActiveTrue(PipelineStatus status);

    List<DataPipeline> findByIsActiveTrue();

    @Query("SELECT dp FROM DataPipeline dp WHERE dp.status = 'ACTIVE' AND dp.isActive = true " +
           "AND dp.nextScheduledAt <= :now")
    List<DataPipeline> findPipelinesDueForExecution(@Param("now") LocalDateTime now);

    @Query("SELECT dp FROM DataPipeline dp WHERE LOWER(dp.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(dp.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<DataPipeline> findByNameOrDescriptionContaining(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT dp FROM DataPipeline dp WHERE dp.owner = :owner")
    Page<DataPipeline> findByOwner(@Param("owner") String owner, Pageable pageable);

    @Query("SELECT dp FROM DataPipeline dp WHERE dp.tags LIKE CONCAT('%', :tag, '%')")
    List<DataPipeline> findByTag(@Param("tag") String tag);

    long countByStatus(PipelineStatus status);

    @Query("SELECT dp FROM DataPipeline dp WHERE dp.failedExecutions > 0 " +
           "AND dp.lastExecutedAt >= :since ORDER BY dp.failedExecutions DESC")
    List<DataPipeline> findPipelinesWithRecentFailures(@Param("since") LocalDateTime since);

    @Query("SELECT dp FROM DataPipeline dp WHERE dp.status = 'ACTIVE' AND dp.isActive = true " +
           "ORDER BY dp.successfulExecutions / NULLIF(dp.totalExecutions, 0) ASC")
    List<DataPipeline> findPipelinesBySuccessRateAsc(Pageable pageable);
}
