Feature: Product Detail View
  As a User or Admin or Guest
  I want to view the details of products
  So that I can learn more about them before making a purchase

  Scenario: Viewing product details as Guest
    Given the Guest is on the homepage
    When the User or Admin or Guest clicks on the the magnifier icon of a product
    Then a modal should appear displaying the details of the product

  Scenario Outline: Viewing product details
    Given the User or Admin is on the login page
    And the User or Admin types in their "<username>" and "<password>"
    Then the User or Admin should be on the homepage
    When the User or Admin or Guest clicks on the the magnifier icon of a product
    Then a modal should appear displaying the details of the product

    Examples:
      | username  | password  |
      | tets@gmail.com | !test123|
      | mickeymouse@example.com | pass123|
