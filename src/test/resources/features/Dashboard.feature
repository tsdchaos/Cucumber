Feature: Dashboard functionality
   Background:
      When user enters valid admin username and password
      And user clicks on login button
      Then admin user is successfully logged in

   @dashboard
   Scenario: Verify dashboard tabs
      Then verify all the dashboard tabs
      |Admin|PIM|Leave|Time|Recruitment|Performance|Dashboard|Directory|