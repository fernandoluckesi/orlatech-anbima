package com.orlatech.testeanbima.services;

import com.orlatech.testeanbima.dtos.EmployeeDTO;
import com.orlatech.testeanbima.entities.EmployeeEntity;
import com.orlatech.testeanbima.exceptions.ValidationExceptionNotFound;
import com.orlatech.testeanbima.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeesRepository employeesRepository;

    public EmployeeDTO getById(UUID id) {
        EmployeeEntity employee = this.employeesRepository.findById(id)
                .orElseThrow(
                        () -> new ValidationExceptionNotFound(HttpStatus.NOT_FOUND.name(), "Funcionário não encontrado",
                                HttpStatus.NOT_FOUND.value()));

        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(), employee.getName(), employee.getEmail(),
                employee.getCpf(), employee.getSalary(), employee.getCreatedAt(), employee.getUpdateAt());

        return employeeDTO;
    }
}
