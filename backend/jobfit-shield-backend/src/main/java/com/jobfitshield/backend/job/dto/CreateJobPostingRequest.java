package com.jobfitshield.backend.job.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateJobPostingRequest {

    @NotBlank(message = "Job title is required")
    private String title;

    @NotBlank(message = "Company is required")
    private String company;

    private String location;

    private String jobUrl;

    @NotBlank(message = "Job description is required")
    private String jobDescription;
}