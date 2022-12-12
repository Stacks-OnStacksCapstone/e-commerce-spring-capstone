@passwordreset
Feature: Password Reset
  Scenario Outline: Successful Password Reset
    # Ask for a password reset link
    Given the user is on the "Password Reset" page
    When the user enters "<email>" as the email
    When the user clicks on the send password reset link
    Then the user should see a message saying "<message>"
    # Reset the password
    When the user goes to the reset link
    When the user enters "<password>" as their new password
    Then the user should be redirected to the "Login" page
    # Verify the new password works
    When the user enters "<email>" as email
    And the user enters "<password>" as password
    And the user clicks on the login button
    Then the user should be redirected to the "Home" page

    Examples:
      | email | password | message |
      | jane@gmail.com | P@ssword123 | A link to reset your password has been sent to 'jane@gmail.com', please check your email! |


    # Entered email does not correspond to a registered user
    # A link to reset your password has been sent to 'jane@gmail.com', please check your email!