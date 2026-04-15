package com.example.demo.service;

import com.example.demo.dto.AggregationResultResponseDto;
import com.example.demo.entity.AggregationJob;
import com.example.demo.entity.AggregationResult;
import com.example.demo.entity.DataSource;
import com.example.demo.enums.AggregationResultStatus;
import com.example.demo.repository.AggregationJobRepository;
import com.example.demo.repository.AggregationResultRepository;
import com.example.demo.repository.DataSourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AggregationResultService {

    private final AggregationResultRepository aggregationResultRepository;
    private final AggregationJobRepository aggregationJobRepository;
    private final DataSourceRepository dataSourceRepository;

    @Transactional
    public AggregationResultResponseDto createAggregationResult(Long jobId, Long dataSourceId,
                                                                  AggregationResultStatus status,
                                                                  Long recordCount, Long failedCount,
                                                                  Long dataSizeBytes, Long executionTimeMs,
                                                                  String resultData, String resultFilePath,
                                                                  String metadata, String errorDetails) {
        log.info("Creating aggregation result for job ID: {}, data source ID: {}", jobId, dataSourceId);

        AggregationJob job = aggregationJobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Aggregation job not found with ID: " + jobId));

        DataSource dataSource = dataSourceRepository.findById(dataSourceId)
                .orElseThrow(() -> new IllegalArgumentException("Data source not found with ID: " + dataSourceId));

        AggregationResult result = new AggregationResult();
        result.setAggregationJob(job);
        result.setDataSource(dataSource);
        result.setStatus(status);
        result.setRecordCount(recordCount != null ? recordCount : 0L);
        result.setFailedCount(failedCount != null ? failedCount : 0L);
        result.setDataSizeBytes(dataSizeBytes);
        result.setExecutionTimeMs(executionTimeMs);
        result.setResultData(resultData);
        result.setResultFilePath(resultFilePath);
        result.setMetadata(metadata);
        result.setErrorDetails(errorDetails);
        result.setAggregatedAt(LocalDateTime.now());

        // Calculate checksum if result data is provided
        if (resultData != null && !resultData.isBlank()) {
            result.setChecksum(calculateChecksum(resultData));
        }

        AggregationResult savedResult = aggregationResultRepository.save(result);
        log.info("Aggregation result created with ID: {}", savedResult.getId());
        return convertToResponse(savedResult);
    }

    @Transactional(readOnly = true)
    public Optional<AggregationResultResponseDto> getAggregationResultById(Long id) {
        return aggregationResultRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationResultResponseDto> getAllAggregationResults(Pageable pageable) {
        return aggregationResultRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationResultResponseDto> getResultsByJobId(Long jobId, Pageable pageable) {
        return aggregationResultRepository.findByAggregationJobId(jobId, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationResultResponseDto> getResultsByDataSourceId(Long dataSourceId, Pageable pageable) {
        return aggregationResultRepository.findByDataSourceId(dataSourceId, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationResultResponseDto> getResultsByStatus(AggregationResultStatus status, Pageable pageable) {
        return aggregationResultRepository.findByStatus(status, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<AggregationResultResponseDto> getResultsByDateRange(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return aggregationResultRepository.findByAggregatedAtBetween(start, end, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public List<AggregationResultResponseDto> getFailedResultsSince(LocalDateTime since) {
        return aggregationResultRepository.findFailedResultsSince(since)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long getTotalRecordsProcessedSince(LocalDateTime since) {
        Long total = aggregationResultRepository.sumRecordCountSince(since);
        return total != null ? total : 0L;
    }

    @Transactional(readOnly = true)
    public Long getTotalDataSizeSince(LocalDateTime since) {
        Long total = aggregationResultRepository.sumDataSizeSince(since);
        return total != null ? total : 0L;
    }

    @Transactional
    public void deleteAggregationResult(Long id) {
        log.info("Deleting aggregation result with ID: {}", id);

        if (!aggregationResultRepository.existsById(id)) {
            throw new IllegalArgumentException("Aggregation result not found with ID: " + id);
        }

        aggregationResultRepository.deleteById(id);
        log.info("Aggregation result deleted successfully with ID: {}", id);
    }

    @Transactional(readOnly = true)
    public boolean verifyResultIntegrity(Long id) {
        AggregationResult result = aggregationResultRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aggregation result not found with ID: " + id));

        if (result.getResultData() == null || result.getChecksum() == null) {
            return false;
        }

        String calculatedChecksum = calculateChecksum(result.getResultData());
        return calculatedChecksum.equals(result.getChecksum());
    }

    private String calculateChecksum(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes());
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            log.error("Failed to calculate checksum", e);
            return null;
        }
    }

    public AggregationResultResponseDto convertToResponse(AggregationResult result) {
        AggregationResultResponseDto response = new AggregationResultResponseDto();
        response.setId(result.getId());
        response.setAggregationJobId(result.getAggregationJob() != null ? result.getAggregationJob().getId() : null);
        response.setAggregationJobName(result.getAggregationJob() != null ? result.getAggregationJob().getName() : null);
        response.setDataSourceId(result.getDataSource() != null ? result.getDataSource().getId() : null);
        response.setDataSourceName(result.getDataSource() != null ? result.getDataSource().getName() : null);
        response.setStatus(result.getStatus());
        response.setStatusDisplayName(result.getStatus() != null ? result.getStatus().getDisplayName() : null);
        response.setRecordCount(result.getRecordCount());
        response.setFailedCount(result.getFailedCount());
        response.setDataSizeBytes(result.getDataSizeBytes());
        response.setDataSizeFormatted(result.getDataSizeFormatted());
        response.setExecutionTimeMs(result.getExecutionTimeMs());
        response.setResultData(result.getResultData());
        response.setResultFilePath(result.getResultFilePath());
        response.setChecksum(result.getChecksum());
        response.setMetadata(result.getMetadata());
        response.setErrorDetails(result.getErrorDetails());
        response.setSchemaVersion(result.getSchemaVersion());
        response.setSuccessRate(result.getSuccessRate());
        response.setIsSuccessful(result.isSuccessful());
        response.setAggregatedAt(result.getAggregatedAt());
        response.setCreatedAt(result.getCreatedAt());
        return response;
    }
}
