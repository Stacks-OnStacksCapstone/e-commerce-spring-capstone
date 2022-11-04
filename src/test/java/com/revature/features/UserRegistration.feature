Feature:

  Scenario: A user can create a new user account
    Given a user is on the home page
    When a user clicks register
    When a user enters a first name
    When a user enter a last name
    When a user enters an email address
    When a user enters a password
    When a user clicks sign up
    Then the user should see the login page