package com.example.carrepairshop.controller;

import com.example.carrepairshop.dto.VisitorRequestDto;
import com.example.carrepairshop.model.VisitorRequest;
import com.example.carrepairshop.service.VisitorRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.carrepairshop.openapi.SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME;

@RestController
@RequestMapping("/api/visitor-requests")
public class VisitorRequestController {

    private final VisitorRequestService visitorRequestService;

    public VisitorRequestController(VisitorRequestService visitorRequestService) {
        this.visitorRequestService = visitorRequestService;
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping
    public List<VisitorRequest> getVisitorRequests() {
        return visitorRequestService.getAllVisitorRequests();
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @GetMapping("{id}")
    public ResponseEntity<VisitorRequest> getVisitorRequest(@PathVariable Long id) {
        return visitorRequestService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVisitorRequest(@PathVariable Long id) {
        visitorRequestService.deleteVisitorRequest(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("new")
    public ResponseEntity<VisitorRequest> createVisitorRequest(@RequestBody VisitorRequestDto dto) {
        return ResponseEntity.ok(visitorRequestService.createVisitorRequest(dto));
    }
}
