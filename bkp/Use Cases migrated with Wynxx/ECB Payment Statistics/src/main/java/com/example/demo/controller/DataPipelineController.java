package com.example.demo.controller;

import com.example.demo.dto.DataPipelineResponseDto;
import com.example.demo.dto.CreateDataPipelineRequestDto;
import com.example.demo.dto.UpdateDataPipelineRequestDto;
import com.example.demo.enums.PipelineStatus;
import com.example.demo.service.DataPipelineService;
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
@Tag(name = "Data Pipeline Management", description = "APIs for managing data aggregation pipelines")
@RequestMapping("/api/data-pipelines")
public class DataPipelineController {

    private final DataPipelineService dataPipelineService;

    @Operation(summary = "Get all data pipelines", description = "Retrieve a paginated list of all data pipelines")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of data pipelines"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Page<DataPipelineResponseDto>> getAllDataPipelines(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(dataPipelineService.getAllDataPipelines(pageable));
    }

    @Operation(summary = "Get data pipeline by ID", description = "Retrieve a specific data pipeline by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of data pipeline"),
        @ApiResponse(responseCode = "404", description = "Data pipeline not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DataPipelineResponseDto> getDataPipelineById(
            @Parameter(description = "Pipeline ID") @PathVariable Long id) {
        return dataPipelineService.getDataPipelineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new data pipeline", description = "Create a new data aggregation pipeline")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Data pipeline created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data or duplicate name"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<DataPipelineResponseDto> createDataPipeline(
            @Valid @RequestBody CreateDataPipelineRequestDto request) {
        DataPipelineResponseDto response = dataPipelineService.createDataPipeline(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update a data pipeline", description = "Update an existing data pipeline by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data pipeline updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request data or invalid status transition"),
        @ApiResponse(responseCode = "404", description = "Data pipeline not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DataPipelineResponseDto> updateDataPipeline(
            @Parameter(description = "Pipeline ID") @PathVariable Long id,
            @Valid @RequestBody UpdateDataPipelineRequestDto request) {
        DataPipelineResponseDto response = dataPipelineService.updateDataPipeline(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete a data pipeline", description = "Delete a data pipeline by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Data pipeline deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Cannot delete active pipeline"),
        @ApiResponse(responseCode = "404", description = "Data pipeline not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataPipeline(
            @Parameter(description = "Pipeline ID") @PathVariable Long id) {
        dataPipelineService.deleteDataPipeline(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get pipelines by status", description = "Retrieve data pipelines filtered by status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "400", description = "Invalid status value"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-status/{status}")
    public ResponseEntity<Page<DataPipelineResponseDto>> getDataPipelinesByStatus(
            @Parameter(description = "Pipeline status") @PathVariable PipelineStatus status,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(dataPipelineService.getDataPipelinesByStatus(status, pageable));
    }

    @Operation(summary = "Search data pipelines", description = "Search data pipelines by name or description")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful search"),
        @ApiResponse(responseCode = "400", description = "Invalid search parameters"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search")
    public ResponseEntity<Page<DataPipelineResponseDto>> searchDataPipelines(
            @Parameter(description = "Search term") @RequestParam String q,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(dataPipelineService.searchDataPipelines(q, pageable));
    }

    @Operation(summary = "Get pipelines by owner", description = "Retrieve data pipelines filtered by owner")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/by-owner")
    public ResponseEntity<Page<DataPipelineResponseDto>> getDataPipelinesByOwner(
            @Parameter(description = "Owner email or identifier") @RequestParam String owner,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(dataPipelineService.getDataPipelinesByOwner(owner, pageable));
    }

    @Operation(summary = "Activate a data pipeline", description = "Activate a data pipeline to enable scheduled execution")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data pipeline activated successfully"),
        @ApiResponse(responseCode = "400", description = "Pipeline cannot be activated in current state"),
        @ApiResponse(responseCode = "404", description = "Data pipeline not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/activate")
    public ResponseEntity<DataPipelineResponseDto> activatePipeline(
            @Parameter(description = "Pipeline ID") @PathVariable Long id) {
        DataPipelineResponseDto response = dataPipelineService.activatePipeline(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deactivate a data pipeline", description = "Deactivate a data pipeline to stop scheduled execution")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data pipeline deactivated successfully"),
        @ApiResponse(responseCode = "404", description = "Data pipeline not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<DataPipelineResponseDto> deactivatePipeline(
            @Parameter(description = "Pipeline ID") @PathVariable Long id) {
        DataPipelineResponseDto response = dataPipelineService.deactivatePipeline(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Pause a data pipeline", description = "Pause an active data pipeline temporarily")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data pipeline paused successfully"),
        @ApiResponse(responseCode = "400", description = "Only active pipelines can be paused"),
        @ApiResponse(responseCode = "404", description = "Data pipeline not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/{id}/pause")
    public ResponseEntity<DataPipelineResponseDto> pausePipeline(
            @Parameter(description = "Pipeline ID") @PathVariable Long id) {
        DataPipelineResponseDto response = dataPipelineService.pausePipeline(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get pipelines due for execution", description = "Get pipelines that are scheduled for execution now")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/due-for-execution")
    public ResponseEntity<List<DataPipelineResponseDto>> getPipelinesDueForExecution() {
        return ResponseEntity.ok(dataPipelineService.getPipelinesDueForExecution());
    }

    @Operation(summary = "Get pipelines with recent failures", description = "Get pipelines that have had failures in the last 24 hours")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/with-recent-failures")
    public ResponseEntity<List<DataPipelineResponseDto>> getPipelinesWithRecentFailures() {
        return ResponseEntity.ok(dataPipelineService.getPipelinesWithRecentFailures());
    }
}
