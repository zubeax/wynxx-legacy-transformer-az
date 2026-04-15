package com.example.demo.dto;

import com.example.demo.enums.PipelineStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for data pipeline information")
public class DataPipelineResponseDto {

    @Schema(description = "Unique identifier of the pipeline", example = "1")
    private Long id;

    @Schema(description = "Name of the pipeline", example = "Customer Data Aggregation Pipeline")
    private String name;

    @Schema(description = "Description of the pipeline")
    private String description;

    @Schema(description = "Current status of the pipeline", example = "ACTIVE")
    private PipelineStatus status;

    @Schema(description = "Display name of the status", example = "Active")
    private String statusDisplayName;

    @Schema(description = "Pipeline configuration as JSON")
    private String pipelineConfig;

    @Schema(description = "Cron expression for scheduling", example = "0 2 * * *")
    private String scheduleExpression;

    @Schema(description = "Number of data sources in this pipeline", example = "3")
    private Integer sourceCount;

    @Schema(description = "Timestamp of the last execution")
    private LocalDateTime lastExecutedAt;

    @Schema(description = "Timestamp of the next scheduled execution")
    private LocalDateTime nextScheduledAt;

    @Schema(description = "Total number of executions", example = "365")
    private Long totalExecutions;

    @Schema(description = "Number of successful executions", example = "360")
    private Long successfulExecutions;

    @Schema(description = "Number of failed executions", example = "5")
    private Long failedExecutions;

    @Schema(description = "Average execution time in milliseconds", example = "45000")
    private Long averageExecutionTimeMs;

    @Schema(description = "Success rate percentage", example = "98.63")
    private Double successRate;

    @Schema(description = "Owner of the pipeline", example = "data-team@company.com")
    private String owner;

    @Schema(description = "Tags for categorization", example = "customer,daily,production")
    private String tags;

    @Schema(description = "Notification email addresses")
    private String notificationEmails;

    @Schema(description = "Whether the pipeline is active", example = "true")
    private Boolean isActive;

    @Schema(description = "Whether the pipeline is runnable", example = "true")
    private Boolean isRunnable;

    @Schema(description = "Error message if status is ERROR")
    private String errorMessage;

    @Schema(description = "Timestamp when the pipeline was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the pipeline was last updated")
    private LocalDateTime updatedAt;
}
