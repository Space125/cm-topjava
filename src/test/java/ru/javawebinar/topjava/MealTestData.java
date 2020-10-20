package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;

    public static final Meal USER_MEAL1 = new Meal(MEAL_ID, LocalDateTime.of(2020, Month.OCTOBER, 15, 10, 0), "Breakfast", 1000);
    public static final Meal USER_MEAL2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2020, Month.OCTOBER, 15, 12, 0), "Lunch", 1500);
    public static final Meal USER_MEAL3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2020, Month.OCTOBER, 15, 18, 0), "Dinner", 500);
    public static final Meal USER_MEAL4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2020, Month.OCTOBER, 15, 22, 0), "Late Dinner", 1500);
    public static final Meal USER_MEAL5 = new Meal(MEAL_ID + 4, LocalDateTime.of(2020, Month.OCTOBER, 16, 10, 0), "Breakfast", 1000);
    public static final Meal USER_MEAL6 = new Meal(MEAL_ID + 5, LocalDateTime.of(2020, Month.OCTOBER, 16, 12, 0), "Lunch", 500);
    public static final Meal USER_MEAL7 = new Meal(MEAL_ID + 6, LocalDateTime.of(2020, Month.OCTOBER, 16, 18, 0), "Dinner", 500);
    public static final List<Meal> USER_MEALS = Arrays.asList(USER_MEAL7, USER_MEAL6, USER_MEAL5, USER_MEAL4, USER_MEAL3, USER_MEAL2, USER_MEAL1);

    public static final Meal ADMIN_MEAL1 = new Meal(MEAL_ID + 7, LocalDateTime.of(2020, Month.OCTOBER, 15, 12, 0), "Admin Lunch", 2500);
    public static final Meal ADMIN_MEAL2 = new Meal(MEAL_ID + 8, LocalDateTime.of(2020, Month.OCTOBER, 16, 12, 0), "Admin Lunch", 1500);
    public static final List<Meal> ADMIN_MEALS = Arrays.asList(ADMIN_MEAL1, ADMIN_MEAL2);

    public static Meal getNew() {
        return new Meal(LocalDateTime.now(), "newDescription", 9999);
    }

    public static Meal getUpdate() {
        Meal updateMeal = new Meal(USER_MEAL1);
        updateMeal.setDescription("updateDescription");
        updateMeal.setCalories(9999);
        return updateMeal;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
