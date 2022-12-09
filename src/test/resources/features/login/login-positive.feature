@login
Feature: Login Positive

  Scenario Outline: Login with correct credentials
    Given the user is on the "Login" page
    When the user enters "<username>" as username
    And the user enters "<password>" as password
    And the user clicks on the login button
    Then the user should be redirected to the "Home" page

    Examples:
      | username         | password       |
      | jane@gmail.com   | password       |
