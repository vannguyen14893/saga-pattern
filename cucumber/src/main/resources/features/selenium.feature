Feature: Test login page

  Scenario Outline: User logs in successfully
     Given I open the login page url "http://localhost:8088"
     Then I enter phone "<phone>" and password "<password>"
     Then I should see the "<message>" on page
     And I click button logout
     Examples:
        | phone      | password    | message                |
        | 1234567891     | 123456a@  | Hello 1234567891     |
        | 1234567890     | 123456a@   | Hello 1234567890    |
