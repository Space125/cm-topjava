# Curl commands

## [Test a REST API with curl](https://www.baeldung.com/curl-rest)
## [How to test a REST api from command line with curl](https://www.codepedia.org/ama/how-to-test-a-rest-api-from-command-line-with-curl/)

Default logged user is user with no admin privileges.

### Get meal
```
curl -s http://localhost:8080/topjava/rest/profile/meals/100008

Produces:
{"id":100008,"dateTime":"2020-01-31T20:00:00","description":"Ужин","calories":510,"user":null}
```

### Delete meal
```
curl -i -X DELETE http://localhost:8080/topjava/rest/profile/meals/100008

Status:
HTTP/1.1 204
```
### Get all meals 
```
curl -s http://localhost:8080/topjava/rest/profile/meals

Produces:
[{"id":100008,"dateTime":"2020-01-31T20:00:00","description":"Ужин","calories":510,"excess":true},
{"id":100007,"dateTime":"2020-01-31T13:00:00","description":"Обед","calories":1000,"excess":true},
{"id":100006,"dateTime":"2020-01-31T10:00:00","description":"Завтрак","calories":500,"excess":true},
{"id":100005,"dateTime":"2020-01-31T00:00:00","description":"Еда на граничное значение","calories":100,"excess":true},
{"id":100004,"dateTime":"2020-01-30T20:00:00","description":"Ужин","calories":500,"excess":false},
{"id":100003,"dateTime":"2020-01-30T13:00:00","description":"Обед","calories":1000,"excess":false},
{"id":100002,"dateTime":"2020-01-30T10:00:00","description":"Завтрак","calories":500,"excess":false}]
```

### Create new meal
```
curl -i -X POST http://localhost:8080/topjava/rest/profile/meals \
-d '{"dateTime":"2020-11-25T16:00:00", "description":"New Meal", "calories":1500}' \
-H 'Content-Type: application/json;charset=UTF-8'

Status:
HTTP/1.1 201
Location:
http://localhost:8080/topjava/rest/profile/meals/100011
Produces:
{"id":100011,"dateTime":"2020-11-25T16:00:00","description":"New Meal","calories":1500,"user":null}
```

### Update Meal
```
curl -i -X PUT http://localhost:8080/topjava/rest/profile/meals/100004 \
-d '{"id":100004, "dateTime":"2020-01-30T20:00:00", "description":"Update Meal", "calories":1500}' \
-H 'Content-Type: application/json;charset=UTF-8'

Status:
HTTP/1.1 204
```

### Meal filter for the period
```
curl -s http://localhost:8080/topjava/rest/profile/meals/filter?startDate=2020-01-31&startTime=06:00&endDate=2020-01-31&endTime=13:00

Produces:
[{"id":100006,"dateTime":"2020-01-31T10:00:00","description":"Завтрак","calories":500,"excess":true}]
```

### Meal filter all
```
curl -s http://localhost:8080/topjava/rest/profile/meals/filter?startDate=&endTime=

Produces:
[{"id":100008,"dateTime":"2020-01-31T20:00:00","description":"Ужин","calories":510,"excess":true},
{"id":100007,"dateTime":"2020-01-31T13:00:00","description":"Обед","calories":1000,"excess":true},
{"id":100006,"dateTime":"2020-01-31T10:00:00","description":"Завтрак","calories":500,"excess":true},
{"id":100005,"dateTime":"2020-01-31T00:00:00","description":"Еда на граничное значение","calories":100,"excess":true},
{"id":100004,"dateTime":"2020-01-30T20:00:00","description":"Ужин","calories":500,"excess":false},
{"id":100003,"dateTime":"2020-01-30T13:00:00","description":"Обед","calories":1000,"excess":false},
{"id":100002,"dateTime":"2020-01-30T10:00:00","description":"Завтрак","calories":500,"excess":false}]
```