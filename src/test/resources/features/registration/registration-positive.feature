@registration
Feature: Registration

  Scenario Outline: Successfully create a new account
    Given the user is on the "Registration" page
    When the user enters a "<first name>" into the first name input
    When the user enters a "<last name>" into the last name input
    When the user enters a "<email>" into the email input
    When the user enters a "<password>" into the password input
    When the user clicks the sign up button
    Then the user should be redirected to the "Login" page
    When the user enters "<email>" as email
    And the user enters "<password>" as password
    And the user clicks on the login button
    Then the user should be redirected to the "Home" page

    Examples:
      | first name | last name | email           | password       |
      | Friedrich  | Gauss     | gauss@gmail.com | Hypotenuse345! |