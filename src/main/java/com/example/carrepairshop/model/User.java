package com.example.carrepairshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users", schema = "car_service")
@SequenceGenerator(name="users_seq", initialValue = 10)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_seq")
    private Long id;
    private String email;
    private String password;
    private String role;
}
