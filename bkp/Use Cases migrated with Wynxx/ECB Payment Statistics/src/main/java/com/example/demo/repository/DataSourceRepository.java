package com.example.demo.repository;

import com.example.demo.entity.DataSource;
import com.example.demo.enums.DataSourceStatus;
import com.example.demo.enums.DataSourceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DataSourceRepository extends JpaRepository<DataSource, Long> {

    Optional<DataSource> findByName(String name);

    boolean existsByName(String name);

    Page<DataSource> findByStatus(DataSourceStatus status, Pageable pageable);

    Page<DataSource> findBySourceType(DataSourceType sourceType, Pageable pageable);

    Page<DataSource> findByStatusAndSourceType(DataSourceStatus status, DataSourceType sourceType, Pageable pageable);

    List<DataSource> findByStatusAndIsActive(DataSourceStatus status, Boolean isActive);

    List<DataSource> findByIsActiveTrue();

    @Query("SELECT ds FROM DataSource ds WHERE ds.status = 'ACTIVE' AND ds.isActive = true " +
           "AND (ds.lastSyncAt IS NULL OR ds.lastSyncAt < :threshold)")
    List<DataSource> findDataSourcesDueForSync(@Param("threshold") LocalDateTime threshold);

    @Query("SELECT ds FROM DataSource ds WHERE ds.status = 'ERROR' AND ds.retryCount < ds.maxRetries")
    List<DataSource> findRetryableErrorDataSources();

    @Query("SELECT ds FROM DataSource ds WHERE LOWER(ds.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
           "OR LOWER(ds.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<DataSource> findByNameOrDescriptionContaining(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT ds FROM DataSource ds WHERE ds.tags LIKE CONCAT('%', :tag, '%')")
    List<DataSource> findByTag(@Param("tag") String tag);

    long countByStatus(DataSourceStatus status);

    long countBySourceType(DataSourceType sourceType);

    @Query("SELECT COUNT(ds) FROM DataSource ds WHERE ds.status = 'ERROR'")
    long countErrorDataSources();

    @Query("SELECT ds FROM DataSource ds WHERE ds.lastSyncAt < :since AND ds.status = 'ACTIVE'")
    List<DataSource> findStaleDataSources(@Param("since") LocalDateTime since);
}
