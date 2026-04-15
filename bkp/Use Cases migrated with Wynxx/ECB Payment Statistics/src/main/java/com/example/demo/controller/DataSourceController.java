package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.enums.DataSourceStatus;
import com.example.demo.enums.DataSourceType;
import com.example.demo.service.DataSourceService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Data Source Management", description = "APIs for managing data sources in the aggregation system")
@RequestMapping("/api/data-sources")
public class DataSourceController {

    private final DataSourceService dataSourceService;

    @Operation(summary = "Get all data sources", description = "Retrieve a paginated list of all data sources")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of data sources"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<DataSourceResponseDto>> getAllDataSources(
            @PageableDefault(size = 20) Pageable pageable) {
        Page<DataSourceResponseDto> dataSources = dataSourceService.getAllDataSources(pageable);
        return ResponseEntity.ok(dataSources);
    }

    @Operation(summary = "Get data source by ID", description = "Retrieve a specific data source by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of data source"),
        @ApiResponse(responseCode = "404", description = "Data source not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DataSourceResponseDto> getDataSourceById(
            @Parameter(description = "Data source ID") @PathVariable Long id) {
        return dataSourceService.getDataSourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new data source", description = "Create a new data source for aggregation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Data source created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data or duplicate name"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<DataSourceResponseDto> createDataSource(
            @Valid @RequestBody CreateDataSourceRequestDto request) {
        DataSourceResponseDto response = dataSourceService.createDataSource(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update a data source", description = "Update an existing data source by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data source updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data"),
        @ApiResponse(responseCode = "404", description = "Data source not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DataSourceResponseDto> updateDataSource(
            @Parameter(description = "Data source ID") @PathVariable Long id,
            @Valid @RequestBody UpdateDataSourceRequestDto request) {
        DataSourceResponseDto response = dataSourceService.updateDataSource(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a data source", description = "Delete a data source by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Data source deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Data source cannot be deleted in current state"),
        @ApiResponse(responseCode = "404", description = "Data source not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataSource(
            @Parameter(description = "Data source ID") @PathVariable Long id) {
        dataSourceService.deleteDataSource(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get data sources by status", description = "Retrieve data sources filtered by status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid status value"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-status/{status}")
    public ResponseEntity<Page<DataSourceResponseDto>> getDataSourcesByStatus(
            @Parameter(description = "Data source status") @PathVariable DataSourceStatus status,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(dataSourceService.getDataSourcesByStatus(status, pageable));
    }

    @Operation(summary = "Get data sources by type", description = "Retrieve data sources filtered by source type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid source type value"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-type/{sourceType}")
    public ResponseEntity<Page<DataSourceResponseDto>> getDataSourcesByType(
            @Parameter(description = "Data source type") @PathVariable DataSourceType sourceType,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(dataSourceService.getDataSourcesByType(sourceType, pageable));
    }

    @Operation(summary = "Search data sources", description = "Search data sources by name or description")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful search"),
        @ApiResponse(responseCode = "400", description = "Invalid search parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search")
    public ResponseEntity<Page<DataSourceResponseDto>> searchDataSources(
            @Parameter(description = "Search term") @RequestParam String q,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(dataSourceService.searchDataSources(q, pageable));
    }

    @Operation(summary = "Activate a data source", description = "Activate a data source to enable aggregation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data source activated successfully"),
        @ApiResponse(responseCode = "400", description = "Data source cannot be activated in current state"),
        @ApiResponse(responseCode = "404", description = "Data source not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/activate")
    public ResponseEntity<DataSourceResponseDto> activateDataSource(
            @Parameter(description = "Data source ID") @PathVariable Long id) {
        DataSourceResponseDto response = dataSourceService.activateDataSource(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deactivate a data source", description = "Deactivate a data source to stop aggregation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data source deactivated successfully"),
        @ApiResponse(responseCode = "404", description = "Data source not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<DataSourceResponseDto> deactivateDataSource(
            @Parameter(description = "Data source ID") @PathVariable Long id) {
        DataSourceResponseDto response = dataSourceService.deactivateDataSource(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Test connection for existing data source", description = "Test the connection of an existing data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Connection test completed"),
        @ApiResponse(responseCode = "404", description = "Data source not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/test-connection")
    public ResponseEntity<DataSourceResponseDto> testConnectionById(
            @Parameter(description = "Data source ID") @PathVariable Long id) {
        DataSourceResponseDto response = dataSourceService.testConnectionById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Test a new connection", description = "Test a connection before creating a data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Connection test completed"),
        @ApiResponse(responseCode = "400", description = "Invalid connection parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/test-connection")
    public ResponseEntity<TestConnectionResponseDto> testConnection(
            @Valid @RequestBody TestConnectionRequestDto request) {
        TestConnectionResponseDto response = dataSourceService.testConnection(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get data sources due for sync", description = "Get data sources that are due for synchronization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/due-for-sync")
    public ResponseEntity<List<DataSourceResponseDto>> getDataSourcesDueForSync() {
        return ResponseEntity.ok(dataSourceService.getDataSourcesDueForSync());
    }

    @Operation(summary = "Get retryable error data sources", description = "Get data sources in error state that can be retried")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/retryable-errors")
    public ResponseEntity<List<DataSourceResponseDto>> getRetryableErrorDataSources() {
        return ResponseEntity.ok(dataSourceService.getRetryableErrorDataSources());
    }
}
