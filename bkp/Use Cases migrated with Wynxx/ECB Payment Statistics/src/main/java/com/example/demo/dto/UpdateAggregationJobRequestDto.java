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
@Schema(description = "Request DTO for updating an existing aggregation job")
public class UpdateAggregationJobRequestDto {

    @Size(min = 1, max = 255, message = "Job name must be between 1 and 255 characters")
    @Schema(description = "Updated name of the aggregation job", example = "Daily Customer Data Sync v2")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Updated description of the aggregation job")
    private String description;

    @Schema(description = "Updated job type", example = "INCREMENTAL")
    private JobType jobType;

    @Positive(message = "Data source ID must be a positive number")
    @Schema(description = "Updated data source ID", example = "2")
    private Long dataSourceId;

    @Positive(message = "Pipeline ID must be a positive number")
    @Schema(description = "Updated pipeline ID", example = "1")
    private Long dataPipelineId;

    @Schema(description = "Updated cron expression for scheduling", example = "0 6 * * *")
    private String scheduleExpression;

    @Schema(description = "Updated transformation script")
    private String transformationScript;

    @Schema(description = "Updated filter expression")
    private String filterExpression;

    @Schema(description = "Updated output format", example = "CSV")
    private OutputFormat outputFormat;

    @Size(max = 2000, message = "Output destination must not exceed 2000 characters")
    @Schema(description = "Updated output destination", example = "s3://my-bucket/output/v2/")
    private String outputDestination;

    @Min(value = 1, message = "Priority must be between 1 and 10")
    @Max(value = 10, message = "Priority must be between 1 and 10")
    @Schema(description = "Updated job priority", example = "7")
    private Integer priority;

    @Schema(description = "Updated retry on failure setting", example = "false")
    private Boolean retryOnFailure;

    @Min(value = 0, message = "Max retries must be non-negative")
    @Max(value = 10, message = "Max retries must not exceed 10")
    @Schema(description = "Updated maximum number of retry attempts", example = "5")
    private Integer maxRetries;

    @Min(value = 1, message = "Timeout must be at least 1 second")
    @Max(value = 86400, message = "Timeout must not exceed 86400 seconds")
    @Schema(description = "Updated job execution timeout in seconds", example = "7200")
    private Integer timeoutSeconds;
}
