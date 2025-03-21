package com.app.controllers;

import com.app.dto.EmployeeDTO;
import com.app.entities.Employee;
import com.app.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<Employee> employee = this.employeeService.getEmployeeByIdOptional(id);
        if(employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(this.employeeService.saveEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/save/{id}")
    public ResponseEntity<Employee> saveEmployee(@PathVariable String id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(this.employeeService.updateEmployee(id,employeeDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        return new ResponseEntity<>(this.employeeService.deleteEmployee(id), HttpStatus.OK);
    }
}
