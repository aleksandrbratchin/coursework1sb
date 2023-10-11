package ru.bratchin.coursework1sb.service.api;

import java.util.List;

public interface MyService<T, I> {

    List<T> findAll();
    void add(T t);
    void update(T t);
    void delete(T t);
    void deleteById(I i);

    List<T> findByDepartment(String department);
    List<T> filterBySalaryLessThan(Double salary);
    List<T> filterBySalaryMoreThan(Double salary);

    Double salarySumByDepartment(String department);

    Double salarySum();
    Double salarySum(List<T> t);
    T maxSalary();
    T maxSalary(List<T> t);
    T maxSalaryByDepartment(String department);
    T minSalary();
    T minSalary(List<T> t);
    T minSalaryByDepartment(String department);
    Double averageSalary();
    Double averageSalary(List<T> t);
    Double averageSalaryByDepartment(String department);

    void salaryIndexing(Double percent);
    void salaryIndexingByDepartment(String department, Double percent);
}
