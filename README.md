# Automation Exercise Test Automation Framework

This is my personal Selenium automation project for the website [Automation Exercise](https://www.automationexercise.com/).

I built this framework to practice real-world test automation using Java, Selenium, TestNG, and the Page Object Model.

---

## What I Used

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- WebDriverManager

---

## Project Structure

---

## Project Structure
```src/
├── main/java/
│   ├── BrowserManager/     → Browser setup
│   ├── MyFrameWork/        → Reusable methods
│   ├── Pages/              → Page classes
│   └── Utilities/          → Constants and helpers
└── test/java/
└── Tests/              → Test cases
```

---

## Features

- Clean Page Object Model design
- Reusable methods for common actions (click, type, wait, etc.)
- Explicit waits
- Support for multiple browsers (Chrome, Firefox, Edge, Brave)
- All test data stored in a Constants class (no hardcoding)
- Clear assertion failure messages

---

## Test Cases I Automated

| #  | Test Case                                      | Status |
|----|------------------------------------------------|--------|
| 1  | Register User                                  | Done   |
| 2  | Login with correct email and password          | Done   |
| 3  | Login with incorrect email and password        | Done   |
| 4  | Logout User                                    | Done   |
| 5  | Register with existing email                   | Done   |
| 6  | Contact Us Form                                | Done   |
| 7  | Verify Test Cases Page                         | Done   |
| 8  | Verify Products and Product Details            | Done   |
| 9  | Search Product                                 | Done   |
| 10 | Subscription on Home page                      | Done   |
| 11 | Subscription on Cart page                      | Done   |
| 12 | Add Products to Cart                           | Done   |
| 13 | Verify Product Quantity in Cart                | Done   |
| 14 | Place Order: Register while Checkout           | Done   |
| 15 | Place Order: Register before Checkout          | Done   |
| 16 | Place Order: Login before Checkout             | Done   |
| 17 | Remove Products From Cart                      | Done   |
| 18 | View Category Products                         | Done   |
| 19 | View & Cart Brand Products                     | Done   |

---

## How to Run the Project

1. Clone the repository
2. Open it in IntelliJ IDEA
3. Make sure you have Java and Maven installed
4. Update the browser in `config.properties` if needed
5. Run any test class, or use:

```
bash
mvn clean test

Author
Omar Monir

Aspiring Software Tester / SDET
I'm currently learning automation testing and building projects to improve my skills.

GitHub: Omar-Monir
