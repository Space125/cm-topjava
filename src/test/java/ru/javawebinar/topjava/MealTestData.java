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

    public static final Meal userMeal1 = new Meal(MEAL_ID, LocalDateTime.of(2020, Month.OCTOBER, 15, 10, 0), "Breakfast", 1000);
    public static final Meal userMeal2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2020, Month.OCTOBER, 15, 12, 0), "Lunch", 1500);
    public static final Meal userMeal3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2020, Month.OCTOBER, 15, 18, 0), "Dinner", 500);
    public static final Meal userMeal4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2020, Month.OCTOBER, 15, 22, 0), "Late Dinner", 1500);
    public static final Meal userMeal5 = new Meal(MEAL_ID + 4, LocalDateTime.of(2020, Month.OCTOBER, 16, 0, 0), "Food to the limit value", 10);
    public static final Meal userMeal6 = new Meal(MEAL_ID + 5, LocalDateTime.of(2020, Month.OCTOBER, 16, 10, 0), "Breakfast", 1000);
    public static final Meal userMeal7 = new Meal(MEAL_ID + 6, LocalDateTime.of(2020, Month.OCTOBER, 16, 12, 0), "Lunch", 500);
    public static final Meal userMeal8 = new Meal(MEAL_ID + 7, LocalDateTime.of(2020, Month.OCTOBER, 16, 18, 0), "Dinner", 500);
    public static final List<Meal> UserMeals = Arrays.asList(userMeal8, userMeal7, userMeal6, userMeal5, userMeal4, userMeal3, userMeal2, userMeal1);

    public static final Meal adminMeal1 = new Meal(MEAL_ID + 8, LocalDateTime.of(2020, Month.OCTOBER, 15, 12, 0), "Admin Lunch", 2500);
    public static final Meal adminMeal2 = new Meal(MEAL_ID + 9, LocalDateTime.of(2020, Month.OCTOBER, 16, 12, 0), "Admin Lunch", 1500);
    public static final List<Meal> adminMeals = Arrays.asList(adminMeal1, adminMeal2);

    public static Meal getNew() {
        return new Meal(LocalDateTime.now(), "newDescription", 9999);
    }

    public static Meal getUpdate() {
        Meal updateMeal = new Meal(userMeal1);
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
