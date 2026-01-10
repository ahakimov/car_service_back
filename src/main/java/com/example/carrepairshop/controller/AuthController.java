package com.example.carrepairshop.controller;

import com.example.carrepairshop.model.*;
import com.example.carrepairshop.security.AuthenticationConfig;
import com.example.carrepairshop.security.user.DuplicatedUserInfoException;
import com.example.carrepairshop.service.ClientService;
import com.example.carrepairshop.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.validPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getEmail()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody RegistrationRequest registrationRequest) {
        if (userService.hasUserWithEmail(registrationRequest.getEmail())) {
            throw new DuplicatedUserInfoException(String.format("Email %s has already been used", registrationRequest.getEmail()));
        }

        User user = userService.createUser(mapSignUpRequestToUser(registrationRequest));
        clientService.createClient(mapSignUpRequestToClient(registrationRequest));

        return new AuthResponse(user.getId(), user.getEmail());
    }

    private User mapSignUpRequestToUser(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEmail(registrationRequest.getEmail());
        user.setRole(AuthenticationConfig.USER);
        return user;
    }

    private Client mapSignUpRequestToClient(RegistrationRequest registrationRequest) {
        Client client = new Client();
        client.setName(registrationRequest.getName());
        client.setPhone(registrationRequest.getPhone());
        client.setEmail(registrationRequest.getEmail());

        return client;
    }
}
