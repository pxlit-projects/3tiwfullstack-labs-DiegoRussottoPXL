package be.pxl.services;

import be.pxl.services.domain.Organization;
import be.pxl.services.repository.OrganizationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Unit test for simple App.
 */
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@ContextConfiguration(classes = OrganizationServiceApplication.class)
public class OrganizationTests
{
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrganizationRepository organizationRepository;

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
    public void testGetOrganizationById() throws Exception {
        Organization organization = Organization.builder()
                .address("Hasselt")
                .name("Cegeka")
                .build();

        Organization savedOrganization = organizationRepository.save(organization);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/organization/" + savedOrganization.getId()))
                .andExpect(status().isOk());

        Optional<Organization> retrievedOrganization = organizationRepository.findById(savedOrganization.getId());

        assertTrue(retrievedOrganization.isPresent());
        assertEquals(organization.getId(), retrievedOrganization.get().getId());
    }
}
