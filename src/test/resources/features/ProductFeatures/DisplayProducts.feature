Feature: Display Products
  Description: A Logged in User, a Guest (not logged in) and Admin should be able to see a list of available
  products and add one product to their shopping cart.

  Scenario Outline: All user types should see list of products and add a product to their cart
    Given I am a <usertype> on the home page
    Then I should see products displayed on page
    When I select <product>
    Then I should see my shopping cart update
    When I select my shopping cart
    Then I should see the <product> I selected in my cart

    Examples:
      |usertype|product|
      |guest|headphones|
      |user|teeshirt|
      |admin|baseball cap|