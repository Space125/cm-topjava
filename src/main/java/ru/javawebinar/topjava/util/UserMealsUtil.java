package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));

        System.out.println("filteredByCyclesOptional2:");
        System.out.println(filteredByCyclesOptional2(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));

        System.out.println("filteredByStreamsOptional2:");
        System.out.println(filteredByStreamsOptional2(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));


    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        final Map<LocalDate, Integer> consumedCaloriesPerDay = new HashMap<>();
        meals.forEach(meal -> consumedCaloriesPerDay.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum));

        final List<UserMealWithExcess> result = new ArrayList<>();
        meals.forEach(meal -> {
            if (TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime)) {
                result.add(createUserMealWithExcess(meal, consumedCaloriesPerDay.get(meal.getDate()) > caloriesPerDay));
            }
        });

        return result;
    }


    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final Map<LocalDate, Integer> consumedCaloriesPerDay = meals.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createUserMealWithExcess(meal, consumedCaloriesPerDay.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static UserMealWithExcess createUserMealWithExcess(UserMeal meal, boolean excess) {
        return new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    public static List<UserMealWithExcess> filteredByCyclesOptional2(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> consumedCaloriesPerDay = new HashMap<>();
        List<Callable<Void>> tasks = new ArrayList<>();
        final List<UserMealWithExcess> res = new CopyOnWriteArrayList<>();

        meals.forEach(userMeal -> {
            consumedCaloriesPerDay.merge(userMeal.getDate(), userMeal.getCalories(), Integer::sum);
            if (TimeUtil.isBetweenHalfOpen(userMeal.getTime(), startTime, endTime)) {
                tasks.add(() -> {
                    res.add(createUserMealWithExcess(userMeal, consumedCaloriesPerDay.get(userMeal.getDate()) > caloriesPerDay));
                    return null;
                });
            }
        });
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.invokeAll(tasks);
            executorService.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return res;

    }

    public static List<UserMealWithExcess> filteredByStreamsOptional2(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        return meals.stream().collect(
                //Create new Collector
                new Collector<UserMeal, Map<LocalDate, List<UserMeal>>, List<UserMealWithExcess>>() {

                    private final Map<LocalDate, Integer> consumedCaloriesPerDay = new HashMap<>();

                    @Override
                    public Supplier<Map<LocalDate, List<UserMeal>>> supplier() {
                        return HashMap::new;
                    }

                    @Override
                    public BiConsumer<Map<LocalDate, List<UserMeal>>, UserMeal> accumulator() {
                        return ((localDateListMap, userMeal) -> {
                            consumedCaloriesPerDay.merge(userMeal.getDate(), userMeal.getCalories(), Integer::sum);
                            localDateListMap.computeIfAbsent(userMeal.getDate(), k -> new ArrayList<>()).add(userMeal);
                        });
                    }

                    @Override
                    public BinaryOperator<Map<LocalDate, List<UserMeal>>> combiner() {
                        return (first, second) -> Stream.concat(first.entrySet().stream(), second.entrySet().stream())
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                    }

                    @Override
                    public Function<Map<LocalDate, List<UserMeal>>, List<UserMealWithExcess>> finisher() {
                        return localDateListMap -> localDateListMap.values().stream()
                                .flatMap(List::stream)
                                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                                .map(userMeal -> createUserMealWithExcess(userMeal, consumedCaloriesPerDay.get(userMeal.getDate()) > caloriesPerDay))
                                .collect(Collectors.toList());
                    }

                    @Override
                    public Set<Characteristics> characteristics() {
                        return Collections.emptySet();
                    }
                }
        );
    }
}
