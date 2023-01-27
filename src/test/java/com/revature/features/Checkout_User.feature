Feature: User Checkout
  Background:
    Given user is logged in

  Scenario: User checks out the cart with valid payment method
    Given user has at least one card stored as payment method
    When user adds an item to cart
    And user goes to cart page and clicks Checkout Now button
    And user fills in shipping address and clicks Next button
    And user chooses payment method and clicks Submit Payment button
    And user clicks Place Order button
    Then user should see order confirmation message

  Scenario: User checks out the cart without valid payment method
    Given user has no card stored as payment method
    When user adds an item to cart
    And user goes to cart page and clicks Checkout Now button
    And user fills in shipping address and clicks Next button
    And user clicks Submit Payment button without choosing payment method
    Then user should not see order confirmation message






