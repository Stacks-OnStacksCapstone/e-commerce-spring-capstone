@productreview

Feature: Product Review

  Scenario: Guests cannot review a product
    Given User is not logged in on the front page
    When User clicks the magnifying glass icon
    And A modal appears displaying the product details
    And User selects a rating for the product
    And User enters a review for the product
    And User clicks the submit button
    Then The review is not added to the product reviews

  # FRONT PAGE
  Scenario: Users logged in can review a product from the front page
    Given User is logged in on the front page
    When User clicks the magnifying glass icon
    And A modal appears displaying the product details
    And User selects a rating for the product
    And User enters a review for the product
    And User clicks the submit button
    Then The review is added to the product reviews

  Scenario: Users logged in can delete their product review from the front page
    Given User is logged in on the front page
    When User clicks the magnifying glass icon
    And A modal appears displaying the product details
    And User clicks the delete button for their previous review
    Then The review is removed from the product reviews

  # PRODUCTS PAGE
  Scenario: Users logged in can review a product from the products page
    Given User is logged in on the front page
    When User clicks the magnifying glass icon
    And User clicks the expand icon
    And User navigates to the products page for the product
    And User selects a rating for the product
    And User enters a review for the product
    And User clicks the submit button
    Then The review is added to the product reviews

  Scenario: Users logged in can delete their product review from the products page
    Given User is logged in on the front page
    When User clicks the magnifying glass icon
    And User clicks the expand icon
    And User navigates to the products page for the product
    And User clicks the delete button for their previous review
    Then The review is removed from the product reviews

  # MULTIPLE REVIEWS
  Scenario: Users cannot submit more than one review for the same product
    Given User is logged in on the front page
    When User clicks the magnifying glass icon
    And A modal appears displaying the product details
    And User selects a rating for the product
    And User enters a review for the product
    And User clicks the submit button
    And User submits another review for the same product
    Then The second review is not added to the product reviews

  Scenario Outline: User cannot submit a product review without a rating or review
    Given User is logged in on the front page
    When User clicks the magnifying glass icon
    And A modal appears displaying the product details
    And User submits a product review without the <element>
    Then The review is not added to the product reviews

    Examples:
    |element|
    |rating |
    |review |
