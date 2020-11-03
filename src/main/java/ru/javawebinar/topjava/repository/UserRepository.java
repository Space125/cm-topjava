package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    default User getWithMeals(int userId){
        throw new UnsupportedOperationException("Implemented only for DataJpa");
    }
}