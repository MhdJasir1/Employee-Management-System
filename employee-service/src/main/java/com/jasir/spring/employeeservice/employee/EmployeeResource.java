package com.jasir.spring.employeeservice.employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeResource {

    private final EmployeeService employeeService;

    @PostMapping("/add-employee")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){
        return employeeService.addEmployee(employeeRequestDTO);
    }

    @GetMapping("/all-employees")
    public ResponseEntity<?> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        return employeeService.findByDepartment(departmentId);
    }

    @PutMapping("/update-employee/{employeeId}")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO, @PathVariable("employeeId") Long employeeId){
        return employeeService.updateEmployee(employeeId,employeeRequestDTO);
    }

    @DeleteMapping("/delete-employee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId){
        return employeeService.deleteEmployee(employeeId);
    }

}
