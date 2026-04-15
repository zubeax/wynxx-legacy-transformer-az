package com.example.demo.dto;

import com.example.demo.enums.JobStatus;
import com.example.demo.enums.JobType;
import com.example.demo.enums.OutputFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for aggregation job information")
public class AggregationJobResponseDto {

    @Schema(description = "Unique identifier of the aggregation job", example = "1")
    private Long id;

    @Schema(description = "Name of the aggregation job", example = "Daily Customer Data Sync")
    private String name;

    @Schema(description = "Description of the aggregation job")
    private String description;

    @Schema(description = "Current status of the job", example = "COMPLETED")
    private JobStatus status;

    @Schema(description = "Display name of the status", example = "Completed")
    private String statusDisplayName;

    @Schema(description = "Type of the aggregation job", example = "FULL_SYNC")
    private JobType jobType;

    @Schema(description = "Display name of the job type", example = "Full Synchronization")
    private String jobTypeDisplayName;

    @Schema(description = "ID of the associated data source", example = "1")
    private Long dataSourceId;

    @Schema(description = "Name of the associated data source", example = "Production PostgreSQL DB")
    private String dataSourceName;

    @Schema(description = "ID of the associated pipeline", example = "1")
    private Long dataPipelineId;

    @Schema(description = "Name of the associated pipeline", example = "Customer Data Pipeline")
    private String dataPipelineName;

    @Schema(description = "Cron expression for scheduling", example = "0 0 * * *")
    private String scheduleExpression;

    @Schema(description = "Output format for the aggregated data", example = "JSON")
    private OutputFormat outputFormat;

    @Schema(description = "Display name of the output format", example = "JSON")
    private String outputFormatDisplayName;

    @Schema(description = "Destination path or URL for the output")
    private String outputDestination;

    @Schema(description = "Number of records successfully processed", example = "150000")
    private Long recordsProcessed;

    @Schema(description = "Number of records that failed processing", example = "5")
    private Long recordsFailed;

    @Schema(description = "Timestamp when the job started")
    private LocalDateTime startedAt;

    @Schema(description = "Timestamp when the job completed")
    private LocalDateTime completedAt;

    @Schema(description = "Error message if job failed")
    private String errorMessage;

    @Schema(description = "Job priority (1=lowest, 10=highest)", example = "5")
    private Integer priority;

    @Schema(description = "Whether to retry on failure", example = "true")
    private Boolean retryOnFailure;

    @Schema(description = "Maximum number of retry attempts", example = "3")
    private Integer maxRetries;

    @Schema(description = "Current retry count", example = "0")
    private Integer retryCount;

    @Schema(description = "Job execution timeout in seconds", example = "3600")
    private Integer timeoutSeconds;

    @Schema(description = "Total execution time in milliseconds", example = "45000")
    private Long executionTimeMs;

    @Schema(description = "Success rate percentage", example = "99.99")
    private Double successRate;

    @Schema(description = "Whether the job can be retried", example = "false")
    private Boolean canRetry;

    @Schema(description = "Whether the job is currently running", example = "false")
    private Boolean isRunning;

    @Schema(description = "Timestamp when the job was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the job was last updated")
    private LocalDateTime updatedAt;
}
