Feature: Product Details View

  Scenario: Product Details View Guest
    Given A Guests is at the home page
    Then they browse the page and are able to see products' titles, descriptions, and prices
    When they hover over the images of the products for the plus icons to be displayed and clicked on the icons
    Then A modal pop up, and once again, they are able to see products' titles, descriptions, and prices

  Scenario Outline: Product Details View When Logged in
    Given User or Admin is logged in with "<email>" and "<password>"
    Then they browse the page and are able to see products' titles, descriptions, and prices
    When they hover over the images of the products for the plus icons to be displayed and clicked on the icons
    Then A modal pop up, and once again, they are able to see products' titles, descriptions, and prices

    Examples:
      |email|password|role|
      |jane@gmail.com|password|user|
      |sean@gmail.com|password|user|
      |testuser@gmail.com|password|admin|