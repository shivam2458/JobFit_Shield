package com.jobfitshield.backend.user;

import com.jobfitshield.backend.user.dto.RegisterRequest;
import com.jobfitshield.backend.user.dto.UpdateProfileRequest;
import com.jobfitshield.backend.user.dto.UserProfileResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequest request) {

        String normalizedEmail = request.email()
                .trim()
                .toLowerCase();

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException(
                    "An account with this email already exists"
            );
        }

        User user = new User();

        user.setFirstName(request.firstName().trim());
        user.setLastName(request.lastName().trim());
        user.setEmail(normalizedEmail);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public UserProfileResponse getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );

        return UserProfileResponse.from(user);
    }

    public UserProfileResponse updateProfile(
            String email,
            UpdateProfileRequest request
    ) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found")
                );

        user.setUniversity(request.getUniversity());
        user.setDegree(request.getDegree());
        user.setGraduationYear(request.getGraduationYear());
        user.setLocation(request.getLocation());
        user.setGithubUrl(request.getGithubUrl());
        user.setLinkedinUrl(request.getLinkedinUrl());
        user.setPortfolioUrl(request.getPortfolioUrl());
        user.setBio(request.getBio());
        user.setSkills(request.getSkills());
        user.setExperience(request.getExperience());
        user.setResumeUrl(request.getResumeUrl());

        User updatedUser = userRepository.save(user);

        return UserProfileResponse.from(updatedUser);
    }
}