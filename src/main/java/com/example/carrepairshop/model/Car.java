package com.example.carrepairshop.model;

import lombok.Data;

@Data
public class Car {
    String id;
    String model;
    String make;
    Integer year;
    String licensePlate;
    Client owner;
}
