package com.example.carrepairshop.model;

import lombok.Data;

@Data
public class Service {
    String id;
    String serviceName;
    String description;
    Double price;
    Long estimatedDuration;
}
