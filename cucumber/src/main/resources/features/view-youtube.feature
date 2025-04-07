Feature: View Youtube

  Scenario: View Youtube Successfully
    When I open the youtube page url "https://accounts.google.com/signin/v2/identifier?service=youtube"
    Then I enter email "<email>"
    Then I click the tag name "button" button
    Then I enter password "<password>"
    Then I click the tag name "button" button
