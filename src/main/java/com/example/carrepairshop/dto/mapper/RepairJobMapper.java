package com.example.carrepairshop.dto.mapper;

import com.example.carrepairshop.dto.RepairJobDto;
import com.example.carrepairshop.model.RepairJob;
import com.example.carrepairshop.service.ClientService;
import com.example.carrepairshop.service.MechanicService;
import com.example.carrepairshop.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RepairJobMapper {
    private final ClientService clientService;
    private final MechanicService mechanicService;
    private final ServicesService servicesService;

    public RepairJob fromDto(RepairJobDto repairJobDto) {
        RepairJob repairJob = new RepairJob();
        repairJob.setClient(clientService.findById(repairJobDto.getClientId().toString()).get());
        repairJob.setMechanic(mechanicService.findById(repairJobDto.getMechanicId().toString()).get());
        repairJob.setStartDateTime(repairJobDto.getStartDateTime());
        repairJob.setEndDateTime(repairJobDto.getEndDateTime());
        repairJob.setService(servicesService.findById(repairJobDto.getServiceId().toString()).get());
        repairJob.setStatus(repairJobDto.getStatus());
        repairJob.setAdditionalDetails(repairJob.getAdditionalDetails());

        return repairJob;
    }
}
