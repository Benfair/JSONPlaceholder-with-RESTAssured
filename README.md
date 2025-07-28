# JSONPlaceholder API Testing with REST Assured

---

## Project Overview
This project demonstrates automated API testing using **REST Assured**, targeting the [JSONPlaceholder](https://jsonplaceholder.typicode.com/) public API.  
It validates API endpoints for **Posts**, **Users**, and **Comments**, focusing on:
- Status codes
- Response body structure
- Headers
- JSON Schema validation

The goal is to ensure the validation of API responses with clear reporting via **Allure Reports**, continuous integration using **GitHub Actions** and containerization with **Docker**.

---

## Features & Test Coverage

### **Posts Endpoint** (`/posts`)
- `GET /posts` → Validate all posts
- `GET /posts/{id}` → Validate a specific post
- `POST /posts` → Create a new post
- `PUT /posts/{id}` → Update an existing post
- `DELETE /posts/{id}` → Delete a post

### **Users Endpoint** (`/users`)
- `GET /users` → Validate all users
- `GET /users/{id}` → Validate specific user
- `DELETE /users/{id}` → Delete a user

### **Comments Endpoint** (`/comments`)
- `GET /comments` → Validate all comments
- `GET /posts/{id}/comments` → Comments for a post
- `POST /comments` → Create a comment

---

## Tech Stack
- **Java 21**
- **REST Assured**
- **TestNG**
- **JSON Schema Validator**
- **Allure TestNG Integration**
- **Maven**
- **GitHub Actions** (CI/CD)
- **Docker**

---

## Project Structure
```
JSONPlaceholder-with-RESTAssured/
│
├── .github/workflows/ci.yml
├── src/test
│   ├── java/api
│   │   ├── BaseTest.java
│   │   ├── CommentsTest.java
│   │   ├── PostsTest.java
│   │   └── UsersTest.java
│   └── resources
│       ├── schemas/
│       └── testng.xml
├── target/allure-results/
├── Dockerfile
├── pom.xml
└── README.md 
```

---

## How to Run Tests Locally

### Option 1: Run with Maven on Local Machine (Requires JDK & Allure CLI Installed)
1. **Clone the repo**
   ```bash
   git clone https://github.com/Benfair/JSONPlaceholder-with-RESTAssured.git
   cd JSONPlaceholder-with-RESTAssured
    ```
2. **Run tests with Maven**
    ```bash
    mvn clean test
     ```
3. **Generate Allure Report**
    ```bash
    allure serve target/allure-results
     ```

---

### Option 2: Run with Docker (No JDK or Allure installation Required)
1. Ensure Docker is installed and running
2. **Build Docker Image**
   ```bash
   docker build -t api-tests .
   ```
3. **Run tests & generate report**
   ```bash
   docker run --rm -v "${PWD}\allure-report:/app/allure-report" api-tests

    ```
4. **View report**
   ```bash
    allure open allure-report
    ```

---

## Continuous Integration (CI/CD)
On each push to main, GitHub Actions:
- Builds the Docker image
- Runs API tests inside the container
- Generates Allure report
- Uploads report as an artifact for download

[View the latest Allure Test Report](https://benfair.github.io/JSONPlaceholder-with-RESTAssured/)

---

## Dependencies
See `pom.xml` for full list:
- `rest-assured`
- `json-schema-validator`
- `testng`
- `allure-testng`

   