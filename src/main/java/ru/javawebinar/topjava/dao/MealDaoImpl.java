package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MockMealRepository;

import java.util.Collection;

/**
 * @author Ivan Kurilov on 01.10.2020
 */
public class MealDaoImpl implements MealDao{

    private final MockMealRepository repository;

    public MealDaoImpl(MockMealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.getMeals();
    }

    @Override
    public void save(Meal meal) {
        repository.save(meal);
    }

    @Override
    public void update(Meal meal) {
        repository.update(meal);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Meal getById(Long id) {
        return repository.getById(id);
    }


}
