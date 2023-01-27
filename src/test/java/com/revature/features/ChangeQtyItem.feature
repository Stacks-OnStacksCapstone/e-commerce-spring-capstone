Feature: CRUD Quantity of item in cart
  As a User or Admin or Guest
  I want to add, change and remove items from my cart
  So that I can manage my cart and make purchase easier

  Scenario: Increasing item quantity
    Given the User or Admin or Guest is on the homepage and has at least one item in the cart
    When the User or Admin or Guest clicks on the cart icon and goes to the cart page
    And the User or Admin or Guest clicks the + button
    Then the quantity of items should increase

  Scenario: Decreasing item quantity
    Given the User or Admin or Guest is on the homepage and has at least one item in the cart
    When the User or Admin or Guest clicks on the cart icon and goes to the cart page
    And the User or Admin or Guest clicks the - button
    Then the quantity of items should decrease

  Scenario: Removing item from cart
    Given the User or Admin or Guest is on the homepage and has at least one item in the cart
    When the User or Admin or Guest clicks on the cart icon and goes to the cart page
    And the User or Admin or Guest clicks the delete button
    Then the item should disappear from the cart page

