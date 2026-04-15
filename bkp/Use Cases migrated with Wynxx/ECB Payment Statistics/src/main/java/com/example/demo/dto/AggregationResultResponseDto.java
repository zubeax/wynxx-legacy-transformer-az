package com.example.demo.dto;

import com.example.demo.enums.AggregationResultStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for aggregation result information")
public class AggregationResultResponseDto {

    @Schema(description = "Unique identifier of the aggregation result", example = "1")
    private Long id;

    @Schema(description = "ID of the aggregation job that produced this result", example = "1")
    private Long aggregationJobId;

    @Schema(description = "Name of the aggregation job", example = "Daily Customer Data Sync")
    private String aggregationJobName;

    @Schema(description = "ID of the data source", example = "1")
    private Long dataSourceId;

    @Schema(description = "Name of the data source", example = "Production PostgreSQL DB")
    private String dataSourceName;

    @Schema(description = "Status of the aggregation result", example = "SUCCESS")
    private AggregationResultStatus status;

    @Schema(description = "Display name of the status", example = "Success")
    private String statusDisplayName;

    @Schema(description = "Number of records successfully aggregated", example = "150000")
    private Long recordCount;

    @Schema(description = "Number of records that failed", example = "5")
    private Long failedCount;

    @Schema(description = "Size of the result data in bytes", example = "1048576")
    private Long dataSizeBytes;

    @Schema(description = "Human-readable data size", example = "1.00 MB")
    private String dataSizeFormatted;

    @Schema(description = "Execution time in milliseconds", example = "45000")
    private Long executionTimeMs;

    @Schema(description = "Result data (may be truncated for large results)")
    private String resultData;

    @Schema(description = "Path to the result file if stored externally")
    private String resultFilePath;

    @Schema(description = "Checksum of the result data for integrity verification")
    private String checksum;

    @Schema(description = "Additional metadata as JSON")
    private String metadata;

    @Schema(description = "Error details if result is FAILED")
    private String errorDetails;

    @Schema(description = "Schema version of the result data")
    private String schemaVersion;

    @Schema(description = "Success rate percentage", example = "99.99")
    private Double successRate;

    @Schema(description = "Whether the result is successful", example = "true")
    private Boolean isSuccessful;

    @Schema(description = "Timestamp when the aggregation was performed")
    private LocalDateTime aggregatedAt;

    @Schema(description = "Timestamp when the result was created")
    private LocalDateTime createdAt;
}
