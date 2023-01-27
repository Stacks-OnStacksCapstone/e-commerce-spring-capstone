Feature: Guest Checkout

  Scenario: Guest checks out the cart
    Given guest has at least one item in the cart
    When guest goes to cart page and clicks Checkout Now button
    And guest fills in shipping address and clicks Next button
    Then guest should be able to select payment method
    # Fail: no way for a guest to add payment method