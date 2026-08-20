package com.jobfitshield.backend.match;

import com.jobfitshield.backend.job.JobPosting;
import com.jobfitshield.backend.job.JobPostingRepository;
import com.jobfitshield.backend.resume.Resume;
import com.jobfitshield.backend.resume.ResumeRepository;
import com.jobfitshield.backend.skill.SkillExtractor;
import com.jobfitshield.backend.user.User;
import com.jobfitshield.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobMatchService {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final JobPostingRepository jobPostingRepository;
    private final SkillExtractor skillExtractor;

    public MatchResult analyzeMatch(String email, Long jobId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );

        Resume resume = resumeRepository.findByUser(user)
                .orElseThrow(() ->
                        new IllegalArgumentException("Resume not found")
                );

        JobPosting jobPosting = jobPostingRepository
                .findByIdAndUser(jobId, user)
                .orElseThrow(() ->
                        new IllegalArgumentException("Job posting not found")
                );

        List<String> resumeSkills =
                skillExtractor.extractSkills(resume.getResumeText());

        List<String> jobSkills =
                skillExtractor.extractSkills(jobPosting.getJobDescription());

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String jobSkill : jobSkills) {

            if (resumeSkills.contains(jobSkill)) {
                matchedSkills.add(jobSkill);
            } else {
                missingSkills.add(jobSkill);
            }
        }

        int matchScore = 0;

        if (!jobSkills.isEmpty()) {
            matchScore = (int) Math.round(
                    ((double) matchedSkills.size() / jobSkills.size()) * 100
            );
        }

        String recommendation = getRecommendation(matchScore);

        List<String> suggestions =
                generateSuggestions(missingSkills);

        return new MatchResult(
                jobId,
                matchScore,
                recommendation,
                matchedSkills,
                missingSkills,
                suggestions,
                resumeSkills,
                jobSkills
        );
    }

    private String getRecommendation(int matchScore) {

        if (matchScore >= 80) {
            return "STRONG_MATCH";
        }

        if (matchScore >= 60) {
            return "GOOD_MATCH";
        }

        if (matchScore >= 40) {
            return "MODERATE_MATCH";
        }

        return "WEAK_MATCH";
    }

    private List<String> generateSuggestions(
            List<String> missingSkills
    ) {

        List<String> suggestions = new ArrayList<>();

        if (missingSkills.isEmpty()) {
            suggestions.add(
                    "Your resume contains all identified skills from this job description."
            );

            return suggestions;
        }

        for (String skill : missingSkills) {
            suggestions.add(
                    "Add or strengthen " + skill +
                            " experience on your resume if you have relevant experience."
            );
        }

        return suggestions;
    }

    public record MatchResult(
            Long jobId,
            int matchScore,
            String recommendation,
            List<String> matchedSkills,
            List<String> missingSkills,
            List<String> suggestions,
            List<String> resumeSkills,
            List<String> jobSkills
    ) {
    }
}