Feature: Users and Guests can not create, update and delete products

  Scenario: Users can not create, update or delete products
    Given I am a user on the home page
    Then I see no where to edit products

  Scenario: Guests can not create, update or delete products
    Given I am a guest on the home page
    Then I see no where to edit products


