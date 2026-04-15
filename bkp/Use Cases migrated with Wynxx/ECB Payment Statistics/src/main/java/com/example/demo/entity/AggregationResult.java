package com.example.demo.entity;

import com.example.demo.enums.AggregationResultStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "aggregation_results", indexes = {
        @Index(name = "idx_aggregation_results_job_id", columnList = "aggregation_job_id"),
        @Index(name = "idx_aggregation_results_data_source_id", columnList = "data_source_id"),
        @Index(name = "idx_aggregation_results_status", columnList = "status"),
        @Index(name = "idx_aggregation_results_aggregated_at", columnList = "aggregated_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aggregation_job_id", nullable = false)
    private AggregationJob aggregationJob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_source_id", nullable = false)
    private DataSource dataSource;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private AggregationResultStatus status;

    @Column(name = "record_count", nullable = false)
    private Long recordCount = 0L;

    @Column(name = "failed_count", nullable = false)
    private Long failedCount = 0L;

    @Column(name = "data_size_bytes")
    private Long dataSizeBytes;

    @Column(name = "execution_time_ms")
    private Long executionTimeMs;

    @Column(name = "result_data", columnDefinition = "TEXT")
    private String resultData;

    @Column(name = "result_file_path", length = 2000)
    private String resultFilePath;

    @Column(name = "checksum", length = 128)
    private String checksum;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata;

    @Column(name = "error_details", columnDefinition = "TEXT")
    private String errorDetails;

    @Column(name = "schema_version", length = 50)
    private String schemaVersion;

    @Column(name = "aggregated_at", nullable = false)
    private LocalDateTime aggregatedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public AggregationResult(AggregationJob aggregationJob, DataSource dataSource, AggregationResultStatus status) {
        this.aggregationJob = aggregationJob;
        this.dataSource = dataSource;
        this.status = status;
        this.recordCount = 0L;
        this.failedCount = 0L;
        this.aggregatedAt = LocalDateTime.now();
    }

    public boolean isSuccessful() {
        return this.status != null && this.status.isSuccessful();
    }

    public double getSuccessRate() {
        long total = this.recordCount + this.failedCount;
        if (total == 0) return 0.0;
        return (double) this.recordCount / total * 100.0;
    }

    public String getDataSizeFormatted() {
        if (this.dataSizeBytes == null) return "N/A";
        if (this.dataSizeBytes < 1024) return this.dataSizeBytes + " B";
        if (this.dataSizeBytes < 1024 * 1024) return String.format("%.2f KB", this.dataSizeBytes / 1024.0);
        if (this.dataSizeBytes < 1024 * 1024 * 1024) return String.format("%.2f MB", this.dataSizeBytes / (1024.0 * 1024));
        return String.format("%.2f GB", this.dataSizeBytes / (1024.0 * 1024 * 1024));
    }
}
