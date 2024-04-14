package com.orlatech.testeanbima.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEmployeeInProjectDTO {

    private UUID projectId;
    private String employeeCpf;

}
