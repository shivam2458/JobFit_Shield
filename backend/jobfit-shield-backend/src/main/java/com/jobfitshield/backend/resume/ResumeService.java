package com.jobfitshield.backend.resume;

import com.jobfitshield.backend.resume.dto.ResumeResponse;
import com.jobfitshield.backend.resume.dto.SaveResumeRequest;
import com.jobfitshield.backend.skill.SkillExtractor;
import com.jobfitshield.backend.user.User;
import com.jobfitshield.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final PdfTextExtractor pdfTextExtractor;
    private final SkillExtractor skillExtractor;

    public ResumeResponse saveOrUpdateResume(
            String email,
            SaveResumeRequest request
    ) {

        User user = getUserByEmail(email);

        Resume resume = resumeRepository.findByUser(user)
                .orElseGet(() -> {
                    Resume newResume = new Resume();
                    newResume.setUser(user);
                    return newResume;
                });

        resume.setResumeText(request.getResumeText());
        resume.setFileName(null);
        resume.setFileType("TEXT");

        return ResumeResponse.from(
                resumeRepository.save(resume)
        );
    }

    public ResumeResponse uploadPdf(
            String email,
            MultipartFile file
    ) {

        User user = getUserByEmail(email);

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("PDF file is required");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null ||
                !fileName.toLowerCase().endsWith(".pdf")) {

            throw new IllegalArgumentException(
                    "Only PDF files are allowed"
            );
        }

        String extractedText;

        try {
            extractedText = pdfTextExtractor.extractText(
                    file.getBytes()
            );
        } catch (IOException exception) {
            throw new IllegalArgumentException(
                    "Could not read uploaded file",
                    exception
            );
        }

        if (extractedText.isBlank()) {
            throw new IllegalArgumentException(
                    "No readable text found in PDF"
            );
        }

        Resume resume = resumeRepository.findByUser(user)
                .orElseGet(() -> {
                    Resume newResume = new Resume();
                    newResume.setUser(user);
                    return newResume;
                });

        resume.setResumeText(extractedText);
        resume.setFileName(fileName);
        resume.setFileType("PDF");

        return ResumeResponse.from(
                resumeRepository.save(resume)
        );
    }

    public ResumeResponse getResume(String email) {

        User user = getUserByEmail(email);

        Resume resume = resumeRepository.findByUser(user)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Resume not found"
                        )
                );

        return ResumeResponse.from(resume);
    }

    public List<String> extractResumeSkills(String email) {

        User user = getUserByEmail(email);

        Resume resume = resumeRepository.findByUser(user)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Resume not found"
                        )
                );

        return skillExtractor.extractSkills(
                resume.getResumeText()
        );
    }

    private User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "User not found"
                        )
                );
    }
}