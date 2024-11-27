# orangeHRM-demo

This project contains web and API automation demos for the OrangeHRM application.

## Web Automation

### OrangeHRM SeleniumCucumber Project

This project is a web automation testing framework for the OrangeHRM application using Selenium and Cucumber. The framework is designed to automate the testing of user management functionalities on the OrangeHRM application.

### Project Structure
```
├── pom.xml
├── src
│ └── test
│ ├── java
│ │ ├── TestRunner.java
│ │ ├── pageObjects
│ │ │ ├── AdminPage.java
│ │ │ └── LoginPage.java
│ │ └── stepDefinitions
│ │ └── AdminSteps.java
│ └── resources
│ └── features
│ └── admin.feature
```

### Prerequisites
- Java 11 or higher
- Maven
- ChromeDriver (compatible with your Chrome version)

### Setup
#### Clone the repository:
git clone https://github.com/unxusr/orangeHRM-demo.git
cd web


#### Install dependencies:


Update the chromedriver path in your system's PATH environment variable.

### Running Tests
To execute the tests, run the following command:


### Project Components
#### Page Objects
- `AdminPage.java`: Contains methods to interact with the Admin Page elements.
- `LoginPage.java`: Contains methods to interact with the Login Page elements.

#### Step Definitions
- `AdminSteps.java`: Contains step definitions for the Cucumber feature file.

#### Feature Files
- `admin.feature`: Contains the Cucumber scenarios for testing user management functionalities.

#### Test Runner
- `TestRunner.java`: Configures and runs the Cucumber tests.

#### Example Scenario

The `admin.feature` file contains a scenario for adding, searching, and deleting a user.

### Reporting
After running the tests, a report will be generated at `target/cucumber-reports.html`.

### TODO
- Next, a CI/CD will be added and configured to run on every commit.

## API Automation

(Include details about the API automation project here. If there is no existing documentation, provide a brief overview of what the API automation tests cover, prerequisites, setup instructions, and how to run the tests.)
