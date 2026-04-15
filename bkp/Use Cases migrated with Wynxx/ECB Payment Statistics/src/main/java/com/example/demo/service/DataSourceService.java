package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.DataSource;
import com.example.demo.enums.DataSourceStatus;
import com.example.demo.enums.DataSourceType;
import com.example.demo.repository.DataSourceRepository;
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
public class DataSourceService {

    private final DataSourceRepository dataSourceRepository;

    @Transactional
    public DataSourceResponseDto createDataSource(CreateDataSourceRequestDto request) {
        log.info("Creating new data source with name: {}", request.getName());

        if (dataSourceRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Data source with name '" + request.getName() + "' already exists");
        }

        DataSource dataSource = new DataSource();
        dataSource.setName(request.getName());
        dataSource.setDescription(request.getDescription());
        dataSource.setSourceType(request.getSourceType());
        dataSource.setConnectionUrl(request.getConnectionUrl());
        dataSource.setAuthType(request.getAuthType());
        dataSource.setCredentials(request.getCredentials());
        dataSource.setStatus(DataSourceStatus.PENDING);
        dataSource.setRefreshIntervalSeconds(request.getRefreshIntervalSeconds());
        dataSource.setMaxRetries(request.getMaxRetries() != null ? request.getMaxRetries() : 3);
        dataSource.setTimeoutSeconds(request.getTimeoutSeconds() != null ? request.getTimeoutSeconds() : 30);
        dataSource.setTags(request.getTags());
        dataSource.setSchemaDefinition(request.getSchemaDefinition());
        dataSource.setRetryCount(0);
        dataSource.setIsActive(true);

        DataSource savedDataSource = dataSourceRepository.save(dataSource);
        log.info("Data source created successfully with ID: {}", savedDataSource.getId());
        return convertToResponse(savedDataSource);
    }

    @Transactional(readOnly = true)
    public Optional<DataSourceResponseDto> getDataSourceById(Long id) {
        return dataSourceRepository.findById(id).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<DataSourceResponseDto> getAllDataSources(Pageable pageable) {
        return dataSourceRepository.findAll(pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<DataSourceResponseDto> getDataSourcesByStatus(DataSourceStatus status, Pageable pageable) {
        return dataSourceRepository.findByStatus(status, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<DataSourceResponseDto> getDataSourcesByType(DataSourceType sourceType, Pageable pageable) {
        return dataSourceRepository.findBySourceType(sourceType, pageable).map(this::convertToResponse);
    }

    @Transactional(readOnly = true)
    public Page<DataSourceResponseDto> searchDataSources(String searchTerm, Pageable pageable) {
        return dataSourceRepository.findByNameOrDescriptionContaining(searchTerm, pageable).map(this::convertToResponse);
    }

    @Transactional
    public DataSourceResponseDto updateDataSource(Long id, UpdateDataSourceRequestDto request) {
        log.info("Updating data source with ID: {}", id);

        DataSource dataSource = dataSourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data source not found with ID: " + id));

        if (request.getName() != null && !request.getName().equals(dataSource.getName())) {
            if (dataSourceRepository.existsByName(request.getName())) {
                throw new IllegalArgumentException("Data source with name '" + request.getName() + "' already exists");
            }
            dataSource.setName(request.getName());
        }
        if (request.getDescription() != null) dataSource.setDescription(request.getDescription());
        if (request.getSourceType() != null) dataSource.setSourceType(request.getSourceType());
        if (request.getConnectionUrl() != null) dataSource.setConnectionUrl(request.getConnectionUrl());
        if (request.getAuthType() != null) dataSource.setAuthType(request.getAuthType());
        if (request.getCredentials() != null) dataSource.setCredentials(request.getCredentials());
        if (request.getStatus() != null) dataSource.setStatus(request.getStatus());
        if (request.getRefreshIntervalSeconds() != null) dataSource.setRefreshIntervalSeconds(request.getRefreshIntervalSeconds());
        if (request.getMaxRetries() != null) dataSource.setMaxRetries(request.getMaxRetries());
        if (request.getTimeoutSeconds() != null) dataSource.setTimeoutSeconds(request.getTimeoutSeconds());
        if (request.getTags() != null) dataSource.setTags(request.getTags());
        if (request.getSchemaDefinition() != null) dataSource.setSchemaDefinition(request.getSchemaDefinition());
        if (request.getIsActive() != null) dataSource.setIsActive(request.getIsActive());

        DataSource updatedDataSource = dataSourceRepository.save(dataSource);
        log.info("Data source updated successfully with ID: {}", updatedDataSource.getId());
        return convertToResponse(updatedDataSource);
    }

    @Transactional
    public void deleteDataSource(Long id) {
        log.info("Deleting data source with ID: {}", id);

        DataSource dataSource = dataSourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data source not found with ID: " + id));

        if (!dataSource.getStatus().canBeDeleted()) {
            throw new IllegalStateException("Data source with status '" + dataSource.getStatus() +
                    "' cannot be deleted. Please deactivate it first.");
        }

        dataSourceRepository.deleteById(id);
        log.info("Data source deleted successfully with ID: {}", id);
    }

    @Transactional
    public DataSourceResponseDto activateDataSource(Long id) {
        log.info("Activating data source with ID: {}", id);

        DataSource dataSource = dataSourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data source not found with ID: " + id));

        if (!dataSource.getStatus().canBeActivated()) {
            throw new IllegalStateException("Data source with status '" + dataSource.getStatus() + "' cannot be activated");
        }

        dataSource.markAsActive();
        DataSource savedDataSource = dataSourceRepository.save(dataSource);
        log.info("Data source activated successfully with ID: {}", id);
        return convertToResponse(savedDataSource);
    }

    @Transactional
    public DataSourceResponseDto deactivateDataSource(Long id) {
        log.info("Deactivating data source with ID: {}", id);

        DataSource dataSource = dataSourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data source not found with ID: " + id));

        dataSource.setStatus(DataSourceStatus.INACTIVE);
        dataSource.setIsActive(false);
        DataSource savedDataSource = dataSourceRepository.save(dataSource);
        log.info("Data source deactivated successfully with ID: {}", id);
        return convertToResponse(savedDataSource);
    }

    @Transactional
    public TestConnectionResponseDto testConnection(TestConnectionRequestDto request) {
        log.info("Testing connection for source type: {}", request.getSourceType());

        long startTime = System.currentTimeMillis();
        try {
            // Validate connection URL format based on source type
            validateConnectionUrl(request.getSourceType(), request.getConnectionUrl());

            // Validate credentials if auth type requires them
            if (request.getAuthType() != null && request.getAuthType().requiresCredentials()) {
                if (request.getCredentials() == null || request.getCredentials().isBlank()) {
                    return TestConnectionResponseDto.failure(
                            "Credentials are required for auth type: " + request.getAuthType(),
                            "Missing credentials for authentication type " + request.getAuthType().getDisplayName()
                    );
                }
            }

            long connectionTimeMs = System.currentTimeMillis() - startTime;
            log.info("Connection test successful for source type: {} in {}ms", request.getSourceType(), connectionTimeMs);
            return TestConnectionResponseDto.success(
                    "Connection configuration is valid",
                    connectionTimeMs,
                    "Source type: " + request.getSourceType().getDisplayName()
            );
        } catch (Exception e) {
            log.error("Connection test failed for source type: {}", request.getSourceType(), e);
            return TestConnectionResponseDto.failure(
                    "Connection test failed: " + e.getMessage(),
                    e.getClass().getSimpleName() + ": " + e.getMessage()
            );
        }
    }

    @Transactional
    public DataSourceResponseDto testConnectionById(Long id) {
        log.info("Testing connection for data source ID: {}", id);

        DataSource dataSource = dataSourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data source not found with ID: " + id));

        try {
            validateConnectionUrl(dataSource.getSourceType(), dataSource.getConnectionUrl());
            dataSource.markAsActive();
            log.info("Connection test successful for data source ID: {}", id);
        } catch (Exception e) {
            log.error("Connection test failed for data source ID: {}", id, e);
            dataSource.markAsError("Connection test failed: " + e.getMessage());
        }

        return convertToResponse(dataSourceRepository.save(dataSource));
    }

    @Transactional
    public DataSourceResponseDto recordSyncSuccess(Long id, Long recordCount) {
        log.info("Recording successful sync for data source ID: {}", id);

        DataSource dataSource = dataSourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data source not found with ID: " + id));

        dataSource.recordSuccessfulSync();
        if (recordCount != null) {
            dataSource.setRecordCount(recordCount);
        }

        return convertToResponse(dataSourceRepository.save(dataSource));
    }

    @Transactional
    public DataSourceResponseDto recordSyncError(Long id, String errorMessage) {
        log.info("Recording sync error for data source ID: {}", id);

        DataSource dataSource = dataSourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Data source not found with ID: " + id));

        dataSource.markAsError(errorMessage);
        return convertToResponse(dataSourceRepository.save(dataSource));
    }

    @Transactional(readOnly = true)
    public List<DataSourceResponseDto> getDataSourcesDueForSync() {
        LocalDateTime threshold = LocalDateTime.now().minusSeconds(300); // Default 5 min threshold
        return dataSourceRepository.findDataSourcesDueForSync(threshold)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<DataSourceResponseDto> getRetryableErrorDataSources() {
        return dataSourceRepository.findRetryableErrorDataSources()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private void validateConnectionUrl(DataSourceType sourceType, String connectionUrl) {
        if (connectionUrl == null || connectionUrl.isBlank()) {
            throw new IllegalArgumentException("Connection URL cannot be empty");
        }

        switch (sourceType) {
            case DATABASE -> {
                if (!connectionUrl.startsWith("jdbc:")) {
                    throw new IllegalArgumentException("Database connection URL must start with 'jdbc:'");
                }
            }
            case REST_API, GRAPHQL, WEBHOOK -> {
                if (!connectionUrl.startsWith("http://") && !connectionUrl.startsWith("https://")) {
                    throw new IllegalArgumentException("API connection URL must start with 'http://' or 'https://'");
                }
            }
            case FILE -> {
                if (!connectionUrl.startsWith("/") && !connectionUrl.startsWith("s3://") &&
                    !connectionUrl.startsWith("gs://") && !connectionUrl.startsWith("file://")) {
                    throw new IllegalArgumentException("File connection URL must be a valid file path or cloud storage URL");
                }
            }
            default -> log.debug("No specific URL validation for source type: {}", sourceType);
        }
    }

    public DataSourceResponseDto convertToResponse(DataSource dataSource) {
        DataSourceResponseDto response = new DataSourceResponseDto();
        response.setId(dataSource.getId());
        response.setName(dataSource.getName());
        response.setDescription(dataSource.getDescription());
        response.setSourceType(dataSource.getSourceType());
        response.setSourceTypeDisplayName(dataSource.getSourceType() != null ? dataSource.getSourceType().getDisplayName() : null);
        response.setConnectionUrl(dataSource.getConnectionUrl());
        response.setAuthType(dataSource.getAuthType());
        response.setAuthTypeDisplayName(dataSource.getAuthType() != null ? dataSource.getAuthType().getDisplayName() : null);
        response.setStatus(dataSource.getStatus());
        response.setStatusDisplayName(dataSource.getStatus() != null ? dataSource.getStatus().getDisplayName() : null);
        response.setRefreshIntervalSeconds(dataSource.getRefreshIntervalSeconds());
        response.setLastSyncAt(dataSource.getLastSyncAt());
        response.setErrorMessage(dataSource.getErrorMessage());
        response.setRetryCount(dataSource.getRetryCount());
        response.setMaxRetries(dataSource.getMaxRetries());
        response.setTimeoutSeconds(dataSource.getTimeoutSeconds());
        response.setTags(dataSource.getTags());
        response.setSchemaDefinition(dataSource.getSchemaDefinition());
        response.setRecordCount(dataSource.getRecordCount());
        response.setIsActive(dataSource.getIsActive());
        response.setCanRetry(dataSource.canRetry());
        response.setIsOperational(dataSource.isOperational());
        response.setCreatedAt(dataSource.getCreatedAt());
        response.setUpdatedAt(dataSource.getUpdatedAt());
        return response;
    }
}
