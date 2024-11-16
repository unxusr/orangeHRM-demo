Feature: Admin Page User Management

  Scenario: Add, Search, and Delete a User
    Given the user is on the OrangeHRM login page
    When the user enters "Admin" as username
    And the user enters "admin123" as password
    And the user clicks on the login button
    And the user clicks on the Admin tab
    Then the user should see the number of records found
    When the user clicks on the Add button
    And the user fills the required data
    And the user clicks on the Save button
    Then the number of records should increase by 1
    When the user searches for the new user by username
    Then the user should see the new user in the search results
    When the user deletes the new user
    Then the number of records should decrease by 1