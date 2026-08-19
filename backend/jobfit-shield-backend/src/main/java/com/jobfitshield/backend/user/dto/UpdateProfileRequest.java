package com.jobfitshield.backend.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest
{
    private String university;

    private String degree;

    private Integer graduationYear;

    private String location;

    private String githubUrl;

    private String linkedinUrl;

    private String portfolioUrl;

    private String bio;

    private String skills;

    private String experience;

    private String resumeUrl;
}
