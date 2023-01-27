Feature: User, Admin & Guest can change between Dark Mode and normal mode

  Scenario Outline: User or admin turns on dark mode
    Given user/admin is logged in using "<username>" and "<password>"
    And the dark mode is off
    When the dark mode switch is toggled
    Then dark mode should be turned on

    Examples:
      | username                | password  |
      | tets@gmail.com          | !test123  |
      | wirtualtm@example.com   | pass123   |

  Scenario Outline: User or admin turns off dark mode
    Given user/admin is logged in using "<username>" and "<password>"
    And the dark mode is on
    When the dark mode switch is toggled
    Then dark mode should be turned off

    Examples:
      | username                | password  |
      | tets@gmail.com          | !test123  |
      | wirtualtm@example.com   | pass123   |

  Scenario: guest turns on dark mode
    Given no user is logged in and the dark mode is off
    When the dark mode switch is toggled
    Then dark mode should be turned on

  Scenario: guest turns off dark mode
    Given no user is logged in and the dark mode is on
    When the dark mode switch is toggled
    Then dark mode should be turned off
