package com.jobfitshield.backend.job.dto;

import com.jobfitshield.backend.job.JobPosting;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class JobPostingResponse {

    private Long id;
    private String title;
    private String company;
    private String location;
    private String jobUrl;
    private String jobDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static JobPostingResponse from(JobPosting jobPosting) {
        return new JobPostingResponse(
                jobPosting.getId(),
                jobPosting.getTitle(),
                jobPosting.getCompany(),
                jobPosting.getLocation(),
                jobPosting.getJobUrl(),
                jobPosting.getJobDescription(),
                jobPosting.getCreatedAt(),
                jobPosting.getUpdatedAt()
        );
    }
}