package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for connection test results")
public class TestConnectionResponseDto {

    @Schema(description = "Whether the connection was successful", example = "true")
    private Boolean success;

    @Schema(description = "Message describing the test result", example = "Connection successful")
    private String message;

    @Schema(description = "Time taken to establish connection in milliseconds", example = "125")
    private Long connectionTimeMs;

    @Schema(description = "Server version or additional info if available", example = "PostgreSQL 15.2")
    private String serverInfo;

    @Schema(description = "Error details if connection failed")
    private String errorDetails;

    public static TestConnectionResponseDto success(String message, Long connectionTimeMs, String serverInfo) {
        return new TestConnectionResponseDto(true, message, connectionTimeMs, serverInfo, null);
    }

    public static TestConnectionResponseDto failure(String message, String errorDetails) {
        return new TestConnectionResponseDto(false, message, null, null, errorDetails);
    }
}
