# Library Management System - Selenium Automation Tests

This project adds Selenium automation test cases for a simple web-based Library Management System.

Your earlier Java Library Management System was console-based. Selenium works with browser UI, so this project includes a small HTML/CSS/JavaScript web UI and Selenium tests written in Java.

## What this project demonstrates

- Java Selenium WebDriver
- TestNG test framework
- Maven dependency management
- Page Object Model
- Browser automation
- Functional UI test cases
- Positive and negative test scenarios

## Project structure

```text
LibraryManagementSystem_SeleniumTests/
  pom.xml
  testng.xml
  src/main/webapp/
    index.html
    app.js
    styles.css
  src/test/java/com/library/automation/
    base/
      BaseTest.java
    pages/
      LibraryPage.java
    tests/
      AddBookTest.java
      SearchBookTest.java
      BorrowReturnBookTest.java
      ValidationTest.java
```

## Test cases included

| Test class | Scenario |
|---|---|
| `AddBookTest` | Add a valid book and verify it appears in the table |
| `SearchBookTest` | Search for an existing book and verify the result |
| `BorrowReturnBookTest` | Borrow a book, verify status, return it, verify status again |
| `ValidationTest` | Verify validation errors for empty and invalid data |

## Requirements

- Java 17 or newer
- Maven
- Chrome browser

Selenium 4 includes Selenium Manager, so it can usually manage the browser driver automatically.

## Run tests

Open terminal in this project folder and run:

```bash
mvn test
```

## Run with visible browser

```bash
mvn test -Dheadless=false
```

## Good interview explanation

You can say:

> I created Selenium automation tests for a Library Management System using Java, Maven, TestNG, and the Page Object Model. I tested core user flows such as adding books, searching books, borrowing books, returning books, and validation messages. I separated page actions from test cases to keep the framework clean and maintainable.
