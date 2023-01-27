Feature: Register
  Scenario: user registers using a malformatted email
    Given the user is on the register page
    When  the user types the first name "Testy" into the first name input
    And   the user types the last name "McTester" into the last name input
    And   the user types the email "testymctester" into the email input
    And   the user types the password "Abcdef1!" into the password input
    And   the user clicks on the Sign Up button
    Then  the user should still be on the register page

  Scenario: user registers using a password less than 8 characters long
    Given the user is on the register page
    When  the user types the first name "Testy" into the first name input
    And   the user types the last name "McTester" into the last name input
    And   the user types the email "testymctester@example.com" into the email input
    And   the user types the password "Aa1!" into the password input
    And   the user clicks on the Sign Up button
    Then  the user should still be on the register page

  Scenario: user registers using a password without an uppercase letter
    Given the user is on the register page
    When  the user types the first name "Testy" into the first name input
    And   the user types the last name "McTester" into the last name input
    And   the user types the email "testymctester@example.com" into the email input
    And   the user types the password "abcdef1!" into the password input
    And   the user clicks on the Sign Up button
    Then  the user should still be on the register page

  Scenario: user registers using a password without a lowercase letter
    Given the user is on the register page
    When  the user types the first name "Testy" into the first name input
    And   the user types the last name "McTester" into the last name input
    And   the user types the email "testymctester@example.com" into the email input
    And   the user types the password "ABCDEF1!" into the password input
    And   the user clicks on the Sign Up button
    Then  the user should still be on the register page

  Scenario: user registers using a password without a number
    Given the user is on the register page
    When  the user types the first name "Testy" into the first name input
    And   the user types the last name "McTester" into the last name input
    And   the user types the email "testymctester@example.com" into the email input
    And   the user types the password "Abcdef!!" into the password input
    And   the user clicks on the Sign Up button
    Then  the user should still be on the register page

  Scenario: user registers using a password without a special character
    Given the user is on the register page
    When  the user types the first name "Testy" into the first name input
    And   the user types the last name "McTester" into the last name input
    And   the user types the email "testymctester@example.com" into the email input
    And   the user types the password "Abcdef11" into the password input
    And   the user clicks on the Sign Up button
    Then  the user should still be on the register page