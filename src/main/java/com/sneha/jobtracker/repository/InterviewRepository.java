package com.sneha.jobtracker.repository;

import com.sneha.jobtracker.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findByApplicationIdOrderByScheduledAtDesc(Long applicationId);

    @Query("SELECT i FROM Interview i WHERE i.application.user.id = :userId ORDER BY i.scheduledAt DESC")
    List<Interview> findByUserId(@Param("userId") Long userId);

    @Query("SELECT i FROM Interview i WHERE i.scheduledAt BETWEEN :start AND :end AND i.reminderSent = false AND i.completed = false")
    List<Interview> findUpcomingInterviews(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    long countByApplicationUserId(Long userId);
}