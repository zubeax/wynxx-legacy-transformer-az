package com.example.demo.controller;

import com.example.demo.dto.AggregationResultResponseDto;
import com.example.demo.enums.AggregationResultStatus;
import com.example.demo.service.AggregationResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Aggregation Result Management", description = "APIs for querying and managing aggregation results")
@RequestMapping("/api/aggregation-results")
public class AggregationResultController {

    private final AggregationResultService aggregationResultService;

    @Operation(summary = "Get all aggregation results", description = "Retrieve a paginated list of all aggregation results")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of aggregation results"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<AggregationResultResponseDto>> getAllAggregationResults(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationResultService.getAllAggregationResults(pageable));
    }

    @Operation(summary = "Get aggregation result by ID", description = "Retrieve a specific aggregation result by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of aggregation result"),
        @ApiResponse(responseCode = "404", description = "Aggregation result not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AggregationResultResponseDto> getAggregationResultById(
            @Parameter(description = "Aggregation result ID") @PathVariable Long id) {
        return aggregationResultService.getAggregationResultById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get results by job ID", description = "Retrieve aggregation results for a specific job")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-job/{jobId}")
    public ResponseEntity<Page<AggregationResultResponseDto>> getResultsByJobId(
            @Parameter(description = "Aggregation job ID") @PathVariable Long jobId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationResultService.getResultsByJobId(jobId, pageable));
    }

    @Operation(summary = "Get results by data source ID", description = "Retrieve aggregation results for a specific data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-data-source/{dataSourceId}")
    public ResponseEntity<Page<AggregationResultResponseDto>> getResultsByDataSourceId(
            @Parameter(description = "Data source ID") @PathVariable Long dataSourceId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationResultService.getResultsByDataSourceId(dataSourceId, pageable));
    }

    @Operation(summary = "Get results by status", description = "Retrieve aggregation results filtered by status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid status value"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-status/{status}")
    public ResponseEntity<Page<AggregationResultResponseDto>> getResultsByStatus(
            @Parameter(description = "Result status") @PathVariable AggregationResultStatus status,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationResultService.getResultsByStatus(status, pageable));
    }

    @Operation(summary = "Get results by date range", description = "Retrieve aggregation results within a date range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid date format"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-date-range")
    public ResponseEntity<Page<AggregationResultResponseDto>> getResultsByDateRange(
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @Parameter(description = "End date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationResultService.getResultsByDateRange(start, end, pageable));
    }

    @Operation(summary = "Get failed results since date", description = "Get aggregation results that failed after a specific date/time")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid date format"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/failed-since")
    public ResponseEntity<List<AggregationResultResponseDto>> getFailedResultsSince(
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        return ResponseEntity.ok(aggregationResultService.getFailedResultsSince(since));
    }

    @Operation(summary = "Verify result integrity", description = "Verify the checksum integrity of an aggregation result")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Integrity check completed"),
        @ApiResponse(responseCode = "404", description = "Aggregation result not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}/verify-integrity")
    public ResponseEntity<Boolean> verifyResultIntegrity(
            @Parameter(description = "Aggregation result ID") @PathVariable Long id) {
        boolean isValid = aggregationResultService.verifyResultIntegrity(id);
        return ResponseEntity.ok(isValid);
    }

    @Operation(summary = "Delete an aggregation result", description = "Delete an aggregation result by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Aggregation result deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Aggregation result not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAggregationResult(
            @Parameter(description = "Aggregation result ID") @PathVariable Long id) {
        aggregationResultService.deleteAggregationResult(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get total records processed since date", description = "Get total records processed after a specific date/time")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid date format"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/stats/total-records-since")
    public ResponseEntity<Long> getTotalRecordsProcessedSince(
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        return ResponseEntity.ok(aggregationResultService.getTotalRecordsProcessedSince(since));
    }

    @Operation(summary = "Get total data size since date", description = "Get total data size processed after a specific date/time")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid date format"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/stats/total-data-size-since")
    public ResponseEntity<Long> getTotalDataSizeSince(
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        return ResponseEntity.ok(aggregationResultService.getTotalDataSizeSince(since));
    }
}
