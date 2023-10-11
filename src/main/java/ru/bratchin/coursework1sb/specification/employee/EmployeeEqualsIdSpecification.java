package ru.bratchin.coursework1sb.specification.employee;

import ru.bratchin.coursework1sb.model.entity.Employee;
import ru.bratchin.coursework1sb.specification.MySpecification;

public class EmployeeEqualsIdSpecification extends MySpecification<Employee> {
    private Integer id;

    public EmployeeEqualsIdSpecification(Integer id) {
        this.id = id;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getId().equals(id);
    }
}
