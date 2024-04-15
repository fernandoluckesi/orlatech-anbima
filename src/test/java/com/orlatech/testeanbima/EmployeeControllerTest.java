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
import com.orlatech.testeanbima.dtos.EmployeeResponseDTO;
import com.orlatech.testeanbima.dtos.ProjectInEmployeeResponseDTO;
import com.orlatech.testeanbima.entities.EmployeeEntity;
import com.orlatech.testeanbima.services.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllEmployees() throws Exception {

        List<ProjectInEmployeeResponseDTO> projectInEmployeeResponseDTOs = Arrays
                .asList(new ProjectInEmployeeResponseDTO(UUID.fromString("1ecbb921-8a5f-4b2d-a629-40243772bd06"),
                        "Time do Bairro 0", LocalDateTime.parse("2024-04-14T17:49:34.921629")));

        List<ProjectInEmployeeResponseDTO> emptyProjectsList = new ArrayList<>();

        List<EmployeeResponseDTO> employees = Arrays.asList(
                new EmployeeResponseDTO(UUID.fromString("ffb20824-48c2-4c17-a11f-f2a6cf9647cd"), "Fernando Luckesi 0",
                        "fernando.luckesi0@gmail.com", "66992554018", 9000.0,
                        LocalDateTime.parse("2024-04-14T17:47:38.962774"),
                        LocalDateTime.parse("2024-04-14T17:47:38.962824"), projectInEmployeeResponseDTOs),
                new EmployeeResponseDTO(UUID.fromString("e96aaaf7-46e6-4de6-93b6-b74f04b71ce0"), "Fernando Luckesi 1",
                        "fernando.luckesi1@gmail.com", "42275937862", 9000.0,
                        LocalDateTime.parse("2024-04-14T17:47:54.969877"),
                        LocalDateTime.parse("2024-04-14T17:47:54.969916"), emptyProjectsList));

        when(employeeService.getAll()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value("ffb20824-48c2-4c17-a11f-f2a6cf9647cd"))
                .andExpect(jsonPath("$[0].name").value("Fernando Luckesi 0"))
                .andExpect(jsonPath("$[0].email").value("fernando.luckesi0@gmail.com"))
                .andExpect(jsonPath("$[0].cpf").value("66992554018"))
                .andExpect(jsonPath("$[0].salary").value(9000.0))
                .andExpect(jsonPath("$[0].projects").isArray())
                .andExpect(jsonPath("$[0].projects[0].id").value("1ecbb921-8a5f-4b2d-a629-40243772bd06"))
                .andExpect(jsonPath("$[0].projects[0].name").value("Time do Bairro 0"))
                .andExpect(jsonPath("$[0].projects[0].createdAt").value("2024-04-14T17:49:34.921629"))
                .andExpect(jsonPath("$[1].id").value("e96aaaf7-46e6-4de6-93b6-b74f04b71ce0"))
                .andExpect(jsonPath("$[1].name").value("Fernando Luckesi 1"))
                .andExpect(jsonPath("$[1].email").value("fernando.luckesi1@gmail.com"))
                .andExpect(jsonPath("$[1].cpf").value("42275937862"))
                .andExpect(jsonPath("$[1].salary").value(9000.0))
                .andExpect(jsonPath("$[1].projects").isArray())
                .andExpect(jsonPath("$[1].projects").isEmpty());
    }

    @Test
    void shouldReturnUniqueEmployee() throws Exception {

        List<ProjectInEmployeeResponseDTO> projectInEmployeeResponseDTOs = Arrays
                .asList(new ProjectInEmployeeResponseDTO(UUID.fromString("1ecbb921-8a5f-4b2d-a629-40243772bd06"),
                        "Time do Bairro 0", LocalDateTime.parse("2024-04-14T17:49:34.921629")));

        EmployeeResponseDTO employee = new EmployeeResponseDTO(UUID.fromString("ffb20824-48c2-4c17-a11f-f2a6cf9647cd"),
                "Fernando Luckesi 0",
                "fernando.luckesi0@gmail.com", "66992554018", 9000.0,
                LocalDateTime.parse("2024-04-14T17:47:38.962774"),
                LocalDateTime.parse("2024-04-14T17:47:38.962824"), projectInEmployeeResponseDTOs);

        when(employeeService.getById(UUID.fromString("ffb20824-48c2-4c17-a11f-f2a6cf9647cd"))).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/ffb20824-48c2-4c17-a11f-f2a6cf9647cd"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("ffb20824-48c2-4c17-a11f-f2a6cf9647cd"))
                .andExpect(jsonPath("$.name").value("Fernando Luckesi 0"))
                .andExpect(jsonPath("$.email").value("fernando.luckesi0@gmail.com"))
                .andExpect(jsonPath("$.cpf").value("66992554018"))
                .andExpect(jsonPath("$.salary").value(9000.0))
                .andExpect(jsonPath("$.projects").isArray())
                .andExpect(jsonPath("$.projects[0].id").value("1ecbb921-8a5f-4b2d-a629-40243772bd06"))
                .andExpect(jsonPath("$.projects[0].name").value("Time do Bairro 0"))
                .andExpect(jsonPath("$.projects[0].createdAt").value("2024-04-14T17:49:34.921629"));
    }

    @Test
    void shouldReturnCreateEmployee() throws Exception {

        when(employeeService.create(any(EmployeeCreateDTO.class))).thenReturn(new EmployeeEntity());

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"name\":\"João Silva\",\"email\":\"joao.silva@example.com\",\"cpf\":\"42275937862\",\"salary\":9000.00}"))
                .andExpect(status().isCreated());

        verify(employeeService, times(1)).create(any(EmployeeCreateDTO.class));
    }
}
