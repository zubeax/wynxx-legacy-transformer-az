package com.example.demo.entity;

import com.example.demo.enums.JobStatus;
import com.example.demo.enums.JobType;
import com.example.demo.enums.OutputFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aggregation_jobs", indexes = {
        @Index(name = "idx_aggregation_jobs_status", columnList = "status"),
        @Index(name = "idx_aggregation_jobs_data_source_id", columnList = "data_source_id"),
        @Index(name = "idx_aggregation_jobs_pipeline_id", columnList = "data_pipeline_id"),
        @Index(name = "idx_aggregation_jobs_job_type", columnList = "job_type"),
        @Index(name = "idx_aggregation_jobs_started_at", columnList = "started_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregationJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private JobStatus status = JobStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type", nullable = false, length = 50)
    private JobType jobType;

    @Column(name = "schedule_expression", length = 100)
    private String scheduleExpression;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_source_id", nullable = false)
    private DataSource dataSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_pipeline_id")
    private DataPipeline dataPipeline;

    @Column(name = "transformation_script", columnDefinition = "TEXT")
    private String transformationScript;

    @Column(name = "filter_expression", columnDefinition = "TEXT")
    private String filterExpression;

    @Enumerated(EnumType.STRING)
    @Column(name = "output_format", nullable = false, length = 50)
    private OutputFormat outputFormat = OutputFormat.JSON;

    @Column(name = "output_destination", length = 2000)
    private String outputDestination;

    @Column(name = "records_processed", nullable = false)
    private Long recordsProcessed = 0L;

    @Column(name = "records_failed", nullable = false)
    private Long recordsFailed = 0L;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "error_message", length = 2000)
    private String errorMessage;

    @Column(name = "priority", nullable = false)
    private Integer priority = 5;

    @Column(name = "retry_on_failure", nullable = false)
    private Boolean retryOnFailure = true;

    @Column(name = "max_retries", nullable = false)
    private Integer maxRetries = 3;

    @Column(name = "retry_count", nullable = false)
    private Integer retryCount = 0;

    @Column(name = "timeout_seconds")
    private Integer timeoutSeconds;

    @Column(name = "checkpoint_data", columnDefinition = "TEXT")
    private String checkpointData;

    @Column(name = "execution_time_ms")
    private Long executionTimeMs;

    @OneToMany(mappedBy = "aggregationJob", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AggregationResult> results = new ArrayList<>();

    @OneToMany(mappedBy = "aggregationJob", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AggregationMetric> metrics = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public AggregationJob(String name, JobType jobType, DataSource dataSource) {
        this.name = name;
        this.jobType = jobType;
        this.dataSource = dataSource;
        this.status = JobStatus.PENDING;
        this.outputFormat = OutputFormat.JSON;
        this.recordsProcessed = 0L;
        this.recordsFailed = 0L;
        this.priority = 5;
        this.retryOnFailure = true;
        this.maxRetries = 3;
        this.retryCount = 0;
    }

    public void start() {
        this.status = JobStatus.RUNNING;
        this.startedAt = LocalDateTime.now();
        this.errorMessage = null;
    }

    public void complete(long recordsProcessed, long recordsFailed) {
        this.status = JobStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
        this.recordsProcessed = recordsProcessed;
        this.recordsFailed = recordsFailed;
        if (this.startedAt != null) {
            this.executionTimeMs = java.time.Duration.between(this.startedAt, this.completedAt).toMillis();
        }
    }

    public void fail(String errorMessage) {
        this.status = JobStatus.FAILED;
        this.completedAt = LocalDateTime.now();
        this.errorMessage = errorMessage;
        this.retryCount = this.retryCount + 1;
        if (this.startedAt != null) {
            this.executionTimeMs = java.time.Duration.between(this.startedAt, this.completedAt).toMillis();
        }
    }

    public void cancel() {
        this.status = JobStatus.CANCELLED;
        this.completedAt = LocalDateTime.now();
    }

    public boolean canRetry() {
        return Boolean.TRUE.equals(this.retryOnFailure) && this.retryCount < this.maxRetries;
    }

    public boolean isRunning() {
        return this.status == JobStatus.RUNNING || this.status == JobStatus.RETRYING;
    }

    public double getSuccessRate() {
        long total = this.recordsProcessed + this.recordsFailed;
        if (total == 0) return 0.0;
        return (double) this.recordsProcessed / total * 100.0;
    }
}
