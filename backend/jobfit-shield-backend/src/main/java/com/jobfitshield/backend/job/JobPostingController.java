package com.jobfitshield.backend.job;

import com.jobfitshield.backend.job.dto.CreateJobPostingRequest;
import com.jobfitshield.backend.job.dto.JobPostingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping
    public JobPostingResponse createJobPosting(
            Authentication authentication,
            @Valid @RequestBody CreateJobPostingRequest request
    ) {
        return jobPostingService.createJobPosting(
                authentication.getName(),
                request
        );
    }

    @GetMapping
    public List<JobPostingResponse> getAllJobPostings(
            Authentication authentication
    ) {
        return jobPostingService.getAllJobPostings(
                authentication.getName()
        );
    }

    @GetMapping("/{id}")
    public JobPostingResponse getJobPosting(
            Authentication authentication,
            @PathVariable Long id
    ) {
        return jobPostingService.getJobPosting(
                authentication.getName(),
                id
        );
    }
}