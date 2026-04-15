-- V4__Create_aggregation_results_table.sql
-- Creates the aggregation_results table for the Data Aggregation system

CREATE TABLE aggregation_results (
    id BIGSERIAL PRIMARY KEY,
    aggregation_job_id BIGINT NOT NULL,
    data_source_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    record_count BIGINT NOT NULL DEFAULT 0,
    failed_count BIGINT NOT NULL DEFAULT 0,
    data_size_bytes BIGINT,
    execution_time_ms BIGINT,
    result_data TEXT,
    result_file_path VARCHAR(2000),
    checksum VARCHAR(128),
    metadata TEXT,
    error_details TEXT,
    schema_version VARCHAR(50),
    aggregated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_aggregation_results_job FOREIGN KEY (aggregation_job_id) REFERENCES aggregation_jobs(id),
    CONSTRAINT fk_aggregation_results_data_source FOREIGN KEY (data_source_id) REFERENCES data_sources(id)
);

-- Indexes for performance
CREATE INDEX idx_aggregation_results_job_id ON aggregation_results(aggregation_job_id);
CREATE INDEX idx_aggregation_results_data_source_id ON aggregation_results(data_source_id);
CREATE INDEX idx_aggregation_results_status ON aggregation_results(status);
CREATE INDEX idx_aggregation_results_aggregated_at ON aggregation_results(aggregated_at);
CREATE INDEX idx_aggregation_results_checksum ON aggregation_results(checksum);

-- Constraints
ALTER TABLE aggregation_results ADD CONSTRAINT chk_aggregation_results_status 
    CHECK (status IN ('SUCCESS', 'PARTIAL', 'FAILED', 'EMPTY', 'SKIPPED'));

ALTER TABLE aggregation_results ADD CONSTRAINT chk_aggregation_results_counts 
    CHECK (record_count >= 0 AND failed_count >= 0);
