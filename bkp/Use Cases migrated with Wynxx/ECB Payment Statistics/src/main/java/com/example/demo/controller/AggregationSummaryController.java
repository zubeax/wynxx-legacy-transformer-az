package com.example.demo.controller;

import com.example.demo.dto.AggregationSummaryResponseDto;
import com.example.demo.service.AggregationSummaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Aggregation Dashboard", description = "APIs for aggregation system dashboard and summary statistics")
@RequestMapping("/api/aggregation-summary")
public class AggregationSummaryController {

    private final AggregationSummaryService aggregationSummaryService;

    @Operation(summary = "Get aggregation system summary", description = "Retrieve a comprehensive summary of the entire data aggregation system including statistics for data sources, pipelines, jobs, and results")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of aggregation summary"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<AggregationSummaryResponseDto> getAggregationSummary() {
        AggregationSummaryResponseDto summary = aggregationSummaryService.getAggregationSummary();
        return ResponseEntity.ok(summary);
    }
}
