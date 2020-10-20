DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2020-10-15 10:00', 'Breakfast', 1000),
       (100000, '2020-10-15 12:00', 'Lunch', 1500),
       (100000, '2020-10-15 18:00', 'Dinner', 500),
       (100000, '2020-10-15 22:00', 'Late Dinner', 1500),
       (100000, '2020-10-16 10:00', 'Breakfast', 1000),
       (100000, '2020-10-16 12:00', 'Lunch', 500),
       (100000, '2020-10-16 18:00', 'Dinner', 500),
       (100001, '2020-10-15 12:00', 'Admin Lunch', 2500),
       (100001, '2020-10-16 12:00', 'Admin Lunch', 1500);

-- INSERT INTO meals (user_id, date_time, description, calories) VALUES
-- (100000, '2020-01-30 10:00', 'Завтрак', 500),
-- (100000, '2020-01-30 13:00', 'Обед', 1000),
-- (100000, '2020-01-30 20:00', 'Ужин', 500),
-- (100000, '2020-01-31 00:00', 'Еда на граничное значение', 100),
-- (100000, '2020-01-31 10:00', 'Завтрак', 1000),
-- (100000, '2020-01-31 13:00', 'Обед', 500),
-- (100000, '2020-01-31 20:00', 'Ужин', 410),
-- (100001, '2015-06-01 14:00', 'Админ ланч', 510),
-- (100001, '2020-01-31 20:00', 'Админ ужин', 1500);