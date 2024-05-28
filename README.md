# Recipe Book Application
This project is a Recipe Book application developed using Spring Boot. It provides RESTful endpoints to manage recipes.

## Pre requisites
- \>= Java 8
- \>= MySQL(5.6)
- Create database with name recipe_database

## Setup Guide
1. Login to your mysql terminal and create mysql database with following command : 
```
  create database recipe_database;
```
2. Clone the repo and change the application.properties file under DataBase Connection
  - Change the datasource url to your Database host as : spring.datasource.url=jdbc:mysql://${database.host}:${database.port}/${database.name}
  - Change the \<username\> to your Mysql Username
  - Change the \<password\> to your Mysql Password
  - Example:
```
#Database connection
spring.datasource.url=jdbc:mysql://localhost:3306/recipe_demo
spring.datasource.username=username
spring.datasource.password=password
```

3. Go to root directory that is ~/spring-boot-crud and run the following command
```
  ./mvnw spring-boot:run
```
 If Step 1 and 2 are properly configured, you will see **Tomcat started on port(s): 8080**.
 
4. Now open your localhost and choose basic auth option in Authrization in Postman. Then enter username and password. Use below Username and Password mentioned in application.properties.
  ```
  #spring security credentials
  spring.security.user.name=root
  spring.security.user.password=root
  ```
5. Now use the Postman collection mentioned at bottom to explore the APIs.

## Here's a brief overview of the endpoints:

1. The RecipeController class defines RESTful endpoints for managing recipes. There are 6 Apis as mentioned below: 
- GET /recipe/getRecipe: Retrieves all recipes.
- GET /recipe/getRecipe/{id}: Retrieves a recipe by ID.
- POST /recipe/addRecipe: Adds a new recipe.
- PUT /recipe/updateRecipe/{id}: Updates an existing recipe.
- DELETE /recipe/deleteRecipe/{id}: Deletes a recipe by ID.
- GET /recipe/filter: Finds recipes based on filter criteria such as vegetarian, servings, ingredients, and keywords.

2.JSON request for recipe_book can be added/updated as follow:
```
{
    "name": "Stuffed Garlic Bread",
    "instruction": "1. Roll out  dough. 2. Spread chesse and herbs. 3. Add mozzarella and apply butter and grated garlic. 4. Bake in oven until golden.",
    "isVeg": true,
    "servings": 3,
    "ingredients": [
        {
           "name": "Pizza dough"
        },
        {
            "name": "Garlic"
        },
        {
            "name": "Mozzarella Cheese"
        },
        {
            "name": "Butter"
        },
        {
            "name": "Salt"
        }
    ]
}
```
## Project Architecture
1. MVC pattern is used to seggregate the functionality,and the view part is not being used for now.
2. A dedicated Repository folder is used to change the datasource with minimal changes. 
3. Outline of Core logic folder is as follows : 
  ```
  -- recipebook
    |-- RecipebookApplication.java
    |-- controller
    |   |-- RecipeController.java
    |-- model
    |   |-- Recipe.java
    |   |-- Ingedients.java
    |-- dao
    |   |-- RecipeDao.java
    |-- service
        |-- RecipeService.java
        |-- RecipeServiceImpl.java
  ```
