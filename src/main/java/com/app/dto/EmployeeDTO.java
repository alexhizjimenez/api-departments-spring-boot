package com.app.dto;

import com.app.services.DepartmentDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotNull
    private String firstName;
    @Size(message = "Limite del apellidoo es 2 a 20 caracteres", min = 2, max = 20)
    private String lastName;
    private String email;
    private Long phone;
    private LocalDate dateOfBirth;
    private char gender;
    private boolean isActive;
    private DepartmentDTO departmentDTO;
}
