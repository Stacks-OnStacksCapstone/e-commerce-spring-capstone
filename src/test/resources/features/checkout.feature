@checkout
Feature: Checkout

  Scenario Outline: User can checkout the items in their cart
    Given <customer> is on the front page
    When <customer> adds items to the cart and clicks the cart icon
    And User navigates to the cart and sees their items
    And User clicks the checkout button
    And <customer> enters a valid shipping address and clicks next
    And <customer> selects a payment method and clicks submit
    And <customer> views the order summary and clicks place order
    Then A confirmation message appears with the order number and other information

    Examples: |customer|
              |"Guest" |
              |"User"  |

  # WITH INVALID SHIPPING ADDRESS
  Scenario Outline: User cannot checkout with invalid shipping address
    Given User is on the front page
    When User adds items to the cart and clicks the cart icon
    And User navigates to the cart and sees their items
    And User clicks the checkout button
    And User enters <first name> to first name input
    And User enters <last name> to last name input
    And User enters <address> to address input
    And User enters <city> to city input
    And Users enters GA to state input
    And User enters <zip code> to zip input
    And User enters <country> to country input
    Then User sees the <input> field turn red with an error message displayed

    Examples: |first name|last name|address      |city     |zip code|country|input     |
              |""        |"Doe"    |"123 Home St"|"Atlanta"|"12345" |"USA"  |first name|
              |"Jane"    |""       |"123 Home St"|"Atlanta"|"12345" |"USA"  |last name |
              |"Jane"    |"Doe"    |""           |"Atlanta"|"12345" |"USA"  |address   |
              |"Jane"    |"Doe"    |"123 Home St"|""       |"12345" |"USA"  |city      |
              |"Jane"    |"Doe"    |"123 Home St"|"Atlanta"|""      |"USA"  |zip code  |
              |"Jane"    |"Doe"    |"123 Home St"|"Atlanta"|"12345" |""     |country   |

  # WITHOUT PAYMENT METHOD
  Scenario: User cannot checkout without a payment method
    Given User is on the front page
    When User adds items to the cart and clicks the cart icon
    And User navigates to the cart and sees their items
    And User clicks the checkout button
    And User enters a valid shipping address and clicks next
    And User does not select a payment method and clicks submit
    Then User will not proceed to the final step to view their order summary


