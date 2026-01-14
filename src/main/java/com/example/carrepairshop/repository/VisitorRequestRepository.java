package com.example.carrepairshop.repository;

import com.example.carrepairshop.model.VisitorRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRequestRepository extends JpaRepository<VisitorRequest, Long> {
    List<VisitorRequest> findAllByOrderByCreatedAtDesc();
    List<VisitorRequest> findByStatus(String status);
}
