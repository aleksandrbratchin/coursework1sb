package ru.bratchin.coursework1sb.repository.api;

import ru.bratchin.coursework1sb.specification.MySpecification;

import java.util.List;
import java.util.Optional;

public interface MyRepository<T> {

    List<T> findAll(MySpecification<T> specification);
    List<T> findAll();
    Optional<T> findOne(MySpecification<T> specification);
    void create(List<T> t);
    void create(T t);
    void update(T t);
    void update(List<T> t);
    void delete(T t);
    void deleteAll();

}
