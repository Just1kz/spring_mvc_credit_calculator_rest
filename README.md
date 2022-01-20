# Annuity Credit Calculator
Technologies:
- REST API
- Spring MVC
- DB - H2 in memory and Spring JDBC
- Connection pool / Transaction control with commons-dbcp2
- GlobalExceptionHandler
- Locale server - Apache Tomcat
- Logging with log4j
- Postman

This application demonstrates a CRUD operations with Credit in DataBase with Spring JDBC and Calculation of the loan schedule

Interface in my Application 

- getAll "/credits" - HTTP.GET
![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/credits.png)

- getId "/credits/{id}" - HTTP.GET
![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/creditsId.png)

- getId "/credits/{id}" - HTTP.GET, exception handler after try get with id="abc"
  ![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/exceptionHandler.png)

- add new "/credits" - HTTP.POST, payload in body request with JSON format
![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/add.png)

- editId "/credits" - HTTP.PUT, payload in body request with JSON format and id for update
![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/edit.png)

- Result after add new and edit credit with id=3 -> - /credits - HTTP.GET
![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/rslAddAndEdit.png)

- Calculate schedule payments "/credits/calculate/{id}" - HTTP.GET and get result /credits/{id} - HTTP.GET
![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/calculate.png)
![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/calculateResult.png)

- Show payments without credit body "/credits/payments/{id}" - HTTP.GET
- ![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/payments.png)

- Delete Credit with schedule Payments "/credits/{id}" - HTTP.DELETE. Delete credit with id = 1 and getAll
![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/delete.png)
![ScreenShot](./src/main/java/com/antonbelykh/spring/spring_mvc/rest/images/deleteResult.png)