@login
Feature: Login Positive

  Scenario Outline: Login with correct credentials
    Given the user is on the "Login" page
    When the user enters "<email>" as email
    And the user enters "<password>" as password
    And the user clicks on the login button
    Then the user should be redirected to the "Home" page

    Examples:
      | email            | password       |
      | jane@gmail.com   | password       |
