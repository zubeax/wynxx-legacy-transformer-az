package com.example.demo.dto;

import com.example.demo.enums.AuthType;
import com.example.demo.enums.DataSourceStatus;
import com.example.demo.enums.DataSourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for data source information")
public class DataSourceResponseDto {

    @Schema(description = "Unique identifier of the data source", example = "1")
    private Long id;

    @Schema(description = "Name of the data source", example = "Production PostgreSQL DB")
    private String name;

    @Schema(description = "Description of the data source")
    private String description;

    @Schema(description = "Type of the data source", example = "DATABASE")
    private DataSourceType sourceType;

    @Schema(description = "Display name of the source type", example = "Database")
    private String sourceTypeDisplayName;

    @Schema(description = "Connection URL (masked for security)", example = "jdbc:postgresql://localhost:5432/mydb")
    private String connectionUrl;

    @Schema(description = "Authentication type", example = "BASIC_AUTH")
    private AuthType authType;

    @Schema(description = "Display name of the auth type", example = "Basic Authentication")
    private String authTypeDisplayName;

    @Schema(description = "Current status of the data source", example = "ACTIVE")
    private DataSourceStatus status;

    @Schema(description = "Display name of the status", example = "Active")
    private String statusDisplayName;

    @Schema(description = "Refresh interval in seconds", example = "300")
    private Integer refreshIntervalSeconds;

    @Schema(description = "Timestamp of the last successful sync")
    private LocalDateTime lastSyncAt;

    @Schema(description = "Error message if status is ERROR")
    private String errorMessage;

    @Schema(description = "Current retry count", example = "0")
    private Integer retryCount;

    @Schema(description = "Maximum number of retries", example = "3")
    private Integer maxRetries;

    @Schema(description = "Connection timeout in seconds", example = "30")
    private Integer timeoutSeconds;

    @Schema(description = "Tags for categorization", example = "production,database")
    private String tags;

    @Schema(description = "JSON schema definition")
    private String schemaDefinition;

    @Schema(description = "Total record count from last sync", example = "150000")
    private Long recordCount;

    @Schema(description = "Whether the data source is active", example = "true")
    private Boolean isActive;

    @Schema(description = "Whether the data source can retry", example = "true")
    private Boolean canRetry;

    @Schema(description = "Whether the data source is operational", example = "true")
    private Boolean isOperational;

    @Schema(description = "Timestamp when the data source was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the data source was last updated")
    private LocalDateTime updatedAt;
}
