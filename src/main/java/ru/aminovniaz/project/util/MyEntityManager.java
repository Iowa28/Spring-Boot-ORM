package ru.aminovniaz.project.util;

import org.hibernate.Criteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface MyEntityManager {

    <T> List<T> find(Class<T> clazz);

    <T, ID> T findById(Class<T> clazz, ID id);

    <T> void save(T objectToSave);

    <T> List<T> find(Class<T> clazz, List<String> conditions, String operator);
}
