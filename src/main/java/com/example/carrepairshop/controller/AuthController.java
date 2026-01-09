package com.example.carrepairshop.controller;

import com.example.carrepairshop.model.AuthResponse;
import com.example.carrepairshop.model.Client;
import com.example.carrepairshop.model.LoginRequest;
import com.example.carrepairshop.model.RegistrationRequest;
import com.example.carrepairshop.security.AuthenticationConfig;
import com.example.carrepairshop.security.user.DuplicatedUserInfoException;
import com.example.carrepairshop.service.ClientService;
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
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<Client> clientOptional = clientService.validPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            return ResponseEntity.ok(new AuthResponse(client.getId(), client.getName()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody RegistrationRequest registrationRequest) {
        if (clientService.hasClientWithEmail(registrationRequest.getEmail())) {
            throw new DuplicatedUserInfoException(String.format("Email %s has already been used", registrationRequest.getEmail()));
        }

        Client client = clientService.createClient(this.mapSignUpRequestToClient(registrationRequest));
        return new AuthResponse(client.getId(), client.getName());
    }

    private Client mapSignUpRequestToClient(RegistrationRequest registrationRequest) {
        Client client = new Client();
        client.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        client.setName(registrationRequest.getName());
        client.setPhone(registrationRequest.getPhone());
        client.setEmail(registrationRequest.getEmail());
        client.setRole(AuthenticationConfig.USER);
        return client;
    }
}
