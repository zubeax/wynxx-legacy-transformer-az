package com.example.demo.repository;

import com.example.demo.entity.AggregationJob;
import com.example.demo.enums.JobStatus;
import com.example.demo.enums.JobType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AggregationJobRepository extends JpaRepository<AggregationJob, Long> {

    Page<AggregationJob> findByStatus(JobStatus status, Pageable pageable);

    Page<AggregationJob> findByDataSourceId(Long dataSourceId, Pageable pageable);

    Page<AggregationJob> findByDataPipelineId(Long dataPipelineId, Pageable pageable);

    Page<AggregationJob> findByJobType(JobType jobType, Pageable pageable);

    List<AggregationJob> findByStatusIn(List<JobStatus> statuses);

    @Query("SELECT aj FROM AggregationJob aj WHERE aj.status = 'RUNNING' OR aj.status = 'RETRYING'")
    List<AggregationJob> findActiveJobs();

    @Query("SELECT aj FROM AggregationJob aj WHERE aj.status = 'FAILED' " +
           "AND aj.retryOnFailure = true AND aj.retryCount < aj.maxRetries")
    List<AggregationJob> findRetryableFailedJobs();

    @Query("SELECT aj FROM AggregationJob aj WHERE aj.status = 'PENDING' " +
           "ORDER BY aj.priority DESC, aj.createdAt ASC")
    List<AggregationJob> findPendingJobsByPriority(Pageable pageable);

    @Query("SELECT aj FROM AggregationJob aj WHERE aj.dataSource.id = :dataSourceId " +
           "AND aj.status = 'COMPLETED' ORDER BY aj.completedAt DESC")
    List<AggregationJob> findLatestCompletedJobsByDataSource(@Param("dataSourceId") Long dataSourceId, Pageable pageable);

    @Query("SELECT aj FROM AggregationJob aj WHERE aj.startedAt >= :since AND aj.status = 'FAILED'")
    List<AggregationJob> findFailedJobsSince(@Param("since") LocalDateTime since);

    @Query("SELECT aj FROM AggregationJob aj WHERE aj.startedAt >= :since AND aj.status = 'COMPLETED'")
    List<AggregationJob> findCompletedJobsSince(@Param("since") LocalDateTime since);

    @Query("SELECT aj FROM AggregationJob aj WHERE aj.status = 'RUNNING' " +
           "AND aj.startedAt < :timeout")
    List<AggregationJob> findTimedOutJobs(@Param("timeout") LocalDateTime timeout);

    @Query("SELECT SUM(aj.recordsProcessed) FROM AggregationJob aj WHERE aj.startedAt >= :since AND aj.status = 'COMPLETED'")
    Long sumRecordsProcessedSince(@Param("since") LocalDateTime since);

    @Query("SELECT SUM(aj.recordsFailed) FROM AggregationJob aj WHERE aj.startedAt >= :since")
    Long sumRecordsFailedSince(@Param("since") LocalDateTime since);

    @Query("SELECT AVG(aj.executionTimeMs) FROM AggregationJob aj WHERE aj.status = 'COMPLETED' AND aj.executionTimeMs IS NOT NULL")
    Double findAverageExecutionTimeMs();

    long countByStatus(JobStatus status);

    long countByDataSourceId(Long dataSourceId);

    long countByDataPipelineId(Long dataPipelineId);

    @Query("SELECT aj FROM AggregationJob aj WHERE LOWER(aj.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<AggregationJob> findByNameContaining(@Param("searchTerm") String searchTerm, Pageable pageable);
}
