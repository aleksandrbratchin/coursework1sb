package ru.bratchin.coursework1sb.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import ru.bratchin.coursework1sb.model.serializer.MyDoubleSerializer;

@Getter
public class EmployeeDto {

    private final Integer id;
    private final String surname;
    private final String name;
    private final String patronymic;
    private final String department;
    @JsonSerialize(using = MyDoubleSerializer.class)
    private final Double salary;

    @Builder
    public EmployeeDto(Integer id, String surname, String name, String patronymic, String department, Double salary) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.department = department;
        this.salary = salary;
    }

    public String getFullName() {
        return surname + " " + name + " " + patronymic;
    }

}
