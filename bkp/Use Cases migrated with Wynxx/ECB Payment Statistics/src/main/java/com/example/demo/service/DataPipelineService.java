package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.DataPipeline;
import com.example.demo.enums.PipelineStatus;
import com.example.demo.repository.DataPipelineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataPipelineService {

    private final DataPipelineRepository dataPipelineRepository;

    @Transactional
    public DataPipelineResponseDto createDataPipeline(CreateDataPipelineRequestDto request) {
        log.info("Creating new data pipeline with name: {}", request.getName());

        if (dataPipelineRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Data pipeline with name '" + request.getName() + "' already exists");
        }

        DataPipeline pipeline = new DataPipeline();
        pipeline.setName(request.getName());
        pipeline.setDescription(request.getDescription());
        pipeline.setStatus(PipelineStatus.DRAFT);
        pipeline.setPipelineConfig(request.getPipelineConfig());
        pipeline.setScheduleExpression(request.getScheduleExpression());
        pipeline.setOwner(request.getOwner());
        pipeline.setTags(request.getTags());
        pipeline.setNotificationEmails(request.getNotificationEmails());
        pipeline.setSourceCount(0);
        pipeline.setTotalExecutions(0L);
        pipeline.setSuccessfulExecutions(0L);
        pipeline.setFailedExecutions(0L);
        pipeline.setIsActive(true);

        DataPipeline savedPipeline = dataPipelineRepository.save(pipeline);
        log.info("Data pipeline created successfully with ID: {}", savedPipeline.getId());
        return convertToResponse(savedPipeline);
    }

    @Transactional(readOnly = true)
    public Optional<DataPipelineResponseDto> getDataPipelineById(Long id) {
        return dataPipelineRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<DataPipelineResponseDto> getAllDataPipelines(Pageable pageable) {
        return dataPipelineRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<DataPipelineResponseDto> getDataPipelinesByStatus(PipelineStatus status, Pageable pageable) {
        return dataPipelineRepository.findByStatus(status, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<DataPipelineResponseDto> searchDataPipelines(String searchTerm, Pageable pageable) {
        return dataPipelineRepository.findByNameOrDescriptionContaining(searchTerm, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<DataPipelineResponseDto> getDataPipelinesByOwner(String owner, Pageable pageable) {
        return dataPipelineRepository.findByOwner(owner, pageable).map(this::convertToResponse);
    }

    @Transactional
    public DataPipelineResponseDto updateDataPipeline(Long id, UpdateDataPipelineRequestDto request) {
        log.info("Updating data pipeline with ID: {}", id);

        DataPipeline pipeline = dataPipelineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data pipeline not found with ID: " + id));

        if (request.getName() != null && !request.getName().equals(pipeline.getName())) {
            if (dataPipelineRepository.existsByName(request.getName())) {
                throw new IllegalArgumentException("Data pipeline with name '" + request.getName() + "' already exists");
            }
            pipeline.setName(request.getName());
        }
        if (request.getDescription() != null) pipeline.setDescription(request.getDescription());
        if (request.getPipelineConfig() != null) pipeline.setPipelineConfig(request.getPipelineConfig());
        if (request.getScheduleExpression() != null) pipeline.setScheduleExpression(request.getScheduleExpression());
        if (request.getOwner() != null) pipeline.setOwner(request.getOwner());
        if (request.getTags() != null) pipeline.setTags(request.getTags());
        if (request.getNotificationEmails() != null) pipeline.setNotificationEmails(request.getNotificationEmails());
        if (request.getIsActive() != null) pipeline.setIsActive(request.getIsActive());

        if (request.getStatus() != null) {
            validateStatusTransition(pipeline.getStatus(), request.getStatus());
            pipeline.setStatus(request.getStatus());
        }

        DataPipeline updatedPipeline = dataPipelineRepository.save(pipeline);
        log.info("Data pipeline updated successfully with ID: {}", updatedPipeline.getId());
        return convertToResponse(updatedPipeline);
    }

    @Transactional
    public void deleteDataPipeline(Long id) {
        log.info("Deleting data pipeline with ID: {}", id);

        DataPipeline pipeline = dataPipelineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data pipeline not found with ID: " + id));

        if (pipeline.getStatus() == PipelineStatus.ACTIVE) {
            throw new IllegalStateException("Cannot delete an active pipeline. Please deactivate it first.");
        }

        dataPipelineRepository.deleteById(id);
        log.info("Data pipeline deleted successfully with ID: {}", id);
    }

    @Transactional
    public DataPipelineResponseDto activatePipeline(Long id) {
        log.info("Activating data pipeline with ID: {}", id);

        DataPipeline pipeline = dataPipelineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data pipeline not found with ID: " + id));

        if (!pipeline.getStatus().canBeActivated()) {
            throw new IllegalStateException("Pipeline with status '" + pipeline.getStatus() + "' cannot be activated");
        }

        pipeline.setStatus(PipelineStatus.ACTIVE);
        pipeline.setIsActive(true);
        pipeline.setErrorMessage(null);

        DataPipeline savedPipeline = dataPipelineRepository.save(pipeline);
        log.info("Data pipeline activated successfully with ID: {}", id);
        return convertToResponse(savedPipeline);
    }

    @Transactional
    public DataPipelineResponseDto deactivatePipeline(Long id) {
        log.info("Deactivating data pipeline with ID: {}", id);

        DataPipeline pipeline = dataPipelineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data pipeline not found with ID: " + id));

        pipeline.setStatus(PipelineStatus.INACTIVE);
        pipeline.setIsActive(false);

        DataPipeline savedPipeline = dataPipelineRepository.save(pipeline);
        log.info("Data pipeline deactivated successfully with ID: {}", id);
        return convertToResponse(savedPipeline);
    }

    @Transactional
    public DataPipelineResponseDto pausePipeline(Long id) {
        log.info("Pausing data pipeline with ID: {}", id);

        DataPipeline pipeline = dataPipelineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data pipeline not found with ID: " + id));

        if (pipeline.getStatus() != PipelineStatus.ACTIVE) {
            throw new IllegalStateException("Only active pipelines can be paused");
        }

        pipeline.setStatus(PipelineStatus.PAUSED);

        DataPipeline savedPipeline = dataPipelineRepository.save(pipeline);
        log.info("Data pipeline paused successfully with ID: {}", id);
        return convertToResponse(savedPipeline);
    }

    @Transactional
    public DataPipelineResponseDto recordExecution(Long id, boolean success, long executionTimeMs) {
        log.info("Recording execution for pipeline ID: {}, success: {}", id, success);

        DataPipeline pipeline = dataPipelineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data pipeline not found with ID: " + id));

        pipeline.recordExecution(success, executionTimeMs);

        return convertToResponse(dataPipelineRepository.save(pipeline));
    }

    @Transactional
    public DataPipelineResponseDto updateSourceCount(Long id, int sourceCount) {
        DataPipeline pipeline = dataPipelineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data pipeline not found with ID: " + id));

        pipeline.setSourceCount(sourceCount);
        return convertToResponse(dataPipelineRepository.save(pipeline));
    }

    @Transactional(readOnly = true)
    public List<DataPipelineResponseDto> getPipelinesDueForExecution() {
        return dataPipelineRepository.findPipelinesDueForExecution(LocalDateTime.now())
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DataPipelineResponseDto> getPipelinesWithRecentFailures() {
        LocalDateTime since = LocalDateTime.now().minusHours(24);
        return dataPipelineRepository.findPipelinesWithRecentFailures(since)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private void validateStatusTransition(PipelineStatus currentStatus, PipelineStatus newStatus) {
        boolean valid = switch (newStatus) {
            case ACTIVE -> currentStatus.canBeActivated();
            case INACTIVE -> currentStatus != PipelineStatus.ARCHIVED;
            case PAUSED -> currentStatus == PipelineStatus.ACTIVE;
            case ARCHIVED -> currentStatus == PipelineStatus.INACTIVE;
            case DRAFT -> currentStatus == PipelineStatus.INACTIVE;
            case ERROR -> true;
        };

        if (!valid) {
            throw new IllegalStateException(
                    "Invalid status transition from '" + currentStatus + "' to '" + newStatus + "'");
        }
    }

    public DataPipelineResponseDto convertToResponse(DataPipeline pipeline) {
        DataPipelineResponseDto response = new DataPipelineResponseDto();
        response.setId(pipeline.getId());
        response.setName(pipeline.getName());
        response.setDescription(pipeline.getDescription());
        response.setStatus(pipeline.getStatus());
        response.setStatusDisplayName(pipeline.getStatus() != null ? pipeline.getStatus().getDisplayName() : null);
        response.setPipelineConfig(pipeline.getPipelineConfig());
        response.setScheduleExpression(pipeline.getScheduleExpression());
        response.setSourceCount(pipeline.getSourceCount());
        response.setLastExecutedAt(pipeline.getLastExecutedAt());
        response.setNextScheduledAt(pipeline.getNextScheduledAt());
        response.setTotalExecutions(pipeline.getTotalExecutions());
        response.setSuccessfulExecutions(pipeline.getSuccessfulExecutions());
        response.setFailedExecutions(pipeline.getFailedExecutions());
        response.setAverageExecutionTimeMs(pipeline.getAverageExecutionTimeMs());
        response.setSuccessRate(pipeline.getSuccessRate());
        response.setOwner(pipeline.getOwner());
        response.setTags(pipeline.getTags());
        response.setNotificationEmails(pipeline.getNotificationEmails());
        response.setIsActive(pipeline.getIsActive());
        response.setIsRunnable(pipeline.isRunnable());
        response.setErrorMessage(pipeline.getErrorMessage());
        response.setCreatedAt(pipeline.getCreatedAt());
        response.setUpdatedAt(pipeline.getUpdatedAt());
        return response;
    }
}
