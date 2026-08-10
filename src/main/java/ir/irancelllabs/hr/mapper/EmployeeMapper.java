package ir.irancelllabs.hr.mapper;

import ir.irancelllabs.hr.dtos.CreateEmployeeRequest;
import ir.irancelllabs.hr.dtos.EmployeeResponse;
import ir.irancelllabs.hr.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(CreateEmployeeRequest request);
    EmployeeResponse toResponse(Employee employee);
}