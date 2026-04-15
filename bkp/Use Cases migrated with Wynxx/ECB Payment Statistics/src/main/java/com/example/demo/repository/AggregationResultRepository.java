package com.example.demo.repository;

import com.example.demo.entity.AggregationResult;
import com.example.demo.enums.AggregationResultStatus;
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
public interface AggregationResultRepository extends JpaRepository<AggregationResult, Long> {

    Page<AggregationResult> findByAggregationJobId(Long aggregationJobId, Pageable pageable);

    Page<AggregationResult> findByDataSourceId(Long dataSourceId, Pageable pageable);

    Page<AggregationResult> findByStatus(AggregationResultStatus status, Pageable pageable);

    List<AggregationResult> findByAggregationJobIdOrderByAggregatedAtDesc(Long aggregationJobId);

    @Query("SELECT ar FROM AggregationResult ar WHERE ar.aggregationJob.id = :jobId " +
           "ORDER BY ar.aggregatedAt DESC")
    Optional<AggregationResult> findLatestByJobId(@Param("jobId") Long jobId, Pageable pageable);

    @Query("SELECT ar FROM AggregationResult ar WHERE ar.dataSource.id = :dataSourceId " +
           "AND ar.aggregatedAt >= :since ORDER BY ar.aggregatedAt DESC")
    List<AggregationResult> findByDataSourceIdSince(@Param("dataSourceId") Long dataSourceId,
                                                     @Param("since") LocalDateTime since);

    @Query("SELECT ar FROM AggregationResult ar WHERE ar.aggregatedAt >= :since AND ar.status = 'FAILED'")
    List<AggregationResult> findFailedResultsSince(@Param("since") LocalDateTime since);

    @Query("SELECT SUM(ar.recordCount) FROM AggregationResult ar WHERE ar.aggregatedAt >= :since " +
           "AND ar.status IN ('SUCCESS', 'PARTIAL')")
    Long sumRecordCountSince(@Param("since") LocalDateTime since);

    @Query("SELECT SUM(ar.dataSizeBytes) FROM AggregationResult ar WHERE ar.aggregatedAt >= :since " +
           "AND ar.status IN ('SUCCESS', 'PARTIAL')")
    Long sumDataSizeSince(@Param("since") LocalDateTime since);

    @Query("SELECT ar FROM AggregationResult ar WHERE ar.checksum = :checksum")
    Optional<AggregationResult> findByChecksum(@Param("checksum") String checksum);

    long countByStatus(AggregationResultStatus status);

    long countByAggregationJobId(Long aggregationJobId);

    @Query("SELECT ar FROM AggregationResult ar WHERE ar.aggregatedAt BETWEEN :start AND :end")
    Page<AggregationResult> findByAggregatedAtBetween(@Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end,
                                                       Pageable pageable);
}
