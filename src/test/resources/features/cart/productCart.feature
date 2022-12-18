@productCart
Feature: Product Cart

  Scenario: Add product to the cart
    Given the user is on the Home Page
    When the user clicks the cart icon on the product to add it to the cart
    Then one item from that product should be added to the cart page

  # Update quantity should be a Scenario Outline with the quantity of items being changed
  # being a positive or negative number (or 0), and the quantity update should be one step implementation
  # which handles the cases by checking for positive or negative first and then doing the appropriate update

  Scenario: Update quantity of products in the cart
    Given the user is on the Home Page
    And the user clicks the cart icon on the product to add it to the cart
    And the user clicks on the Cart link
    When the user clicks on plus button in item that is in the cart
    Then the quantity of items for that product should be updated

  Scenario: Update quantity of products in the cart
    Given the user is on the Home Page
    And the user added two items from a determined product
    When the user go to the Cart Page
    When the user clicks on less button in item that is in the cart
    Then the quantity of items for that product should be only one

  Scenario: delete products in the cart
    Given the user is on the Home Page
    And the user clicks the cart icon on the product to add it to the cart
    And the user clicks on the Cart link
    When the user clicks on delete button in item that is in the cart
    Then the product should not be in the cart