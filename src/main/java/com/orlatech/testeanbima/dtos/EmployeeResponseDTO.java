package com.orlatech.testeanbima.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

import com.orlatech.testeanbima.entities.EmployeeEntity;

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

    public EmployeeResponseDTO(EmployeeEntity data) {
        this.id = data.getId();
        this.name = data.getName();
        this.email = data.getEmail();
        this.cpf = data.getCpf();
        this.salary = data.getSalary();
        this.createdAt = data.getCreatedAt();
        this.updateAt = data.getUpdateAt();
    }

}
