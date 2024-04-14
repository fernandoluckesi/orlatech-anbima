package com.orlatech.testeanbima.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class EmployeeInProjectResponseDTO {
    private UUID id;

    private String name;

    private String email;

    private String cpf;

}
