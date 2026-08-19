package com.jobfitshield.backend.resume;

import com.jobfitshield.backend.resume.dto.ResumeResponse;
import com.jobfitshield.backend.resume.dto.SaveResumeRequest;
import com.jobfitshield.backend.user.User;
import com.jobfitshield.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeResponse saveOrUpdateResume(
            String email,
            SaveResumeRequest request
    ) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );

        Resume resume = resumeRepository.findByUser(user)
                .orElseGet(() -> {
                    Resume newResume = new Resume();
                    newResume.setUser(user);
                    return newResume;
                });

        resume.setResumeText(request.getResumeText());
        resume.setFileName(null);
        resume.setFileType("TEXT");

        Resume savedResume = resumeRepository.save(resume);

        return ResumeResponse.from(savedResume);
    }

    public ResumeResponse getResume(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );

        Resume resume = resumeRepository.findByUser(user)
                .orElseThrow(() ->
                        new IllegalArgumentException("Resume not found")
                );

        return ResumeResponse.from(resume);
    }
}