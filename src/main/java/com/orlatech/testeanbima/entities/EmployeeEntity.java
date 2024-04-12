package com.orlatech.testeanbima.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "employees")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O Nome não pode estar vazio")
    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Email(message = "Informe um e-mail válido")
    private String email;

    @CPF(message = "Número do CPF inválido")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "\\d*", message = "CPF deve conter apenas números")
    private String cpf;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "O salário não pode estar vazio")
    private Double salary;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;
}
