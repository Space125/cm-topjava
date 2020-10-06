package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * @author Ivan Kurilov on 01.10.2020
 */
public interface MealDao {
    Collection<Meal> getAll();
    Meal create(Meal meal);
    boolean delete(Long id);
    Meal getById(Long id);
}
