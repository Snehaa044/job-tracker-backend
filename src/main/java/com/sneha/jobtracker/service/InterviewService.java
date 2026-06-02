package com.sneha.jobtracker.service;

import com.sneha.jobtracker.dto.request.InterviewRequest;
import com.sneha.jobtracker.dto.response.InterviewResponse;
import com.sneha.jobtracker.entity.Interview;
import com.sneha.jobtracker.entity.JobApplication;
import com.sneha.jobtracker.entity.User;
import com.sneha.jobtracker.exception.ResourceNotFoundException;
import com.sneha.jobtracker.repository.InterviewRepository;
import com.sneha.jobtracker.repository.JobApplicationRepository;
import com.sneha.jobtracker.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    public InterviewService(InterviewRepository interviewRepository,
                            JobApplicationRepository jobApplicationRepository,
                            UserRepository userRepository) {
        this.interviewRepository = interviewRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private InterviewResponse toResponse(Interview i) {
        InterviewResponse r = new InterviewResponse();
        r.setId(i.getId());
        r.setApplicationId(i.getApplication().getId());
        r.setCompanyName(i.getApplication().getCompanyName());
        r.setJobTitle(i.getApplication().getJobTitle());
        r.setType(i.getType());
        r.setScheduledAt(i.getScheduledAt());
        r.setDurationMinutes(i.getDurationMinutes());
        r.setInterviewerName(i.getInterviewerName());
        r.setPlatform(i.getPlatform());
        r.setCompleted(i.isCompleted());
        r.setNotes(i.getNotes());
        r.setFeedback(i.getFeedback());
        r.setRating(i.getRating());
        return r;
    }

    public InterviewResponse schedule(InterviewRequest request) {
        JobApplication application = jobApplicationRepository.findById(request.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        Interview interview = new Interview();
        interview.setApplication(application);
        interview.setType(request.getType());
        interview.setScheduledAt(request.getScheduledAt());
        interview.setDurationMinutes(request.getDurationMinutes());
        interview.setInterviewerName(request.getInterviewerName());
        interview.setPlatform(request.getPlatform());
        interview.setNotes(request.getNotes());
        return toResponse(interviewRepository.save(interview));
    }

    public List<InterviewResponse> getMyInterviews() {
        User user = getCurrentUser();
        return interviewRepository.findByUserId(user.getId())
                .stream().map(this::toResponse).toList();
    }

    public List<InterviewResponse> getByApplication(Long applicationId) {
        return interviewRepository.findByApplicationIdOrderByScheduledAtDesc(applicationId)
                .stream().map(this::toResponse).toList();
    }

    public InterviewResponse complete(Long id, String feedback, Integer rating) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found"));
        interview.setCompleted(true);
        interview.setFeedback(feedback);
        interview.setRating(rating);
        return toResponse(interviewRepository.save(interview));
    }

    public void delete(Long id) {
        interviewRepository.deleteById(id);
    }
}