package com.example.carrepairshop.model;

import lombok.Data;

@Data
public class Mechanic {
    String id;
    String name;
    String phone;
    String email;
    String password;
    String specialty;
    Long experience;
}
