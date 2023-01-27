Feature: Notifications

  Background: User has items in their cart and proceeds to purchase them
  # The notification is supposed to occur after you complete a purchase
  # not sure if you'll have to simulate going through that process
  # within the step implementation
  Scenario: User receives notification when a transaction has occurred
    When The user has made their way to the checkout page and clicks the 'Submit Payment' button
    Then The user clicks the place order button
    Then The user should receive a notification about the transaction