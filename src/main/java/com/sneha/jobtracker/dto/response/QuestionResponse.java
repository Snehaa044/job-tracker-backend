package com.sneha.jobtracker.dto.response;

import com.sneha.jobtracker.enums.Difficulty;

public class QuestionResponse {
    private Long id;
    private String question;
    private String answer;
    private String company;
    private String role;
    private String tags;
    private Difficulty difficulty;
    private String category;

    public Long getId() { return id; }
    public void setId(Long v) { this.id = v; }
    public String getQuestion() { return question; }
    public void setQuestion(String v) { this.question = v; }
    public String getAnswer() { return answer; }
    public void setAnswer(String v) { this.answer = v; }
    public String getCompany() { return company; }
    public void setCompany(String v) { this.company = v; }
    public String getRole() { return role; }
    public void setRole(String v) { this.role = v; }
    public String getTags() { return tags; }
    public void setTags(String v) { this.tags = v; }
    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty v) { this.difficulty = v; }
    public String getCategory() { return category; }
    public void setCategory(String v) { this.category = v; }
}