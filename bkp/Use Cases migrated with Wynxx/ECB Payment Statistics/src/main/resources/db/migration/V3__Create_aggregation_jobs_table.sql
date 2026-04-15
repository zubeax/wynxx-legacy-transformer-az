-- V3__Create_aggregation_jobs_table.sql
-- Creates the aggregation_jobs table for the Data Aggregation system

CREATE TABLE aggregation_jobs (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    job_type VARCHAR(50) NOT NULL,
    schedule_expression VARCHAR(100),
    data_source_id BIGINT NOT NULL,
    data_pipeline_id BIGINT,
    transformation_script TEXT,
    filter_expression TEXT,
    output_format VARCHAR(50) NOT NULL DEFAULT 'JSON',
    output_destination VARCHAR(2000),
    records_processed BIGINT NOT NULL DEFAULT 0,
    records_failed BIGINT NOT NULL DEFAULT 0,
    started_at TIMESTAMP,
    completed_at TIMESTAMP,
    error_message VARCHAR(2000),
    priority INTEGER NOT NULL DEFAULT 5,
    retry_on_failure BOOLEAN NOT NULL DEFAULT TRUE,
    max_retries INTEGER NOT NULL DEFAULT 3,
    retry_count INTEGER NOT NULL DEFAULT 0,
    timeout_seconds INTEGER,
    checkpoint_data TEXT,
    execution_time_ms BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_aggregation_jobs_data_source FOREIGN KEY (data_source_id) REFERENCES data_sources(id),
    CONSTRAINT fk_aggregation_jobs_data_pipeline FOREIGN KEY (data_pipeline_id) REFERENCES data_pipelines(id)
);

-- Indexes for performance
CREATE INDEX idx_aggregation_jobs_status ON aggregation_jobs(status);
CREATE INDEX idx_aggregation_jobs_data_source_id ON aggregation_jobs(data_source_id);
CREATE INDEX idx_aggregation_jobs_pipeline_id ON aggregation_jobs(data_pipeline_id);
CREATE INDEX idx_aggregation_jobs_job_type ON aggregation_jobs(job_type);
CREATE INDEX idx_aggregation_jobs_started_at ON aggregation_jobs(started_at);
CREATE INDEX idx_aggregation_jobs_priority ON aggregation_jobs(priority DESC);
CREATE INDEX idx_aggregation_jobs_created_at ON aggregation_jobs(created_at);

-- Constraints
ALTER TABLE aggregation_jobs ADD CONSTRAINT chk_aggregation_jobs_status 
    CHECK (status IN ('PENDING', 'QUEUED', 'RUNNING', 'COMPLETED', 'FAILED', 'CANCELLED', 'PAUSED', 'RETRYING'));

ALTER TABLE aggregation_jobs ADD CONSTRAINT chk_aggregation_jobs_job_type 
    CHECK (job_type IN ('FULL_SYNC', 'INCREMENTAL', 'REAL_TIME', 'SNAPSHOT', 'DELTA', 'VALIDATION', 'TRANSFORMATION'));

ALTER TABLE aggregation_jobs ADD CONSTRAINT chk_aggregation_jobs_output_format 
    CHECK (output_format IN ('JSON', 'CSV', 'XML', 'PARQUET', 'AVRO', 'ORC', 'EXCEL', 'TSV'));

ALTER TABLE aggregation_jobs ADD CONSTRAINT chk_aggregation_jobs_priority 
    CHECK (priority >= 1 AND priority <= 10);

ALTER TABLE aggregation_jobs ADD CONSTRAINT chk_aggregation_jobs_retry_count 
    CHECK (retry_count >= 0);

ALTER TABLE aggregation_jobs ADD CONSTRAINT chk_aggregation_jobs_max_retries 
    CHECK (max_retries >= 0 AND max_retries <= 10);

ALTER TABLE aggregation_jobs ADD CONSTRAINT chk_aggregation_jobs_records 
    CHECK (records_processed >= 0 AND records_failed >= 0);
