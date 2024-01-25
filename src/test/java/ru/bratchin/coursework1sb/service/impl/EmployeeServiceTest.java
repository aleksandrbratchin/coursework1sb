package ru.bratchin.coursework1sb.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bratchin.coursework1sb.model.entity.Employee;
import ru.bratchin.coursework1sb.repository.api.MyRepository;
import ru.bratchin.coursework1sb.repository.impl.EmployeeRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class EmployeeServiceTest {

    private EmployeeService service;

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
        service = new EmployeeService(repository);
    }

    @Test
    void salarySum() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);

        Double sum = service.salarySum();

        assertThat(sum).isEqualTo(773994.5, withPrecision(2d));
    }

    @Test
    void testSalarySum() {
    }

    @Test
    void maxSalary() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);

        Employee maxSalary = service.maxSalary();

        assertThat(maxSalary.getSalary()).isEqualTo(97159.11, withPrecision(2d));
        assertThat(maxSalary.getSurname()).isEqualTo("Широков");
    }

    @Test
    void testMaxSalary() {
    }

    @Test
    void maxSalaryByDepartment() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);

        Employee maxSalary = service.maxSalaryByDepartment("1");

        assertThat(maxSalary.getSalary()).isEqualTo(87333.51, withPrecision(2d));
        assertThat(maxSalary.getSurname()).isEqualTo("Филиппова");
    }

    @Test
    void minSalary() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);

        Employee maxSalary = service.minSalary();

        assertThat(maxSalary.getSalary()).isEqualTo(59343.29, withPrecision(2d));
        assertThat(maxSalary.getSurname()).isEqualTo("Соловьева");
    }

    @Test
    void testMinSalary() {
    }

    @Test
    void minSalaryByDepartment() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);

        Employee maxSalary = service.minSalaryByDepartment("1");

        assertThat(maxSalary.getSalary()).isEqualTo(60250.60);
        assertThat(maxSalary.getSurname()).isEqualTo("Козловский");
    }

    @Test
    void averageSalary() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);

        Double sum = service.averageSalary();

        assertThat(sum).isEqualTo(77399.45, withPrecision(2d));
    }

    @Test
    void testAverageSalary() {
    }

    @Test
    void averageSalaryByDepartment() throws IllegalAccessException {
        fieldEmployees.set(repository, testEmployees);

        Double sum = service.averageSalaryByDepartment("1");

        assertThat(sum).isEqualTo(76542.33, withPrecision(2d));
    }


}