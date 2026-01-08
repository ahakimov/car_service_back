package com.example.carrepairshop.controller;

import com.example.carrepairshop.model.Mechanic;
import com.example.carrepairshop.service.MechanicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.carrepairshop.openapi.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicsController {
    private final MechanicService mechanicService;

    public MechanicsController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping
    public List<Mechanic> mechanicList() {
        return mechanicService.getAllMechanics();
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("{id}")
    public Mechanic getMechanic(@PathVariable String id) {
        return mechanicService.findById(id).orElse(null);
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PutMapping
    public ResponseEntity<Mechanic> updateMechanic(@RequestBody Mechanic mechanic) {
        return ResponseEntity.ok(mechanicService.updateMechanic(mechanic));
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMechanic(@PathVariable String id) {
        mechanicService.deleteMechanic(id);

        return ResponseEntity.ok("Deleted");
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PostMapping("new")
    public ResponseEntity<Mechanic> createMechanic(@RequestBody Mechanic mechanic) {
        return ResponseEntity.ok(mechanicService.createMechanic(mechanic));
    }
}
