@password-reset
Feature: Password Reset
  Scenario Outline: Invalid Email
    # Ask for a password reset link
    Given the user is on the "Password Reset" page
    When the user enters "<email>" as the email
    When the user clicks on the send password reset link button
    Then the user should see a message saying "<message>"
    Examples:
      | email | message |
      | fake@domain.com | Entered email does not correspond to a registered user|

  Scenario Outline: Invalid Password
    # Ask for a password reset link
    Given the user is on the "Password Reset" page
    When the user enters "<email>" as the email
    When the user clicks on the send password reset link button
    Then the user should see a message saying "<confirmation message>"
    # Reset the password
    When the user goes to the reset link
    When the user enters "<password>" as their new password
    Then the user should see an error message under the "password" field saying "<error message>"

    Examples:
      | email          | password     | confirmation message                                                                      | error message                                                                                                   |
      | jane@gmail.com | password123! | A link to reset your password has been sent to 'jane@gmail.com', please check your email! | Password must contain at least 1 upper case letter                                                              |
      | jane@gmail.com | PASSWORD123! | A link to reset your password has been sent to 'jane@gmail.com', please check your email! | Password must contain at least 1 lower case letter                                                              |
      | jane@gmail.com | Password!    | A link to reset your password has been sent to 'jane@gmail.com', please check your email! | Password must contain at least 1 number                                                                         |
      | jane@gmail.com | Password123  | A link to reset your password has been sent to 'jane@gmail.com', please check your email! | Password must contain at least 1 special character                                                              |
      | jane@gmail.com | Pass1!       | A link to reset your password has been sent to 'jane@gmail.com', please check your email! | Password must contain 8 or more characters                                                                      |
      | jane@gmail.com |              | A link to reset your password has been sent to 'jane@gmail.com', please check your email! | Password must contain 8 or more characters with at least one of each: uppercase, lowercase, number, and special |

