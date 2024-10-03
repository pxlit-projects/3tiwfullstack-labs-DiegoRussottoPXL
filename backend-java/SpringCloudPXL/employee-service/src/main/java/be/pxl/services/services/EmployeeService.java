package be.pxl.services.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.exceptions.NotFoundException;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {
    private  final EmployeeRepository employeeRepository;
    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> mapToEmployeeResponse(employee)).toList();
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .age(employee.getAge())
                .name(employee.getName())
                .position(employee.getPosition())
                .build();
    }

    @Override
    public void addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .age(employeeRequest.getAge())
                .name(employeeRequest.getName())
                .position(employeeRequest.getPosition())
                .build();
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponse findEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("No employee with id [" + employeeId + "]"));
        return new EmployeeResponse(employee.getOrganizationId(), employee.getDepartmentId(), employee.getName(), employee.getAge(), employee.getPosition());
    }

    @Override
    public List<EmployeeResponse> findEmployeeByDepartment(Long departmentId) {
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId).stream().toList();
        if (employees.isEmpty()) {
            throw new NotFoundException("No employees with departmentId [" + departmentId + "]");
        }
        List<EmployeeResponse> employeeDTOS = new ArrayList<>();
        for (Employee employee:employees
        ) {
            employeeDTOS.add(new EmployeeResponse(employee.getOrganizationId(), employee.getDepartmentId(), employee.getName(), employee.getAge(), employee.getPosition()));
        }
        return employeeDTOS;
    }

    @Override
    public List<EmployeeResponse> findEmployeeByOrganization(Long organizationId) {
        List<Employee> employees = employeeRepository.findByOrganizationId(organizationId).stream().toList();
        if (employees.isEmpty()) {
            throw new NotFoundException("No employees with organizationId [" + organizationId + "]");
        }
        List<EmployeeResponse> employeeDTOS = new ArrayList<>();
        for (Employee employee:employees
        ) {
            employeeDTOS.add(new EmployeeResponse(employee.getOrganizationId(), employee.getDepartmentId(), employee.getName(), employee.getAge(), employee.getPosition()));
        }
        return employeeDTOS;
    }
}
