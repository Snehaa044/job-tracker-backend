package com.sneha.jobtracker.dto.response;

import com.sneha.jobtracker.enums.ApplicationStatus;
import com.sneha.jobtracker.enums.JobPlatform;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobApplicationResponse {
    private Long id;
    private String companyName;
    private String jobTitle;
    private String jobUrl;
    private JobPlatform platform;
    private ApplicationStatus status;
    private LocalDate appliedDate;
    private LocalDate deadlineDate;
    private String notes;
    private String location;
    private String salaryRange;
    private boolean remoteOption;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int interviewCount;

    public Long getId() { return id; }
    public void setId(Long v) { this.id = v; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String v) { this.companyName = v; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String v) { this.jobTitle = v; }
    public String getJobUrl() { return jobUrl; }
    public void setJobUrl(String v) { this.jobUrl = v; }
    public JobPlatform getPlatform() { return platform; }
    public void setPlatform(JobPlatform v) { this.platform = v; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus v) { this.status = v; }
    public LocalDate getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDate v) { this.appliedDate = v; }
    public LocalDate getDeadlineDate() { return deadlineDate; }
    public void setDeadlineDate(LocalDate v) { this.deadlineDate = v; }
    public String getNotes() { return notes; }
    public void setNotes(String v) { this.notes = v; }
    public String getLocation() { return location; }
    public void setLocation(String v) { this.location = v; }
    public String getSalaryRange() { return salaryRange; }
    public void setSalaryRange(String v) { this.salaryRange = v; }
    public boolean isRemoteOption() { return remoteOption; }
    public void setRemoteOption(boolean v) { this.remoteOption = v; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime v) { this.updatedAt = v; }
    public int getInterviewCount() { return interviewCount; }
    public void setInterviewCount(int v) { this.interviewCount = v; }
}