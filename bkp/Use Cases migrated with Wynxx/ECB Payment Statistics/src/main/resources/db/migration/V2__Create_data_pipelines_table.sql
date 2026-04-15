-- V2__Create_data_pipelines_table.sql
-- Creates the data_pipelines table for the Data Aggregation system

CREATE TABLE data_pipelines (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    status VARCHAR(50) NOT NULL DEFAULT 'DRAFT',
    pipeline_config TEXT,
    schedule_expression VARCHAR(100),
    source_count INTEGER NOT NULL DEFAULT 0,
    last_executed_at TIMESTAMP,
    next_scheduled_at TIMESTAMP,
    total_executions BIGINT NOT NULL DEFAULT 0,
    successful_executions BIGINT NOT NULL DEFAULT 0,
    failed_executions BIGINT NOT NULL DEFAULT 0,
    average_execution_time_ms BIGINT,
    owner VARCHAR(255),
    tags VARCHAR(500),
    notification_emails VARCHAR(1000),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    error_message VARCHAR(2000),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for performance
CREATE INDEX idx_data_pipelines_status ON data_pipelines(status);
CREATE INDEX idx_data_pipelines_name ON data_pipelines(name);
CREATE INDEX idx_data_pipelines_next_scheduled ON data_pipelines(next_scheduled_at);
CREATE INDEX idx_data_pipelines_owner ON data_pipelines(owner);
CREATE INDEX idx_data_pipelines_is_active ON data_pipelines(is_active);

-- Constraints
ALTER TABLE data_pipelines ADD CONSTRAINT chk_data_pipelines_status 
    CHECK (status IN ('ACTIVE', 'INACTIVE', 'DRAFT', 'ARCHIVED', 'ERROR', 'PAUSED'));

ALTER TABLE data_pipelines ADD CONSTRAINT chk_data_pipelines_source_count 
    CHECK (source_count >= 0);

ALTER TABLE data_pipelines ADD CONSTRAINT chk_data_pipelines_executions 
    CHECK (total_executions >= 0 AND successful_executions >= 0 AND failed_executions >= 0);
