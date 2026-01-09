package com.example.carrepairshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mechanics", schema = "car_service")
@SequenceGenerator(name="mechanics_seq", initialValue = 10)
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="mechanics_seq")
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String specialty;
    private Long experience;
}
