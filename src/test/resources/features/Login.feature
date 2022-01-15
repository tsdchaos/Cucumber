Feature: Login feature

  Scenario: Valid admin login
    Given user is xyz
    Given user is navigated to HRMS application
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    Then user closes the browser

  Scenario: Valid ess login
    Given user is navigated to HRMS application
    When user enters valid ess username and password
    And user clicks on login button
    Then user closes the browser