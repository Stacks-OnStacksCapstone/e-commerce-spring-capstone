Feature: Login
  Scenario: user or admin logs in with incorrect password
    Given the user or admin is on the login page
    When  the user or admin types in the email "tets@gmail.com" into the email input
    And   the user or admin types in the password "fakepassword" into the password input
    And   the user or admin clicks on the Sign In button
    Then  the user or admin should see a notification saying that their credentials are incorrect

  Scenario: the user or admin logs in with a non-existing email
    Given the user or admin is on the login page
    When  the user or admin types the email "doesnotexist@example.com" into the email input
    And   the user or admin types the password "!test123" into the password input
    And   the user or admin clicks on the Sign In button
    Then  the user or admin should see a notification saying that their credentials are incorrect