package com.example.carrepairshop.service;

import com.example.carrepairshop.dto.RepairJobDto;
import com.example.carrepairshop.dto.mapper.RepairJobMapper;
import com.example.carrepairshop.model.RepairJob;
import com.example.carrepairshop.repository.RepairJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairJobService {
    private final RepairJobMapper repairJobMapper;

    private final RepairJobRepository repairJobRepository;

    public RepairJobService(RepairJobRepository repairJobRepository, RepairJobMapper repairJobMapper) {
        this.repairJobRepository = repairJobRepository;
        this.repairJobMapper = repairJobMapper;
    }

    public List<RepairJob> getAllRepairJobs() {
        return repairJobRepository.findAll();
    }

    public Optional<RepairJob> findById(Long id) {
        return repairJobRepository.findById(id);
    }

    public RepairJob updateRepairJob(Long id, RepairJobDto repairJobDto) {
        RepairJob repairJob = repairJobMapper.fromDto(repairJobDto);
        repairJob.setId(id);
        return repairJobRepository.save(repairJob);
    }

    public void deleteRepairJob(Long id) {
        repairJobRepository.deleteById(id);
    }

    public RepairJob createRepairJob(RepairJobDto repairJobDto) {
        return repairJobRepository.save(repairJobMapper.fromDto(repairJobDto));
    }

    public List<RepairJob> findRepairJobsByMechanic(Long mechanicId) {
        return repairJobRepository.findRepairJobsByMechanic_Id(mechanicId);
    }

}
