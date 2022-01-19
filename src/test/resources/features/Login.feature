Feature: Login feature
  Background:
   # Given user is navigated to HRMS application
  @smoke
  Scenario Outline: Valid admin login
    When user enters different "<userName>" and "<password>"
    And  user clicks on login button
    Then "<admin>" user is successfully logged in
    Examples:
      |userName|password|admin|
      |Admin   |Hum@nhrm123|Admin|

  @regression
  Scenario: Valid ess login
    When user enters valid ess username and password
    And user clicks on login button
    Then admin user is successfully logged in

    @login
  Scenario Outline: negative login test
    When user enters different "<username>" and "<password>" and verify the "<error>" for all the combinations
    Examples:
      |userName|password|errorMessage|
      |Admin   |xyz     |Invalid credentials|
      |Alan    |Hum@nhrm123|Invalid credentials|
      |        |Hum@nhrm123|Username cannot be empty|
      |Admin   |          |Password cannot be empty|


