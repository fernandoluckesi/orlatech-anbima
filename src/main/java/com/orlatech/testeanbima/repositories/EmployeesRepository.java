package com.orlatech.testeanbima.repositories;

import com.orlatech.testeanbima.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeesRepository extends JpaRepository<EmployeeEntity, UUID> {

}
