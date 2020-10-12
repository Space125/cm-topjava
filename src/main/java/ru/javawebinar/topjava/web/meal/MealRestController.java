package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.getTos;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        final int userId = authUserId();
        log.info("get all meals for user id {}", userId);
        return getTos(service.getAll(userId), authUserCaloriesPerDay());
    }

    public Meal get(int id) {
        final int userId = authUserId();
        log.info("get meal {} for user {}", id, userId);
        return service.get(userId, id);
    }

    public void delete(int id) {
        final int userId = authUserId();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(userId, id);
    }

    public Meal create(Meal meal) {
        final int userId = authUserId();
        checkNew(meal);
        log.info("create meal for user {}", userId);
        return service.create(userId, meal);
    }

    public void update(int id, Meal meal) {
        final int userId = authUserId();
        assureIdConsistent(meal, id);
        log.info("update meal {} for user {}", id, userId);
        service.update(userId, meal);
    }

    public List<MealTo> getAllFilterBetweenDateAndTime(LocalDate startDate, LocalDate endDate,
                                                       LocalTime startTime, LocalTime endTime) {
        final int userId = authUserId();
        log.info("filter between date ({} - {}) time ({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

        List<Meal> filterMealsBetweenDate = service.getBetweenDateInclusive(userId, startDate, endDate);
        return MealsUtil.getFilteredTos(filterMealsBetweenDate, authUserCaloriesPerDay(),
                startTime == null ? LocalTime.MIN : startTime,
                endTime == null ? LocalTime.MAX : endTime);
    }
}