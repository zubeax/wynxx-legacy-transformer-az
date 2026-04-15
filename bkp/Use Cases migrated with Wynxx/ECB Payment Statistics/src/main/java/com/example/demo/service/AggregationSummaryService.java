package com.example.demo.service;

import com.example.demo.dto.AggregationJobResponseDto;
import com.example.demo.dto.AggregationSummaryResponseDto;
import com.example.demo.enums.DataSourceStatus;
import com.example.demo.enums.JobStatus;
import com.example.demo.enums.PipelineStatus;
import com.example.demo.repository.AggregationJobRepository;
import com.example.demo.repository.AggregationResultRepository;
import com.example.demo.repository.DataPipelineRepository;
import com.example.demo.repository.DataSourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AggregationSummaryService {

    private final DataSourceRepository dataSourceRepository;
    private final DataPipelineRepository dataPipelineRepository;
    private final AggregationJobRepository aggregationJobRepository;
    private final AggregationResultRepository aggregationResultRepository;
    private final AggregationJobService aggregationJobService;

    @Transactional(readOnly = true)
    public AggregationSummaryResponseDto getAggregationSummary() {
        log.info("Generating aggregation system summary");

        LocalDateTime last24h = LocalDateTime.now().minusHours(24);

        AggregationSummaryResponseDto summary = new AggregationSummaryResponseDto();

        // Data Source statistics
        summary.setTotalDataSources(dataSourceRepository.count());
        summary.setActiveDataSources(dataSourceRepository.countByStatus(DataSourceStatus.ACTIVE));
        summary.setErrorDataSources(dataSourceRepository.countErrorDataSources());

        // Pipeline statistics
        summary.setTotalPipelines(dataPipelineRepository.count());
        summary.setActivePipelines(dataPipelineRepository.countByStatus(PipelineStatus.ACTIVE));

        // Job statistics
        summary.setTotalJobs(aggregationJobRepository.count());
        summary.setRunningJobs(aggregationJobRepository.countByStatus(JobStatus.RUNNING) +
                               aggregationJobRepository.countByStatus(JobStatus.RETRYING));
        summary.setPendingJobs(aggregationJobRepository.countByStatus(JobStatus.PENDING) +
                               aggregationJobRepository.countByStatus(JobStatus.QUEUED));

        // Last 24h statistics
        List<AggregationJobResponseDto> failedJobsLast24h = aggregationJobService.getFailedJobsSince(last24h);
        List<AggregationJobResponseDto> completedJobsLast24h = aggregationJobService.getCompletedJobsSince(last24h);

        summary.setFailedJobsLast24h((long) failedJobsLast24h.size());
        summary.setCompletedJobsLast24h((long) completedJobsLast24h.size());

        // Records statistics
        Long totalProcessed = aggregationJobRepository.sumRecordsProcessedSince(last24h);
        Long totalFailed = aggregationJobRepository.sumRecordsFailedSince(last24h);
        summary.setTotalRecordsProcessedLast24h(totalProcessed != null ? totalProcessed : 0L);
        summary.setTotalRecordsFailedLast24h(totalFailed != null ? totalFailed : 0L);

        // Overall success rate
        long totalJobsLast24h = failedJobsLast24h.size() + completedJobsLast24h.size();
        if (totalJobsLast24h > 0) {
            summary.setOverallSuccessRate((double) completedJobsLast24h.size() / totalJobsLast24h * 100.0);
        } else {
            summary.setOverallSuccessRate(100.0);
        }

        // Average execution time
        Double avgExecTime = aggregationJobRepository.findAverageExecutionTimeMs();
        summary.setAverageJobExecutionTimeMs(avgExecTime != null ? avgExecTime.longValue() : null);

        // Recent failed jobs (last 5)
        summary.setRecentFailedJobs(failedJobsLast24h.stream().limit(5).collect(Collectors.toList()));

        // Currently running jobs
        summary.setCurrentlyRunningJobs(aggregationJobService.getActiveJobs());

        summary.setGeneratedAt(LocalDateTime.now());

        log.info("Aggregation summary generated successfully");
        return summary;
    }
}
