Feature: Login
  Scenario Outline: user or admin logs into the application
    Given the user or admin is on the login page
    When  the user or admin types the email "<email>" into the username
    And   the user or admin types the password "<password>" into the password input
    And   the user or admin clicks on the Sign In button
    Then  the user or admin should be on the home page

    Examples:
    | email                     | password  |
    | tets@gmail.com            | !test123  |
    | nameynamenson@example.com | pass123   |

