package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MockMealRepository;

import java.util.List;

/**
 * @author Ivan Kurilov on 01.10.2020
 */
public class MealDaoImpl implements MealDao{

    private final MockMealRepository repository;

    public MealDaoImpl(MockMealRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Meal> getAll() {
        return repository.getMeals();
    }


}
