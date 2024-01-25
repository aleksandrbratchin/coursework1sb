package ru.bratchin.coursework1sb.model.mapper.api;

public interface MyMapper <T, D> {
    T fromDto(D d);
    D toDto(T t);
}
