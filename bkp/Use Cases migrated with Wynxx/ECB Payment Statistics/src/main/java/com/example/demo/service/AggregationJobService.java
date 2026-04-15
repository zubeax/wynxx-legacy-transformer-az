package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.AggregationJob;
import com.example.demo.entity.DataPipeline;
import com.example.demo.entity.DataSource;
import com.example.demo.enums.JobStatus;
import com.example.demo.enums.JobType;
import com.example.demo.enums.OutputFormat;
import com.example.demo.repository.AggregationJobRepository;
import com.example.demo.repository.DataPipelineRepository;
import com.example.demo.repository.DataSourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class AggregationJobService {

    private final AggregationJobRepository aggregationJobRepository;
    private final DataSourceRepository dataSourceRepository;
    private final DataPipelineRepository dataPipelineRepository;

    @Transactional
    public AggregationJobResponseDto createAggregationJob(CreateAggregationJobRequestDto request) {
        log.info("Creating new aggregation job with name: {}", request.getName());

        DataSource dataSource = dataSourceRepository.findById(request.getDataSourceId())
                .orElseThrow(() -> new IllegalArgumentException("Data source not found with ID: " + request.getDataSourceId()));

        if (!dataSource.isOperational()) {
            throw new IllegalStateException("Data source '" + dataSource.getName() +
                    "' is not operational. Status: " + dataSource.getStatus());
        }

        AggregationJob job = new AggregationJob();
        job.setName(request.getName());
        job.setDescription(request.getDescription());
        job.setStatus(JobStatus.PENDING);
        job.setJobType(request.getJobType());
        job.setDataSource(dataSource);
        job.setScheduleExpression(request.getScheduleExpression());
        job.setTransformationScript(request.getTransformationScript());
        job.setFilterExpression(request.getFilterExpression());
        job.setOutputFormat(request.getOutputFormat() != null ? request.getOutputFormat() : OutputFormat.JSON);
        job.setOutputDestination(request.getOutputDestination());
        job.setRecordsProcessed(0L);
        job.setRecordsFailed(0L);
        job.setPriority(request.getPriority() != null ? request.getPriority() : 5);
        job.setRetryOnFailure(request.getRetryOnFailure() != null ? request.getRetryOnFailure() : true);
        job.setMaxRetries(request.getMaxRetries() != null ? request.getMaxRetries() : 3);
        job.setRetryCount(0);
        job.setTimeoutSeconds(request.getTimeoutSeconds());

        if (request.getDataPipelineId() != null) {
            DataPipeline pipeline = dataPipelineRepository.findById(request.getDataPipelineId())
                    .orElseThrow(() -> new IllegalArgumentException("Data pipeline not found with ID: " + request.getDataPipelineId()));
            job.setDataPipeline(pipeline);
        }

        AggregationJob savedJob = aggregationJobRepository.save(job);
        log.info("Aggregation job created successfully with ID: {}", savedJob.getId());
        return convertToResponse(savedJob);
    }

    @Transactional(readOnly = true)
    public Optional<AggregationJobResponseDto> getAggregationJobById(Long id) {
        return aggregationJobRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationJobResponseDto> getAllAggregationJobs(Pageable pageable) {
        return aggregationJobRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationJobResponseDto> getAggregationJobsByStatus(JobStatus status, Pageable pageable) {
        return aggregationJobRepository.findByStatus(status, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationJobResponseDto> getAggregationJobsByDataSource(Long dataSourceId, Pageable pageable) {
        return aggregationJobRepository.findByDataSourceId(dataSourceId, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationJobResponseDto> getAggregationJobsByPipeline(Long pipelineId, Pageable pageable) {
        return aggregationJobRepository.findByDataPipelineId(pipelineId, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationJobResponseDto> getAggregationJobsByType(JobType jobType, Pageable pageable) {
        return aggregationJobRepository.findByJobType(jobType, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationJobResponseDto> searchAggregationJobs(String searchTerm, Pageable pageable) {
        return aggregationJobRepository.findByNameContaining(searchTerm, pageable).map(this::convertToResponse);
    }

    @Transactional
    public AggregationJobResponseDto updateAggregationJob(Long id, UpdateAggregationJobRequestDto request) {
        log.info("Updating aggregation job with ID: {}", id);

        AggregationJob job = aggregationJobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aggregation job not found with ID: " + id));

        if (job.isRunning()) {
            throw new IllegalStateException("Cannot update a running job. Cancel it first.");
        }

        if (request.getName() != null) job.setName(request.getName());
        if (request.getDescription() != null) job.setDescription(request.getDescription());
        if (request.getJobType() != null) job.setJobType(request.getJobType());
        if (request.getScheduleExpression() != null) job.setScheduleExpression(request.getScheduleExpression());
        if (request.getTransformationScript() != null) job.setTransformationScript(request.getTransformationScript());
        if (request.getFilterExpression() != null) job.setFilterExpression(request.getFilterExpression());
        if (request.getOutputFormat() != null) job.setOutputFormat(request.getOutputFormat());
        if (request.getOutputDestination() != null) job.setOutputDestination(request.getOutputDestination());
        if (request.getPriority() != null) job.setPriority(request.getPriority());
        if (request.getRetryOnFailure() != null) job.setRetryOnFailure(request.getRetryOnFailure());
        if (request.getMaxRetries() != null) job.setMaxRetries(request.getMaxRetries());
        if (request.getTimeoutSeconds() != null) job.setTimeoutSeconds(request.getTimeoutSeconds());

        if (request.getDataSourceId() != null) {
            DataSource dataSource = dataSourceRepository.findById(request.getDataSourceId())
                    .orElseThrow(() -> new IllegalArgumentException("Data source not found with ID: " + request.getDataSourceId()));
            job.setDataSource(dataSource);
        }

        if (request.getDataPipelineId() != null) {
            DataPipeline pipeline = dataPipelineRepository.findById(request.getDataPipelineId())
                    .orElseThrow(() -> new IllegalArgumentException("Data pipeline not found with ID: " + request.getDataPipelineId()));
            job.setDataPipeline(pipeline);
        }

        AggregationJob updatedJob = aggregationJobRepository.save(job);
        log.info("Aggregation job updated successfully with ID: {}", updatedJob.getId());
        return convertToResponse(updatedJob);
    }

    @Transactional
    public void deleteAggregationJob(Long id) {
        log.info("Deleting aggregation job with ID: {}", id);

        AggregationJob job = aggregationJobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aggregation job not found with ID: " + id));

        if (job.isRunning()) {
            throw new IllegalStateException("Cannot delete a running job. Cancel it first.");
        }

        aggregationJobRepository.deleteById(id);
        log.info("Aggregation job deleted successfully with ID: {}", id);
    }

    @Transactional
    public AggregationJobResponseDto triggerJobExecution(Long id, JobExecutionRequestDto request) {
        log.info("Triggering execution for aggregation job ID: {}", id);

        AggregationJob job = aggregationJobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aggregation job not found with ID: " + id));

        if (job.isRunning() && (request.getForceExecution() == null || !request.getForceExecution())) {
            throw new IllegalStateException("Job is already running. Use forceExecution=true to override.");
        }

        if (!job.getStatus().canBeCancelled() && job.getStatus() != JobStatus.PENDING &&
            job.getStatus() != JobStatus.FAILED && job.getStatus() != JobStatus.COMPLETED) {
            throw new IllegalStateException("Job with status '" + job.getStatus() + "' cannot be triggered");
        }

        // Apply overrides if provided
        if (request.getFilterExpressionOverride() != null) {
            job.setFilterExpression(request.getFilterExpressionOverride());
        }
        if (request.getTransformationScriptOverride() != null) {
            job.setTransformationScript(request.getTransformationScriptOverride());
        }

        job.start();
        AggregationJob savedJob = aggregationJobRepository.save(job);
        log.info("Aggregation job execution triggered for ID: {}", id);
        return convertToResponse(savedJob);
    }

    @Transactional
    public AggregationJobResponseDto cancelJob(Long id) {
        log.info("Cancelling aggregation job with ID: {}", id);

        AggregationJob job = aggregationJobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aggregation job not found with ID: " + id));

        if (!job.getStatus().canBeCancelled()) {
            throw new IllegalStateException("Job with status '" + job.getStatus() + "' cannot be cancelled");
        }

        job.cancel();
        AggregationJob savedJob = aggregationJobRepository.save(job);
        log.info("Aggregation job cancelled successfully with ID: {}", id);
        return convertToResponse(savedJob);
    }

    @Transactional
    public AggregationJobResponseDto retryJob(Long id) {
        log.info("Retrying aggregation job with ID: {}", id);

        AggregationJob job = aggregationJobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aggregation job not found with ID: " + id));

        if (!job.getStatus().canBeRetried()) {
            throw new IllegalStateException("Job with status '" + job.getStatus() + "' cannot be retried");
        }

        if (!job.canRetry()) {
            throw new IllegalStateException("Job has exceeded maximum retry attempts (" + job.getMaxRetries() + ")");
        }

        job.setStatus(JobStatus.RETRYING);
        job.start();
        AggregationJob savedJob = aggregationJobRepository.save(job);
        log.info("Aggregation job retry initiated for ID: {}", id);
        return convertToResponse(savedJob);
    }

    @Transactional
    public AggregationJobResponseDto completeJob(Long id, long recordsProcessed, long recordsFailed) {
        log.info("Completing aggregation job with ID: {}, processed: {}, failed: {}", id, recordsProcessed, recordsFailed);

        AggregationJob job = aggregationJobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aggregation job not found with ID: " + id));

        job.complete(recordsProcessed, recordsFailed);
        AggregationJob savedJob = aggregationJobRepository.save(job);
        log.info("Aggregation job completed for ID: {}", id);
        return convertToResponse(savedJob);
    }

    @Transactional
    public AggregationJobResponseDto failJob(Long id, String errorMessage) {
        log.info("Marking aggregation job as failed with ID: {}", id);

        AggregationJob job = aggregationJobRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aggregation job not found with ID: " + id));

        job.fail(errorMessage);
        AggregationJob savedJob = aggregationJobRepository.save(job);
        log.info("Aggregation job marked as failed for ID: {}", id);
        return convertToResponse(savedJob);
    }

    @Transactional(readOnly = true)
    public List<AggregationJobResponseDto> getActiveJobs() {
        return aggregationJobRepository.findActiveJobs()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AggregationJobResponseDto> getRetryableFailedJobs() {
        return aggregationJobRepository.findRetryableFailedJobs()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AggregationJobResponseDto> getFailedJobsSince(LocalDateTime since) {
        return aggregationJobRepository.findFailedJobsSince(since)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AggregationJobResponseDto> getCompletedJobsSince(LocalDateTime since) {
        return aggregationJobRepository.findCompletedJobsSince(since)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public int processTimedOutJobs() {
        log.info("Processing timed out jobs");
        LocalDateTime timeoutThreshold = LocalDateTime.now().minusHours(24);
        List<AggregationJob> timedOutJobs = aggregationJobRepository.findTimedOutJobs(timeoutThreshold);

        for (AggregationJob job : timedOutJobs) {
            if (job.getTimeoutSeconds() != null) {
                LocalDateTime jobTimeout = job.getStartedAt().plusSeconds(job.getTimeoutSeconds());
                if (LocalDateTime.now().isAfter(jobTimeout)) {
                    job.fail("Job timed out after " + job.getTimeoutSeconds() + " seconds");
                    aggregationJobRepository.save(job);
                    log.warn("Job ID {} timed out and marked as failed", job.getId());
                }
            }
        }

        return timedOutJobs.size();
    }

    public AggregationJobResponseDto convertToResponse(AggregationJob job) {
        AggregationJobResponseDto response = new AggregationJobResponseDto();
        response.setId(job.getId());
        response.setName(job.getName());
        response.setDescription(job.getDescription());
        response.setStatus(job.getStatus());
        response.setStatusDisplayName(job.getStatus() != null ? job.getStatus().getDisplayName() : null);
        response.setJobType(job.getJobType());
        response.setJobTypeDisplayName(job.getJobType() != null ? job.getJobType().getDisplayName() : null);
        response.setDataSourceId(job.getDataSource() != null ? job.getDataSource().getId() : null);
        response.setDataSourceName(job.getDataSource() != null ? job.getDataSource().getName() : null);
        response.setDataPipelineId(job.getDataPipeline() != null ? job.getDataPipeline().getId() : null);
        response.setDataPipelineName(job.getDataPipeline() != null ? job.getDataPipeline().getName() : null);
        response.setScheduleExpression(job.getScheduleExpression());
        response.setOutputFormat(job.getOutputFormat());
        response.setOutputFormatDisplayName(job.getOutputFormat() != null ? job.getOutputFormat().getDisplayName() : null);
        response.setOutputDestination(job.getOutputDestination());
        response.setRecordsProcessed(job.getRecordsProcessed());
        response.setRecordsFailed(job.getRecordsFailed());
        response.setStartedAt(job.getStartedAt());
        response.setCompletedAt(job.getCompletedAt());
        response.setErrorMessage(job.getErrorMessage());
        response.setPriority(job.getPriority());
        response.setRetryOnFailure(job.getRetryOnFailure());
        response.setMaxRetries(job.getMaxRetries());
        response.setRetryCount(job.getRetryCount());
        response.setTimeoutSeconds(job.getTimeoutSeconds());
        response.setExecutionTimeMs(job.getExecutionTimeMs());
        response.setSuccessRate(job.getSuccessRate());
        response.setCanRetry(job.canRetry());
        response.setIsRunning(job.isRunning());
        response.setCreatedAt(job.getCreatedAt());
        response.setUpdatedAt(job.getUpdatedAt());
        return response;
    }
}
