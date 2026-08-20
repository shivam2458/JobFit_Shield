package com.jobfitshield.backend.resume;

import com.jobfitshield.backend.resume.dto.ResumeResponse;
import com.jobfitshield.backend.resume.dto.SaveResumeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping("/upload")
    public ResumeResponse uploadResume(
            Authentication authentication,
            @RequestParam("file") MultipartFile file
    ) {
        return resumeService.uploadPdf(
                authentication.getName(),
                file
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

    @GetMapping("/skills")
    public List<String> getResumeSkills(
            Authentication authentication
    ) {
        return resumeService.extractResumeSkills(
                authentication.getName()
        );
    }
}