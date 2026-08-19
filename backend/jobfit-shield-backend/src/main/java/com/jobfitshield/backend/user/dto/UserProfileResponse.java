package com.jobfitshield.backend.user.dto;

import com.jobfitshield.backend.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
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

    public static UserProfileResponse from(User user) {
        return new UserProfileResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUniversity(),
                user.getDegree(),
                user.getGraduationYear(),
                user.getLocation(),
                user.getGithubUrl(),
                user.getLinkedinUrl(),
                user.getPortfolioUrl(),
                user.getBio(),
                user.getSkills(),
                user.getExperience(),
                user.getResumeUrl()
        );
    }
}