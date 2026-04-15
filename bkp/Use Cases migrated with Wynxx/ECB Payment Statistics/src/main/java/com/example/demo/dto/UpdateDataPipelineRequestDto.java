package com.example.demo.dto;

import com.example.demo.enums.PipelineStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for updating an existing data pipeline")
public class UpdateDataPipelineRequestDto {

    @Size(min = 1, max = 255, message = "Pipeline name must be between 1 and 255 characters")
    @Schema(description = "Updated name of the data pipeline", example = "Customer Data Aggregation Pipeline v2")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Updated description of the pipeline")
    private String description;

    @Schema(description = "Updated pipeline configuration as JSON")
    private String pipelineConfig;

    @Schema(description = "Updated cron expression for scheduling", example = "0 3 * * *")
    private String scheduleExpression;

    @Schema(description = "Updated status of the pipeline", example = "ACTIVE")
    private PipelineStatus status;

    @Size(max = 255, message = "Owner must not exceed 255 characters")
    @Schema(description = "Updated owner of the pipeline", example = "new-owner@company.com")
    private String owner;

    @Size(max = 500, message = "Tags must not exceed 500 characters")
    @Schema(description = "Updated comma-separated tags", example = "customer,daily,updated")
    private String tags;

    @Size(max = 1000, message = "Notification emails must not exceed 1000 characters")
    @Schema(description = "Updated comma-separated notification emails")
    private String notificationEmails;

    @Schema(description = "Whether the pipeline is active", example = "true")
    private Boolean isActive;
}
