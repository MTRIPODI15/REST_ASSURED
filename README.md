# ReqRes API Test Suite – Rest Assured + Maven + Allure

This project is a modular API test suite built with Rest Assured, JUnit 5, and Maven, targeting the public ReqRes API. It validates core endpoints such as user listing, authentication, registration, and error handling, with structured reporting via Allure.

# Test Modules

UserTests.java – Validates GET /users?page=2 with status, headers, and response structure  
AuthTests.java – Covers login success and failure via POST /login  
RegisterTests.java – Covers registration success and failure via POST /register  
ErrorTests.java – Validates error responses for missing data and non-existent resources

# Tech Stack

Rest Assured – Fluent DSL for HTTP request validation  
JUnit 5 – Test framework with annotations and assertions  
Maven – Dependency management and test execution  
Allure – Visual reporting with severity, steps, and traceability  
Java 17

# Testing Approach

Black-box testing – Focused on API behavior and contract validation  
Modular structure – Each test targets a specific endpoint or scenario  
Status code validation – 200, 400, 404 and more  
Header checks – Ensures correct content type and server responses  
Body assertions – Validates fields like token, email, error  
Error handling – Covers missing credentials and invalid resources

# Author
Matías Tripodi  
[LinkedIn](https://www.linkedin.com/in/matias-tripodi-139925a0/)
[GitHub Repo](https://github.com/matitripodi/demoqa-selenium-tests)
