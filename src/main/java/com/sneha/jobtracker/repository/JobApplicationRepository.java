package com.sneha.jobtracker.repository;

import com.sneha.jobtracker.entity.JobApplication;
import com.sneha.jobtracker.enums.ApplicationStatus;
import com.sneha.jobtracker.enums.JobPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByUserIdOrderByCreatedAtDesc(Long userId);

    List<JobApplication> findByUserIdAndStatus(Long userId, ApplicationStatus status);

    long countByUserIdAndStatus(Long userId, ApplicationStatus status);

    @Query("SELECT j.status, COUNT(j) FROM JobApplication j WHERE j.user.id = :userId GROUP BY j.status")
    List<Object[]> countByStatusForUser(@Param("userId") Long userId);

    @Query("SELECT j.platform, COUNT(j) FROM JobApplication j WHERE j.user.id = :userId GROUP BY j.platform")
    List<Object[]> countByPlatformForUser(@Param("userId") Long userId);

    @Query("SELECT DATE_FORMAT(j.appliedDate, '%Y-%m'), COUNT(j) FROM JobApplication j WHERE j.user.id = :userId AND j.appliedDate IS NOT NULL GROUP BY DATE_FORMAT(j.appliedDate, '%Y-%m') ORDER BY 1")
    List<Object[]> monthlyApplications(@Param("userId") Long userId);

    // For scheduler: applications with no update in 7 days
    @Query("SELECT j FROM JobApplication j WHERE j.user.id = :userId AND j.status = 'APPLIED' AND j.updatedAt < :cutoff AND j.reminderSent = false")
    List<JobApplication> findStaleApplications(@Param("userId") Long userId, @Param("cutoff") java.time.LocalDateTime cutoff);

    @Query("SELECT j FROM JobApplication j WHERE j.status = 'APPLIED' AND j.updatedAt < :cutoff AND j.reminderSent = false")
    List<JobApplication> findAllStaleApplications(@Param("cutoff") java.time.LocalDateTime cutoff);
}