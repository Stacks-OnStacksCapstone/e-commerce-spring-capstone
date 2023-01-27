Feature: User & Guest can add items to cart

  Scenario: User adds item to cart
    Given user is logged in and on the product page
    When the Cart icon on a product is clicked
    Then the item is added to cart

  Scenario: Guest adds item to cart
    Given guest is on the product page
    When the Cart icon on a product is clicked
    Then the item is added to cart