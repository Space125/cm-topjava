package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController{

    @GetMapping("/delete")
    public String delete(HttpServletRequest request){
        delete(getId(request));
        return "redirect:/meals";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request){
        request.setAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                "", 1000));
        return "mealForm";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request){
        request.setAttribute("meal", get(getId(request)));
        return "mealForm";
    }

    @PostMapping
    public String save(HttpServletRequest request){
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = new Meal(dateTime, description, calories);

        if(!StringUtils.hasText(request.getParameter("id"))){
            create(meal);
        } else {
            super.update(meal, getId(request));
        }
        return "redirect:/meals";
    }

    @GetMapping("/filter")
    public String getFilter(HttpServletRequest request, Model model){
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals", getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

    @GetMapping
    public String getMeals(Model model){
        model.addAttribute("meals", getAll());
        return "meals";
    }

    private int getId(HttpServletRequest request){
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
