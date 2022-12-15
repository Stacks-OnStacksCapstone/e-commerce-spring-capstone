@login
Feature: Login Negative

  Scenario Outline: Login with incorrect credentials
    Given the user is on the "Login" page
    When the user enters "<email>" as email
    And the user enters "<password>" as password
    And the user clicks on the login button
    Then the user should see alert message "<message>" on the "Login" page

    Examples:
      | email               | password          | message                      |
      | jane@gmail.com      | wrong_password    | Invalid username or password |
      | wrong@gmail.com     | password          | Invalid username or password |
      | jane@gmail.com      |                   | Invalid username or password |
      |                     | password          | Invalid username or password |
