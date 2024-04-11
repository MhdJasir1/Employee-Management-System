package com.jasir.spring.departmentservice.department;

import com.jasir.spring.departmentservice.client.EmployeeClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentResource {

    private final DepartmentService departmentService;
    private final EmployeeClient employeeClient;
    private final DepartmentRepository departmentRepository;

    @PostMapping("/add-department")
    public ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentRequestDTO departmentRequestDTO){
        return departmentService.addDepartment(departmentRequestDTO);
    }

    @GetMapping("/all-departments")
    public ResponseEntity<?> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PutMapping("/update-department/{departmentId}")
    public ResponseEntity<?> updateDepartment(@Valid @RequestBody DepartmentRequestDTO departmentRequestDTO, @PathVariable("departmentId") Long departmentId){
        return departmentService.updateDepartment(departmentId,departmentRequestDTO);
    }

    @DeleteMapping("/delete-department/{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("departmentId") Long departmentId){
        return departmentService.deleteDepartment(departmentId);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        List<Department> departments
                = departmentRepository.findAll();
        departments.forEach(department ->
                department.setEmployees(
                employeeClient.findByDepartment(department.getId()))
    );

        return  departments;
    }

}
