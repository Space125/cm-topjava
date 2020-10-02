package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo extends Meal{

    private boolean excess;

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess) {
        super(dateTime, description, calories);
        this.excess = excess;
    }

    public MealTo(Long id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this(dateTime, description, calories, excess);
        this.setId(id);

    }


    public boolean isExcess() {
        return excess;
    }

    public void setExcess(boolean excess) {
        this.excess = excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }


}
