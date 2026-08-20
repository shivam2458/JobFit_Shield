package com.jobfitshield.backend.job;

import com.jobfitshield.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobPostingRepository
        extends JpaRepository<JobPosting, Long> {

    List<JobPosting> findByUserOrderByCreatedAtDesc(User user);

    Optional<JobPosting> findByIdAndUser(Long id, User user);
}