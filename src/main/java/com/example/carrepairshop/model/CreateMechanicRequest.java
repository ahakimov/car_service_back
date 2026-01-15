package com.example.carrepairshop.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMechanicRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @Email
    private String email;
    @NotBlank
    private String password;
    private String specialty;
    private Integer experience;
}
