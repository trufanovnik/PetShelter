package com.ensep.petshelter.controllers.rest.auth;

import com.ensep.petshelter.dto.AuthenticationResponse;
import com.ensep.petshelter.dto.LoginRequest;
import com.ensep.petshelter.dto.ShelterRegistrationRequest;
import com.ensep.petshelter.dto.UserRegistrationRequest;
import com.ensep.petshelter.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/shelter")
    public ResponseEntity<String> registerShelter(
            @Valid @RequestBody ShelterRegistrationRequest request) {
        authService.registerShelter(request);
        return ResponseEntity.ok("Приют успешно зарегистрирован");
    }

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(
            @RequestBody UserRegistrationRequest request) {
        authService.registerUser(request);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
