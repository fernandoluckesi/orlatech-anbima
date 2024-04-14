package com.orlatech.testeanbima.services;

import com.orlatech.testeanbima.dtos.EmployeeCreateDTO;
import com.orlatech.testeanbima.dtos.EmployeeResponseDTO;
import com.orlatech.testeanbima.entities.EmployeeEntity;
import com.orlatech.testeanbima.exceptions.ValidationExceptionConflict;
import com.orlatech.testeanbima.exceptions.ValidationExceptionNotFound;
import com.orlatech.testeanbima.repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

        @Autowired
        private EmployeesRepository employeesRepository;

        public EmployeeEntity create(EmployeeCreateDTO employeeCreateDTO) {

                this.employeesRepository.findByEmail(employeeCreateDTO.getEmail())
                                .ifPresent((employee) -> {
                                        throw new ValidationExceptionConflict(HttpStatus.CONFLICT.name(),
                                                        "Funcionário já cadastrado com esse e-mail",
                                                        HttpStatus.CONFLICT.value());
                                });

                this.employeesRepository.findByCpf(employeeCreateDTO.getCpf())
                                .ifPresent((employee) -> {
                                        throw new ValidationExceptionConflict(HttpStatus.CONFLICT.name(),
                                                        "Funcionário já cadastrado com esse cpf",
                                                        HttpStatus.CONFLICT.value());
                                });

                EmployeeEntity employee = new EmployeeEntity(employeeCreateDTO);

                return this.employeesRepository.save(employee);
        }

        public EmployeeResponseDTO getById(UUID id) {
                EmployeeEntity employee = this.employeesRepository.findById(id)
                                .orElseThrow(
                                                () -> new ValidationExceptionNotFound(HttpStatus.NOT_FOUND.name(),
                                                                "Funcionário não encontrado",
                                                                HttpStatus.NOT_FOUND.value()));

                EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(employee);

                return employeeResponseDTO;
        }

        public List<EmployeeResponseDTO> getAll() {

                List<EmployeeEntity> employees = this.employeesRepository.findAll();

                List<EmployeeResponseDTO> employeeResponseDTOs = employees.stream()
                                .map(EmployeeResponseDTO::new)
                                .collect(Collectors.toList());

                return employeeResponseDTOs;
        }
}
