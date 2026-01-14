package com.example.carrepairshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "visitor_requests", schema = "car_service")
@SequenceGenerator(name="visitor_requests_seq", initialValue = 1)
public class VisitorRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="visitor_requests_seq")
    private Long id;

    private String fullName;
    private String contactNumber;
    private String email;
    private Long serviceId;
    private String serviceName;
    private LocalDateTime visitDate;
    private String time;
    private String description;
    private String status;
    private LocalDateTime createdAt;
}
