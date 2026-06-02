package com.sneha.jobtracker.dto.request;

import com.sneha.jobtracker.enums.ApplicationStatus;
import com.sneha.jobtracker.enums.JobPlatform;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class JobApplicationRequest {
    @NotBlank private String companyName;
    @NotBlank private String jobTitle;
    private String jobUrl;
    private JobPlatform platform;
    private ApplicationStatus status = ApplicationStatus.WISHLIST;
    private LocalDate appliedDate;
    private LocalDate deadlineDate;
    private String notes;
    private String location;
    private String salaryRange;
    private boolean remoteOption;

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
}