package com.example.carrepairshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    private String model;
    private String make;
    private Integer year;
    private String licensePlate;

    @ManyToOne
    @JoinColumn(name = "id")
    private Client owner;
}
