package com.jobfitshield.backend.job;

import com.jobfitshield.backend.job.dto.CreateJobPostingRequest;
import com.jobfitshield.backend.job.dto.JobPostingResponse;
import com.jobfitshield.backend.user.User;
import com.jobfitshield.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;

    public JobPostingResponse createJobPosting(
            String email,
            CreateJobPostingRequest request
    ) {

        User user = getUserByEmail(email);

        JobPosting jobPosting = new JobPosting();

        jobPosting.setUser(user);
        jobPosting.setTitle(request.getTitle().trim());
        jobPosting.setCompany(request.getCompany().trim());
        jobPosting.setLocation(request.getLocation());
        jobPosting.setJobUrl(request.getJobUrl());
        jobPosting.setJobDescription(request.getJobDescription());

        JobPosting savedJobPosting =
                jobPostingRepository.save(jobPosting);

        return JobPostingResponse.from(savedJobPosting);
    }

    public List<JobPostingResponse> getAllJobPostings(
            String email
    ) {

        User user = getUserByEmail(email);

        return jobPostingRepository
                .findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(JobPostingResponse::from)
                .toList();
    }

    public JobPostingResponse getJobPosting(
            String email,
            Long id
    ) {

        User user = getUserByEmail(email);

        JobPosting jobPosting =
                jobPostingRepository
                        .findByIdAndUser(id, user)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Job posting not found"
                                )
                        );

        return JobPostingResponse.from(jobPosting);
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