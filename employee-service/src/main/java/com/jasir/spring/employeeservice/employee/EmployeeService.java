package com.jasir.spring.employeeservice.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public ResponseEntity<?> addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setAge(employeeRequestDTO.getAge());
        employee.setPosition(employeeRequestDTO.getPosition());
        employee.setDepartmentId(employeeRequestDTO.getDepartmentId());
        employeeRepository.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body("New employee created successfully");
    }

    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        ArrayList<Employee> employeeArrayList = new ArrayList<>(employeeList);

        return ResponseEntity.status(HttpStatus.FOUND).body(employeeArrayList);
    }

    public ResponseEntity<?> updateEmployee(Long employeeId, EmployeeRequestDTO employeeRequestDTO) {
        if (employeeRepository.findById(employeeId).isPresent()){

            Employee employee = employeeRepository.findById(employeeId).get();
            employee.setName(employeeRequestDTO.getName());
            employee.setAge(employeeRequestDTO.getAge());
            employee.setPosition(employeeRequestDTO.getPosition());
            employee.setDepartmentId(employeeRequestDTO.getDepartmentId());
            employeeRepository.save(employee);

            return ResponseEntity.status(HttpStatus.OK).body("Employee updated successfully");

        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee");
        }
    }

    public List<Employee> findByDepartment(Long departmentId) {
            List<Employee> employeeList = employeeRepository.findByDepartmentId(departmentId);
            ArrayList<Employee> employeeArrayList = new ArrayList<>(employeeList);

            return employeeArrayList;
    }

    public ResponseEntity<?> deleteEmployee(Long employeeId) {
        if (employeeRepository.findById(employeeId).isPresent()){

            Employee employee = employeeRepository.findById(employeeId).get();
            employeeRepository.delete(employee);

            return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");

        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid employee");
        }
    }
}
