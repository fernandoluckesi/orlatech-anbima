package com.orlatech.testeanbima.controllers;

import com.orlatech.testeanbima.dtos.EmployeeCreateDTO;
import com.orlatech.testeanbima.dtos.EmployeeResponseDTO;
import com.orlatech.testeanbima.services.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")

public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody EmployeeCreateDTO employeeCreateDTO) {
        var employeeCreated = this.employeeService.create(employeeCreateDTO);
        return ResponseEntity.created((java.net.URI.create("/employee" + employeeCreated.getId())))
                .body(employeeCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable UUID id) {
        EmployeeResponseDTO employee = this.employeeService.getById(id);

        return ResponseEntity.ok().body(employee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAll() {
        var employees = this.employeeService.getAll();

        return ResponseEntity.ok().body(employees);
    }
}
