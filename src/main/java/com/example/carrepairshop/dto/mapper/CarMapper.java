package com.example.carrepairshop.dto.mapper;

import com.example.carrepairshop.dto.CarDto;
import com.example.carrepairshop.model.Car;
import com.example.carrepairshop.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CarMapper {
    private final ClientService clientService;

    public Car fromDto(CarDto carDto) {
        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setMake(carDto.getMake());
        car.setYear(carDto.getYear());
        car.setLicensePlate(carDto.getLicensePlate());
        car.setOwner(clientService.findById(carDto.getOwnerId().toString()).get());

        return car;
    }
}
