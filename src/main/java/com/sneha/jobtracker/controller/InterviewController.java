package com.sneha.jobtracker.controller;

import com.sneha.jobtracker.dto.request.InterviewRequest;
import com.sneha.jobtracker.dto.response.InterviewResponse;
import com.sneha.jobtracker.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    public ResponseEntity<InterviewResponse> schedule(@Valid @RequestBody InterviewRequest request) {
        return ResponseEntity.ok(interviewService.schedule(request));
    }

    @GetMapping
    public ResponseEntity<List<InterviewResponse>> getMyInterviews() {
        return ResponseEntity.ok(interviewService.getMyInterviews());
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<InterviewResponse>> getByApplication(@PathVariable Long applicationId) {
        return ResponseEntity.ok(interviewService.getByApplication(applicationId));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<InterviewResponse> complete(@PathVariable Long id,
                                                      @RequestParam(required = false) String feedback,
                                                      @RequestParam(required = false) Integer rating) {
        return ResponseEntity.ok(interviewService.complete(id, feedback, rating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        interviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}