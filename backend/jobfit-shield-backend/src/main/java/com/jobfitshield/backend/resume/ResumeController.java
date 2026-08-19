package com.jobfitshield.backend.resume;

import com.jobfitshield.backend.resume.dto.ResumeResponse;
import com.jobfitshield.backend.resume.dto.SaveResumeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResumeResponse saveResume(
            Authentication authentication,
            @Valid @RequestBody SaveResumeRequest request
    ) {
        return resumeService.saveOrUpdateResume(
                authentication.getName(),
                request
        );
    }

    @GetMapping
    public ResumeResponse getResume(
            Authentication authentication
    ) {
        return resumeService.getResume(
                authentication.getName()
        );
    }
}