package com.example.demo.controller;

import com.example.demo.dto.AggregationJobResponseDto;
import com.example.demo.dto.CreateAggregationJobRequestDto;
import com.example.demo.dto.JobExecutionRequestDto;
import com.example.demo.dto.UpdateAggregationJobRequestDto;
import com.example.demo.enums.JobStatus;
import com.example.demo.enums.JobType;
import com.example.demo.service.AggregationJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Aggregation Job Management", description = "APIs for managing data aggregation jobs")
@RequestMapping("/api/aggregation-jobs")
public class AggregationJobController {

    private final AggregationJobService aggregationJobService;

    @Operation(summary = "Get all aggregation jobs", description = "Retrieve a paginated list of all aggregation jobs")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of aggregation jobs"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<AggregationJobResponseDto>> getAllAggregationJobs(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationJobService.getAllAggregationJobs(pageable));
    }

    @Operation(summary = "Get aggregation job by ID", description = "Retrieve a specific aggregation job by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of aggregation job"),
        @ApiResponse(responseCode = "404", description = "Aggregation job not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AggregationJobResponseDto> getAggregationJobById(
            @Parameter(description = "Aggregation job ID") @PathVariable Long id) {
        return aggregationJobService.getAggregationJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new aggregation job", description = "Create a new data aggregation job")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Aggregation job created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data or data source not operational"),
        @ApiResponse(responseCode = "404", description = "Data source or pipeline not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<AggregationJobResponseDto> createAggregationJob(
            @Valid @RequestBody CreateAggregationJobRequestDto request) {
        AggregationJobResponseDto response = aggregationJobService.createAggregationJob(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update an aggregation job", description = "Update an existing aggregation job by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aggregation job updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data or job is running"),
        @ApiResponse(responseCode = "404", description = "Aggregation job not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AggregationJobResponseDto> updateAggregationJob(
            @Parameter(description = "Aggregation job ID") @PathVariable Long id,
            @Valid @RequestBody UpdateAggregationJobRequestDto request) {
        AggregationJobResponseDto response = aggregationJobService.updateAggregationJob(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete an aggregation job", description = "Delete an aggregation job by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Aggregation job deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Cannot delete a running job"),
        @ApiResponse(responseCode = "404", description = "Aggregation job not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAggregationJob(
            @Parameter(description = "Aggregation job ID") @PathVariable Long id) {
        aggregationJobService.deleteAggregationJob(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get jobs by status", description = "Retrieve aggregation jobs filtered by status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid status value"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-status/{status}")
    public ResponseEntity<Page<AggregationJobResponseDto>> getJobsByStatus(
            @Parameter(description = "Job status") @PathVariable JobStatus status,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationJobService.getAggregationJobsByStatus(status, pageable));
    }

    @Operation(summary = "Get jobs by data source", description = "Retrieve aggregation jobs for a specific data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-data-source/{dataSourceId}")
    public ResponseEntity<Page<AggregationJobResponseDto>> getJobsByDataSource(
            @Parameter(description = "Data source ID") @PathVariable Long dataSourceId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationJobService.getAggregationJobsByDataSource(dataSourceId, pageable));
    }

    @Operation(summary = "Get jobs by pipeline", description = "Retrieve aggregation jobs for a specific pipeline")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-pipeline/{pipelineId}")
    public ResponseEntity<Page<AggregationJobResponseDto>> getJobsByPipeline(
            @Parameter(description = "Pipeline ID") @PathVariable Long pipelineId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationJobService.getAggregationJobsByPipeline(pipelineId, pageable));
    }

    @Operation(summary = "Get jobs by type", description = "Retrieve aggregation jobs filtered by job type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid job type value"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-type/{jobType}")
    public ResponseEntity<Page<AggregationJobResponseDto>> getJobsByType(
            @Parameter(description = "Job type") @PathVariable JobType jobType,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationJobService.getAggregationJobsByType(jobType, pageable));
    }

    @Operation(summary = "Search aggregation jobs", description = "Search aggregation jobs by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful search"),
        @ApiResponse(responseCode = "400", description = "Invalid search parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search")
    public ResponseEntity<Page<AggregationJobResponseDto>> searchAggregationJobs(
            @Parameter(description = "Search term") @RequestParam String q,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationJobService.searchAggregationJobs(q, pageable));
    }

    @Operation(summary = "Trigger job execution", description = "Manually trigger an aggregation job execution")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Job execution triggered successfully"),
        @ApiResponse(responseCode = "400", description = "Job cannot be triggered in current state"),
        @ApiResponse(responseCode = "404", description = "Aggregation job not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/execute")
    public ResponseEntity<AggregationJobResponseDto> triggerJobExecution(
            @Parameter(description = "Aggregation job ID") @PathVariable Long id,
            @RequestBody(required = false) JobExecutionRequestDto request) {
        if (request == null) request = new JobExecutionRequestDto();
        AggregationJobResponseDto response = aggregationJobService.triggerJobExecution(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cancel a job", description = "Cancel a running or pending aggregation job")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Job cancelled successfully"),
        @ApiResponse(responseCode = "400", description = "Job cannot be cancelled in current state"),
        @ApiResponse(responseCode = "404", description = "Aggregation job not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/cancel")
    public ResponseEntity<AggregationJobResponseDto> cancelJob(
            @Parameter(description = "Aggregation job ID") @PathVariable Long id) {
        AggregationJobResponseDto response = aggregationJobService.cancelJob(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Retry a failed job", description = "Retry a failed aggregation job")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Job retry initiated successfully"),
        @ApiResponse(responseCode = "400", description = "Job cannot be retried or max retries exceeded"),
        @ApiResponse(responseCode = "404", description = "Aggregation job not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/retry")
    public ResponseEntity<AggregationJobResponseDto> retryJob(
            @Parameter(description = "Aggregation job ID") @PathVariable Long id) {
        AggregationJobResponseDto response = aggregationJobService.retryJob(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get active jobs", description = "Get all currently running or retrying aggregation jobs")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/active")
    public ResponseEntity<List<AggregationJobResponseDto>> getActiveJobs() {
        return ResponseEntity.ok(aggregationJobService.getActiveJobs());
    }

    @Operation(summary = "Get retryable failed jobs", description = "Get failed jobs that can be retried")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/retryable-failures")
    public ResponseEntity<List<AggregationJobResponseDto>> getRetryableFailedJobs() {
        return ResponseEntity.ok(aggregationJobService.getRetryableFailedJobs());
    }

    @Operation(summary = "Get failed jobs since date", description = "Get jobs that failed after a specific date/time")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid date format"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/failed-since")
    public ResponseEntity<List<AggregationJobResponseDto>> getFailedJobsSince(
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        return ResponseEntity.ok(aggregationJobService.getFailedJobsSince(since));
    }

    @Operation(summary = "Get completed jobs since date", description = "Get jobs that completed after a specific date/time")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid date format"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/completed-since")
    public ResponseEntity<List<AggregationJobResponseDto>> getCompletedJobsSince(
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        return ResponseEntity.ok(aggregationJobService.getCompletedJobsSince(since));
    }
}
