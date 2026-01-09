package com.example.carrepairshop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDto {
    private Long clientId;
    private Long carId;
    private Long mechanicId;
    private Long serviceId;
    private LocalDateTime dateAdded;
    private LocalDateTime visitDateTime;
    private String status;
    private String additionalDetails;
}
