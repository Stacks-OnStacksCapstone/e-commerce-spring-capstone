@payment
Feature: Payment Method

  # Check payment-negative.feature for similar changes
  Scenario Outline: create payment method
    Given the user is logged in
    When the user clicks on the Profile link
    Then the user is navigated to the Profile page
    When the user enters "<cardNumber>" as cardNumber
    And the user enters "<expDate>" as expDate
    And the user enters "<ccv>" as ccv
    And the user clicks on the add payment button
    Then the user should see alert message "<message>" on the profile page

    Examples:
      | cardNumber     | expDate       | ccv          |message|
      | 1234455467854634   | 08082028   | 993 |You've successfully added your payment method! |
      | 9587567467854635  | 06072030  | 762 |You've successfully added your payment method! |
      | 3679567467856743  | 01052025  | 546 |You've successfully added your payment method! |
      | 9587567467868571  | 04032026  | 589 |You've successfully added your payment method! |

  # Check payment-negative.feature for similar changes
  Scenario Outline:  delete payment method
    Given the user is logged in
    When the user clicks on the Profile link
    Then the user is navigated to the Profile page
    When the user clicks on the delete payment button
    Then the user should see alert message "<message>" on the profile page

    # If there is only one example then it should be a Scenario not a Scenario Outline
    Examples:
      |message|
    |   You've successfully removed your payment method!    |

  # Check payment-negative.feature for similar changes
  Scenario Outline:  update payment method
    Given the user is logged in
    When the user clicks on the Profile link
    Then the user is navigated to the Profile page
    When the user clicks on the update payment button
    Then the user should see alert message "<message>" on the profile page

    # If there is only one example then it should be a Scenario not a Scenario Outline, however
    # update and delete could be combined by having the button be a template
    # the user clicks on the "<button>" button, with button having update payment and delete payment
    Examples:
      |message|
      |   You've successfully updated your payment method!    |