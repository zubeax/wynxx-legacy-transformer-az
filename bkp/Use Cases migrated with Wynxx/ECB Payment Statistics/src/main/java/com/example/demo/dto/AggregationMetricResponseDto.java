package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for aggregation metric information")
public class AggregationMetricResponseDto {

    @Schema(description = "Unique identifier of the metric", example = "1")
    private Long id;

    @Schema(description = "ID of the associated pipeline", example = "1")
    private Long dataPipelineId;

    @Schema(description = "Name of the associated pipeline", example = "Customer Data Aggregation Pipeline")
    private String dataPipelineName;

    @Schema(description = "ID of the associated aggregation job", example = "1")
    private Long aggregationJobId;

    @Schema(description = "Name of the associated aggregation job", example = "Daily Customer Data Sync")
    private String aggregationJobName;

    @Schema(description = "Name of the metric", example = "records_per_second")
    private String metricName;

    @Schema(description = "Value of the metric", example = "1250.50")
    private BigDecimal metricValue;

    @Schema(description = "Unit of the metric", example = "records/sec")
    private String metricUnit;

    @Schema(description = "Category of the metric", example = "performance")
    private String metricCategory;

    @Schema(description = "Tags for categorization", example = "production,daily")
    private String tags;

    @Schema(description = "Timestamp when the metric was recorded")
    private LocalDateTime recordedAt;

    @Schema(description = "Start of the measurement period")
    private LocalDateTime periodStart;

    @Schema(description = "End of the measurement period")
    private LocalDateTime periodEnd;

    @Schema(description = "Additional data as JSON")
    private String additionalData;

    @Schema(description = "Timestamp when the metric was created")
    private LocalDateTime createdAt;
}
