package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "aggregation_metrics", indexes = {
        @Index(name = "idx_aggregation_metrics_pipeline_id", columnList = "data_pipeline_id"),
        @Index(name = "idx_aggregation_metrics_job_id", columnList = "aggregation_job_id"),
        @Index(name = "idx_aggregation_metrics_metric_name", columnList = "metric_name"),
        @Index(name = "idx_aggregation_metrics_recorded_at", columnList = "recorded_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregationMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_pipeline_id")
    private DataPipeline dataPipeline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aggregation_job_id")
    private AggregationJob aggregationJob;

    @Column(name = "metric_name", nullable = false, length = 255)
    private String metricName;

    @Column(name = "metric_value", nullable = false, precision = 20, scale = 6)
    private BigDecimal metricValue;

    @Column(name = "metric_unit", length = 100)
    private String metricUnit;

    @Column(name = "metric_category", length = 100)
    private String metricCategory;

    @Column(name = "tags", length = 500)
    private String tags;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    @Column(name = "period_start")
    private LocalDateTime periodStart;

    @Column(name = "period_end")
    private LocalDateTime periodEnd;

    @Column(name = "additional_data", columnDefinition = "TEXT")
    private String additionalData;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public AggregationMetric(DataPipeline dataPipeline, AggregationJob aggregationJob,
                              String metricName, BigDecimal metricValue, String metricUnit) {
        this.dataPipeline = dataPipeline;
        this.aggregationJob = aggregationJob;
        this.metricName = metricName;
        this.metricValue = metricValue;
        this.metricUnit = metricUnit;
        this.recordedAt = LocalDateTime.now();
    }

    public AggregationMetric(String metricName, BigDecimal metricValue, String metricUnit) {
        this.metricName = metricName;
        this.metricValue = metricValue;
        this.metricUnit = metricUnit;
        this.recordedAt = LocalDateTime.now();
    }
}
