Feature: An Admin should be able to create, update and delete products

  Background: Admin log in and access to edit products
    Given admin on the home page
    And admin has access to edit products

  Scenario: Admin can create a new product
    When admin creates a new product
    And admin enters new information
      | Name                     | Description       | Price |
      | Fitness Tracker          | Stress Management | 99    |
    When admin creates the product
    Then admin should see the new product update
    When admin checks all products available
    Then admin should see the new product displayed

  Scenario: Admin can update an existing product
    When admin selects the Headphones
    Then  admin should see pre-populated data
    And admin makes changes with the following details
      | Description                  | Price |
      | New amazing audio quality    | 40    |
    When admin updates the product
    Then admin should see product update
    When admin checks all products available
    Then admin should see the new update displayed

  Scenario: Admin can delete an existing product
    When admin selects an existing product
    And admin deletes it
    Then It should no longer be displayed with products


