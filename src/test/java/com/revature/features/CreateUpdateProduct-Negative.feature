Feature: Create/Update Product Page

  Scenario: Admin creates a new product with no name
    Given The admin is logged in
    When Admin clicks the Edit Product button
    When Admin clicks Create New Product button
    When Admin types a description
    When Admin types a price
    Then Admin clicks the Create Product button
    Then The Admin will be given an error saying the required fields must be filled in

  Scenario: Admin creates a new product with no description
    Given The admin is logged in
    When Admin clicks the Edit Product button
    When Admin clicks Create New Product button
    When Admin types a name
    When Admin types a price
    Then Admin clicks the Create Product button
    Then The Admin will be given an error saying the required fields must be filled in

  Scenario: Admin creates a new product with no price
    Given The admin is logged in
    When Admin clicks the Edit Product button
    When Admin clicks Create New Product button
    When Admin types a name
    When Admin types a description
    Then Admin clicks the Create Product button
    Then The Admin will be given an error saying the required fields must be filled in

  Scenario: Admin updates a product field to blank
    Given The admin is logged in
    When Admin clicks the Edit Product button
    When Admin clicks on an existing product
    When Admin deletes the description and leaves it blank
    Then Admin clicks the update button
    Then The Admin will be given an error saying the required fields must be filled in