package com.sneha.jobtracker.dto.response;

import java.util.List;
import java.util.Map;

public class AnalyticsResponse {
    private long totalApplications;
    private long activeApplications;
    private long interviews;
    private long offers;
    private long rejected;
    private double applicationToInterviewRate;
    private double offerRate;
    private Map<String, Long> statusBreakdown;
    private Map<String, Long> platformBreakdown;
    private List<MonthlyStats> monthlyStats;

    public long getTotalApplications() { return totalApplications; }
    public void setTotalApplications(long v) { this.totalApplications = v; }
    public long getActiveApplications() { return activeApplications; }
    public void setActiveApplications(long v) { this.activeApplications = v; }
    public long getInterviews() { return interviews; }
    public void setInterviews(long v) { this.interviews = v; }
    public long getOffers() { return offers; }
    public void setOffers(long v) { this.offers = v; }
    public long getRejected() { return rejected; }
    public void setRejected(long v) { this.rejected = v; }
    public double getApplicationToInterviewRate() { return applicationToInterviewRate; }
    public void setApplicationToInterviewRate(double v) { this.applicationToInterviewRate = v; }
    public double getOfferRate() { return offerRate; }
    public void setOfferRate(double v) { this.offerRate = v; }
    public Map<String, Long> getStatusBreakdown() { return statusBreakdown; }
    public void setStatusBreakdown(Map<String, Long> v) { this.statusBreakdown = v; }
    public Map<String, Long> getPlatformBreakdown() { return platformBreakdown; }
    public void setPlatformBreakdown(Map<String, Long> v) { this.platformBreakdown = v; }
    public List<MonthlyStats> getMonthlyStats() { return monthlyStats; }
    public void setMonthlyStats(List<MonthlyStats> v) { this.monthlyStats = v; }

    public static class MonthlyStats {
        private String month;
        private long applied;
        private long interviews;
        private long offers;

        public String getMonth() { return month; }
        public void setMonth(String v) { this.month = v; }
        public long getApplied() { return applied; }
        public void setApplied(long v) { this.applied = v; }
        public long getInterviews() { return interviews; }
        public void setInterviews(long v) { this.interviews = v; }
        public long getOffers() { return offers; }
        public void setOffers(long v) { this.offers = v; }
    }
}