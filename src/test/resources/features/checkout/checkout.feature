@checkout
Feature: Checkout

  Background: User is logged in
    Given User is on the login page
    When User enters valid credentials
    Then User logs in to the front page

  Scenario: User can checkout the items in their cart
    Given User is on the front page
    When User adds an item to the cart and clicks the cart icon
    And User navigates to the cart
    And User clicks the checkout button
    And User enters a valid shipping address and clicks next
    And User selects a payment method and clicks submit
    And User clicks place order
    Then A checkout message is displayed

  # WITHOUT PAYMENT METHOD
  Scenario: User cannot checkout without a payment method
    Given User is on the front page
    When User adds an item to the cart and clicks the cart icon
    And User navigates to the cart
    And User clicks the checkout button
    And User enters a valid shipping address and clicks next
    And User does not select a payment method and clicks submit
    Then User remains on the payment method page

  # SHIPPING ADDRESS WITH EMPTY FIELDS
  Scenario Outline: User cannot checkout with empty fields for shipping address
    Given User is on the front page
    When User adds an item to the cart and clicks the cart icon
    And User navigates to the cart
    And User clicks the checkout button
    And User enters "<firstname>" to first name input
    And User enters "<lastname>" to last name input
    And User enters "<address>" to address input
    And User enters "<city>" to city input
    And Users enters GA to state input
    And User enters "<zip>" to zip input
    And User enters "<country>" to country input
    And User clicks next
    Then The empty field turns red and displays "<message>"
    And User remains on the shipping address page

    Examples:
      |firstname |lastname |address      |city   |zip     |country|message               |
      |          |Doe      |123 Home St  |Atlanta|12345   |USA    |First Name is required|
      |Jane      |         |123 Home St  |Atlanta|12345   |USA    |Last Name is required |
      |Jane      |Doe      |             |Atlanta|12345   |USA    |Address is required   |
      |Jane      |Doe      |123 Home St  |       |12345   |USA    |City is required      |
      |Jane      |Doe      |123 Home St  |Atlanta|        |USA    |Zipcode is required   |
      |Jane      |Doe      |123 Home St  |Atlanta|12345   |       |Country is required   |

  # FIELDS WITH SPECIAL CHARACTERS
  Scenario Outline: User cannot checkout with special characters in input fields
    Given User is on the front page
    When User adds an item to the cart and clicks the cart icon
    And User navigates to the cart
    And User clicks the checkout button
    And User enters "<firstname>" to first name input
    And User enters "<lastname>" to last name input
    And User enters "<address>" to address input
    And User enters "<city>" to city input
    And Users enters GA to state input
    And User enters "<zip>" to zip input
    And User enters "<country>" to country input
    And The empty field turns red and displays "<message>"
    Then User clicks next
    And User remains on the shipping address page

    Examples:
      |firstname |lastname |address      |city    |zip     |country|message                    |
      |Jane%     |Doe      |123 Home St  |Atlanta |12345   |USA    |Use only allowed characters|
      |Jane      |Doe%     |123 Home St  |Atlanta |12345   |USA    |Use only allowed characters|
      |Jane      |Doe      |123 Home St% |Atlanta |12345   |USA    |Use only allowed characters|
      |Jane      |Doe      |123 Home St  |Atlanta%|12345   |USA    |Use only allowed characters|
      |Jane      |Doe      |123 Home St  |Atlanta |12345%  |USA    |Must be only digits        |
      |Jane      |Doe      |123 Home St  |Atlanta |12345   |USA%   |Use only allowed characters|