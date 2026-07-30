package com.jobfitshield.backend.auth;

import com.jobfitshield.backend.user.UserService;
import com.jobfitshield.backend.user.dto.LoginRequest;
import com.jobfitshield.backend.user.dto.LoginResponse;
import com.jobfitshield.backend.user.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@Valid @RequestBody RegisterRequest request) {

        userService.registerUser(request);

        return "User registered successfully!";
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        return authenticationService.login(request);
    }
}