package com.sneha.jobtracker.entity;

import com.sneha.jobtracker.enums.InterviewType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private JobApplication application;

    @Enumerated(EnumType.STRING)
    private InterviewType type;

    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    private Integer durationMinutes;
    private String interviewerName;
    private String platform;
    private boolean completed = false;
    private boolean reminderSent = false;

    @Column(length = 2000)
    private String notes;

    @Column(length = 1000)
    private String feedback;

    private Integer rating;

    public Interview() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public JobApplication getApplication() { return application; }
    public void setApplication(JobApplication application) { this.application = application; }
    public InterviewType getType() { return type; }
    public void setType(InterviewType type) { this.type = type; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public String getInterviewerName() { return interviewerName; }
    public void setInterviewerName(String interviewerName) { this.interviewerName = interviewerName; }
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public boolean isReminderSent() { return reminderSent; }
    public void setReminderSent(boolean reminderSent) { this.reminderSent = reminderSent; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}