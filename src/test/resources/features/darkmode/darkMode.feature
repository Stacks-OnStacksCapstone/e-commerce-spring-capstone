@darkmode

Feature: Dark Mode

  Scenario Outline: User can change theme to dark mode
    Given User navigates to the <type> page
    When User clicks the theme switch
    Then The theme of the page changes to dark mode

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
