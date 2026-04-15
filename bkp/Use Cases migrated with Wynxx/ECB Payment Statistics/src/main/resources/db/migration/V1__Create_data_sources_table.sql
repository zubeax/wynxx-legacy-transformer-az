-- V1__Create_data_sources_table.sql
-- Creates the data_sources table for the Data Aggregation system

CREATE TABLE data_sources (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    source_type VARCHAR(50) NOT NULL,
    connection_url VARCHAR(2000) NOT NULL,
    auth_type VARCHAR(50) NOT NULL DEFAULT 'NONE',
    credentials TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    refresh_interval_seconds INTEGER,
    last_sync_at TIMESTAMP,
    error_message VARCHAR(2000),
    retry_count INTEGER NOT NULL DEFAULT 0,
    max_retries INTEGER NOT NULL DEFAULT 3,
    timeout_seconds INTEGER NOT NULL DEFAULT 30,
    tags VARCHAR(500),
    schema_definition TEXT,
    sample_data TEXT,
    record_count BIGINT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for performance
CREATE INDEX idx_data_sources_status ON data_sources(status);
CREATE INDEX idx_data_sources_source_type ON data_sources(source_type);
CREATE INDEX idx_data_sources_name ON data_sources(name);
CREATE INDEX idx_data_sources_is_active ON data_sources(is_active);
CREATE INDEX idx_data_sources_last_sync_at ON data_sources(last_sync_at);

-- Constraints
ALTER TABLE data_sources ADD CONSTRAINT chk_data_sources_status 
    CHECK (status IN ('ACTIVE', 'INACTIVE', 'ERROR', 'PENDING', 'TESTING', 'DEPRECATED'));

ALTER TABLE data_sources ADD CONSTRAINT chk_data_sources_source_type 
    CHECK (source_type IN ('DATABASE', 'REST_API', 'FILE', 'STREAM', 'WEBHOOK', 'GRAPHQL', 'SOAP', 'MESSAGE_QUEUE'));

ALTER TABLE data_sources ADD CONSTRAINT chk_data_sources_auth_type 
    CHECK (auth_type IN ('NONE', 'API_KEY', 'BASIC_AUTH', 'OAUTH2', 'JWT', 'CERTIFICATE', 'CUSTOM'));

ALTER TABLE data_sources ADD CONSTRAINT chk_data_sources_retry_count 
    CHECK (retry_count >= 0);

ALTER TABLE data_sources ADD CONSTRAINT chk_data_sources_max_retries 
    CHECK (max_retries >= 0 AND max_retries <= 10);

ALTER TABLE data_sources ADD CONSTRAINT chk_data_sources_timeout_seconds 
    CHECK (timeout_seconds >= 1 AND timeout_seconds <= 3600);
