package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * @author Ivan Kurilov on 01.10.2020
 */
public interface ObjectDao<T> {
    Collection<T> getAll();
    void save(Meal meal);
    void update(Meal meal);
    void delete(Long id);
    Meal getById(Long id);
}
