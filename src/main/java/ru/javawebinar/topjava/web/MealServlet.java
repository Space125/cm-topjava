package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MockMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Ivan Kurilov on 01.10.2020
 */
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static String CREATE_OR_EDIT = "/editMeal.jsp";
    private static String MEALS_LIST = "/meals.jsp";
    private MealDao mealDao;

    @Override
    public void init() throws ServletException {
        mealDao = new MealDaoImpl(new MockMealRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.debug("redirect to meals");
        req.setAttribute("listMeals", MealsUtil.filteredByStreams(mealDao.getAll()));
        req.getRequestDispatcher(MEALS_LIST).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Long.valueOf(id),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));

        log.info(meal.getId() == null ? "Add Meal {}" : "Edit Meal {}", meal);
        mealDao.save(meal);
        resp.sendRedirect("meals");
    }
}
