@productCart
Feature: Product Cart

  Scenario: Add product to the cart
    Given the user is on the Home Page
    When the user see the products
    And the user clicks on "cart" products link
    Then products should be added to the "cart page"

  Scenario: Update quantity of products in the cart
    Given the user is on the Cart Page
    When the user see the products that were added to the cart
    And the user clicks on plus button
    Then the quantity of items for that product should be updated

  Scenario: Update quantity of products in the cart
    Given the user is on the Cart Page
    When the user see the products that were added to the cart
    And the user clicks on less button
    Then the quantity of items for that product should be updated

  Scenario: delete products in the cart
    Given the user is on the Cart Page
    When the user see the products that were added in the cart
    And the user clicks on delete button
    Then the product should  not be in the cart