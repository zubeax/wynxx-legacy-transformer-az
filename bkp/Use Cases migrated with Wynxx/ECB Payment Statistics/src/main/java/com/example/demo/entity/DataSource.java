package com.example.demo.entity;

import com.example.demo.enums.AuthType;
import com.example.demo.enums.DataSourceStatus;
import com.example.demo.enums.DataSourceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "data_sources", indexes = {
        @Index(name = "idx_data_sources_status", columnList = "status"),
        @Index(name = "idx_data_sources_source_type", columnList = "source_type"),
        @Index(name = "idx_data_sources_name", columnList = "name")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_type", nullable = false, length = 50)
    private DataSourceType sourceType;

    @Column(name = "connection_url", nullable = false, length = 2000)
    private String connectionUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_type", nullable = false, length = 50)
    private AuthType authType = AuthType.NONE;

    @Column(name = "credentials", columnDefinition = "TEXT")
    private String credentials;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private DataSourceStatus status = DataSourceStatus.PENDING;

    @Column(name = "refresh_interval_seconds")
    private Integer refreshIntervalSeconds;

    @Column(name = "last_sync_at")
    private LocalDateTime lastSyncAt;

    @Column(name = "error_message", length = 2000)
    private String errorMessage;

    @Column(name = "retry_count", nullable = false)
    private Integer retryCount = 0;

    @Column(name = "max_retries", nullable = false)
    private Integer maxRetries = 3;

    @Column(name = "timeout_seconds", nullable = false)
    private Integer timeoutSeconds = 30;

    @Column(name = "tags", length = 500)
    private String tags;

    @Column(name = "schema_definition", columnDefinition = "TEXT")
    private String schemaDefinition;

    @Column(name = "sample_data", columnDefinition = "TEXT")
    private String sampleData;

    @Column(name = "record_count")
    private Long recordCount;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "dataSource", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AggregationJob> aggregationJobs = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public DataSource(String name, DataSourceType sourceType, String connectionUrl) {
        this.name = name;
        this.sourceType = sourceType;
        this.connectionUrl = connectionUrl;
        this.authType = AuthType.NONE;
        this.status = DataSourceStatus.PENDING;
        this.retryCount = 0;
        this.maxRetries = 3;
        this.timeoutSeconds = 30;
        this.isActive = true;
    }

    public void markAsActive() {
        this.status = DataSourceStatus.ACTIVE;
        this.errorMessage = null;
        this.retryCount = 0;
    }

    public void markAsError(String errorMessage) {
        this.status = DataSourceStatus.ERROR;
        this.errorMessage = errorMessage;
        this.retryCount = this.retryCount + 1;
    }

    public void recordSuccessfulSync() {
        this.lastSyncAt = LocalDateTime.now();
        this.retryCount = 0;
        this.errorMessage = null;
        this.status = DataSourceStatus.ACTIVE;
    }

    public boolean canRetry() {
        return this.retryCount < this.maxRetries;
    }

    public boolean isOperational() {
        return this.status == DataSourceStatus.ACTIVE && Boolean.TRUE.equals(this.isActive);
    }
}
