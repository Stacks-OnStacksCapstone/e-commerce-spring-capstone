@payment
Feature: Payment Method

  Scenario Outline: Invalid Payment Method
    # the user is logged in should be replaced with the login steps for the user
    # as used in login-positive.feature
    Given the user is logged in
    # Navigation should be a single step implementation by changing
    # Profile to "Profile" and adding a POM for the navigation bar that all the other POMs extend
    # This way we don't have to care which page we're on when we click the link
    When the user clicks on the Profile link
    # Redirections should be a single step implementation by changing the line to be
    # of the same form as the redirections in login-positive.feature and passwordreset-positive.feature
    # make sure to add the links to the Hooks.java class and the cases to the method (currently stored in LoginSteps.java)
    Then the user is navigated to the Profile page
    When the user enters "<cardNumber>" as cardNumber
    And the user enters "<expDate>" as expDate
    And the user enters "<ccv>" as ccv
    And the user clicks on the add payment button
    # This alert should probably be made generic with all the green/red alerts
    # which can be done by replacing profile with "<page>" so that the step implementation
    # can determine which POM to use to get the xpath to the alert
    Then the user should see alert message "<message>" on the profile page

    Examples:
      | cardNumber     | expDate       | ccv          |message|
      |    | 08082028   | 993 |Please fill out all fields |
      | 9587567467854635  |   | 762 |Please fill out all fields |
      | 9587567467854635  | 06072030  |  |Please fill out all fields |
      | this is a string  | 06072030  | 762 |Please enter a valid Card number! |
      | 9587567467854635  | 06072030  | this is a string |Please enter a valid Card number! |

