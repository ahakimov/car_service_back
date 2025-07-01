package com.example.carrepairshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private String contactInfo;

    @ManyToOne
    @JoinColumn(name = "id")
    private Mechanic mechanic;
    private LocalDateTime dateAdded;
    private String status;
}
