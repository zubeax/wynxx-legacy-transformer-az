package com.example.demo.service;

import com.example.demo.dto.AggregationMetricResponseDto;
import com.example.demo.dto.CreateAggregationMetricRequestDto;
import com.example.demo.entity.AggregationJob;
import com.example.demo.entity.AggregationMetric;
import com.example.demo.entity.DataPipeline;
import com.example.demo.repository.AggregationJobRepository;
import com.example.demo.repository.AggregationMetricRepository;
import com.example.demo.repository.DataPipelineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AggregationMetricService {

    private final AggregationMetricRepository aggregationMetricRepository;
    private final DataPipelineRepository dataPipelineRepository;
    private final AggregationJobRepository aggregationJobRepository;

    @Transactional
    public AggregationMetricResponseDto createMetric(CreateAggregationMetricRequestDto request) {
        log.info("Creating aggregation metric: {}", request.getMetricName());

        AggregationMetric metric = new AggregationMetric();
        metric.setMetricName(request.getMetricName());
        metric.setMetricValue(request.getMetricValue());
        metric.setMetricUnit(request.getMetricUnit());
        metric.setMetricCategory(request.getMetricCategory());
        metric.setTags(request.getTags());
        metric.setRecordedAt(request.getRecordedAt() != null ? request.getRecordedAt() : LocalDateTime.now());
        metric.setPeriodStart(request.getPeriodStart());
        metric.setPeriodEnd(request.getPeriodEnd());
        metric.setAdditionalData(request.getAdditionalData());

        if (request.getDataPipelineId() != null) {
            DataPipeline pipeline = dataPipelineRepository.findById(request.getDataPipelineId())
                    .orElseThrow(() -> new IllegalArgumentException("Data pipeline not found with ID: " + request.getDataPipelineId()));
            metric.setDataPipeline(pipeline);
        }

        if (request.getAggregationJobId() != null) {
            AggregationJob job = aggregationJobRepository.findById(request.getAggregationJobId())
                    .orElseThrow(() -> new IllegalArgumentException("Aggregation job not found with ID: " + request.getAggregationJobId()));
            metric.setAggregationJob(job);
        }

        AggregationMetric savedMetric = aggregationMetricRepository.save(metric);
        log.info("Aggregation metric created with ID: {}", savedMetric.getId());
        return convertToResponse(savedMetric);
    }

    @Transactional
    public AggregationMetricResponseDto recordJobMetric(Long jobId, String metricName,
                                                         BigDecimal metricValue, String metricUnit,
                                                         String metricCategory) {
        log.info("Recording metric '{}' for job ID: {}", metricName, jobId);

        AggregationJob job = aggregationJobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Aggregation job not found with ID: " + jobId));

        AggregationMetric metric = new AggregationMetric();
        metric.setAggregationJob(job);
        metric.setDataPipeline(job.getDataPipeline());
        metric.setMetricName(metricName);
        metric.setMetricValue(metricValue);
        metric.setMetricUnit(metricUnit);
        metric.setMetricCategory(metricCategory);
        metric.setRecordedAt(LocalDateTime.now());

        return convertToResponse(aggregationMetricRepository.save(metric));
    }

    @Transactional(readOnly = true)
    public Optional<AggregationMetricResponseDto> getMetricById(Long id) {
        return aggregationMetricRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationMetricResponseDto> getAllMetrics(Pageable pageable) {
        return aggregationMetricRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationMetricResponseDto> getMetricsByPipelineId(Long pipelineId, Pageable pageable) {
        return aggregationMetricRepository.findByDataPipelineId(pipelineId, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationMetricResponseDto> getMetricsByJobId(Long jobId, Pageable pageable) {
        return aggregationMetricRepository.findByAggregationJobId(jobId, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public List<AggregationMetricResponseDto> getMetricsByName(String metricName) {
        return aggregationMetricRepository.findByMetricName(metricName)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AggregationMetricResponseDto> getMetricsByNameSince(String metricName, LocalDateTime since) {
        return aggregationMetricRepository.findByMetricNameSince(metricName, since)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AggregationMetricResponseDto> getMetricsByPipelineAndPeriod(Long pipelineId,
                                                                              LocalDateTime start,
                                                                              LocalDateTime end) {
        return aggregationMetricRepository.findByPipelineIdAndPeriod(pipelineId, start, end)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BigDecimal getAverageMetricValue(String metricName, LocalDateTime since) {
        return aggregationMetricRepository.findAverageMetricValueSince(metricName, since);
    }

    @Transactional(readOnly = true)
    public BigDecimal getMaxMetricValue(String metricName, LocalDateTime since) {
        return aggregationMetricRepository.findMaxMetricValueSince(metricName, since);
    }

    @Transactional(readOnly = true)
    public BigDecimal getMinMetricValue(String metricName, LocalDateTime since) {
        return aggregationMetricRepository.findMinMetricValueSince(metricName, since);
    }

    @Transactional(readOnly = true)
    public List<String> getAllDistinctMetricNames() {
        return aggregationMetricRepository.findAllDistinctMetricNames();
    }

    @Transactional(readOnly = true)
    public List<String> getAllDistinctMetricCategories() {
        return aggregationMetricRepository.findAllDistinctMetricCategories();
    }

    @Transactional(readOnly = true)
    public Page<AggregationMetricResponseDto> getMetricsByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return aggregationMetricRepository.findByRecordedAtBetween(start, end, pageable).map(this::convertToResponse);
    }

    @Transactional
    public void deleteMetric(Long id) {
        log.info("Deleting aggregation metric with ID: {}", id);

        if (!aggregationMetricRepository.existsById(id)) {
            throw new IllegalArgumentException("Aggregation metric not found with ID: " + id);
        }

        aggregationMetricRepository.deleteById(id);
        log.info("Aggregation metric deleted successfully with ID: {}", id);
    }

    public AggregationMetricResponseDto convertToResponse(AggregationMetric metric) {
        AggregationMetricResponseDto response = new AggregationMetricResponseDto();
        response.setId(metric.getId());
        response.setDataPipelineId(metric.getDataPipeline() != null ? metric.getDataPipeline().getId() : null);
        response.setDataPipelineName(metric.getDataPipeline() != null ? metric.getDataPipeline().getName() : null);
        response.setAggregationJobId(metric.getAggregationJob() != null ? metric.getAggregationJob().getId() : null);
        response.setAggregationJobName(metric.getAggregationJob() != null ? metric.getAggregationJob().getName() : null);
        response.setMetricName(metric.getMetricName());
        response.setMetricValue(metric.getMetricValue());
        response.setMetricUnit(metric.getMetricUnit());
        response.setMetricCategory(metric.getMetricCategory());
        response.setTags(metric.getTags());
        response.setRecordedAt(metric.getRecordedAt());
        response.setPeriodStart(metric.getPeriodStart());
        response.setPeriodEnd(metric.getPeriodEnd());
        response.setAdditionalData(metric.getAdditionalData());
        response.setCreatedAt(metric.getCreatedAt());
        return response;
    }
}
