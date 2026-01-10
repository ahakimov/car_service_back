package com.example.carrepairshop.dto;

import lombok.Data;

@Data
public class CarDto {
    private String model;
    private String make;
    private Integer year;
    private String licensePlate;
    private Long ownerId;
}
