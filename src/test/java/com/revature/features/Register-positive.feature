Feature: Register
  Scenario: user registers for the application
    Given the user is on the register page
    When  the user types the first name "Testy" into the first name input
    And   the user types the last name "McTester" into the last name input
    And   the user types the email "testymctester@example.com" into the email input
    And   the user types the password "Abcdef1!" into the password input
    And   the user clicks on the Sign Up button
    Then  the user should be redirected to the login page
