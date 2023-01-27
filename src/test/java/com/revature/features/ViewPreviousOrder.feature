Feature: View Previous Orders
  As a User,
  I want to be able to view a list of all my previous orders

  Scenario: View previous orders
    Given the User is logged in and is on the homepage
    When the User clicks on the Orders button
    Then a list of previous orders should be displayed