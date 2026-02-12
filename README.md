This is a sample project to demonstrate Playwright Java test automation framework using Maven, JUnit 5, Page Object Model, Allure reporting, and GitHub Actions CI.

## Building the Project

To build the project, use the following command:

```bash
mvn clean install
```

## Git — clone & prepare repository (run before testing locally)

Follow these commands to get the repository locally:

```bash

git clone <https://github.com/rvkvld/playwright-pom>
cd playwright-pom

# Fetch and switch to the main branch (or the branch you use)
git fetch origin
git checkout main
git pull origin main

```

Build the project once to download dependencies:

```bash
mvn clean install
```

# Running Tests:

```bash
# Run all tests (headless by default in tests)
mvn test

# Run a single test class with logs
mvn clean test -Dtest=com.company.tests.CartTests

# Run tests in headful mode (shows browser window) and with logs
mvn clean test -Dheadless=false -Dtest=CartTests
```

### End of Document
