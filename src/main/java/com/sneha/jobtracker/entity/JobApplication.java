package com.sneha.jobtracker.entity;

import com.sneha.jobtracker.enums.ApplicationStatus;
import com.sneha.jobtracker.enums.JobPlatform;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String jobTitle;

    @Column(length = 1000)
    private String jobUrl;

    @Enumerated(EnumType.STRING)
    private JobPlatform platform;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.WISHLIST;

    private LocalDate appliedDate;
    private LocalDate deadlineDate;

    @Column(length = 2000)
    private String notes;

    private String location;
    private String salaryRange;
    private boolean remoteOption;
    private boolean reminderSent = false;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<Interview> interviews = new ArrayList<>();

    public JobApplication() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getJobUrl() { return jobUrl; }
    public void setJobUrl(String jobUrl) { this.jobUrl = jobUrl; }
    public JobPlatform getPlatform() { return platform; }
    public void setPlatform(JobPlatform platform) { this.platform = platform; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public LocalDate getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }
    public LocalDate getDeadlineDate() { return deadlineDate; }
    public void setDeadlineDate(LocalDate deadlineDate) { this.deadlineDate = deadlineDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getSalaryRange() { return salaryRange; }
    public void setSalaryRange(String salaryRange) { this.salaryRange = salaryRange; }
    public boolean isRemoteOption() { return remoteOption; }
    public void setRemoteOption(boolean remoteOption) { this.remoteOption = remoteOption; }
    public boolean isReminderSent() { return reminderSent; }
    public void setReminderSent(boolean reminderSent) { this.reminderSent = reminderSent; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public List<Interview> getInterviews() { return interviews; }
    public void setInterviews(List<Interview> interviews) { this.interviews = interviews; }
}