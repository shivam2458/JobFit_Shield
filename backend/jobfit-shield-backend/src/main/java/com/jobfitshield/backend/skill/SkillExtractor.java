package com.jobfitshield.backend.skill;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class SkillExtractor {

    private static final Map<String, String> SKILL_PATTERNS =
            new LinkedHashMap<>();

    static {
        SKILL_PATTERNS.put("Java", "\\bjava\\b");
        SKILL_PATTERNS.put("Python", "\\bpython\\b");
        SKILL_PATTERNS.put(
                "C++",
                "(?<![A-Za-z0-9+#])c\\+\\+(?![A-Za-z0-9+#])"
        );
        SKILL_PATTERNS.put(
                "C",
                "(?<![A-Za-z0-9+#])c(?![A-Za-z0-9+#])"
        );
        SKILL_PATTERNS.put("JavaScript", "\\bjavascript\\b");
        SKILL_PATTERNS.put("SQL", "\\bsql\\b");
        SKILL_PATTERNS.put("Spring Boot", "\\bspring\\s+boot\\b");
        SKILL_PATTERNS.put("Flask", "\\bflask\\b");
        SKILL_PATTERNS.put(
                "PostgreSQL",
                "\\b(postgresql|postgres)\\b"
        );
        SKILL_PATTERNS.put("SQLite", "\\bsqlite\\b");
        SKILL_PATTERNS.put("HTML", "\\bhtml\\b");
        SKILL_PATTERNS.put("CSS", "\\bcss\\b");
        SKILL_PATTERNS.put("Git", "\\bgit\\b");
        SKILL_PATTERNS.put("GitHub", "\\bgithub\\b");
        SKILL_PATTERNS.put("Linux", "\\blinux\\b");
        SKILL_PATTERNS.put("Docker", "\\bdocker\\b");
        SKILL_PATTERNS.put(
                "REST API",
                "\\brest\\s+apis?\\b"
        );
        SKILL_PATTERNS.put("JWT", "\\bjwt\\b");
        SKILL_PATTERNS.put("JUnit", "\\bjunit\\b");
        SKILL_PATTERNS.put("Maven", "\\bmaven\\b");
    }

    public List<String> extractSkills(String text) {

        List<String> extractedSkills = new ArrayList<>();

        if (text == null || text.isBlank()) {
            return extractedSkills;
        }

        for (Map.Entry<String, String> entry :
                SKILL_PATTERNS.entrySet()) {

            Pattern pattern = Pattern.compile(
                    entry.getValue(),
                    Pattern.CASE_INSENSITIVE
            );

            if (pattern.matcher(text).find()) {
                extractedSkills.add(entry.getKey());
            }
        }

        return extractedSkills;
    }
}