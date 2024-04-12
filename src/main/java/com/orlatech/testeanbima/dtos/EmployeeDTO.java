package com.orlatech.testeanbima.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    private UUID id;

    private String name;

    private String email;

    private String cpf;

    private Double salary;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

}
