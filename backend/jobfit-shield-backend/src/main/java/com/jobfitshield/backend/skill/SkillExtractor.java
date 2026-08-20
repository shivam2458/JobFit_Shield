package com.jobfitshield.backend.skill;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillExtractor {

    private static final List<String> KNOWN_SKILLS = List.of(
            "Java",
            "Python",
            "C++",
            "C",
            "JavaScript",
            "SQL",
            "Spring Boot",
            "Flask",
            "PostgreSQL",
            "SQLite",
            "HTML",
            "CSS",
            "Git",
            "GitHub",
            "Linux",
            "Docker",
            "REST API",
            "JWT",
            "JUnit",
            "Maven"
    );

    public List<String> extractSkills(String resumeText) {

        List<String> extractedSkills = new ArrayList<>();

        if (resumeText == null || resumeText.isBlank()) {
            return extractedSkills;
        }

        String normalizedText = resumeText.toLowerCase();

        for (String skill : KNOWN_SKILLS) {

            if (normalizedText.contains(skill.toLowerCase())) {
                extractedSkills.add(skill);
            }
        }

        return extractedSkills;
    }
}