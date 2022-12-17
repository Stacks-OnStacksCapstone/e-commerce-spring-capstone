@orderhistory

Feature: Order History

  Background: User is logged in on the Orders page
    Given User logs in with valid credentials
    And User clicks the Orders link

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

