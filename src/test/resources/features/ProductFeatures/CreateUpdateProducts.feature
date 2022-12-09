Feature: Admin can create, update and delete products

  Background: Admin signs in with credentials and goes to the edit products page
    Given I am on the home page
    But I should not see a menu link that says "Edit Products"
    When I click on the sign in menu link
    When I enter "testuser@gmail.com" in the email address input
    When I enter "password" in the password input
    And I click the sign in button
    Then I should see a new menu link that says "Edit Products"
    When I click the edit products menu link
    Then I should be on the Edit Products page

  Scenario: Admin can update an existing product to be displayed
    When I click on the baseball cap image link
    Then I should be on the update product page
    Then I should see all input fields pre filled with data
    When I enter product name in the product name input
    And I enter product image url in the product image url input
    And I enter product description in the product description input
    And I enter product price in the product price input
    When I click the update button
    Then I should see the update immediately
    When I click the back to products button
    Then I should see the new product update displayed with all the other products

  Scenario: Admin can create a new product to be displayed
    When I click on the create new product button
    Then I should be on the create product page
    When I enter product name in the product name input
    And I enter product image url in the product image url input
    And I enter product description in the product description input
    And I enter product price in the product price input
    When I click the create product button
    Then I should see the update immediately
    When I click the back to products button
    Then I should see the new product update displayed with all the other products

  Scenario: Admin can delete an existing product
    When I click on the teeshirt image link
    And I should be on the teeshirt product page
    When I click the delete button
    Then I should be on the Edit Products page
    Then I should not see the teeshirt product displayed in the product list