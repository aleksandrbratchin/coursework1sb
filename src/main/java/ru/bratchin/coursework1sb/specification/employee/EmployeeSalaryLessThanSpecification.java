package ru.bratchin.coursework1sb.specification.employee;

import ru.bratchin.coursework1sb.model.entity.Employee;
import ru.bratchin.coursework1sb.specification.MySpecification;

public class EmployeeSalaryLessThanSpecification extends MySpecification<Employee> {
    private Double salary;

    public EmployeeSalaryLessThanSpecification(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() < salary;
    }
}
