@payment
Feature: Payment Method

  Scenario Outline: create payment method
    Given the user is on the Home Page
    When the user log in
    And the user clicks on the Profile link
    Then the user is navigated to the Profile page
    When the user enters "<cardNumber>" as cardNumber
    And the user enters "<expDate>" as expDate
    And the user enters "<ccv>" as ccv
    And the user clicks on the "add" payment button
    Then the user should see alert message "<message>" on the profile page

    Examples:
      | cardNumber     | expDate       | ccv          |message|
      | 1234455467854634   | 08082028   | 993 |You've successfully added your payment method! |
      | 9587567467854635  | 06072030  | 762 |You've successfully added your payment method! |
      | 3679567467856743  | 01052025  | 546 |You've successfully added your payment method! |
      | 9587567467868571  | 04032026  | 589 |You've successfully added your payment method! |


  Scenario:  delete payment method
    Given the user is on the Home Page
    When the user log in
    And the user clicks on the Profile link
    Then the user is navigated to the Profile page
    When the user clicks on the "delete" payment button
    Then the user should see an alert message saying "You've successfully removed your payment method!"


  Scenario:  update payment method
    Given the user is on the Home Page
    When the user log in
    And the user clicks on the Profile link
    Then the user is navigated to the Profile page
    When the user clicks on the "update" payment button
    Then the user should see an alert message saying "You've successfully updated your payment method!"