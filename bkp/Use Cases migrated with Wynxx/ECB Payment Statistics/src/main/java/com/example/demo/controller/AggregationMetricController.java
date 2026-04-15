package com.example.demo.controller;

import com.example.demo.dto.AggregationMetricResponseDto;
import com.example.demo.dto.CreateAggregationMetricRequestDto;
import com.example.demo.service.AggregationMetricService;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Aggregation Metrics", description = "APIs for recording and querying aggregation metrics")
@RequestMapping("/api/aggregation-metrics")
public class AggregationMetricController {

    private final AggregationMetricService aggregationMetricService;

    @Operation(summary = "Get all aggregation metrics", description = "Retrieve a paginated list of all aggregation metrics")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of aggregation metrics"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<AggregationMetricResponseDto>> getAllMetrics(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationMetricService.getAllMetrics(pageable));
    }

    @Operation(summary = "Get aggregation metric by ID", description = "Retrieve a specific aggregation metric by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of aggregation metric"),
        @ApiResponse(responseCode = "404", description = "Aggregation metric not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AggregationMetricResponseDto> getMetricById(
            @Parameter(description = "Metric ID") @PathVariable Long id) {
        return aggregationMetricService.getMetricById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new aggregation metric", description = "Record a new aggregation metric")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Aggregation metric created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Pipeline or job not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<AggregationMetricResponseDto> createMetric(
            @Valid @RequestBody CreateAggregationMetricRequestDto request) {
        AggregationMetricResponseDto response = aggregationMetricService.createMetric(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete an aggregation metric", description = "Delete an aggregation metric by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Aggregation metric deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Aggregation metric not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetric(
            @Parameter(description = "Metric ID") @PathVariable Long id) {
        aggregationMetricService.deleteMetric(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get metrics by pipeline ID", description = "Retrieve aggregation metrics for a specific pipeline")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-pipeline/{pipelineId}")
    public ResponseEntity<Page<AggregationMetricResponseDto>> getMetricsByPipelineId(
            @Parameter(description = "Pipeline ID") @PathVariable Long pipelineId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationMetricService.getMetricsByPipelineId(pipelineId, pageable));
    }

    @Operation(summary = "Get metrics by job ID", description = "Retrieve aggregation metrics for a specific job")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-job/{jobId}")
    public ResponseEntity<Page<AggregationMetricResponseDto>> getMetricsByJobId(
            @Parameter(description = "Job ID") @PathVariable Long jobId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationMetricService.getMetricsByJobId(jobId, pageable));
    }

    @Operation(summary = "Get metrics by name", description = "Retrieve all metrics with a specific name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-name")
    public ResponseEntity<List<AggregationMetricResponseDto>> getMetricsByName(
            @Parameter(description = "Metric name") @RequestParam String name) {
        return ResponseEntity.ok(aggregationMetricService.getMetricsByName(name));
    }

    @Operation(summary = "Get metrics by name since date", description = "Retrieve metrics with a specific name after a date/time")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid date format"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-name-since")
    public ResponseEntity<List<AggregationMetricResponseDto>> getMetricsByNameSince(
            @Parameter(description = "Metric name") @RequestParam String name,
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        return ResponseEntity.ok(aggregationMetricService.getMetricsByNameSince(name, since));
    }

    @Operation(summary = "Get metrics by pipeline and period", description = "Retrieve metrics for a pipeline within a time period")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid date format"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-pipeline/{pipelineId}/period")
    public ResponseEntity<List<AggregationMetricResponseDto>> getMetricsByPipelineAndPeriod(
            @Parameter(description = "Pipeline ID") @PathVariable Long pipelineId,
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @Parameter(description = "End date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(aggregationMetricService.getMetricsByPipelineAndPeriod(pipelineId, start, end));
    }

    @Operation(summary = "Get average metric value", description = "Get the average value of a metric since a date/time")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/stats/average")
    public ResponseEntity<BigDecimal> getAverageMetricValue(
            @Parameter(description = "Metric name") @RequestParam String name,
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        return ResponseEntity.ok(aggregationMetricService.getAverageMetricValue(name, since));
    }

    @Operation(summary = "Get max metric value", description = "Get the maximum value of a metric since a date/time")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/stats/max")
    public ResponseEntity<BigDecimal> getMaxMetricValue(
            @Parameter(description = "Metric name") @RequestParam String name,
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        return ResponseEntity.ok(aggregationMetricService.getMaxMetricValue(name, since));
    }

    @Operation(summary = "Get min metric value", description = "Get the minimum value of a metric since a date/time")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/stats/min")
    public ResponseEntity<BigDecimal> getMinMetricValue(
            @Parameter(description = "Metric name") @RequestParam String name,
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime since) {
        return ResponseEntity.ok(aggregationMetricService.getMinMetricValue(name, since));
    }

    @Operation(summary = "Get all distinct metric names", description = "Get a list of all distinct metric names in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/metric-names")
    public ResponseEntity<List<String>> getAllDistinctMetricNames() {
        return ResponseEntity.ok(aggregationMetricService.getAllDistinctMetricNames());
    }

    @Operation(summary = "Get all distinct metric categories", description = "Get a list of all distinct metric categories in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/metric-categories")
    public ResponseEntity<List<String>> getAllDistinctMetricCategories() {
        return ResponseEntity.ok(aggregationMetricService.getAllDistinctMetricCategories());
    }

    @Operation(summary = "Get metrics by date range", description = "Retrieve metrics recorded within a date range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid date format"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-date-range")
    public ResponseEntity<Page<AggregationMetricResponseDto>> getMetricsByDateRange(
            @Parameter(description = "Start date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @Parameter(description = "End date/time (ISO format)") 
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(aggregationMetricService.getMetricsByDateRange(start, end, pageable));
    }
}
