package com.example.carrepairshop.repository;

import com.example.carrepairshop.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    boolean existsByEmail(String email);
    Optional<Client> findClientByEmail(String email);
}
