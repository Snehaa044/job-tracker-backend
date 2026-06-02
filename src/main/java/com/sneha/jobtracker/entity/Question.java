package com.sneha.jobtracker.entity;

import com.sneha.jobtracker.enums.Difficulty;
import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 1000)
    private String question;

    @Column(length = 3000)
    private String answer;

    private String company;
    private String role;
    private String tags;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private String category;

    public Question() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}