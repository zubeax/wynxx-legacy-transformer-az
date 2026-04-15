package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for creating a new data pipeline")
public class CreateDataPipelineRequestDto {

    @NotBlank(message = "Pipeline name is required")
    @Size(min = 1, max = 255, message = "Pipeline name must be between 1 and 255 characters")
    @Schema(description = "Name of the data pipeline", example = "Customer Data Aggregation Pipeline", required = true)
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Description of the pipeline", example = "Aggregates customer data from multiple sources daily")
    private String description;

    @Schema(description = "Pipeline configuration as JSON", example = "{\"parallelism\":4,\"batchSize\":1000}")
    private String pipelineConfig;

    @Schema(description = "Cron expression for scheduling", example = "0 2 * * *")
    private String scheduleExpression;

    @Size(max = 255, message = "Owner must not exceed 255 characters")
    @Schema(description = "Owner of the pipeline", example = "data-team@company.com")
    private String owner;

    @Size(max = 500, message = "Tags must not exceed 500 characters")
    @Schema(description = "Comma-separated tags for categorization", example = "customer,daily,production")
    private String tags;

    @Size(max = 1000, message = "Notification emails must not exceed 1000 characters")
    @Schema(description = "Comma-separated email addresses for notifications", example = "admin@company.com,team@company.com")
    private String notificationEmails;
}
