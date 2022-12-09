@payment
Feature: Payment Method

  Scenario Outline: Invalid Payment Method
    Given the user is logged in
    When the user clicks on the Profile link
    Then the user is navigated to the Profile page
    When the user enters "<cardNumber>" as cardNumber
    And the user enters "<expDate>" as expDate
    And the user enters "<ccv>" as ccv
    And the user clicks on the add payment button
    Then the user should see alert message "<message>"

    Examples:
      | cardNumber     | expDate       | ccv          |message|
      |    | 08082028   | 993 |Please fill out all fields |
      | 9587567467854635  |   | 762 |Please fill out all fields |
      | 9587567467854635  | 06072030  |  |Please fill out all fields |
      | this is a string  | 06072030  | 762 |Please enter a valid Card number! |
      | 9587567467854635  | 06072030  | this is a string |Please enter a valid Card number! |

