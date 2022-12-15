@checkout

Feature: Checkout Positive

  Scenario Outline: User/Guest can checkout the items in their cart
    Given User is <status> on the front page
    When User adds an item to the cart and clicks the cart icon
    And User navigates to the cart
    And User clicks the checkout button
    And User enters a valid shipping address and clicks next
    And User selects a payment method and clicks submit
    And User clicks place order
    Then A checkout message is displayed

    Examples:
      |status       |
      |logged in    |
      |not logged in|