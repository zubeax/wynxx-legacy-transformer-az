package com.example.demo.dto;

import com.example.demo.enums.AuthType;
import com.example.demo.enums.DataSourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for testing a data source connection")
public class TestConnectionRequestDto {

    @NotNull(message = "Source type is required")
    @Schema(description = "Type of the data source to test", example = "DATABASE", required = true)
    private DataSourceType sourceType;

    @NotBlank(message = "Connection URL is required")
    @Schema(description = "Connection URL to test", example = "jdbc:postgresql://localhost:5432/mydb", required = true)
    private String connectionUrl;

    @NotNull(message = "Auth type is required")
    @Schema(description = "Authentication type", example = "BASIC_AUTH", required = true)
    private AuthType authType;

    @Schema(description = "Credentials in JSON format for testing")
    private String credentials;

    @Schema(description = "Timeout in seconds for the test", example = "10")
    private Integer timeoutSeconds;
}
