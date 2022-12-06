Feature: Update Profile

  Background:
    Given User is logged in
    When User clicks on the Profile link
    Then User is navigated to the Profile page
    Then User is able to see two MUI boxes "Update Your Profile" and "Deactivate Your Account"

  Scenario: Update a Profile
#    clear fields before sendKeys() in steps implementation
    When User types in "Jane123" in the first name input field
    And types in "Doe123" in the last name input field
    And types in "password" in the password input field
    And clicks on the Update button
    Then An Alert for successfully updated profile says "You've successfully updated your profile!"

  Scenario: Update Profile Without FirstName
    When User clears the first name input field
    And types in "password" in the password input field
    And clicks on the Update button
    Then An Alert says "Update was unsuccessful because "

  Scenario: Update Profile Without LastName
    When User clears the last name input field
    And types in "password" in the password input field
    And clicks on the Update button
    Then An Alert says "Update was unsuccessful because "

  Scenario: Update Profile Without password
    When User types in "Jane123" in the first name input field
    And types in "Doe123" in the last name input field
    And leaves the password input field empty
    And clicks on the Update button
    Then An Alert says "Update was unsuccessful because "

  Scenario: Update Profile With All Input Field Empty
    When User clears all input fields
    And clicks on the Update button
    Then An Alert says "Please update a field"

  Scenario: Update Profile With Only One Input Field Filled
    When User types in "Jane123" in the first name input field
    And User clears the last name input field
    And leaves the password input field empty
    And clicks on the Update button
    Then An Alert says "Update was unsuccessful because "