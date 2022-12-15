@checkout
Feature: Checkout

  # The "user is <status> on the front page" should probably be broken up into
  # two Givens, one being the status and the other being the location
  # This way we can reuse "the user logs in with credentials "<username>" and "<password>" "
  # and "the user is on the Home page"

  # As for the not logged in case, that can use a different method
  # that logs out if logged in or does nothing otherwise
  Scenario Outline: User/Guest can checkout the items in their cart
    Given User is <status> on the front page
    When User adds an item to the cart and clicks the cart icon
    # Navigation should be combined with the
    # "the user is redirected to the "<page>" page" method
    # with the verb "is redirected" able to be replaced with "navigates" as in
    # "the user navigates to the "<page>" page"
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

  # the Scenario Outlines for empty fields and special characters should be combined
  # since the steps are almost exactly the same. These are all testing types of invalid input
  # into the address field so the examples tables can just be combined.

  # SHIPPING ADDRESS WITH EMPTY FIELDS
  Scenario Outline: User cannot checkout with empty fields for shipping address
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
    And User clicks next
    Then The invalid field turns red and displays "<message>"
    # This will reuse the "the user is on the "<page>" page" step implementation
    # but does not have to be changed here
    And User remains on the shipping address page

    Examples:
      |firstname |lastname |address       |city   |zip    |country|message               |
      |          |Man      |500 Nowhere Rd|Dallas|50000   |USA    |First Name is required|
      |Mark      |         |500 Nowhere Rd|Dallas|50000   |USA    |Last Name is required |
      |Mark      |Man      |              |Dallas|50000   |USA    |Address is required   |
      |Mark      |Man      |500 Nowhere Rd|      |50000   |USA    |City is required      |
      |Mark      |Man      |500 Nowhere Rd|Dallas|        |USA    |Zipcode is required   |
      |Mark      |Man      |500 Nowhere Rd|Dallas|50000   |       |Country is required   |

  # FIELDS WITH SPECIAL CHARACTERS
  Scenario Outline: User cannot checkout with special characters in input fields
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
    And The invalid field turns red and displays "<message>"
    Then User clicks next
    And User remains on the shipping address page

    Examples:
      |firstname   |lastname |address          |city     |zip     |country|message                    |
      |Mark%@!     |Man      |500 Nowhere Rd   |Dallas   |50000   |USA    |Use only allowed characters|
      |Mark        |Man%@!   |500 Nowhere Rd   |Dallas   |50000   |USA    |Use only allowed characters|
      |Mark        |Man      |500 Nowhere Rd%@!|Dallas   |50000   |USA    |Use only allowed characters|
      |Mark        |Man      |500 Nowhere Rd   |Dallas%@!|50000   |USA    |Use only allowed characters|
      |Mark        |Man      |500 Nowhere Rd   |Dallas   |50000%@!|USA    |Must be only digits        |
      |Mark        |Man      |500 Nowhere Rd   |Dallas   |50000   |USA%@! |Use only allowed characters|