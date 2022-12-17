@checkout
Feature: Checkout

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

  # WITHOUT PAYMENT METHOD
  Scenario: User cannot checkout without a payment method
    Given User is logged in on the front page
    When User adds an item to the cart and clicks the cart icon
    And User navigates to the cart
    And User clicks the checkout button
    And User enters a valid shipping address and clicks next
    And User does not select a payment method and clicks submit
    Then User remains on the payment method page

  # SHIPPING ADDRESS WITH INVALID FIELDS
  Scenario Outline: User cannot checkout with invalid fields for shipping address
    Given User is logged in on the front page
    When User adds an item to the cart and clicks the cart icon
    And User navigates to the cart
    And User clicks the checkout button
    And User enters "<firstname>" to first name input
    And User enters "<lastname>" to last name input
    And User enters "<address>" to address input
    And User enters "<city>" to city input
    And Users enters TX to state input
    And User enters "<zip>" to zip input
    And User enters "<country>" to country input
    Then The invalid field turns red and displays "<message>"
    And User clicks next
    And User remains on the shipping address page

    Examples:
      |firstname |lastname |address          |city     |zip     |country|message                    |
      |          |Man      |500 Nowhere Rd   |Dallas   |50000   |USA    |First Name is required     |
      |Mark      |         |500 Nowhere Rd   |Dallas   |50000   |USA    |Last Name is required      |
      |Mark      |Man      |                 |Dallas   |50000   |USA    |Address is required        |
      |Mark      |Man      |500 Nowhere Rd   |         |50000   |USA    |City is required           |
      |Mark      |Man      |500 Nowhere Rd   |Dallas   |        |USA    |Zipcode is required        |
      |Mark      |Man      |500 Nowhere Rd   |Dallas   |50000   |       |Country is required        |
      |Mark%@!   |Man      |500 Nowhere Rd   |Dallas   |50000   |USA    |Use only allowed characters|
      |Mark      |Man%@!   |500 Nowhere Rd   |Dallas   |50000   |USA    |Use only allowed characters|
      |Mark      |Man      |500 Nowhere Rd%@!|Dallas   |50000   |USA    |Use only allowed characters|
      |Mark      |Man      |500 Nowhere Rd   |Dallas%@!|50000   |USA    |Use only allowed characters|
      |Mark      |Man      |500 Nowhere Rd   |Dallas   |50000%@!|USA    |Must be only digits        |
      |Mark      |Man      |500 Nowhere Rd   |Dallas   |50000   |USA%@! |Use only allowed characters|

