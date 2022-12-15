Feature: Product Details View

  Scenario: Product Details View Guess
    # "a guest" is misspelled
    Given A Guesses is at the home page
    # This When shouldn't be here, do the wait inside the "Then" where you're asserting they can see the products
    When they browse the page
    Then they are able to see products' titles, descriptions, and prices
    When they hover over the images of the products for the plus icons to be displayed and clicked on the icons
    # This And seems unnecessary for a similar reason to the when above,
    # the "Then" should include the information being displayed in a popup
    And modals pop up
    Then once again, they are able to see products' titles, descriptions, and prices

  Scenario Outline: Product Details View When Logged in
    Given User or Admin is logged in with "<email>" and "<password>"
    When they browse the page
    Then they are able to see products' titles, descriptions, and prices
    When they hover over the images of the products for the plus icons to be displayed and clicked on the icons
    And modals pop up
    Then once again, they are able to see products' titles, descriptions, and prices

    Examples:
      |email|password|role|
      |jane@gmail.com|password|user|
      |sean@gmail.com|password|user|
      |testuser@gmail.com|password|admin|