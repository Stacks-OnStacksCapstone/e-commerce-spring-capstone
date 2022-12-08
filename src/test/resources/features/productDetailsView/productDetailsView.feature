Feature: Product Details View

  Scenario: Product Details View Guess
    Given A Guesses is at the home page
    When they browse the page
    Then they are able to see products' titles, descriptions, and prices
    When they hover over the images of the products
    Then plus icons are displayed
    When they clicks on the plus icons of the products
    And modals pop up
    Then once again, they are able to see products' titles, descriptions, and prices

  Scenario Outline: Product Details View When Logged in
    Given User or Admin is logged in with "<email>" and "<password>"
    When they browse the page
    Then they are able to see products' titles, descriptions, and prices
    When they hover over the images of the products
    Then plus icons are displayed
    When they clicks on the plus icons of the products
    And modals pop up
    Then once again, they are able to see products' titles, descriptions, and prices

    Examples:
      |email|password|role|
      |testuser@gmail.com|password|admin|
      |jane@gmail.com|password|user|
      |sean@gmail.com|password|user|