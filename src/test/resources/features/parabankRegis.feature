@All

  Feature: Parabank Registration

    @positive @register
      Scenario: Success Register
      Given User is on parabank homepage
      When User click register link button
      Then User is in register page
      When User input name
      And User input address detail
      And User fill valid username and password
      And User input password confirmation
      When User click register button
      Then User regist successfully

    @negative @register
    Scenario: Failed Register - Mismatch password
      Given User is on parabank homepage
      When User click register link button
      Then User is in register page
      When User input name
      And User input address detail
      And User fill valid username and password
      And User input password confirmation
      And user input invalid password confirmation
      When User click register button
      Then User get error password did not match
