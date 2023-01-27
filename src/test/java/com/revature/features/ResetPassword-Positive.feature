Feature: Reset Password

  Scenario Outline: User resets password with correct credentials
    Given The user is on the Profile page
    When The user types "<password>" into password input
    Then The user clicks the update button
    Then The user should see a notification that says you've successfully updated your profile

    #The  password must uppercase letters, lowercase letters,
    # numbers and special characters with a length of at least 8 characters

  Examples:
    | password   |
    | Testing123!|
    |Newp@ssword123|
    | Ex@mp1es   |