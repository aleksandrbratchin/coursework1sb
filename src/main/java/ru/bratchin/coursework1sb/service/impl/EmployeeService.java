package ru.bratchin.coursework1sb.service.impl;

import org.springframework.stereotype.Service;
import ru.bratchin.coursework1sb.model.entity.Employee;
import ru.bratchin.coursework1sb.repository.api.MyRepository;
import ru.bratchin.coursework1sb.service.api.MyService;
import ru.bratchin.coursework1sb.specification.employee.EmployeeEqualsDepartmentSpecification;
import ru.bratchin.coursework1sb.specification.employee.EmployeeEqualsIdSpecification;
import ru.bratchin.coursework1sb.specification.employee.EmployeeSalaryLessThanSpecification;
import ru.bratchin.coursework1sb.specification.employee.EmployeeSalaryMoreThanSpecification;

import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeService implements MyService<Employee, Integer> {

    private final MyRepository<Employee> repository;

    public EmployeeService(MyRepository<Employee> repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public void add(Employee employee) {
        repository.create(employee);
    }

    @Override
    public void update(Employee employee) {
        repository.update(employee);
    }

    @Override
    public void delete(Employee employee) {
        repository.delete(employee);
    }

    @Override
    public void deleteById(Integer id) {
        repository.delete(
                repository.findOne(new EmployeeEqualsIdSpecification(id))
                        .orElseThrow(() -> new RuntimeException("Пользователь уже удален!"))
        );//todo
    }

    @Override
    public List<Employee> findByDepartment(String department) {
        return repository.findAll(new EmployeeEqualsDepartmentSpecification(department));
    }

    /**
     * Траты за месяц
     */
    @Override
    public Double salarySum() {
        return salarySum(repository.findAll());
    }

    @Override
    public Double salarySum(List<Employee> employees) {
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }

    /**
     * Сотрудник с максимальной зарплатой
     */
    @Override
    public Employee maxSalary() {
        return maxSalary(repository.findAll());
    }

    @Override
    public Employee maxSalary(List<Employee> employees) {
        return employees.stream().max(
                Comparator.comparingDouble(Employee::getSalary)
        ).orElseThrow(() -> new RuntimeException("Ошибка при поиске максимального числа"));
    }

    /**
     * Сотрудник с максимальной зарплатой по номеру отдела
     */
    @Override
    public Employee maxSalaryByDepartment(String department) {
        return maxSalary(repository.findAll(new EmployeeEqualsDepartmentSpecification(department)));
    }

    /**
     * Сотрудник с минимальной зарплатой
     */
    @Override
    public Employee minSalary() {
        return minSalary(repository.findAll());
    }

    @Override
    public Employee minSalary(List<Employee> employees) {
        return employees.stream().min(
                Comparator.comparingDouble(Employee::getSalary)
        ).orElseThrow(() -> new RuntimeException("Ошибка при поиске минимального числа"));
    }

    /**
     * Сотрудник с минимальной зарплатой по номеру отдела
     */
    @Override
    public Employee minSalaryByDepartment(String department) {
        return minSalary(repository.findAll(new EmployeeEqualsDepartmentSpecification(department)));
    }

    /**
     * Среднее значение зарплат
     */
    @Override
    public Double averageSalary() {
        return averageSalary(repository.findAll());
    }

    @Override
    public Double averageSalary(List<Employee> employees) {
        if (employees == null || employees.size() == 0)
            throw new RuntimeException("Ошибка при нахождении средней суммы зарплат");//todo
        return salarySum(employees) / employees.size();
    }

    @Override
    public Double averageSalaryByDepartment(String department) {
        return averageSalary(repository.findAll(new EmployeeEqualsDepartmentSpecification(department)));
    }


    @Override
    public List<Employee> filterBySalaryLessThan(Double checkSalary) {
        return repository.findAll(new EmployeeSalaryLessThanSpecification(checkSalary));
    }

    @Override
    public List<Employee> filterBySalaryMoreThan(Double checkSalary) {
        return repository.findAll(new EmployeeSalaryMoreThanSpecification(checkSalary));
    }

    @Override
    public void salaryIndexing(Double percent) {
        repository.update(
                repository.findAll().stream().peek(
                        employee ->
                                employee.setSalary(
                                        getSalaryIndexing(employee.getSalary(), percent)
                                )
                ).toList()
        );
    }

    @Override
    public void salaryIndexingByDepartment(String department, Double percent) {
        repository.update(
                repository.findAll(new EmployeeEqualsDepartmentSpecification(department)).stream().peek(
                        employee ->
                                employee.setSalary(
                                        getSalaryIndexing(employee.getSalary(), percent)
                                )
                ).toList()
        );
    }

    private Double getSalaryIndexing(Double salary, Double percent){
        return salary + (salary / 100 * percent);
    }

    @Override
    public Double salarySumByDepartment(String department) {
        return salarySum(repository.findAll(new EmployeeEqualsDepartmentSpecification(department)));
    }


}
