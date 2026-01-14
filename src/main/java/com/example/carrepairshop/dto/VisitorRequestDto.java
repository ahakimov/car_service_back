package com.example.carrepairshop.dto;

import lombok.Data;

@Data
public class VisitorRequestDto {
    private String fullName;
    private String contactNumber;
    private String email;
    private Long serviceId;
    private String serviceName;
    private String visitDate;
    private String time;
    private String description;
}
