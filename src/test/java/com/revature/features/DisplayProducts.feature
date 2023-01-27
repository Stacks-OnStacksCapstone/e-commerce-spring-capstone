Feature: Display Products
  Scenario: guest can see a list of products
    Given the guest is on the home page
    Then  the guest can see a list of products

  Scenario Outline: user or admin can see a list of products
    Given the user logs in with the email "<email>" and the password "<password>"
    Then  the user should be on the home page
    And   the user should see a list of products

    Examples:
    | email                     | password  |
    | tets@gmail.com            | !test123  |
    | nameynamenson@example.com | pass123   |