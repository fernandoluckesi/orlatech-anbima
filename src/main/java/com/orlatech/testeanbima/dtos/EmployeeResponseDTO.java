package com.orlatech.testeanbima.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

import com.orlatech.testeanbima.entities.EmployeeEntity;
import com.orlatech.testeanbima.entities.ProjectEntity;

@Data
@AllArgsConstructor
public class EmployeeResponseDTO {
    private UUID id;

    private String name;

    private String email;

    private String cpf;

    private Double salary;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private List<ProjectInEmployeeResponseDTO> projects = new ArrayList<>();

    public EmployeeResponseDTO(EmployeeEntity data) {
        this.id = data.getId();
        this.name = data.getName();
        this.email = data.getEmail();
        this.cpf = data.getCpf();
        this.salary = data.getSalary();
        this.createdAt = data.getCreatedAt();
        this.updateAt = data.getUpdateAt();
        this.projects = convertToProjectResponseDTOs(data.getProjects());
    }

    private List<ProjectInEmployeeResponseDTO> convertToProjectResponseDTOs(List<ProjectEntity> projects) {
        return projects.stream()
                .map(project -> new ProjectInEmployeeResponseDTO(project.getId(), project.getName(),
                        project.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
