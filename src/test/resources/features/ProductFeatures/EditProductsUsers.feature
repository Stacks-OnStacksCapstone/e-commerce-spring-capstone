Feature: Users and Guests can not create, update and delete products

  Scenario: Users can not create, update or delete products
    Given user on the home page
    Then user should not be able to edit products

  Scenario: A guest user can not create, update or delete products
    Given guest on the home page
    Then user should not be able to edit products


