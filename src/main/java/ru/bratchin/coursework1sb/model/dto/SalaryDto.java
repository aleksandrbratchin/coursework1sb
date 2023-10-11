package ru.bratchin.coursework1sb.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import ru.bratchin.coursework1sb.model.serializer.MyDoubleSerializer;

@Getter
public class SalaryDto {

    @JsonSerialize(using = MyDoubleSerializer.class)
    private final Double salary;


    public SalaryDto(Double salary) {
        this.salary = salary;
    }
}
