package com.sneha.jobtracker.dto.request;

import com.sneha.jobtracker.enums.InterviewType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class InterviewRequest {
    @NotNull private Long applicationId;
    @NotNull private InterviewType type;
    @NotNull private LocalDateTime scheduledAt;
    private Integer durationMinutes;
    private String interviewerName;
    private String platform;
    private String notes;

    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long v) { this.applicationId = v; }
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
    public String getNotes() { return notes; }
    public void setNotes(String v) { this.notes = v; }
}