package com.example.carrepairshop.service;

import com.example.carrepairshop.dto.VisitorRequestDto;
import com.example.carrepairshop.model.VisitorRequest;
import com.example.carrepairshop.repository.VisitorRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorRequestService {

    private final VisitorRequestRepository visitorRequestRepository;

    public VisitorRequestService(VisitorRequestRepository visitorRequestRepository) {
        this.visitorRequestRepository = visitorRequestRepository;
    }

    public List<VisitorRequest> getAllVisitorRequests() {
        return visitorRequestRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<VisitorRequest> findById(Long id) {
        return visitorRequestRepository.findById(id);
    }

    public VisitorRequest createVisitorRequest(VisitorRequestDto dto) {
        VisitorRequest request = new VisitorRequest();
        request.setFullName(dto.getFullName());
        request.setContactNumber(dto.getContactNumber());
        request.setEmail(dto.getEmail());
        request.setServiceId(dto.getServiceId());
        request.setServiceName(dto.getServiceName());

        if (dto.getVisitDate() != null && !dto.getVisitDate().isEmpty()) {
            request.setVisitDate(LocalDateTime.parse(dto.getVisitDate()));
        }

        request.setTime(dto.getTime());
        request.setDescription(dto.getDescription());
        request.setStatus("new");
        request.setCreatedAt(LocalDateTime.now());

        return visitorRequestRepository.save(request);
    }

    public void deleteVisitorRequest(Long id) {
        visitorRequestRepository.deleteById(id);
    }

    public List<VisitorRequest> findByStatus(String status) {
        return visitorRequestRepository.findByStatus(status);
    }
}
