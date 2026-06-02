package com.sneha.jobtracker.controller;

import com.sneha.jobtracker.dto.request.QuestionRequest;
import com.sneha.jobtracker.dto.response.QuestionResponse;
import com.sneha.jobtracker.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getAll() {
        return ResponseEntity.ok(questionService.getAll());
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<QuestionResponse>> getByDifficulty(@PathVariable String difficulty) {
        return ResponseEntity.ok(questionService.getByDifficulty(difficulty));
    }

    @GetMapping("/company/{company}")
    public ResponseEntity<List<QuestionResponse>> getByCompany(@PathVariable String company) {
        return ResponseEntity.ok(questionService.getByCompany(company));
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> create(@Valid @RequestBody QuestionRequest request) {
        return ResponseEntity.ok(questionService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> update(@PathVariable Long id,
                                                   @Valid @RequestBody QuestionRequest request) {
        return ResponseEntity.ok(questionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}