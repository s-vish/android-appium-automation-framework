# Android Appium Automation Framework

A Java-based mobile test automation framework built with Appium, Selenium, TestNG, and Maven to automate Android application workflows using a structured, reusable, and maintainable approach.

The framework combines automated UI testing with foundational test automation engineering practices, including centralized driver management, reusable wait utilities, screen-based interaction design, failure screenshot capture, and externalized test data.

## Current Scope

The framework currently targets Android UI automation for the Sauce Labs sample application, with automated login scenarios covering successful authentication and invalid credentials.

## Engineering Focus

- **Reusable architecture:** Organizing common automation capabilities into dedicated framework components.
- **Maintainable test design:** Separating screen interactions, test logic, and supporting utilities.
- **Reliable execution:** Managing driver lifecycle and using explicit waits for UI interactions.
- **Test data management:** Keeping authentication test data in JSON resources separate from test code.
- **Failure investigation:** Capturing screenshots when tests fail.

## Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Programming language |
| Maven | 3.9.9* | Build and dependency management |
| Appium Java Client | 10.1.1 | Mobile automation API |
| Selenium | Managed transitively | WebDriver APIs |
| TestNG | 7.11.0 | Test execution and lifecycle management |
| Jackson Databind | 2.21.8 | JSON test data parsing |
| Appium Server | 2.5.1* | Receives and processes automation commands |
| UiAutomator2 Driver | 3.0.1* | Android automation driver |

*Versions marked with an asterisk reflect the current development environment and are not pinned by the project's Maven configuration.*

## Prerequisites

Before running the framework, ensure the following are available:

- JDK 17
- Apache Maven
- Android SDK and the required platform tools
- An Android emulator or a connected Android device
- Appium Server with the UiAutomator2 driver installed
- The Sauce Labs sample application APK, placed at the location expected by the project

Refer to the Setup and Configuration section for environment setup, application configuration, and execution instructions.


## Architecture and Project Structure

The framework follows a modular design that separates test scenarios, application screen interactions, driver management, configuration, reusable helpers, and test data.

### Architecture Overview

The framework is organized into the following logical areas:

- **Test Layer:** Defines test scenarios, assertions, test setup, teardown, and failure handling.
- **Screen Layer:** Encapsulates application-specific screen locators, interactions, and screen checks.
- **Driver Management:** Creates and manages the Android driver used to communicate with the Appium server.
- **Configuration:** Loads framework settings from a properties file.
- **Reusable Helpers:** Provides common utilities for explicit waits and screenshot capture.
- **Test Data Management:** Loads authentication test data from JSON resources through `TestDataReader`, keeping test inputs separate from test logic.

### Responsibilities of Major Components

| Component | Responsibility |
|---|---|
| `BaseTest` | Initializes the driver before each test and handles teardown, failure screenshot capture, and driver cleanup. |
| `LoginTest` | Executes successful and unsuccessful login scenarios and verifies the expected outcomes. |
| `DriverTest` | Verifies that an Appium session starts and prints the session ID and device name. |
| `DriverFactory` | Creates an `AndroidDriver` using configured Appium capabilities and the Appium server URL. |
| `DriverManager` | Stores and retrieves driver references using `ThreadLocal` and provides driver cleanup. |
| `BaseScreen` | Provides shared functionality for screen objects, including wait support. |
| `LoginScreen` | Encapsulates login-screen locators and interactions. |
| `HomeScreen` | Encapsulates home-screen checks. |
| `WaitHelper` | Provides reusable explicit waits for visibility, clickability, presence, and invisibility. |
| `ScreenshotHelper` | Captures screenshots and saves them under `target/screenshots/`. |
| `ConfigReader` | Loads configuration properties and validates requested values. |
| `FrameworkConfig` | Provides access to the shared configuration reader. |
| `TestDataReader` | Reads values from JSON test data by scenario name and key. |

### Project Structure

```text
android-appium-automation-framework/
├── .gitignore
├── README.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── dev/vishx/qa/framework/
    │   │       ├── config/
    │   │       │   ├── ConfigReader.java
    │   │       │   └── FrameworkConfig.java
    │   │       ├── driver/
    │   │       │   ├── DriverFactory.java
    │   │       │   └── DriverManager.java
    │   │       ├── helpers/
    │   │       │   ├── ScreenshotHelper.java
    │   │       │   └── WaitHelper.java
    │   │       └── screens/
    │   │           ├── BaseScreen.java
    │   │           ├── HomeScreen.java
    │   │           └── LoginScreen.java
    │   └── resources/
    │       └── config.properties
    └── test/
        ├── java/
        │   └── dev/vishx/qa/
        │       ├── framework/
        │       │   └── data/
        │       │       └── TestDataReader.java
        │       └── tests/
        │           ├── BaseTest.java
        │           ├── DriverTest.java
        │           └── LoginTest.java
        └── resources/
            └── testdata/
                └── authentication.json
```

The `pom.xml` defines project dependencies and Maven build plugins. The `src/main` directory contains reusable framework code and configuration, while `src/test` contains test classes, test-support code, and test data. The `.gitignore` file excludes generated output, IDE-specific files, and the local application APK from normal Git tracking.

### Why This Structure?

The framework separates test scenarios, screen interactions, and reusable infrastructure to make responsibilities easier to understand and maintain. Shared components for driver management, explicit waits, configuration handling, and screenshot capture reduce duplication across tests, while externalized JSON test data keeps test inputs separate from test logic.

This modular structure provides a foundation for future enhancements. Capabilities such as parallel execution or additional platform support will require further implementation and validation before they can be considered supported features.


## Setup and Configuration

### 1. Clone the Repository

Clone the repository and navigate to the project directory:

```bash
git clone https://github.com/s-vish/android-appium-automation-framework.git
cd android-appium-automation-framework
```

### 2. Install Project Dependencies

Ensure that JDK 17 and Maven are installed and configured on your system.

Verify the installations:

```bash
java -version
mvn -version
```

Download the project dependencies by running:

```bash
mvn clean install -DskipTests
```

### 3. Set Up the Android Environment

Ensure that the Android SDK is installed and that an Android emulator or physical device is available.

Verify that the device is connected and recognized:

```bash
adb devices
```

Install Appium Server and the UiAutomator2 driver. Start the Appium server before executing the tests.

### 4. Configure the Application

Place the Sauce Labs sample application APK at the following location:

```text
app/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk
```

The APK is excluded from Git tracking, so it must be obtained separately.

The application package and launch activity are configured in `src/main/resources/config.properties`.

### 5. Configure Framework Properties

Update `src/main/resources/config.properties` to match your local Android environment:

```properties
platform=android
automationName=UiAutomator2
deviceName=emulator-5554
appPackage=com.swaglabsmobileapp
appActivity=com.swaglabsmobileapp.SplashActivity
appiumServerUrl=http://127.0.0.1:4723
explicitWaitTimeoutSeconds=10
```

Adjust `deviceName` and `appiumServerUrl` as required for your environment.

Ensure that the configured Appium server URL matches the address and port on which your Appium server is running.

### 6. Verify the Setup

After completing the configuration, run the test suite using the commands documented in the Test Execution section.


## Test Execution

Before running the tests, ensure that the Android emulator or device is available, the Appium server is running, the application APK is in the expected location, and `config.properties` is configured correctly.

### 1. Run the Complete Test Suite

Execute all tests configured for the Maven test lifecycle:

```bash
mvn clean test
```

This command cleans previous build output and executes the tests through Maven Surefire.

### 2. Run a Specific Test Class

To execute only the login test scenarios:

```bash
mvn clean test -Dtest=LoginTest
```

To execute only the driver initialization test:

```bash
mvn clean test -Dtest=DriverTest
```

### 3. Review Test Results

After execution, Maven displays the test summary in the terminal.

Detailed TestNG results are available under:

```text
target/surefire-reports/
```

Screenshots captured for failed tests are saved under:

```text
target/screenshots/
```

These generated files are local test artifacts and are not intended to be committed to the repository.

## Current Test Coverage

The framework currently includes tests for driver initialization and authentication workflows in the Sauce Labs sample Android application.

### Test Scenarios

| Test Class | Scenario | Expected Outcome |
|---|---|---|
| `DriverTest` | Appium driver initialization | An Appium session starts successfully, and the session ID and device name are printed. |
| `LoginTest` | Login with valid credentials | The user successfully logs in and the home screen is displayed. |
| `LoginTest` | Login with invalid credentials | The expected authentication error message is displayed. |

### Test Data Management

Authentication test data is stored separately from test logic in:

```text
src/test/resources/testdata/authentication.json
```

The `TestDataReader` utility retrieves values from the JSON file using a scenario name and key. The current scenarios are `validLogin` and `invalidLogin`.

### Failure Diagnostics

When a test fails, the test teardown attempts to capture a screenshot for troubleshooting. Screenshots are saved under:

```text
target/screenshots/
```

Test execution results are available in Maven Surefire reports under:

```text
target/surefire-reports/
```

### Current Scope and Limitations

The current implementation focuses on Android UI automation, driver lifecycle management, login validation, reusable explicit waits, and failure screenshot capture.

Additional workflows, broader test coverage, and advanced capabilities can be introduced as the framework evolves.


## Troubleshooting

The following checks can help diagnose common environment and test execution issues.

| Problem | Possible Cause | What to Check |
|---|---|---|
| Appium server connection fails | The Appium server is not running, or the configured URL is incorrect. | Start the Appium server and verify `appiumServerUrl` in `config.properties`. |
| Android device is not detected | The emulator is stopped, or the physical device is not connected or authorized. | Run `adb devices` and confirm that the target device is listed as available. |
| Application fails to launch | The APK is missing, or the configured package or activity is incorrect. | Verify the APK location and check `appPackage` and `appActivity` in `config.properties`. |
| Test fails while locating an element | The locator may be incorrect, or the element may not be available in the expected state. | Review the screen object's locator, application state, and explicit wait conditions. |
| Test data cannot be loaded | The JSON resource is missing, incorrectly named, or contains invalid JSON. | Check `src/test/resources/testdata/authentication.json` and verify scenario names and keys. |
| Test fails during setup or teardown | Driver initialization or cleanup may have failed. | Review the terminal output and Maven Surefire reports for the underlying exception. |
| Failure screenshot is missing | Screenshot capture may have failed, or the test may have failed before normal teardown handling completed. | Check the test output and inspect `target/screenshots/`. |

### Useful Diagnostic Commands

Check the Java and Maven installations:

```bash
java -version
mvn -version
```

Check connected Android devices:

```bash
adb devices
```

Run the test suite with Maven:

```bash
mvn clean test
```

Review test execution details in:

```text
target/surefire-reports/
```


## Limitations and Future Enhancements

### Current Limitations

The framework currently provides a foundation for Android UI automation, with login validation and reusable framework components. The following areas remain opportunities for further development:

- **Platform support:** The current driver implementation targets Android using UiAutomator2.
- **Test coverage:** Automated scenarios currently focus on driver initialization and valid and invalid login workflows.
- **Test data handling:** JSON test data is retrieved by scenario name and key; more advanced data-driven execution can be introduced later.
- **Parallel execution:** `ThreadLocal` driver storage provides a foundation for thread-specific driver instances, but complete parallel execution support has not yet been implemented and validated.
- **Reporting:** Maven Surefire reports and failure screenshots are available; richer reporting and centralized logging can be added in the future.

### Planned Enhancements

Potential next steps for evolving the framework include:

- Expand functional coverage to additional application workflows.
- Introduce data-driven testing for executing scenarios with multiple sets of test data.
- Enhance reporting with Allure or another reporting solution.
- Improve logging and failure diagnostics.
- Evaluate parallel test execution with appropriate driver and test lifecycle isolation.
- Explore API and mobile UI integration testing where application workflows require it.
- Evaluate CI/CD integration to automate test execution in a continuous integration pipeline.

These enhancements will be introduced and validated incrementally as the framework evolves.
