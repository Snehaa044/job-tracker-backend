package com.sneha.jobtracker.controller;

import com.sneha.jobtracker.dto.request.JobApplicationRequest;
import com.sneha.jobtracker.dto.response.JobApplicationResponse;
import com.sneha.jobtracker.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> create(@Valid @RequestBody JobApplicationRequest request) {
        return ResponseEntity.ok(jobApplicationService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationResponse>> getAll() {
        return ResponseEntity.ok(jobApplicationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(jobApplicationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> update(@PathVariable Long id,
                                                         @Valid @RequestBody JobApplicationRequest request) {
        return ResponseEntity.ok(jobApplicationService.update(id, request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<JobApplicationResponse> updateStatus(@PathVariable Long id,
                                                               @RequestParam String status) {
        return ResponseEntity.ok(jobApplicationService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jobApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}