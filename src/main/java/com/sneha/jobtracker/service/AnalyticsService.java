package com.sneha.jobtracker.service;

import com.sneha.jobtracker.dto.response.AnalyticsResponse;
import com.sneha.jobtracker.entity.User;
import com.sneha.jobtracker.enums.ApplicationStatus;
import com.sneha.jobtracker.exception.ResourceNotFoundException;
import com.sneha.jobtracker.repository.InterviewRepository;
import com.sneha.jobtracker.repository.JobApplicationRepository;
import com.sneha.jobtracker.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AnalyticsService {

    private final JobApplicationRepository jobApplicationRepository;
    private final InterviewRepository interviewRepository;
    private final UserRepository userRepository;

    public AnalyticsService(JobApplicationRepository jobApplicationRepository,
                            InterviewRepository interviewRepository,
                            UserRepository userRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.interviewRepository = interviewRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public AnalyticsResponse getAnalytics() {
        User user = getCurrentUser();
        Long uid = user.getId();

        long total = jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.APPLIED)
                + jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.WISHLIST)
                + jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.PHONE_SCREEN)
                + jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.TECHNICAL)
                + jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.ONSITE)
                + jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.OFFER)
                + jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.ACCEPTED)
                + jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.REJECTED)
                + jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.WITHDRAWN);

        long interviews = interviewRepository.countByApplicationUserId(uid);
        long offers = jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.OFFER)
                + jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.ACCEPTED);
        long rejected = jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.REJECTED);
        long applied = jobApplicationRepository.countByUserIdAndStatus(uid, ApplicationStatus.APPLIED);

        double interviewRate = total > 0 ? Math.round((double) interviews / total * 100.0 * 10) / 10.0 : 0;
        double offerRate = total > 0 ? Math.round((double) offers / total * 100.0 * 10) / 10.0 : 0;

        Map<String, Long> statusBreakdown = new LinkedHashMap<>();
        jobApplicationRepository.countByStatusForUser(uid)
                .forEach(row -> statusBreakdown.put(row[0].toString(), ((Number) row[1]).longValue()));

        Map<String, Long> platformBreakdown = new LinkedHashMap<>();
        jobApplicationRepository.countByPlatformForUser(uid)
                .forEach(row -> {
                    if (row[0] != null) {
                        platformBreakdown.put(row[0].toString(), ((Number) row[1]).longValue());
                    }
                });

        List<AnalyticsResponse.MonthlyStats> monthlyStats = new ArrayList<>();
        jobApplicationRepository.monthlyApplications(uid).forEach(row -> {
            AnalyticsResponse.MonthlyStats stat = new AnalyticsResponse.MonthlyStats();
            stat.setMonth(row[0].toString());
            stat.setApplied(((Number) row[1]).longValue());
            monthlyStats.add(stat);
        });

        AnalyticsResponse response = new AnalyticsResponse();
        response.setTotalApplications(total);
        response.setActiveApplications(applied);
        response.setInterviews(interviews);
        response.setOffers(offers);
        response.setRejected(rejected);
        response.setApplicationToInterviewRate(interviewRate);
        response.setOfferRate(offerRate);
        response.setStatusBreakdown(statusBreakdown);
        response.setPlatformBreakdown(platformBreakdown);
        response.setMonthlyStats(monthlyStats);
        return response;
    }
}