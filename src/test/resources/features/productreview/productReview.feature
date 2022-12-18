@productreview

Feature: Product Review

   # FRONT PAGE
  Scenario: Add product review on the front page
    Given User is logged in on the front page
    When User clicks the magnifying glass icon for shopping bag
    And A modal appears displaying the product details
    And User enters a rating and review for the product
    And User clicks the submit button
    Then The review is added to the product reviews

  Scenario: Delete product review on the front page
    Given User is logged in on the front page
    When User clicks the magnifying glass icon for shopping bag
    And A modal appears displaying the product details
    And User clicks the delete button for their previous review
    Then The review is removed from the product reviews

  # PRODUCTS PAGE
  Scenario: Delete product review on the products page
    Given User is logged in on the front page
    When User clicks the magnifying glass icon for tee shirt
    And User clicks the expand icon
    And User navigates to the products page for the product
    And User clicks the delete button for their previous review
    Then The review is removed from the product reviews

  Scenario: Add product review on the products page
    Given User is logged in on the front page
    When User clicks the magnifying glass icon for tee shirt
    And User clicks the expand icon
    And User navigates to the products page for the product
    And User enters a rating and review for the product
    And User clicks the submit button
    Then The review is added to the product reviews

  # GUEST REVIEWS
  Scenario: Guests cannot review a product
    Given User is not logged in on the front page
    When User clicks the magnifying glass icon for shopping bag
    And A modal appears displaying the product details
    And User enters a rating and review for the product
    And User clicks the submit button
    Then The review is not added to the product reviews

  # MORE THAN ONE REVIEW
  Scenario: Users cannot submit more than one review for the same product
    Given User is logged in on the front page
    When User clicks the magnifying glass icon for shopping bag
    And A modal appears displaying the product details
    And User enters a rating and review for the product
    And User clicks the submit button
    And User submits another review for the same product
    Then The second review is not added to the product reviews

  # WITHOUT RATING OR REVIEW
  Scenario Outline: User cannot submit a product review without a rating or review
    Given User is logged in on the front page
    When User clicks the magnifying glass icon for tee shirt
    And A modal appears displaying the product details
    And User submits a product review without the <element>
    Then The review is not added to the product reviews

    Examples:
    |element|
    |rating |
    |review |
