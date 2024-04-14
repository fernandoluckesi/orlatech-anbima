package com.orlatech.testeanbima.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.orlatech.testeanbima.entities.EmployeeEntity;
import com.orlatech.testeanbima.entities.ProjectEntity;

@Data
@AllArgsConstructor
public class ProjectResponseDTO {

    private UUID id;

    private String name;

    private LocalDateTime createdAt;

    private List<EmployeeEntity> employees;

    public ProjectResponseDTO(ProjectEntity data) {
        this.id = data.getId();
        this.name = data.getName();
        this.createdAt = data.getCreatedAt();
        this.employees = data.getEmployees();
    }

}
