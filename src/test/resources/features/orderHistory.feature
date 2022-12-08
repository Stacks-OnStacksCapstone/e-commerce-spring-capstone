@orderhistory

Feature: Order History

  Background: User is logged in
    Given User is on login page
    When User enters an email
    And User enters a password
    And User clicks login
    And User logs in to Front page
    And User clicks the Orders link
    Then User navigates to the Orders page

  Scenario: User can view list of previous orders
    Given User is on the Orders page
    Then User sees a list of their previous orders
    And User can see the order date and total of each order

  Scenario: User can view order details of a previous order
    Given User is on the Orders page
    When User sees a list of their previous orders
    Then User can view the order detail ID and order ID
    And User can view the product purchased
    And User can view the product ID and quantity of the product purchased

  Scenario: User can view product details of a previous order
    Given User is on the Orders page
    When User clicks on the name of a product from a previous order
    Then User navigates to the details page of the product
    And User can view product rating and reviews

  Scenario: User can delete a product review from a previously ordered product
    Given User is on the Orders page
    When User clicks on the name of a product from a previous order
    And User navigates to the details page of the product
    And User can view product rating and reviews
    And User clicks the delete button for their previous review
    Then The review is removed from the product reviews

  Scenario: User can add a product review to a previously ordered product
    Given User is on the Orders page
    When User clicks on the name of a product from a previous order
    And User navigates to the details page of the product
    And User clicks the delete button for their previous review
    And User selects a rating for the product
    And User enters a review for the product
    And User clicks the submit button
    Then User sees their rating and review added to the product reviews

