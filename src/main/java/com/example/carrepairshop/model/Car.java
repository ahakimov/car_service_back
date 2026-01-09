package com.example.carrepairshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cars", schema = "car_service")
@SequenceGenerator(name="cars_seq", initialValue = 10)
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cars_seq")
    private Long id;
    private String model;
    private String make;
    @Column(name = "produced")
    private Integer year;
    private String licensePlate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Client owner;
}
