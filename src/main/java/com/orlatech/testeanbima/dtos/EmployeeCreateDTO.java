package com.orlatech.testeanbima.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeCreateDTO {

    private String name;

    private String email;

    private String cpf;

    private Double salary;

}
