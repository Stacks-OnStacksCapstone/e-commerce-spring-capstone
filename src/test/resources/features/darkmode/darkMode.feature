@darkmode

Feature: Dark Mode

  Scenario Outline: User can change theme to dark mode
    # Should be "<type>" so the same method works for all the examples
    Given User navigates to the <type> page
    When User clicks the theme switch
    # the icon should also be an example template so that sliding to dark mode and to light mode
    # can use the same method. You might need to add a "Given the "<page>" page is in "<mode>" mode"
    # so you don't try to switch from light to light or dark to dark
    Then The switch slides towards the moon icon
    # This also gets generalized
    And The theme of the page changes to dark mode
    # This also gets generalized
    And The font color changes to white

    Examples:
      |type         |
      |front        |
      |register     |
      |login        |
      |profile      |
      |orders       |
      |product      |
      |cart         |
      |checkout     |
      |edit products|

  Scenario Outline: User can change theme back to light mode
    Given User navigates to the <type> page
    When User clicks the theme switch
    And The theme of the page changes to dark mode
    And User clicks the theme switch again
    Then The switch slides towards the sun icon
    And The theme of the page changes back to light mode
    And The font color changes to black

    Examples:
      |type         |
      |front        |
      |register     |
      |login        |
      |profile      |
      |orders       |
      |product      |
      |cart         |
      |checkout     |
      |edit products|