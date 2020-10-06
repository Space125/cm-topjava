package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ivan Kurilov on 01.10.2020
 */
public class MealDaoImpl implements MealDao {
    private final AtomicLong counterId = new AtomicLong();
    private final Map<Long, Meal> dbMeals = new ConcurrentHashMap<>();

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Collection<Meal> getAll() {
        return dbMeals.values();
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(counterId.incrementAndGet());
            dbMeals.put(meal.getId(), meal);
            return meal;
        }
        return dbMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(Long id) {
        return dbMeals.remove(id) != null;
    }

    @Override
    public Meal getById(Long id) {
        return dbMeals.get(id);
    }
}
