
This is a sample project to demonstrate ...

## Building the Project

To build the project, use the following command:

```bash
mvn clean install
```

# Running Tests:

```bash
# Run all tests (headless by default in tests)
mvn test

# Run a single test class with logs
mvn -Dtest=com.epam.tests.CartTests test

# Run a single test in headful mode (shows browser window) and with logs
HEADLESS=false mvn -Dtest=com.epam.tests.CartTests test
```

If you want to use a custom Logback configuration at runtime, pass the system property:

```bash
mvn -Dlogback.configurationFile=path/to/logback.xml -Dtest=com.epam.tests.CartTests test
```

## Running Tests

To run the tests, use the following command:

```bash
mvn test
```

# End of Document
