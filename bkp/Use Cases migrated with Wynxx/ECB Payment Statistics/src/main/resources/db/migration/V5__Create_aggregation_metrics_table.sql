-- V5__Create_aggregation_metrics_table.sql
-- Creates the aggregation_metrics table for the Data Aggregation system

CREATE TABLE aggregation_metrics (
    id BIGSERIAL PRIMARY KEY,
    data_pipeline_id BIGINT,
    aggregation_job_id BIGINT,
    metric_name VARCHAR(255) NOT NULL,
    metric_value DECIMAL(20, 6) NOT NULL,
    metric_unit VARCHAR(100),
    metric_category VARCHAR(100),
    tags VARCHAR(500),
    recorded_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    period_start TIMESTAMP,
    period_end TIMESTAMP,
    additional_data TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_aggregation_metrics_pipeline FOREIGN KEY (data_pipeline_id) REFERENCES data_pipelines(id),
    CONSTRAINT fk_aggregation_metrics_job FOREIGN KEY (aggregation_job_id) REFERENCES aggregation_jobs(id)
);

-- Indexes for performance
CREATE INDEX idx_aggregation_metrics_pipeline_id ON aggregation_metrics(data_pipeline_id);
CREATE INDEX idx_aggregation_metrics_job_id ON aggregation_metrics(aggregation_job_id);
CREATE INDEX idx_aggregation_metrics_metric_name ON aggregation_metrics(metric_name);
CREATE INDEX idx_aggregation_metrics_recorded_at ON aggregation_metrics(recorded_at);
CREATE INDEX idx_aggregation_metrics_metric_category ON aggregation_metrics(metric_category);
CREATE INDEX idx_aggregation_metrics_name_recorded ON aggregation_metrics(metric_name, recorded_at);
