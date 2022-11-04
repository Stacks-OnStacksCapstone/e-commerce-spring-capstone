Feature: Login

  Scenario: A user can login with correct credentials
    Given a user is on the login page.
    When a user enters the correct username
    When a user enters the correct password
    When a user clicks sign in
    Then a User should be on the homepage and see a LOGOUT button