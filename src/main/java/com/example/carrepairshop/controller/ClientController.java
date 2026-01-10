package com.example.carrepairshop.controller;

import com.example.carrepairshop.model.Client;
import com.example.carrepairshop.model.Reservation;
import com.example.carrepairshop.model.ReservationFilter;
import com.example.carrepairshop.security.user.CustomUserDetails;
import com.example.carrepairshop.service.ClientService;
import com.example.carrepairshop.service.ReservationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.carrepairshop.openapi.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;
    private final ReservationsService reservationsService;

    public ClientController(ClientService clientService, ReservationsService reservationsService) {
        this.clientService = clientService;
        this.reservationsService = reservationsService;
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping
    public List<Client> clientList() {
        return clientService.getAllClients();
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("profile")
    public Client getMyProfile(@AuthenticationPrincipal CustomUserDetails currentUserDetails) {
        return clientService.findById(currentUserDetails.getId().toString()).get();
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("{id}")
    public Client getClient(@PathVariable String id) {
        return clientService.findById(id).orElse(null);
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.updateClient(client));
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);

        return ResponseEntity.ok("Deleted");
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsForClient(@PathVariable(name = "id") Long clientId) {
        return ResponseEntity.ok(reservationsService.filterReservations(ReservationFilter.builder().clientId(clientId).build()));
    }
}
