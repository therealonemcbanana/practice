package com.example.zoo.controller;

import com.example.zoo.dto.EmployeeDTO;
import com.example.zoo.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/zoo/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<EmployeeDTO> employeesDTO = employeeService.getEmployees();
        return ResponseEntity.ok(employeesDTO);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployeeDTO = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployeeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable @Min(0) Integer id,
            @RequestBody @Valid EmployeeDTO employeeDTO
    ) {
        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updatedEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable @Min(0) Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}