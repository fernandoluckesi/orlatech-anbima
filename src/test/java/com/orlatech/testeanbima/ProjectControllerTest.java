package com.orlatech.testeanbima;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orlatech.testeanbima.dtos.EmployeeCreateDTO;
import com.orlatech.testeanbima.dtos.EmployeeInProjectResponseDTO;
import com.orlatech.testeanbima.dtos.EmployeeResponseDTO;
import com.orlatech.testeanbima.dtos.ProjectCreateDTO;
import com.orlatech.testeanbima.dtos.ProjectInEmployeeResponseDTO;
import com.orlatech.testeanbima.dtos.ProjectResponseDTO;
import com.orlatech.testeanbima.entities.EmployeeEntity;
import com.orlatech.testeanbima.entities.ProjectEntity;
import com.orlatech.testeanbima.services.EmployeeService;
import com.orlatech.testeanbima.services.ProjectService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private ProjectService projectService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void shouldReturnAllProjects() throws Exception {
                List<EmployeeInProjectResponseDTO> employeeInProjectResponseDTOs = Arrays.asList(
                                new EmployeeInProjectResponseDTO(
                                                UUID.fromString("ffb20824-48c2-4c17-a11f-f2a6cf9647cd"),
                                                "Fernando Luckesi 0", "fernando.luckesi0@gmail.com", "16664146037"),
                                new EmployeeInProjectResponseDTO(
                                                UUID.fromString("e96aaaf7-46e6-4de6-93b6-b74f04b71ce0"),
                                                "Fernando Luckesi 1",
                                                "fernando.luckesi1@gmail.com",
                                                "42275937862"));

                List<EmployeeInProjectResponseDTO> emptyEmployeeList = new ArrayList<>();

                List<ProjectResponseDTO> projects = Arrays.asList(
                                new ProjectResponseDTO(UUID.fromString("1ecbb921-8a5f-4b2d-a629-40243772bd06"),
                                                "Time do Bairro 0",
                                                LocalDateTime.parse("2024-04-14T17:47:38.962824"),
                                                employeeInProjectResponseDTOs),
                                new ProjectResponseDTO(UUID.fromString("83d6e585-a1bc-49c7-84f8-f321866da933"),
                                                "Time do Bairro 1",
                                                LocalDateTime.parse("2024-04-14T17:47:54.969916"), emptyEmployeeList));

                when(projectService.getAll()).thenReturn(projects);

                mockMvc.perform(MockMvcRequestBuilders.get("/projects"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", hasSize(2)))
                                .andExpect(jsonPath("$[0].id").value("1ecbb921-8a5f-4b2d-a629-40243772bd06"))
                                .andExpect(jsonPath("$[0].name").value("Time do Bairro 0"))
                                .andExpect(jsonPath("$[0].createdAt").value("2024-04-14T17:47:38.962824"))
                                .andExpect(jsonPath("$[0].employees").isArray())
                                .andExpect(jsonPath("$[0].employees[0].id")
                                                .value("ffb20824-48c2-4c17-a11f-f2a6cf9647cd"))
                                .andExpect(jsonPath("$[0].employees[0].name").value("Fernando Luckesi 0"))
                                .andExpect(jsonPath("$[0].employees[0].email").value("fernando.luckesi0@gmail.com"))
                                .andExpect(jsonPath("$[0].employees[0].cpf").value("16664146037"))

                                .andExpect(jsonPath("$[0].employees[1].id")
                                                .value("e96aaaf7-46e6-4de6-93b6-b74f04b71ce0"))
                                .andExpect(jsonPath("$[0].employees[1].name").value("Fernando Luckesi 1"))
                                .andExpect(jsonPath("$[0].employees[1].email").value("fernando.luckesi1@gmail.com"))
                                .andExpect(jsonPath("$[0].employees[1].cpf").value("42275937862"))

                                .andExpect(jsonPath("$[1].id").value("83d6e585-a1bc-49c7-84f8-f321866da933"))
                                .andExpect(jsonPath("$[1].name").value("Time do Bairro 1"))
                                .andExpect(jsonPath("$[1].createdAt").value("2024-04-14T17:47:54.969916"))
                                .andExpect(jsonPath("$[1].employees").isArray())
                                .andExpect(jsonPath("$[1].employees").isEmpty());
        }

        @Test
        void shouldReturnUniqueProject() throws Exception {

                List<EmployeeInProjectResponseDTO> employeeInProjectResponseDTOs = Arrays.asList(
                                new EmployeeInProjectResponseDTO(
                                                UUID.fromString("e96aaaf7-46e6-4de6-93b6-b74f04b71ce0"),
                                                "Fernando Luckesi 1",
                                                "fernando.luckesi1@gmail.com",
                                                "42275937862"));

                ProjectResponseDTO project = new ProjectResponseDTO(
                                UUID.fromString("83d6e585-a1bc-49c7-84f8-f321866da933"),
                                "Time do Bairro 0",
                                LocalDateTime.parse("2024-04-14T17:47:38.962824"),
                                employeeInProjectResponseDTOs);

                when(projectService.getById(UUID.fromString("83d6e585-a1bc-49c7-84f8-f321866da933")))
                                .thenReturn(project);

                mockMvc.perform(MockMvcRequestBuilders.get("/projects/83d6e585-a1bc-49c7-84f8-f321866da933"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value("83d6e585-a1bc-49c7-84f8-f321866da933"))
                                .andExpect(jsonPath("$.name").value("Time do Bairro 0"))
                                .andExpect(jsonPath("$.createdAt").value("2024-04-14T17:47:38.962824"))
                                .andExpect(jsonPath("$.employees").isArray())
                                .andExpect(jsonPath("$.employees[0].id")
                                                .value("e96aaaf7-46e6-4de6-93b6-b74f04b71ce0"))
                                .andExpect(jsonPath("$.employees[0].name").value("Fernando Luckesi 1"))
                                .andExpect(jsonPath("$.employees[0].email").value("fernando.luckesi1@gmail.com"))
                                .andExpect(jsonPath("$.employees[0].cpf").value("42275937862"));

        }

        @Test
        void shouldReturnCreateProject() throws Exception {

                when(projectService.create(any(ProjectCreateDTO.class))).thenReturn(new ProjectEntity());

                mockMvc.perform(post("/projects")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                                "{\"employeeCpf\":\"42275937862\",\"name\":\"Time do Bairro 1\"}"))
                                .andExpect(status().isCreated());

                verify(projectService, times(1)).create(any(ProjectCreateDTO.class));
        }
}
