package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for triggering a job execution")
public class JobExecutionRequestDto {

    @Schema(description = "Whether to force execution even if job is already running", example = "false")
    private Boolean forceExecution;

    @Schema(description = "Override filter expression for this execution only")
    private String filterExpressionOverride;

    @Schema(description = "Override transformation script for this execution only")
    private String transformationScriptOverride;

    @Schema(description = "Additional parameters as JSON for this execution")
    private String executionParameters;

    @Schema(description = "Reason for manual trigger", example = "Manual re-sync requested by admin")
    private String triggerReason;
}
