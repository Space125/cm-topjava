package ru.javawebinar.topjava.dao;

import java.util.List;

/**
 * @author Ivan Kurilov on 01.10.2020
 */
public interface ObjectDao<T> {
    List<T> getAll();
}
