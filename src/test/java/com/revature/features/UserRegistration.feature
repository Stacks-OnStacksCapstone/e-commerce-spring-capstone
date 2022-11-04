Feature:

  Scenario: A user can create a new user account
    Given a user is on the home page
    When a user clicks register
    When a user enters a first name
    When a user enter a last name
    When a user enters an email address
    When a user enters a password
    When a user clicks sign up
    When a user enters the correct username
    When a user enters the correct password
    When a user clicks sign in
    Then a User should be on the homepage and see a LOGOUT button