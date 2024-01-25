package ru.bratchin.coursework1sb.specification.employee;

import ru.bratchin.coursework1sb.model.entity.Employee;
import ru.bratchin.coursework1sb.specification.MySpecification;

public class EmployeeSalaryMoreThanSpecification extends MySpecification<Employee> {
    private Double salary;

    public EmployeeSalaryMoreThanSpecification(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > salary;
    }
}
