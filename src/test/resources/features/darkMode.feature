@darkmode

Feature: Dark Mode

  Scenario Outline: User can change theme to dark mode
    Given User is on the <type> page
    When User clicks the theme switch
    Then The theme of the <type> page changes to dark mode

    Examples: |type    |
              |front   |
              |register|
              |login   |
              |profile |
              |orders  |
              |product |
              |cart    |
              |checkout|

  Scenario: Dark mode changes product image backgrounds to black
    Given User is on the front page
    When User clicks the theme switch
    Then The theme of the front page changes to dark mode
    And The image background for each product changes to black
