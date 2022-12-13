@registration
Feature: Registration Negative

  Scenario Outline: Invalid registration inputs
    Given the user is on the "Registration" page
    When the user enters a "<first name>" into the first name input
    When the user enters a "<last name>" into the last name input
    When the user enters a "<email>" into the email input
    When the user enters a "<password>" into the password input
    Then the user should see an error message under the "<field>" field saying "<message>"

    Examples:
      | field     | first name | last name | email           | password       | message                                                                                                         |
      | firstname |            | Gauss     | gauss@gmail.com | Hypotenuse345! | First Name is required |
      | lastname  | Friedrich  |           | gauss@gmail.com | Hypotenuse345! | Last Name is required |
      | email     | Friedrich  | Gauss     |                 | Hypotenuse345! | Email Address is required |
      | email     | Friedrich  | Gauss     | fakeemail       | Hypotenuse345! | Email Address must be valid |
      | password  | Friedrich  | Gauss     | gauss@gmail.com | hypotenuse345! | Password must contain at least 1 upper case letter |
      | password  | Friedrich  | Gauss     | gauss@gmail.com | HYPOTENUSE345! | Password must contain at least 1 lower case letter |
      | password  | Friedrich  | Gauss     | gauss@gmail.com | Hypotenuse!    | Password must contain at least 1 number |
      | password  | Friedrich  | Gauss     | gauss@gmail.com | Hypotenuse345  | Password must contain at least 1 special character |
      | password  | Friedrich  | Gauss     | gauss@gmail.com | Hyp345!        | Password must contain 8 or more characters |
      | password  | Friedrich  | Gauss     | gauss@gmail.com |                | Password must contain 8 or more characters with at least one of each: uppercase, lowercase, number, and special |
