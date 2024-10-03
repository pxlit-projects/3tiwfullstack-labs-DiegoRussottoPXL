package be.pxl.services.controller;

import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;


    @GetMapping
    public ResponseEntity getEmployees(){
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody EmployeeRequest employeeRequest){
        employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponse findEmployeeById(@PathVariable Long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }
    @GetMapping("/department/{departmentId}")
    public List<EmployeeResponse> findEmployeeByDepartment(@PathVariable Long departmentId) {
        return employeeService.findEmployeeByDepartment(departmentId);
    }
    @GetMapping("/organization/{organizationId}")
    public List<EmployeeResponse> findEmployeeByOrganization(@PathVariable Long organizationId) {
        return employeeService.findEmployeeByOrganization(organizationId);
    }

}
