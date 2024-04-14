package com.orlatech.testeanbima.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orlatech.testeanbima.dtos.AddEmployeeInProjectDTO;
import com.orlatech.testeanbima.dtos.ProjectCreateDTO;
import com.orlatech.testeanbima.dtos.ProjectResponseDTO;
import com.orlatech.testeanbima.services.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    ProjectService projectService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody ProjectCreateDTO projectCreateDTO) {
        var projectCreated = this.projectService.create(projectCreateDTO);

        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO(projectCreated);

        return ResponseEntity.created((java.net.URI.create("/project" + projectResponseDTO.getId())))
                .body(projectResponseDTO);
    }

    @PostMapping("/add-employee")
    public ResponseEntity<Object> addEmployee(@Valid @RequestBody AddEmployeeInProjectDTO addEmployeeInProjectDTO) {
        var projectWithEmployeeAdded = this.projectService.addEmployee(addEmployeeInProjectDTO.getProjectId(),
                addEmployeeInProjectDTO.getEmployeeCpf());

        return ResponseEntity.created((java.net.URI.create("/project" + projectWithEmployeeAdded.getId())))
                .body(projectWithEmployeeAdded);
    }

    @PostMapping("/remove-employee")
    public ResponseEntity<Object> removeEmployee(@Valid @RequestBody AddEmployeeInProjectDTO addEmployeeInProjectDTO) {
        var projectWithEmployeeRemoved = this.projectService.removeEmployee(addEmployeeInProjectDTO.getProjectId(),
                addEmployeeInProjectDTO.getEmployeeCpf());

        return ResponseEntity.created((java.net.URI.create("/project" + projectWithEmployeeRemoved.getId())))
                .body(projectWithEmployeeRemoved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        ProjectResponseDTO projectResponseDTO = projectService.getById(id);

        return ResponseEntity.ok().body(projectResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAll() {
        var projects = this.projectService.getAll();

        return ResponseEntity.ok().body(projects);
    }

}
