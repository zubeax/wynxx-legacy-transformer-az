package com.example.demo.entity;

import com.example.demo.enums.PipelineStatus;
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
@Table(name = "data_pipelines", indexes = {
        @Index(name = "idx_data_pipelines_status", columnList = "status"),
        @Index(name = "idx_data_pipelines_name", columnList = "name"),
        @Index(name = "idx_data_pipelines_next_scheduled", columnList = "next_scheduled_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataPipeline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private PipelineStatus status = PipelineStatus.DRAFT;

    @Column(name = "pipeline_config", columnDefinition = "TEXT")
    private String pipelineConfig;

    @Column(name = "schedule_expression", length = 100)
    private String scheduleExpression;

    @Column(name = "source_count", nullable = false)
    private Integer sourceCount = 0;

    @Column(name = "last_executed_at")
    private LocalDateTime lastExecutedAt;

    @Column(name = "next_scheduled_at")
    private LocalDateTime nextScheduledAt;

    @Column(name = "total_executions", nullable = false)
    private Long totalExecutions = 0L;

    @Column(name = "successful_executions", nullable = false)
    private Long successfulExecutions = 0L;

    @Column(name = "failed_executions", nullable = false)
    private Long failedExecutions = 0L;

    @Column(name = "average_execution_time_ms")
    private Long averageExecutionTimeMs;

    @Column(name = "owner", length = 255)
    private String owner;

    @Column(name = "tags", length = 500)
    private String tags;

    @Column(name = "notification_emails", length = 1000)
    private String notificationEmails;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "error_message", length = 2000)
    private String errorMessage;

    @OneToMany(mappedBy = "dataPipeline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AggregationJob> aggregationJobs = new ArrayList<>();

    @OneToMany(mappedBy = "dataPipeline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AggregationMetric> metrics = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public DataPipeline(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = PipelineStatus.DRAFT;
        this.sourceCount = 0;
        this.totalExecutions = 0L;
        this.successfulExecutions = 0L;
        this.failedExecutions = 0L;
        this.isActive = true;
    }

    public void recordExecution(boolean success, long executionTimeMs) {
        this.totalExecutions++;
        this.lastExecutedAt = LocalDateTime.now();
        if (success) {
            this.successfulExecutions++;
        } else {
            this.failedExecutions++;
        }
        if (this.averageExecutionTimeMs == null) {
            this.averageExecutionTimeMs = executionTimeMs;
        } else {
            this.averageExecutionTimeMs = (this.averageExecutionTimeMs * (this.totalExecutions - 1) + executionTimeMs) / this.totalExecutions;
        }
    }

    public double getSuccessRate() {
        if (this.totalExecutions == 0) return 0.0;
        return (double) this.successfulExecutions / this.totalExecutions * 100.0;
    }

    public boolean isRunnable() {
        return this.status == PipelineStatus.ACTIVE && Boolean.TRUE.equals(this.isActive);
    }
}
