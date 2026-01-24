package com.example.carrepairshop.dto.mapper;

import com.example.carrepairshop.dto.ReservationDto;
import com.example.carrepairshop.model.Reservation;
import com.example.carrepairshop.service.CarService;
import com.example.carrepairshop.service.ClientService;
import com.example.carrepairshop.service.MechanicService;
import com.example.carrepairshop.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ReservationMapper {
    private final ClientService clientService;
    private final CarService carService;
    private final MechanicService mechanicService;
    private final ServicesService servicesService;

    public Reservation fromDto(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setClient(clientService.findById(reservationDto.getClientId().toString()).get());
        reservation.setCar(carService.findById(reservationDto.getCarId().toString()).get());
        reservation.setMechanic(mechanicService.findById(reservationDto.getMechanicId().toString()).get());
        reservation.setService(servicesService.findById(reservationDto.getServiceId().toString()).get());
        reservation.setDateAdded(reservationDto.getDateAdded());
        reservation.setVisitDateTime(reservationDto.getVisitDateTime());
        reservation.setEndDateTime(reservationDto.getEndDateTime());
        reservation.setStatus(reservationDto.getStatus());
        reservation.setAdditionalDetails(reservationDto.getAdditionalDetails());

        return reservation;
    }
}
