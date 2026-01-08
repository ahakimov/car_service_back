package com.example.carrepairshop.service;

import com.example.carrepairshop.model.Client;
import com.example.carrepairshop.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(String id) {
        return clientRepository.findById(id);
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

    public boolean hasClientWithEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    public Optional<Client> validPassword(String id, String password) {
        return findById(id)
                .filter(client -> passwordEncoder.matches(password, client.getPassword()));
    }
}
