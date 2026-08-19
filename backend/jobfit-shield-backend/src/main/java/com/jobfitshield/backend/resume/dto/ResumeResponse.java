package com.jobfitshield.backend.resume.dto;

import com.jobfitshield.backend.resume.Resume;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResumeResponse {

    private Long id;
    private String resumeText;
    private String fileName;
    private String fileType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ResumeResponse from(Resume resume) {
        return new ResumeResponse(
                resume.getId(),
                resume.getResumeText(),
                resume.getFileName(),
                resume.getFileType(),
                resume.getCreatedAt(),
                resume.getUpdatedAt()
        );
    }
}
