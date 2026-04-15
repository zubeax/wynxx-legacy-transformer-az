package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for recording an aggregation metric")
public class CreateAggregationMetricRequestDto {

    @Positive(message = "Pipeline ID must be a positive number")
    @Schema(description = "ID of the associated pipeline (optional)", example = "1")
    private Long dataPipelineId;

    @Positive(message = "Job ID must be a positive number")
    @Schema(description = "ID of the associated aggregation job (optional)", example = "1")
    private Long aggregationJobId;

    @NotBlank(message = "Metric name is required")
    @Size(min = 1, max = 255, message = "Metric name must be between 1 and 255 characters")
    @Schema(description = "Name of the metric", example = "records_per_second", required = true)
    private String metricName;

    @NotNull(message = "Metric value is required")
    @Schema(description = "Value of the metric", example = "1250.50", required = true)
    private BigDecimal metricValue;

    @Size(max = 100, message = "Metric unit must not exceed 100 characters")
    @Schema(description = "Unit of the metric", example = "records/sec")
    private String metricUnit;

    @Size(max = 100, message = "Metric category must not exceed 100 characters")
    @Schema(description = "Category of the metric", example = "performance")
    private String metricCategory;

    @Size(max = 500, message = "Tags must not exceed 500 characters")
    @Schema(description = "Comma-separated tags", example = "production,daily")
    private String tags;

    @Schema(description = "Timestamp when the metric was recorded (defaults to now)")
    private LocalDateTime recordedAt;

    @Schema(description = "Start of the measurement period")
    private LocalDateTime periodStart;

    @Schema(description = "End of the measurement period")
    private LocalDateTime periodEnd;

    @Schema(description = "Additional data as JSON")
    private String additionalData;
}
