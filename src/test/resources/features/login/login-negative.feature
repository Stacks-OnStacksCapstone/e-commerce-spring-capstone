@login
Feature: Login Negative

  Scenario Outline: Login with incorrect credentials
    Given the user is on the "Login" page
    When the user enters "<username>" as username
    And the user enters "<password>" as password
    And the user clicks on the login button
    Then the user should see alert message "<message>"

    Examples:
      | username            | password          | message                      |
      | jane@gmail.com      | wrong_password    | Invalid username or password |
      | wrong@gmail.com     | password          | Invalid username or password |
      | jane@gmail.com      |                   | Invalid username or password |
      |                     | password          | Invalid username or password |
