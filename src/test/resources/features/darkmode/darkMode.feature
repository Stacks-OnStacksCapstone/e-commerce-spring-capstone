@darkmode

Feature: Dark Mode

  Scenario Outline: User can change theme to dark mode or light mode
    Given User navigates to the "<type>" page
    When The theme switch is set towards the sun
    And User clicks the theme switch "<clicks>"
    Then The switch slides towards the "<icon>" icon
    And The theme of the page changes to "<theme>" mode
    And The font color changes to "<color>"

    Examples:
      |type         |clicks |icon|theme|color|
      |front        |once   |moon|dark |white|
      |front        |twice  |sun |light|black|
      |register     |once   |moon|dark |white|
      |register     |twice  |sun |light|black|
      |login        |once   |moon|dark |white|
      |login        |twice  |sun |light|black|
      |profile      |once   |moon|dark |white|
      |profile      |twice  |sun |light|black|
      |orders       |once   |moon|dark |white|
      |orders       |twice  |sun |light|black|
      |product      |once   |moon|dark |white|
      |product      |twice  |sun |light|black|
      |cart         |once   |moon|dark |white|
      |cart         |twice  |sun |light|black|
      |checkout     |once   |moon|dark |white|
      |checkout     |twice  |sun |light|black|
      |edit products|once   |moon|dark |white|
      |edit products|twice  |sun |light|black|