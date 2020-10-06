package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Ivan Kurilov on 01.10.2020
 */
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final String MEALS_LIST = "/meals.jsp";
    private MealDao mealDao;

    @Override
    public void init() throws ServletException {
        mealDao = new MealDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        final String CREATE_OR_EDIT = "/editMeal.jsp";

        switch (action == null ? "all" : action) {
            case "delete":
                long id = getIdFromResp(req);
                log.info("Delete {}", id);
                mealDao.delete(id);
                resp.sendRedirect("meals");
                break;
            case "create":
            case "update":
                Meal meal = "create".equals(action) ? null :
                        mealDao.getById(getIdFromResp(req));
                req.setAttribute("meal", meal);
                req.getRequestDispatcher(CREATE_OR_EDIT).forward(req, resp);
                break;
            case "all":
            default:
                log.info("Get all Meals");
                req.setAttribute("listMeals", MealsUtil.filteredByPredicate(mealDao.getAll()));
                req.getRequestDispatcher(MEALS_LIST).forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Long.valueOf(id),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));
        log.info(meal.getId() == null ? "Create Meal {}" : "Update Meal {}", id);
        mealDao.create(meal);
        resp.sendRedirect("meals");
    }

    private long getIdFromResp(HttpServletRequest req) {
        return Long.parseLong(Objects.requireNonNull(req.getParameter("id")));
    }
}
