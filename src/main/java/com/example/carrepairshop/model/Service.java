package com.example.carrepairshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "services", schema = "car_service")
@SequenceGenerator(name="service_seq", initialValue = 10)
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="service_seq")
    private Long id;
    private String serviceName;
    private String description;
    private Double price;
    private Long estimatedDuration;
}
