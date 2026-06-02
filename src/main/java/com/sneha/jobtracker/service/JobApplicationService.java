package com.sneha.jobtracker.service;

import com.sneha.jobtracker.dto.request.JobApplicationRequest;
import com.sneha.jobtracker.dto.response.JobApplicationResponse;
import com.sneha.jobtracker.entity.JobApplication;
import com.sneha.jobtracker.entity.User;
import com.sneha.jobtracker.enums.ApplicationStatus;
import com.sneha.jobtracker.exception.ResourceNotFoundException;
import com.sneha.jobtracker.repository.JobApplicationRepository;
import com.sneha.jobtracker.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
                                 UserRepository userRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private JobApplicationResponse toResponse(JobApplication j) {
        JobApplicationResponse r = new JobApplicationResponse();
        r.setId(j.getId());
        r.setCompanyName(j.getCompanyName());
        r.setJobTitle(j.getJobTitle());
        r.setJobUrl(j.getJobUrl());
        r.setPlatform(j.getPlatform());
        r.setStatus(j.getStatus());
        r.setAppliedDate(j.getAppliedDate());
        r.setDeadlineDate(j.getDeadlineDate());
        r.setNotes(j.getNotes());
        r.setLocation(j.getLocation());
        r.setSalaryRange(j.getSalaryRange());
        r.setRemoteOption(j.isRemoteOption());
        r.setCreatedAt(j.getCreatedAt());
        r.setUpdatedAt(j.getUpdatedAt());
        r.setInterviewCount(j.getInterviews().size());
        return r;
    }

    public JobApplicationResponse create(JobApplicationRequest request) {
        User user = getCurrentUser();
        JobApplication j = new JobApplication();
        j.setUser(user);
        j.setCompanyName(request.getCompanyName());
        j.setJobTitle(request.getJobTitle());
        j.setJobUrl(request.getJobUrl());
        j.setPlatform(request.getPlatform());
        j.setStatus(request.getStatus());
        j.setAppliedDate(request.getAppliedDate());
        j.setDeadlineDate(request.getDeadlineDate());
        j.setNotes(request.getNotes());
        j.setLocation(request.getLocation());
        j.setSalaryRange(request.getSalaryRange());
        j.setRemoteOption(request.isRemoteOption());
        return toResponse(jobApplicationRepository.save(j));
    }

    public List<JobApplicationResponse> getAll() {
        User user = getCurrentUser();
        return jobApplicationRepository.findByUserIdOrderByCreatedAtDesc(user.getId())
                .stream().map(this::toResponse).toList();
    }

    public JobApplicationResponse getById(Long id) {
        User user = getCurrentUser();
        JobApplication j = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        if (!j.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        return toResponse(j);
    }

    public JobApplicationResponse update(Long id, JobApplicationRequest request) {
        User user = getCurrentUser();
        JobApplication j = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        if (!j.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        j.setCompanyName(request.getCompanyName());
        j.setJobTitle(request.getJobTitle());
        j.setJobUrl(request.getJobUrl());
        j.setPlatform(request.getPlatform());
        j.setStatus(request.getStatus());
        j.setAppliedDate(request.getAppliedDate());
        j.setDeadlineDate(request.getDeadlineDate());
        j.setNotes(request.getNotes());
        j.setLocation(request.getLocation());
        j.setSalaryRange(request.getSalaryRange());
        j.setRemoteOption(request.isRemoteOption());
        j.setUpdatedAt(LocalDateTime.now());
        return toResponse(jobApplicationRepository.save(j));
    }

    public JobApplicationResponse updateStatus(Long id, String status) {
        User user = getCurrentUser();
        JobApplication j = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        if (!j.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        j.setStatus(ApplicationStatus.valueOf(status));
        j.setUpdatedAt(LocalDateTime.now());
        return toResponse(jobApplicationRepository.save(j));
    }

    public void delete(Long id) {
        User user = getCurrentUser();
        JobApplication j = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        if (!j.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        jobApplicationRepository.delete(j);
    }
}