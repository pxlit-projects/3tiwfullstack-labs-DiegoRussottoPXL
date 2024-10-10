package be.pxl.services;

import be.pxl.services.domain.Department;
import be.pxl.services.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@ContextConfiguration(classes = DepartmentServiceApplication.class)
public class DepartmentTests
{
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Container
    private static MySQLContainer sqlContainer =
            new MySQLContainer("mysql:5.7.37");

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registery){
        registery.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registery.add("spring.datasource.username", sqlContainer::getUsername);
        registery.add("spring.datasource.password", sqlContainer::getPassword);
    }

    @Test
    public void testCreateDepartment() throws Exception{
        Department department = Department.builder()
                .name("gebouw")
                .position("seg")
                .build();

        String departmentString = objectMapper.writeValueAsString(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content(departmentString))
                .andExpect(status().isCreated());
        assertEquals(1, departmentRepository.findAll().size());
    }

}
