package com.example.carrepairshop.controller;

import com.example.carrepairshop.model.RepairJob;
import com.example.carrepairshop.service.RepairJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.carrepairshop.openapi.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@RestController
@RequestMapping("/api/repair-jobs")
public class RepairJobController {

    private final RepairJobService repairJobService;

    public RepairJobController(RepairJobService repairJobService) {
        this.repairJobService = repairJobService;
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping
    public List<RepairJob> repairJobList() {
        return repairJobService.getAllRepairJobs();
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("{id}")
    public RepairJob getRepairJob(@PathVariable String id) {
        return repairJobService.findById(Long.valueOf(id)).orElse(null);
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PutMapping
    public ResponseEntity<RepairJob> updateRepairJob(@RequestBody RepairJob RepairJob) {
        return ResponseEntity.ok(repairJobService.updateRepairJob(RepairJob));
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRepairJob(@PathVariable String id) {
        repairJobService.deleteRepairJob(Long.valueOf(id));

        return ResponseEntity.ok("Deleted");
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @PostMapping("new")
    public ResponseEntity<RepairJob> createRepairJob(@RequestBody RepairJob repairJob) {
        return ResponseEntity.ok(repairJobService.createRepairJob(repairJob));
    }
}
