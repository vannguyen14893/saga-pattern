Feature: User API
  Scenario: Management user
    When I send a GET request to url "/user"
    Then the response status code should be 200
    Then the response should contain 0 users
    Then I send a POST request to "/user" with the following details:
    | name  | email           |
    | John  | john@example.com |
    Then the response status code should be 201
    Then the response should contain a user with name "John" and email "john@example.com"
    Then I send a GET request to url "/user/email/{email}" with the user's email "john@example.com"
    Then the response status code should be 200
    Then the response should contain a user with name "John" and email "john@example.com"
    Then I send a PUT request to "/user" with the following details:
    | name  | email           |
    | JohnUpdate  | john@example.com |
    Then the response status code should be 200
    Then the response should contain a user with name "JohnUpdate" and email "john@example.com"
    Then I send a DELETE request to url "/user/{email}" with the user's email "john@example.com"
    Then the response status code should be 200
    And the response should contain a user with name "JohnUpdate" and email "john@example.com"
