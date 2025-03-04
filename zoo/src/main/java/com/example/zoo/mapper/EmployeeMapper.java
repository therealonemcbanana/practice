package com.example.zoo.mapper;

import com.example.zoo.dto.EmployeeDTO;
import com.example.zoo.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toDTO(Employee employee);
    Employee toEntity(EmployeeDTO employeeDTO);
}
