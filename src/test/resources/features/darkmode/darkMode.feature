@darkmode

Feature: Dark Mode

  Scenario Outline: User can change theme to dark mode
    Given User navigates to the <type> page
    When User clicks the theme switch
    Then The theme of the page changes to dark mode
    And The font color changes to white

    Examples:
      |type    |
      |front   |
      |register|
      |login   |
      |profile |
      |orders  |
      |product |
      |cart    |
      |checkout|

  Scenario Outline: User can change theme back to light mode
    Given User navigates to the <type> page
    When User clicks the theme switch
    And The theme of the page changes to dark mode
    And User clicks the theme switch again
    Then The theme of the page changes back to light mode
    And The font color changes to black

    Examples:
      |type    |
      |front   |
      |register|
      |login   |
      |profile |
      |orders  |
      |product |
      |cart    |
      |checkout|