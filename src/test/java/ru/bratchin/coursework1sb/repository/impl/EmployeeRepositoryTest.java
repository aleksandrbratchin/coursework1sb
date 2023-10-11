package ru.bratchin.coursework1sb.repository.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bratchin.coursework1sb.model.entity.Employee;
import ru.bratchin.coursework1sb.repository.api.MyRepository;
import ru.bratchin.coursework1sb.specification.employee.EmployeeEqualsDepartmentSpecification;
import ru.bratchin.coursework1sb.specification.employee.EmployeeEqualsIdSpecification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeRepositoryTest {

    private MyRepository<Employee> repository;

    private static Field fieldEmployees;

    private List<Employee> testEmployees;

    @BeforeAll
    public static void setup() throws NoSuchFieldException {
        fieldEmployees = EmployeeRepository.class.getDeclaredField("employees");
        fieldEmployees.setAccessible(true);
    }

    @BeforeEach
    public void initEach() throws NoSuchFieldException, IllegalAccessException {
        repository = new EmployeeRepository();
        Field fieldId = Employee.class.getDeclaredField("index");
        fieldId.setAccessible(true);
        fieldId.set(null, 1);
        testEmployees = new ArrayList<>(
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
    }

    @Test
    void create() throws IllegalAccessException {

        repository.create(new Employee("Малышева", "Амалия", "Михайловна", "2", 83166.43));
        repository.create(new Employee("Козловский", "Денис", "Дмитриевич", "1", 60250.60));
        List<Employee> employees = (List<Employee>) fieldEmployees.get(repository);

        assertThat(employees.size()).isEqualTo(2);
    }

    @Test
    void findAll() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);

        List<Employee> employees = repository.findAll();

        assertThat(employees.size()).isEqualTo(10);
    }

    @Test
    void findAllByDepartment() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);

        List<Employee> employees = repository.findAll(new EmployeeEqualsDepartmentSpecification("5"));

        assertThat(employees.size()).isEqualTo(2);
    }

    @Test
    void findOneById() throws IllegalAccessException {
        int id = 2;
        fieldEmployees.set(repository, testEmployees);

        Employee employees = repository.findOne(new EmployeeEqualsIdSpecification(id)).get();

        assertThat(employees.getPatronymic()).isEqualTo(testEmployees.get(id - 1).getPatronymic());
    }

    @Test
    void update() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);
        List<Employee> employees = (List<Employee>) fieldEmployees.get(repository);
        Employee employee = employees.get(3);
        employee.setSalary(100000D);
        employee.setDepartment("10");

        repository.update(employee);

        employees = (List<Employee>) fieldEmployees.get(repository);
        assertThat(employees).contains(employee);
    }

    @Test
    void updateList() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);
        List<Employee> employees = (List<Employee>) fieldEmployees.get(repository);
        List<Employee> employeesUpd = employees.subList(2, 5);
        for (Employee employee : employeesUpd) {
            employee.setSalary(100000D);
            employee.setDepartment("10");
        }

        repository.update(employeesUpd);

        employees = (List<Employee>) fieldEmployees.get(repository);
        assertThat(employees).containsAll(employeesUpd);
    }

    @Test
    void delete() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);
        List<Employee> employees = (List<Employee>) fieldEmployees.get(repository);
        Employee employee = employees.get(3);

        repository.delete(employee);
        employees = (List<Employee>) fieldEmployees.get(repository);

        assertThat(employees.size()).isEqualTo(9);
        assertThat(employees).doesNotContain(employee);
    }

    @Test
    void deleteAll() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);

        repository.deleteAll();
        List<Employee> employees = (List<Employee>) fieldEmployees.get(repository);

        assertThat(employees.size()).isEqualTo(0);
    }
}