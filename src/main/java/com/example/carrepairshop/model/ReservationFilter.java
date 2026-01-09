package com.example.carrepairshop.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationFilter {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Long clientId;
    private Long carId;
    private Long serviceId;
    private Long mechanicId;
}
