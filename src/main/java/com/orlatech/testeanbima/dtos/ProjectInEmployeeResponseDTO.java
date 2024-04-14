package com.orlatech.testeanbima.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ProjectInEmployeeResponseDTO {

    private UUID id;

    private String name;

    private LocalDateTime createdAt;

}
