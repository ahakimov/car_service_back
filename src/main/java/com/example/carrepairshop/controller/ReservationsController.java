package com.example.carrepairshop.controller;

import com.example.carrepairshop.model.Reservation;
import com.example.carrepairshop.service.ReservationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationsController {

    private final ReservationsService reservationsService;

    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @GetMapping
    public List<Reservation> reservationList() {
        return reservationsService.getAllReservations();
    }

    @GetMapping("{id}")
    public Reservation getReservation(@PathVariable String id) {
        return reservationsService.findById(id).orElse(null);
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationsService.updateReservation(reservation));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable String id) {
        reservationsService.deleteReservation(id);

        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("new")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationsService.createReservation(reservation));
    }
}
