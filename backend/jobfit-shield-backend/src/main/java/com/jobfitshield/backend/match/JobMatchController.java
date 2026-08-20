package com.jobfitshield.backend.match;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class JobMatchController {

    private final JobMatchService jobMatchService;

    @GetMapping("/{jobId}")
    public JobMatchService.MatchResult analyzeMatch(
            Authentication authentication,
            @PathVariable Long jobId
    ) {
        return jobMatchService.analyzeMatch(
                authentication.getName(),
                jobId
        );
    }
}