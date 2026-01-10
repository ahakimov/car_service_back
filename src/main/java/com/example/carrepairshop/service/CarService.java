package com.example.carrepairshop.service;

import com.example.carrepairshop.dto.CarDto;
import com.example.carrepairshop.dto.mapper.CarMapper;
import com.example.carrepairshop.model.Car;
import com.example.carrepairshop.model.Client;
import com.example.carrepairshop.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CarService {
    private final CarMapper carMapper;

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public List<Car> getAllCars() {
        log.info("carService getAllCars started");
        var result = carRepository.findAll();
        log.info("carService getAllCars finished with response: {}", result);
        return result;
    }

    public Optional<Car> findById(String id) {
        return carRepository.findById(id);
    }

    public Car updateCar(Long id, CarDto carDto) {
        Car car = carMapper.fromDto(carDto);
        car.setId(id);
        return carRepository.save(car);
    }

    public void deleteCar(String id) {
        carRepository.deleteById(id);
    }

    public Car createCar(CarDto carDto) {
        return carRepository.save(carMapper.fromDto(carDto));
    }

    public Optional<Car> findByOwner(Client client) {
        return carRepository.findByOwner(client);
    }
}
