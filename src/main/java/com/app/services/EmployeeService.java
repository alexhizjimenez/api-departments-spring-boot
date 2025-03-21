package com.app.services;

import com.app.dto.EmployeeDTO;
import com.app.entities.Department;
import com.app.entities.Employee;
import com.app.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getEmployeeByIdOptional(String id) {
        return this.employeeRepository.findById(id);
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .phone(employeeDTO.getPhone())
                .dateOfBirth(employeeDTO.getDateOfBirth())
                .gender(employeeDTO.getGender())
                .isActive(employeeDTO.isActive())
                .department(Department.builder()
                        .name(employeeDTO.getDepartmentDTO().getName())
                        .city(employeeDTO.getDepartmentDTO().getCity())
                        .build())
                .build();
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(String id, EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployee = this.employeeRepository.findById(id);

        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();

            employee = Employee.builder()
                    .id(employee.getId())
                    .firstName(employeeDTO.getFirstName())
                    .lastName(employeeDTO.getLastName())
                    .email(employeeDTO.getEmail())
                    .phone(employeeDTO.getPhone())
                    .dateOfBirth(employeeDTO.getDateOfBirth())
                    .gender(employeeDTO.getGender())
                    .isActive(employeeDTO.isActive())
                    .department(Department.builder()
                            .id(employee.getDepartment().getId())
                            .name(employeeDTO.getDepartmentDTO().getName())
                            .city(employeeDTO.getDepartmentDTO().getCity())
                            .build())
                    .build();

            return this.employeeRepository.save(employee);
        } else {
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }
    }

    public Void deleteEmployee(String id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        if (employee.isPresent()) {
            this.employeeRepository.delete(employee.get());
        }
        return null;
    }
}
