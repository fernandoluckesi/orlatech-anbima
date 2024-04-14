package com.orlatech.testeanbima.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orlatech.testeanbima.entities.ProjectEntity;

public interface ProjectsRespository extends JpaRepository<ProjectEntity, UUID> {

    List<ProjectEntity> findByNameLikeIgnoreCase(String name);

    Optional<ProjectEntity> findByNameIgnoreCase(String name);

}
