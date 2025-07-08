package com.example.carrepairshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservations", schema = "car_service")
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private String contactInfo;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Mechanic mechanic;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Service service;
    private LocalDateTime dateAdded;
    private String status;
}
