package com.jobfitshield.backend.resume.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveResumeRequest {

    @NotBlank(message = "Resume text is required")
    private String resumeText;
}