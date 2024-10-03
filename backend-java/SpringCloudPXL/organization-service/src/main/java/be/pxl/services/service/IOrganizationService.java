package be.pxl.services.service;

import be.pxl.services.domain.dto.OrganizationResponse;

public interface IOrganizationService {
    OrganizationResponse findOrganizationById(Long organizationId);

    OrganizationResponse findByOrganizationIdWithDepartments(Long organizationId);

    OrganizationResponse findByOrganizationIdWithDepartmentsAndEmployees(Long organizationId);

    OrganizationResponse findByOrganizationIdWithEmployees(Long organizationId);
}
