package com.example.demo.repository;

import com.example.demo.entity.AggregationMetric;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AggregationMetricRepository extends JpaRepository<AggregationMetric, Long> {

    Page<AggregationMetric> findByDataPipelineId(Long dataPipelineId, Pageable pageable);

    Page<AggregationMetric> findByAggregationJobId(Long aggregationJobId, Pageable pageable);

    List<AggregationMetric> findByMetricName(String metricName);

    List<AggregationMetric> findByMetricCategory(String metricCategory);

    @Query("SELECT am FROM AggregationMetric am WHERE am.metricName = :metricName " +
           "AND am.recordedAt >= :since ORDER BY am.recordedAt DESC")
    List<AggregationMetric> findByMetricNameSince(@Param("metricName") String metricName,
                                                   @Param("since") LocalDateTime since);

    @Query("SELECT am FROM AggregationMetric am WHERE am.dataPipeline.id = :pipelineId " +
           "AND am.recordedAt BETWEEN :start AND :end ORDER BY am.recordedAt ASC")
    List<AggregationMetric> findByPipelineIdAndPeriod(@Param("pipelineId") Long pipelineId,
                                                       @Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end);

    @Query("SELECT am FROM AggregationMetric am WHERE am.aggregationJob.id = :jobId " +
           "ORDER BY am.recordedAt DESC")
    List<AggregationMetric> findByJobIdOrderByRecordedAtDesc(@Param("jobId") Long jobId);

    @Query("SELECT AVG(am.metricValue) FROM AggregationMetric am WHERE am.metricName = :metricName " +
           "AND am.recordedAt >= :since")
    BigDecimal findAverageMetricValueSince(@Param("metricName") String metricName,
                                           @Param("since") LocalDateTime since);

    @Query("SELECT MAX(am.metricValue) FROM AggregationMetric am WHERE am.metricName = :metricName " +
           "AND am.recordedAt >= :since")
    BigDecimal findMaxMetricValueSince(@Param("metricName") String metricName,
                                       @Param("since") LocalDateTime since);

    @Query("SELECT MIN(am.metricValue) FROM AggregationMetric am WHERE am.metricName = :metricName " +
           "AND am.recordedAt >= :since")
    BigDecimal findMinMetricValueSince(@Param("metricName") String metricName,
                                       @Param("since") LocalDateTime since);

    @Query("SELECT DISTINCT am.metricName FROM AggregationMetric am ORDER BY am.metricName")
    List<String> findAllDistinctMetricNames();

    @Query("SELECT DISTINCT am.metricCategory FROM AggregationMetric am WHERE am.metricCategory IS NOT NULL ORDER BY am.metricCategory")
    List<String> findAllDistinctMetricCategories();

    @Query("SELECT am FROM AggregationMetric am WHERE am.recordedAt BETWEEN :start AND :end")
    Page<AggregationMetric> findByRecordedAtBetween(@Param("start") LocalDateTime start,
                                                     @Param("end") LocalDateTime end,
                                                     Pageable pageable);
}
