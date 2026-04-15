package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for aggregation system summary/dashboard")
public class AggregationSummaryResponseDto {

    @Schema(description = "Total number of data sources", example = "15")
    private Long totalDataSources;

    @Schema(description = "Number of active data sources", example = "12")
    private Long activeDataSources;

    @Schema(description = "Number of data sources in error state", example = "2")
    private Long errorDataSources;

    @Schema(description = "Total number of pipelines", example = "8")
    private Long totalPipelines;

    @Schema(description = "Number of active pipelines", example = "6")
    private Long activePipelines;

    @Schema(description = "Total number of aggregation jobs", example = "45")
    private Long totalJobs;

    @Schema(description = "Number of currently running jobs", example = "3")
    private Long runningJobs;

    @Schema(description = "Number of pending jobs", example = "5")
    private Long pendingJobs;

    @Schema(description = "Number of failed jobs in last 24 hours", example = "2")
    private Long failedJobsLast24h;

    @Schema(description = "Number of completed jobs in last 24 hours", example = "18")
    private Long completedJobsLast24h;

    @Schema(description = "Total records processed in last 24 hours", example = "2500000")
    private Long totalRecordsProcessedLast24h;

    @Schema(description = "Total records failed in last 24 hours", example = "150")
    private Long totalRecordsFailedLast24h;

    @Schema(description = "Overall success rate percentage", example = "99.99")
    private Double overallSuccessRate;

    @Schema(description = "Average job execution time in milliseconds", example = "45000")
    private Long averageJobExecutionTimeMs;

    @Schema(description = "List of recently failed jobs")
    private List<AggregationJobResponseDto> recentFailedJobs;

    @Schema(description = "List of currently running jobs")
    private List<AggregationJobResponseDto> currentlyRunningJobs;

    @Schema(description = "Timestamp when this summary was generated")
    private LocalDateTime generatedAt;
}
