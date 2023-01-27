Feature: Reset Password

  Scenario Outline: User resets password with incorrect credentials
    Given The user is on the Profile page
    When The user types "<password>" into password input
    Then The user clicks the update button
    Then The user should see a notification saying that there was an error when updating their profile

    Examples:
      | password   |
      |   Te123!   |
      |TESTING123! |
      |testing123! |
      |  Testing!  |
      | Testing123 |