package com.sneha.jobtracker.service;

import com.sneha.jobtracker.dto.request.QuestionRequest;
import com.sneha.jobtracker.dto.response.QuestionResponse;
import com.sneha.jobtracker.entity.Question;
import com.sneha.jobtracker.entity.User;
import com.sneha.jobtracker.enums.Difficulty;
import com.sneha.jobtracker.exception.ResourceNotFoundException;
import com.sneha.jobtracker.repository.QuestionRepository;
import com.sneha.jobtracker.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private QuestionResponse toResponse(Question q) {
        QuestionResponse r = new QuestionResponse();
        r.setId(q.getId());
        r.setQuestion(q.getQuestion());
        r.setAnswer(q.getAnswer());
        r.setCompany(q.getCompany());
        r.setRole(q.getRole());
        r.setTags(q.getTags());
        r.setDifficulty(q.getDifficulty());
        r.setCategory(q.getCategory());
        return r;
    }

    public QuestionResponse create(QuestionRequest request) {
        User user = getCurrentUser();
        Question q = new Question();
        q.setUser(user);
        q.setQuestion(request.getQuestion());
        q.setAnswer(request.getAnswer());
        q.setCompany(request.getCompany());
        q.setRole(request.getRole());
        q.setTags(request.getTags());
        q.setDifficulty(request.getDifficulty());
        q.setCategory(request.getCategory());
        return toResponse(questionRepository.save(q));
    }

    public List<QuestionResponse> getAll() {
        User user = getCurrentUser();
        return questionRepository.findAllForUser(user.getId())
                .stream().map(this::toResponse).toList();
    }

    public List<QuestionResponse> getByDifficulty(String difficulty) {
        User user = getCurrentUser();
        return questionRepository.findByDifficulty(user.getId(), Difficulty.valueOf(difficulty))
                .stream().map(this::toResponse).toList();
    }

    public List<QuestionResponse> getByCompany(String company) {
        User user = getCurrentUser();
        return questionRepository.findByCompany(user.getId(), company)
                .stream().map(this::toResponse).toList();
    }

    public QuestionResponse update(Long id, QuestionRequest request) {
        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        q.setQuestion(request.getQuestion());
        q.setAnswer(request.getAnswer());
        q.setCompany(request.getCompany());
        q.setRole(request.getRole());
        q.setTags(request.getTags());
        q.setDifficulty(request.getDifficulty());
        q.setCategory(request.getCategory());
        return toResponse(questionRepository.save(q));
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }
}