package com.example.carrepairshop.controller;

import com.example.carrepairshop.model.CreateMechanicRequest;
import com.example.carrepairshop.model.Mechanic;
import com.example.carrepairshop.model.RepairJob;
import com.example.carrepairshop.security.user.CustomUserDetails;
import com.example.carrepairshop.service.MechanicService;
import com.example.carrepairshop.service.RepairJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.carrepairshop.openapi.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicsController {
    private final MechanicService mechanicService;
    private final RepairJobService repairJobService;

    public MechanicsController(MechanicService mechanicService, RepairJobService repairJobService) {
        this.mechanicService = mechanicService;
        this.repairJobService = repairJobService;
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping
    public List<Mechanic> mechanicList() {
        return mechanicService.getAllMechanics();
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("profile")
    public Mechanic getMyProfile(@AuthenticationPrincipal CustomUserDetails currentUserDetails) {
        return mechanicService.findById(currentUserDetails.getId().toString()).get();
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
    public ResponseEntity<Mechanic> createMechanic(@Valid @RequestBody CreateMechanicRequest request) {
        return ResponseEntity.ok(mechanicService.createMechanicWithUser(request));
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("{id}/repair-jobs")
    public List<RepairJob> getRepairJobsForMechanic(@PathVariable Long id) {
        return repairJobService.findRepairJobsByMechanic(id);
    }
}
