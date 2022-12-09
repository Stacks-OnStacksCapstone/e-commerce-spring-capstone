Feature: Notifications

  Scenario Outline: Notifications when transactions occurred
    Given A user is logged in with "<email>" and "<password>"
    When User hovers over the product and clicked on the cart icon
    When User clicks on the cart icon link on the navigation bar
    When User clicks on the Checkout Now button on the cart page
    When User filled out "<fName>" and "<lName>" for the Shipping Address on the checkout page
    And filled out "<address>" line 1
    And filled out "<city>" and "<state>"
    And filled out "<zipCode>" and "<country>"
    And clicks on the Next button
    When User select a payment method
    And clicks on the Submit Payment button
    When User clicks on the Place Order button
    Then a notification with title "Thank you for your order." is displayed

    Examples:
      |email|password|fName|lName|address|city|state|zipCode|country|
      |jane@gmail.com|password|Jane|Doe|123 test rd|testcity|teststate|12345|us|
      |sean@gmail.com|password|Sean|Person|456 test ct|tcity|tstate|67890|us|
      |testuser@gmail.com|password|Test|User|789 test blv|thecity|thestate|12390|us|