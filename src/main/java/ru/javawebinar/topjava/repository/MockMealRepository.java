package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ivan Kurilov on 01.10.2020
 */
public class MockMealRepository {

    private static volatile AtomicLong counterId = new AtomicLong();
    private final Map<Long, Meal> dbMeals = new ConcurrentHashMap();

    {
        Arrays.asList(
                new Meal(0L, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(1L, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(2L, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(3L, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(4L, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(5L, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(6L, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        ).forEach(this::save);
    }

    public Collection<Meal> getMeals() {
        return Collections.unmodifiableCollection(dbMeals.values());
    }

    public void save(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(counterId.getAndIncrement());
        }
        dbMeals.put(meal.getId(), meal);
    }

    public void update(Meal meal) {
        if (dbMeals.containsKey(meal.getId())) {
            final Meal mealUpdate = getById(meal.getId());
            mealUpdate.setDateTime(meal.getDateTime());
            mealUpdate.setDescription(meal.getDescription());
            mealUpdate.setCalories(meal.getCalories());
        }
    }

    public void delete(Long id) {
        dbMeals.remove(id);
    }

    public Meal getById(Long id){
        return dbMeals.get(id);
    }


}
