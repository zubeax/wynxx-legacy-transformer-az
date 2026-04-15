package com.example.demo.dto;

import com.example.demo.enums.AuthType;
import com.example.demo.enums.DataSourceStatus;
import com.example.demo.enums.DataSourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for updating an existing data source")
public class UpdateDataSourceRequestDto {

    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    @Schema(description = "Updated name of the data source", example = "Production PostgreSQL DB v2")
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Updated description of the data source")
    private String description;

    @Schema(description = "Updated source type", example = "DATABASE")
    private DataSourceType sourceType;

    @Size(min = 1, max = 2000, message = "Connection URL must be between 1 and 2000 characters")
    @Schema(description = "Updated connection URL", example = "jdbc:postgresql://newhost:5432/mydb")
    private String connectionUrl;

    @Schema(description = "Updated authentication type", example = "OAUTH2")
    private AuthType authType;

    @Schema(description = "Updated credentials in JSON format")
    private String credentials;

    @Schema(description = "Updated status of the data source", example = "ACTIVE")
    private DataSourceStatus status;

    @Min(value = 1, message = "Refresh interval must be at least 1 second")
    @Max(value = 86400, message = "Refresh interval must not exceed 86400 seconds")
    @Schema(description = "Updated refresh interval in seconds", example = "600")
    private Integer refreshIntervalSeconds;

    @Min(value = 0, message = "Max retries must be non-negative")
    @Max(value = 10, message = "Max retries must not exceed 10")
    @Schema(description = "Updated maximum number of retry attempts", example = "5")
    private Integer maxRetries;

    @Min(value = 1, message = "Timeout must be at least 1 second")
    @Max(value = 3600, message = "Timeout must not exceed 3600 seconds")
    @Schema(description = "Updated connection timeout in seconds", example = "60")
    private Integer timeoutSeconds;

    @Size(max = 500, message = "Tags must not exceed 500 characters")
    @Schema(description = "Updated comma-separated tags", example = "production,database,updated")
    private String tags;

    @Schema(description = "Updated JSON schema definition")
    private String schemaDefinition;

    @Schema(description = "Whether the data source is active", example = "true")
    private Boolean isActive;
}
