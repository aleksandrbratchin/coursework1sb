package ru.bratchin.coursework1sb.model.entity;

import lombok.Builder;

import java.util.Objects;

public class Employee {

    private static int index = 1;
    private final Integer id;
    private final String surname;
    private final String name;
    private final String patronymic;
    private String department;
    private Double salary;


    public Employee(String surname, String name, String patronymic, String department, Double salary) {
        this.id = index++;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.department = department;
        this.salary = salary;
    }
    @Builder
    public Employee(Integer id, String surname, String name, String patronymic, String department, Double salary) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.department = department;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getDepartment() {
        return department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Сотрудник №" + id + " {\n" +
                "\tФамилия = '" + surname + "',\n" +
                "\tИмя = '" + name + "',\n" +
                "\tОтчество = '" + patronymic + "',\n" +
                "\tОтдел = '" + department + "',\n" +
                "\tЗарплата = " + salary + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(surname, employee.surname) && Objects.equals(name, employee.name) && Objects.equals(patronymic, employee.patronymic) && Objects.equals(department, employee.department) && Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, department, salary);
    }
}
