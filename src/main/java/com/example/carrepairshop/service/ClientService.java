package com.example.carrepairshop.service;

import com.example.carrepairshop.model.Client;
import com.example.carrepairshop.model.CreateClientRequest;
import com.example.carrepairshop.model.User;
import com.example.carrepairshop.repository.ClientRepository;
import com.example.carrepairshop.security.AuthenticationConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    public ClientService(ClientRepository clientRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Client> getAllClients() {
        List<Client> allClients = clientRepository.findAll();

        return allClients.stream()
                .filter(client -> {
                    if (client.getEmail() == null || client.getEmail().trim().isEmpty()) {
                        return false;
                    }
                    Optional<User> user = userService.findByEmail(client.getEmail());
                    if (!user.isPresent()) {
                        return false;
                    }
                    String userRole = user.get().getRole();
                    if (AuthenticationConfig.ADMIN.equals(userRole)) {
                        return false;
                    }
                    return AuthenticationConfig.USER.equals(userRole);
                })
                .collect(Collectors.toList());
    }

    public Optional<Client> findById(String id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> findByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client createClientWithUser(CreateClientRequest request) {
        if (userService.hasUserWithEmail(request.getEmail())) {
            throw new RuntimeException("User with email " + request.getEmail() + " already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(AuthenticationConfig.USER);
        userService.createUser(user);

        Client client = new Client();
        client.setName(request.getName());
        client.setPhone(request.getPhone());
        client.setEmail(request.getEmail());
        
        return clientRepository.save(client);
    }

    public boolean hasClientWithEmail(String email) {
        return clientRepository.existsByEmail(email);
    }
}
