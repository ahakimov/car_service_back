package com.example.carrepairshop.model;

import lombok.Data;

@Data
public class Reservation {
    String id;
    String contactInfo;
    String mechanic;
    String dateAdded;
    String status;
}
