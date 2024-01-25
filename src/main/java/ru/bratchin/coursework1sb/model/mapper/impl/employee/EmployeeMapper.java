package ru.bratchin.coursework1sb.model.mapper.impl.employee;

import org.springframework.stereotype.Component;
import ru.bratchin.coursework1sb.model.dto.EmployeeDto;
import ru.bratchin.coursework1sb.model.entity.Employee;
import ru.bratchin.coursework1sb.model.mapper.api.MyMapper;

@Component
public class EmployeeMapper implements MyMapper<Employee, EmployeeDto> {

    @Override
    public Employee fromDto(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .surname(employeeDto.getSurname())
                .patronymic(employeeDto.getPatronymic())
                .department(employeeDto.getDepartment())
                .salary(employeeDto.getSalary())
                .build();
    }

    @Override
    public EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .patronymic(employee.getPatronymic())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .build();
    }
}
