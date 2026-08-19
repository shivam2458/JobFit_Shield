package com.jobfitshield.backend.resume;

import com.jobfitshield.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByUser(User user);

    boolean existsByUser(User user);
}