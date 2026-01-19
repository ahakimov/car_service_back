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
        List<Reservation> reservations = reservationRepository.findAll();
        updateExpiredReservationStatuses(reservations);
        return reservations;
    }

    /**
     * Auto-update status for past reservations:
     * - Confirmed + past date → completed
     * - Unconfirmed/In Progress + past date → cancelled
     */
    private void updateExpiredReservationStatuses(List<Reservation> reservations) {
        LocalDateTime now = LocalDateTime.now();
        for (Reservation r : reservations) {
            if (r.getVisitDateTime() != null && r.getVisitDateTime().isBefore(now)) {
                String currentStatus = r.getStatus() != null ? r.getStatus().toLowerCase() : "";
                // Skip already finalized reservations
                if ("completed".equals(currentStatus) || "cancelled".equals(currentStatus)) {
                    continue;
                }
                // Confirmed reservations become completed
                if ("confirmed".equals(currentStatus)) {
                    r.setStatus("completed");
                    reservationRepository.save(r);
                } else {
                    // Unconfirmed, In Progress, etc. become cancelled
                    r.setStatus("cancelled");
                    reservationRepository.save(r);
                }
            }
        }
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
        List<Reservation> reservations = reservationRepository.findAll(ReservationSpecification.filter(filter));
        updateExpiredReservationStatuses(reservations);
        return reservations;
    }

}
