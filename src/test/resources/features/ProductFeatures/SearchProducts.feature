Feature: Search Products
  Description: As a User, Guest or Admin, I should be able to search the
  product list to better find the item(s) I am interested in.

  Background: Start on Home Page
    Given I am on the home page

  Scenario: Guest (not logged in) searches to find product that exists in the
  product list (Positive)
    When I enter "headphones" into the search bar
    And I click the search icon
    Then I should see results returned and at least 1 product displayed
    Then I check to see if result contains "headphones" in product title
    Then I check to see if result contains "headphones" in product description
    When I click the cancel button in the search bar
    Then I see all products displayed on the page


  Scenario: Guest (not logged in) searches to find product that does not exist in
  the product list (Negative)
    When I enter "strawberry" into the search bar
    And I click the search icon
    Then I should see no results returned and no product displayed
    When I click the cancel button in the search bar
    Then I see all products displayed on the page


  Scenario Outline: Logged in User and Admin searches to find product that exists in
  the product list (Positive)
    When I click on the sign in menu link
    When I enter <email> in the email address input
    When I enter <password> in the password input
    And I click the sign in button
    When I enter "cap" into the search bar
    And I click the search icon
    Then I should see results returned and at least 1 product displayed
    Then I check to see if result contains "cap" in product title
    Then I check to see if result contains "cap" in product description
    When I click the cancel button in the search bar
    Then I see all products displayed on the page

    Examples:
      |email   |password
      |testuser@gmail.com | password
      |jane@gmail.com | password


  Scenario Outline: Logged in User and Admin searches to find product that does not
  exist in the product list (Negative)
    When I click on the sign in menu link
    When I enter <email> in the email address input
    When I enter <password> in the password input
    And I click the sign in button
    When I enter "mercury" into the search bar
    And I click the search icon
    Then I should see no results returned and no product displayed
    When I click the cancel button in the search bar
    Then I see all products displayed on the page

    Examples:
      |email   |password
      |testuser@gmail.com | password
      |jane@gmail.com | password