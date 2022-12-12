@passwordreset
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
      | email | password | confirmation message | error message |
      | jane@gmail.com | P@ssword123 | A link to reset your password has been sent to 'jane@gmail.com', please check your email! | |
