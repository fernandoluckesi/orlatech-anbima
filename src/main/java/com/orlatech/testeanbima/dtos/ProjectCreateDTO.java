package com.orlatech.testeanbima.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreateDTO {

    private String employeeCpf;

    private String name;

}
