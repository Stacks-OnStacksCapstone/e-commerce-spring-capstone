Feature: Display Products

  Description: A Logged in User, a Guest (not logged in) and Admin should be able to see a list of available
  products and add one product to their shopping cart.

  Scenario Outline: Users should see list of products and add a product to their cart
    Given <usertype> on the home page
    Then user should see products displayed on page
    When user selects <product>
    Then user should see shopping cart update
    When "<usertype>" is in their shopping cart
    Then user should see the "<product>" in their cart

    Examples:
      |usertype|product|
      |guest|Headphones|
      |user|TeeShirt|
      |admin|Baseball Cap|




    ## product values in Examples table above are case sensitive