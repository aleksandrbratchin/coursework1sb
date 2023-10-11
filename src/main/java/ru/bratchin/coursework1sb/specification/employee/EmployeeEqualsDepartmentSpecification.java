package ru.bratchin.coursework1sb.specification.employee;

import ru.bratchin.coursework1sb.model.entity.Employee;
import ru.bratchin.coursework1sb.specification.MySpecification;

public class EmployeeEqualsDepartmentSpecification extends MySpecification<Employee> {
    private String department;

    public EmployeeEqualsDepartmentSpecification(String department) {
        this.department = department;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getDepartment().equals(department);
    }
}
