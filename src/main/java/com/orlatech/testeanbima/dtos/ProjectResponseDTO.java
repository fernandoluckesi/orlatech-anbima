package com.orlatech.testeanbima.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.orlatech.testeanbima.entities.EmployeeEntity;
import com.orlatech.testeanbima.entities.ProjectEntity;

@Data
@AllArgsConstructor
public class ProjectResponseDTO {

    private UUID id;

    private String name;

    private LocalDateTime createdAt;

    private List<EmployeeInProjectResponseDTO> employees;

    public ProjectResponseDTO(ProjectEntity data) {
        this.id = data.getId();
        this.name = data.getName();
        this.createdAt = data.getCreatedAt();
        this.employees = convertToEmployeeResponseDTOs(data.getEmployees());
    }

    private List<EmployeeInProjectResponseDTO> convertToEmployeeResponseDTOs(List<EmployeeEntity> employees) {
        return employees.stream()
                .map(employee -> new EmployeeInProjectResponseDTO(employee.getId(), employee.getName(),
                        employee.getEmail(), employee.getCpf()))
                .collect(Collectors.toList());
    }

}
