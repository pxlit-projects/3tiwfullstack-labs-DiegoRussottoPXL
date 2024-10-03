package be.pxl.services.controller;

import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.service.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final IOrganizationService organizationService;
    @GetMapping("/{organizationId}")
    public OrganizationResponse findOrganizationById(@PathVariable Long organizationId) {
        return organizationService.findOrganizationById(organizationId);
    }
    @GetMapping("/{organizationId}/with-departments")
    public OrganizationResponse findByOrganizationIdWithDepartments(@PathVariable Long organizationId) {
        return organizationService.findByOrganizationIdWithDepartments(organizationId);
    }
    @GetMapping("/{organizationId}/with-departments-and-employees")
    public OrganizationResponse findByOrganizationIdWithDepartmentsAndEmployees(@PathVariable Long organizationId) {
        return organizationService.findByOrganizationIdWithDepartmentsAndEmployees(organizationId);
    }

    @GetMapping("/{organizationId}/with-employees")
    public OrganizationResponse findByOrganizationIdWithEmployees(@PathVariable Long organizationId) {
        return organizationService.findByOrganizationIdWithEmployees(organizationId);
    }
}
