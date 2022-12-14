Feature: Searching Products

  Description: As a User, Guest or Admin, I should be able to search the
  product list to better find the item(s) I am interested in

  Scenario Outline: Positive Tests - Search Products that are available
    Given I am a <usertype> on the home page
    When I search for "<product>"
    Then "<product>" should be in the title of results
    When I cancel the search
    Then I should see products displayed on page

    Examples:
      |usertype|product|
      |guest|Headphones|
      |user|TeeShirt|
      |admin|Baseball Cap|


  Scenario Outline: Negative Tests - Searching Products not currently available
    Given I am a <usertype> on the home page
    When I search for "<product>"
    Then I should see an empty results page
    When I cancel the search
    Then I should see products displayed on page

    Examples:
      |usertype|product|
      |guest|trucks|
      |user|oranges|
      |admin|mercury|
