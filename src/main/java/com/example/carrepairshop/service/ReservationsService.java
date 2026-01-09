package com.example.carrepairshop.service;

import com.example.carrepairshop.dto.ReservationDto;
import com.example.carrepairshop.dto.mapper.ReservationMapper;
import com.example.carrepairshop.model.Reservation;
import com.example.carrepairshop.model.ReservationFilter;
import com.example.carrepairshop.repository.ReservationRepository;
import com.example.carrepairshop.repository.utils.ReservationSpecification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationsService {
    private final ReservationMapper reservationMapper;

    private final ReservationRepository reservationRepository;

    public ReservationsService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> findById(String id) {
        return reservationRepository.findById(id);
    }

    public Reservation updateReservation(Long id, ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.fromDto(reservationDto);
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(String id) {
        reservationRepository.deleteById(id);
    }

    public Reservation createReservation(ReservationDto reservationDto) {
        return reservationRepository.save(reservationMapper.fromDto(reservationDto));
    }

    public List<Reservation> findReservationByVisitDate(LocalDateTime from, LocalDateTime to) {
        if (from != null && to != null)
            return reservationRepository.findByVisitDateTimeBetween(from, to);

        if (from != null)
            return reservationRepository.findByVisitDateTimeAfter(from);

        return reservationRepository.findByVisitDateTimeBefore(to);
    }

    public List<Reservation> filterReservations(ReservationFilter filter) {
        return reservationRepository.findAll(ReservationSpecification.filter(filter));
    }

}
