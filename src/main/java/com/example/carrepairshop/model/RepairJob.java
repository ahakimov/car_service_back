package com.example.carrepairshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "repair_jobs", schema = "car_service")
@SequenceGenerator(name="repair_jobs_seq", initialValue = 10)
public class RepairJob {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="repair_jobs_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Client client;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Mechanic mechanic;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Service service;
    private String status;
    private String additionalDetails;
}
