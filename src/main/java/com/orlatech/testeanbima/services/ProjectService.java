package com.orlatech.testeanbima.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.orlatech.testeanbima.dtos.ProjectCreateDTO;
import com.orlatech.testeanbima.dtos.ProjectResponseDTO;
import com.orlatech.testeanbima.entities.EmployeeEntity;
import com.orlatech.testeanbima.entities.ProjectEntity;
import com.orlatech.testeanbima.exceptions.ValidationExceptionConflict;
import com.orlatech.testeanbima.exceptions.ValidationExceptionNotFound;
import com.orlatech.testeanbima.repositories.EmployeesRepository;
import com.orlatech.testeanbima.repositories.ProjectsRespository;

@Service
public class ProjectService {

    @Autowired
    private ProjectsRespository projectsRespository;

    @Autowired
    private EmployeesRepository employeesRepository;

    public ProjectEntity create(ProjectCreateDTO projectCreateDTO) {
        this.projectsRespository.findByNameIgnoreCase(projectCreateDTO.getName()).ifPresent((project) -> {
            throw new ValidationExceptionConflict(HttpStatus.CONFLICT.name(), "Projeto já cadastrado com esse nome",
                    HttpStatus.CONFLICT.value());
        });

        EmployeeEntity employee = this.employeesRepository.findByCpf(projectCreateDTO.getEmployeeCpf())
                .orElseThrow(
                        () -> new ValidationExceptionNotFound(HttpStatus.NOT_FOUND.name(),
                                "Funcionário não encontrado",
                                HttpStatus.NOT_FOUND.value()));

        ProjectEntity project = new ProjectEntity(projectCreateDTO);

        project.getEmployees().add(employee);

        return this.projectsRespository.save(project);
    }

    public ProjectEntity addEmployee(UUID projectId, String employeeCpf) {

        ProjectEntity project = this.projectsRespository.findById(projectId)
                .orElseThrow(
                        () -> new ValidationExceptionNotFound(HttpStatus.NOT_FOUND.name(),
                                "Projeto não encontrado",
                                HttpStatus.NOT_FOUND.value()));

        EmployeeEntity employee = this.employeesRepository.findByCpf(employeeCpf)
                .orElseThrow(
                        () -> new ValidationExceptionNotFound(HttpStatus.NOT_FOUND.name(),
                                "Funcionário não encontrado",
                                HttpStatus.NOT_FOUND.value()));

        if (project.getEmployees().contains(employee)) {
            throw new ValidationExceptionConflict(HttpStatus.CONFLICT.name(), "Funcionário já cadastrado nesse projeto",
                    HttpStatus.CONFLICT.value());
        } else {
            project.getEmployees().add(employee);
            return this.projectsRespository.save(project);
        }
    }

    public ProjectEntity removeEmployee(UUID projectId, String employeeCpf) {

        ProjectEntity project = this.projectsRespository.findById(projectId)
                .orElseThrow(
                        () -> new ValidationExceptionNotFound(HttpStatus.NOT_FOUND.name(),
                                "Projeto não encontrado",
                                HttpStatus.NOT_FOUND.value()));

        EmployeeEntity employee = this.employeesRepository.findByCpf(employeeCpf)
                .orElseThrow(
                        () -> new ValidationExceptionNotFound(HttpStatus.NOT_FOUND.name(),
                                "Funcionário não encontrado",
                                HttpStatus.NOT_FOUND.value()));

        if (project.getEmployees().contains(employee)) {
            project.getEmployees().remove(employee);
            return this.projectsRespository.save(project);
        } else {
            throw new ValidationExceptionNotFound(HttpStatus.NOT_FOUND.name(),
                    "Funcionário não encontrado neste projeto",
                    HttpStatus.NOT_FOUND.value());
        }

    }

    public ProjectResponseDTO getById(UUID id) {
        ProjectEntity project = this.projectsRespository.findById(id)
                .orElseThrow(
                        () -> new ValidationExceptionNotFound(HttpStatus.NOT_FOUND.name(),
                                "Projeto não encontrado",
                                HttpStatus.NOT_FOUND.value()));

        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO(project);

        return projectResponseDTO;
    }

    public List<ProjectResponseDTO> getAll() {

        List<ProjectEntity> projects = this.projectsRespository.findAll();

        List<ProjectResponseDTO> projectResponseDTOs = projects.stream()
                .map(ProjectResponseDTO::new)
                .collect(Collectors.toList());

        return projectResponseDTOs;
    }

}
