package com.example.carrepairshop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RepairJobDto {
    private Long clientId;
    private Long mechanicId;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private Long serviceId;
    private String status;
    private String additionalDetails;
}
