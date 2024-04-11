package com.jasir.spring.departmentservice.department;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;
}
