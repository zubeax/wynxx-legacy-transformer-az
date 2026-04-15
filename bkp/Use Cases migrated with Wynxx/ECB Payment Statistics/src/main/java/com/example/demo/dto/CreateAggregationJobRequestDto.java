package com.example.demo.dto;

import com.example.demo.enums.JobType;
import com.example.demo.enums.OutputFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for creating a new aggregation job")
public class CreateAggregationJobRequestDto {

    @NotBlank(message = "Job name is required")
    @Size(min = 1, max = 255, message = "Job name must be between 1 and 255 characters")
    @Schema(description = "Name of the aggregation job", example = "Daily Customer Data Sync", required = true)
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Description of the aggregation job", example = "Aggregates customer data daily from production DB")
    private String description;

    @NotNull(message = "Job type is required")
    @Schema(description = "Type of the aggregation job", example = "FULL_SYNC", required = true)
    private JobType jobType;

    @NotNull(message = "Data source ID is required")
    @Positive(message = "Data source ID must be a positive number")
    @Schema(description = "ID of the data source to aggregate from", example = "1", required = true)
    private Long dataSourceId;

    @Positive(message = "Pipeline ID must be a positive number")
    @Schema(description = "ID of the pipeline this job belongs to (optional)", example = "1")
    private Long dataPipelineId;

    @Schema(description = "Cron expression for scheduling", example = "0 0 * * *")
    private String scheduleExpression;

    @Schema(description = "Transformation script (JavaScript/SQL) to apply to data")
    private String transformationScript;

    @Schema(description = "Filter expression to apply when extracting data", example = "status = 'ACTIVE' AND created_at > '2024-01-01'")
    private String filterExpression;

    @Schema(description = "Output format for the aggregated data", example = "JSON")
    private OutputFormat outputFormat;

    @Size(max = 2000, message = "Output destination must not exceed 2000 characters")
    @Schema(description = "Destination path or URL for the output", example = "s3://my-bucket/output/")
    private String outputDestination;

    @Min(value = 1, message = "Priority must be between 1 and 10")
    @Max(value = 10, message = "Priority must be between 1 and 10")
    @Schema(description = "Job priority (1=lowest, 10=highest)", example = "5")
    private Integer priority;

    @Schema(description = "Whether to retry on failure", example = "true")
    private Boolean retryOnFailure;

    @Min(value = 0, message = "Max retries must be non-negative")
    @Max(value = 10, message = "Max retries must not exceed 10")
    @Schema(description = "Maximum number of retry attempts", example = "3")
    private Integer maxRetries;

    @Min(value = 1, message = "Timeout must be at least 1 second")
    @Max(value = 86400, message = "Timeout must not exceed 86400 seconds")
    @Schema(description = "Job execution timeout in seconds", example = "3600")
    private Integer timeoutSeconds;
}
