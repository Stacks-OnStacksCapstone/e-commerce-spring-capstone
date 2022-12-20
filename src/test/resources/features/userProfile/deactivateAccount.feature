@userprofile
Feature: Deactivate User Account

  Background:
    Given User is logged in using "mark@gmail.com" and "password"
    When User clicks on the Profile link
    Then The user should be navigated to the Profile page
    Then The user should be able to see two MUI boxes "Update Your Profile" and "Deactivate Your Account"

  Scenario: Deactivate a user's account  (negative)
    When User types nothing in the input field
    And attempt to click on the Deactivate button
    Then the deactivate button is unclickable
    Then Account is not deactivated and User stays on the Profile page

  Scenario: Deactivate a user's account
    When User types in "deactivate" in the input field
    And clicks on the Deactivate button
    Then The user is logged out, navigated to the Login page
    When User types in again their email and password to login
    Then An Alert for re-attempting to login says "Login was unsuccessful because your account has been deactivated!"