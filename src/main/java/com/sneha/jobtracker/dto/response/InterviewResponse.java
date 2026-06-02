package com.sneha.jobtracker.dto.response;

import com.sneha.jobtracker.enums.InterviewType;

import java.time.LocalDateTime;

public class InterviewResponse {
    private Long id;
    private Long applicationId;
    private String companyName;
    private String jobTitle;
    private InterviewType type;
    private LocalDateTime scheduledAt;
    private Integer durationMinutes;
    private String interviewerName;
    private String platform;
    private boolean completed;
    private String notes;
    private String feedback;
    private Integer rating;

    public Long getId() { return id; }
    public void setId(Long v) { this.id = v; }
    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long v) { this.applicationId = v; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String v) { this.companyName = v; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String v) { this.jobTitle = v; }
    public InterviewType getType() { return type; }
    public void setType(InterviewType v) { this.type = v; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime v) { this.scheduledAt = v; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer v) { this.durationMinutes = v; }
    public String getInterviewerName() { return interviewerName; }
    public void setInterviewerName(String v) { this.interviewerName = v; }
    public String getPlatform() { return platform; }
    public void setPlatform(String v) { this.platform = v; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean v) { this.completed = v; }
    public String getNotes() { return notes; }
    public void setNotes(String v) { this.notes = v; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String v) { this.feedback = v; }
    public Integer getRating() { return rating; }
    public void setRating(Integer v) { this.rating = v; }
}