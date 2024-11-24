# OrangeHRM SeleniumCucumber Project

This project is a web automation testing framework for the OrangeHRM application using Selenium and Cucumber. The framework is designed to automate the testing of user management functionalities on the Admin Page.

## Project Structure
```
.
├── pom.xml
├── src
│   └── test
│       ├── java
│       │   ├── TestRunner.java
│       │   ├── pageObjects
│       │   │   ├── AdminPage.java
│       │   │   └── LoginPage.java
│       │   └── test
│       │       └── java
│       │           └── stepDefinitions
│       │               └── AdminSteps.java
│       └── resources
│           └── features
│               └── admin.feature

```
### Prerequisites
- Java 11 or higher
- Maven
- ChromeDriver (compatible with your Chrome version)
### Setup
#### Clone the repository:
```
git clone https://github.com/unxusr/orangeHRM-demo.git
cd web
```

### Install dependencies:

#### install
`mvn clean install`

Update the chromedriver path in your system's PATH environment variable.

#### Running Tests
To execute the tests, run the following command:

`mvn test`

## Project Components
### Page Objects
AdminPage.java: Contains methods to interact with the Admin Page elements.\
LoginPage.java: Contains methods to interact with the Login Page elements.\
### Step Definitions
AdminSteps.java: Contains step definitions for the Cucumber feature file.
### Feature Files
admin.feature: Contains the Cucumber scenarios for testing user management functionalities.\
### Test Runner
TestRunner.java: Configures and runs the Cucumber tests.
### Example Scenario
The admin.feature file contains a scenario for adding, searching, and deleting a user:

```
Feature: Admin Page User Management
  Scenario: Add, Search, and Delete a User
    Given the user is on the OrangeHRM login page
    When the user enters "Admin" as username
    And the user enters "admin123" as password
    And the user clicks on the login button
    And clicks on the PIM tab
    And user click on the Add Employee tab
    And the user enters employee first name "<firstname>"
    And the user enters employee last name "<lastname>"
    And the user enters employee id "<id>"
    And user saves the employee data
    And the user clicks on the Admin tab
    Then the user should see the number of records found
    When the user clicks on the Add button
    And the user fills the required data
    And the user clicks on the Save button
    Then the number of records should increase by one
    When the user searches for the new user by username
    Then the user should see the new user in the search results
    When the user deletes the new user
    Then the number of records should decrease by 1
```
    
## Reporting
After running the tests, a report will be generated at target/cucumber-reports.html.

## TODO
- Next a CI/CD will be added and configured to run on every commit

