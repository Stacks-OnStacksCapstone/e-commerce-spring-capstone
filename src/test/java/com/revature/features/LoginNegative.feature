Feature: Login negative

  Scenario: A user can not login with the incorrect username and the correct password
    Given a user is on the login page.
    When a user enters "notMyEamil.com" into the input username
    When a user enters "team1" into the password input box
    When a user clicks sign in
    Then the user should see an alert that their account is deactivated