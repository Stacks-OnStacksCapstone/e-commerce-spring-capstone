Feature: Searching Products

  Description: As a User, Guest or Admin - all these user types should be able to search the
  product list to better find the item(s) they are interested in

  Scenario Outline: Positive Tests - Search Products that are available
    Given <usertype> on the home page
    When user searches for "<product>"
    Then "<product>" should be in the title of results
    When user closes the search
    Then user should see products displayed on page

    Examples:
      |usertype|product|
      |guest|Headphones|
      |user|TeeShirt|
      |admin|Baseball Cap|


  Scenario Outline: Negative Tests - Searching Products not currently available
    Given <usertype> on the home page
    When user searches for "<product>"
    Then user should see an empty results page
    When user closes the search
    Then user should see products displayed on page

    Examples:
      |usertype|product|
      |guest|trucks|
      |user|oranges|
      |admin|mercury|
