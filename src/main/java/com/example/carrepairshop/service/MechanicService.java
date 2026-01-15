package com.example.carrepairshop.service;

import com.example.carrepairshop.model.CreateMechanicRequest;
import com.example.carrepairshop.model.Mechanic;
import com.example.carrepairshop.model.User;
import com.example.carrepairshop.repository.MechanicRepository;
import com.example.carrepairshop.security.AuthenticationConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MechanicService {
    private final MechanicRepository mechanicRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public MechanicService(MechanicRepository mechanicRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.mechanicRepository = mechanicRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Mechanic> getAllMechanics() {
        return mechanicRepository.findAll();
    }

    public Optional<Mechanic> findById(String id) {
        return mechanicRepository.findById(id);
    }

    public Mechanic updateMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public void deleteMechanic(String id) {
        mechanicRepository.deleteById(id);
    }

    public Mechanic createMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public Mechanic createMechanicWithUser(CreateMechanicRequest request) {
        if (userService.hasUserWithEmail(request.getEmail())) {
            throw new RuntimeException("User with email " + request.getEmail() + " already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(AuthenticationConfig.MECHANIC);
        userService.createUser(user);

        Mechanic mechanic = new Mechanic();
        mechanic.setName(request.getName());
        mechanic.setPhone(request.getPhone());
        mechanic.setEmail(request.getEmail());
        mechanic.setSpecialty(request.getSpecialty());
        if (request.getExperience() != null) {
            mechanic.setExperience(request.getExperience().longValue());
        }
        
        return mechanicRepository.save(mechanic);
    }
}
