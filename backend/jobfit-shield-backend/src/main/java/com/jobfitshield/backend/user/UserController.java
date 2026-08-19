package com.jobfitshield.backend.user;

import com.jobfitshield.backend.user.dto.UpdateProfileRequest;
import com.jobfitshield.backend.user.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserProfileResponse getMyProfile(Authentication authentication) {

        return userService.getProfile(authentication.getName());
    }

    @PutMapping("/me")
    public UserProfileResponse updateMyProfile(
            Authentication authentication,
            @RequestBody UpdateProfileRequest request
    ) {

        return userService.updateProfile(
                authentication.getName(),
                request
        );
    }
}