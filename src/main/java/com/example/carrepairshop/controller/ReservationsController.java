package com.example.carrepairshop.controller;

import com.example.carrepairshop.dto.ReservationDto;
import com.example.carrepairshop.model.Reservation;
import com.example.carrepairshop.model.ReservationFilter;
import com.example.carrepairshop.service.ReservationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.carrepairshop.openapi.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController {

    private final ReservationsService reservationsService;

    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping
    public List<Reservation> reservationList() {
        return reservationsService.getAllReservations();
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("{id}")
    public Reservation getReservation(@PathVariable String id) {
        return reservationsService.findById(id).orElse(null);
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PutMapping("{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationsService.updateReservation(id, reservationDto));
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable String id) {
        reservationsService.deleteReservation(id);

        return ResponseEntity.ok("Deleted");
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PostMapping("new")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationsService.createReservation(reservationDto));
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("schedule")
    public ResponseEntity<List<Reservation>> getReservationsSchedule(@RequestParam String from, @RequestParam String to) {
        return ResponseEntity.ok(reservationsService.findReservationByVisitDate(LocalDateTime.parse(from), LocalDateTime.parse(to)));
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PostMapping("filter")
    public ResponseEntity<List<Reservation>> filterReservations(@RequestBody ReservationFilter filter) {
        return ResponseEntity.ok(reservationsService.filterReservations(filter));
    }
}
