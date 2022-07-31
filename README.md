# Fix Your Habits

![screenshot page](readme%20assets/home.jpg)

## Table of contents
* [About the Project](#about-the-project)
* [Getting Started](#getting-started)
    + [Prerequisites](#prerequisites)
    + [Installation](#installation)
* [Data for login](#data-for-login)



## About the Project
Here you can find the Backend for my webapplication Fix Your Habits. It's a habit tracker where you can add, create, edit and delete habits. You can earn rewards for creating habits and discover all kinds of pre-made habits! 

Take a look at the <a href="https://github.com/divonne-c/FixYourHabits--FrontEnd">Frontend</a> code of this project.

## Getting Started
To install this project and use it locally, you need to follow a few steps.

### Prerequisites
* pg4Admin
* PostgreSQL
* Intellij
* Postman (optional)

### Installation

1. Download and Install Pg4Admin, PostgreSQL, IntelliJ and Postman (optional).

2. Create a database in Pg4Admin and use the name of the database, username and password in the `application.properties` file below.

3. Clone the repo with the SSH key in Intellij.
```
git clone git@github.com:divonne-c/FixYourHabits--BackEnd.git
```

4. Fill in the `application.properties` in Intellij (src > java > resources > application.properties)
```
// EXAMPLE
spring.datasource.url = jbdc:postgresql://localhost:5432/databaseName
spring.datasource.username= username
spring.datasource.password= password
```
5. Install JDK and add it to the project.
6. Click on ▶️ in the top right corner of IntelliJ to run the code.
7. Test it in `postman`. Copy this link: https://www.getpostman.com/collections/9e14c41c46ff4aa8a20c and paste it in `import` in `Collections` to make the testing easier.


## Data for login

To test the webapplication, you need to authenticate by signing in or creating an account. Below you will see the dummy accounts to log in. Be aware that the username and password are both lowercase!


| Role          | Username | Password |
|---------------|:--------:|---------:|
| User          |   user   | password |
| Admin         |  admin   | password |













