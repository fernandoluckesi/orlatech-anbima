package com.orlatech.testeanbima.controllers;

import com.orlatech.testeanbima.dtos.EmployeeDTO;
import com.orlatech.testeanbima.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable UUID id) {
        EmployeeDTO employee = this.employeeService.getById(id);

        return ResponseEntity.ok().body(employee);
    }
}
