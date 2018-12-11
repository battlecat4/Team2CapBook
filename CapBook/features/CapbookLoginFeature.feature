Feature: Capbook Login Feature
Allows user to login to their capbook account
 
Scenario: User enters wrong password and correct username
Given user is in the login page
When user enters wrong password and correct username
Then display "Incorrect username or password." message in the same page
 
Scenario: User enters correct password and correct username
Given user is in the login page
When user enters correct password and correct username
Then displays the account