Feature: An Admin should be able to create, update and delete products

  Background: Admin log in and access to edit products
    Given I am a admin on the home page
    And I have access to edit products

  Scenario: Admin can create a new product
    When I go to create a product
    And I enter new information
      | Name                     | Description       | Price |
      | Fitness Tracker          | Stress Management | 99    |
    When I create the product
    Then I should see the new product update
    When I go to check all products
    Then I should see the new product displayed

  Scenario: Admin can update an existing product
    When I select the Headphones product
    Then  I should see pre-populated data
    And I change these details
      | Description                  | Price |
      | New amazing audio quality    | 40    |
    When I update the product
    Then I should see product update
    When I go to check all products
    Then I should see the new update displayed

  Scenario: Admin can delete an existing product
    When I select the newly created product
    And I delete it
    Then It should no longer be displayed with products


