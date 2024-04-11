package com.jasir.spring.departmentservice.department;

import com.jasir.spring.departmentservice.client.EmployeeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeClient employeeClient;

    public ResponseEntity<?> addDepartment(DepartmentRequestDTO departmentRequestDTO) {
        if (departmentRepository.findByName(departmentRequestDTO.getName()).isPresent()) {

            return ResponseEntity.status(HttpStatus.CREATED).body("Department already exists");

        } else {
            Department department = new Department();
            department.setName(departmentRequestDTO.getName());
            departmentRepository.save(department);

            return ResponseEntity.status(HttpStatus.CREATED).body("New department created successfully");
        }
    }

    public ResponseEntity<?> getAllDepartments() {

        List<Department> departmentList = departmentRepository.findAll();
        ArrayList<Department> departmentArrayList = new ArrayList<>(departmentList);

        return ResponseEntity.status(HttpStatus.FOUND).body(departmentArrayList);
    }

    public ResponseEntity<?> updateDepartment(Long departmentId, DepartmentRequestDTO departmentRequestDTO) {

        if (departmentRepository.findById(departmentId).isPresent()) {

            Optional<Department> ownName = departmentRepository.findByIdAndName(departmentId, departmentRequestDTO.getName());

            if (ownName.isPresent() || departmentRepository.findByName(departmentRequestDTO.getName()).isEmpty()) {

                Department department = departmentRepository.findById(departmentId).get();
                department.setName(departmentRequestDTO.getName());
                departmentRepository.save(department);
                return ResponseEntity.status(HttpStatus.OK).body("Department updated successfully");

            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department name already exists");
            }

        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid department");
        }

    }

    public ResponseEntity<?> deleteDepartment(Long departmentId) {

        if (employeeClient.findByDepartment(departmentId).isEmpty()) {
            if (departmentRepository.findById(departmentId).isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid department");

            } else if (departmentRepository.findById(departmentId).isPresent()) {
                Department department = departmentRepository.findById(departmentId).get();
                departmentRepository.delete(department);
                return ResponseEntity.status(HttpStatus.OK).body("Department deleted successfully");

            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong. Please try again later");
            }
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot delete department. This department has employees.");
        }
    }
}
