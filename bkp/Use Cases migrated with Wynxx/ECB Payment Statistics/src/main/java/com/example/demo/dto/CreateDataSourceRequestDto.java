package com.example.demo.dto;

import com.example.demo.enums.AuthType;
import com.example.demo.enums.DataSourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for creating a new data source")
public class CreateDataSourceRequestDto {

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    @Schema(description = "Name of the data source", example = "Production PostgreSQL DB", required = true)
    private String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Schema(description = "Description of the data source", example = "Main production database for customer data")
    private String description;

    @NotNull(message = "Source type is required")
    @Schema(description = "Type of the data source", example = "DATABASE", required = true)
    private DataSourceType sourceType;

    @NotBlank(message = "Connection URL is required")
    @Size(min = 1, max = 2000, message = "Connection URL must be between 1 and 2000 characters")
    @Schema(description = "Connection URL for the data source", example = "jdbc:postgresql://localhost:5432/mydb", required = true)
    private String connectionUrl;

    @NotNull(message = "Auth type is required")
    @Schema(description = "Authentication type for the data source", example = "BASIC_AUTH", required = true)
    private AuthType authType;

    @Schema(description = "Credentials in JSON format (encrypted at rest)", example = "{\"username\":\"user\",\"password\":\"pass\"}")
    private String credentials;

    @Min(value = 1, message = "Refresh interval must be at least 1 second")
    @Max(value = 86400, message = "Refresh interval must not exceed 86400 seconds (24 hours)")
    @Schema(description = "Refresh interval in seconds", example = "300")
    private Integer refreshIntervalSeconds;

    @Min(value = 0, message = "Max retries must be non-negative")
    @Max(value = 10, message = "Max retries must not exceed 10")
    @Schema(description = "Maximum number of retry attempts", example = "3")
    private Integer maxRetries;

    @Min(value = 1, message = "Timeout must be at least 1 second")
    @Max(value = 3600, message = "Timeout must not exceed 3600 seconds")
    @Schema(description = "Connection timeout in seconds", example = "30")
    private Integer timeoutSeconds;

    @Size(max = 500, message = "Tags must not exceed 500 characters")
    @Schema(description = "Comma-separated tags for categorization", example = "production,database,customer-data")
    private String tags;

    @Schema(description = "JSON schema definition for the data source output")
    private String schemaDefinition;
}
