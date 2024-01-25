package ru.bratchin.coursework1sb.repository.impl;

import org.springframework.stereotype.Repository;
import ru.bratchin.coursework1sb.model.entity.Employee;
import ru.bratchin.coursework1sb.repository.api.MyRepository;
import ru.bratchin.coursework1sb.specification.MySpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository implements MyRepository<Employee> {

    List<Employee> employees = new ArrayList<>(
            List.of(new Employee("Малышева", "Амалия", "Михайловна", "2", 83166.43),
                    new Employee("Козловский", "Денис", "Дмитриевич", "1", 60250.60),
                    new Employee("Соловьева", "Серафима", "Тимофеевна", "3", 59343.29),
                    new Employee("Макарова", "Дарья", "Тимофеевна", "1", 82042.89),
                    new Employee("Лебедева", "Таисия", "Макаровна", "5", 72881.88),
                    new Employee("Романов", "Артём", "Егорович", "2", 62761.97),
                    new Employee("Широков", "Павел", "Тимофеевич", "4", 97159.11),
                    new Employee("Кудрявцев", "Лев", "Егорович", "2", 89845.70),
                    new Employee("Белякова", "Антонина", "Дмитриевна", "5", 79209.12),
                    new Employee("Филиппова", "Алиса", "Александровна", "1", 87333.51))
    );

    //todo private List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> findAll(MySpecification<Employee> specification) {
        return employees.stream().filter(specification).toList();
    }
    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Optional<Employee> findOne(MySpecification<Employee> specification) {
        return employees.stream().filter(specification).findFirst();
    }

    @Override
    public void create(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void create(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    @Override
    public void update(Employee employee) {
        var oldEmployee = employees.stream()
                .filter(employee1 -> employee.getId().equals(employee1.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Попытка найти несуществующий элемент")); //todo exception
        oldEmployee.setDepartment(employee.getDepartment());
        oldEmployee.setSalary(employee.getSalary());
    }

    @Override
    public void update(List<Employee> employees) {
        for (Employee employee : employees) {
            update(employee);
        }
    }

    @Override
    public void delete(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
        } else {
            throw new RuntimeException("Попытка удалить несуществующий элемент");//todo exception
        }
    }

    @Override
    public void deleteAll() {
        employees = new ArrayList<>();
    }

}
