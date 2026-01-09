package com.example.carrepairshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clients", schema = "car_service")
@SequenceGenerator(name="clients_seq", initialValue = 10)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="clients_seq")
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;

    private String role;
}
