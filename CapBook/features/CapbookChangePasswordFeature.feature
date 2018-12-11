Feature: Capbook Change Password 
Using this feature user is able to change the password of his account
 
Scenario: User wants to change the password
Given User visits the Change Password page
When User enters the valid credentials to change password
Then Success message appears
 
Scenario: User wants to change the password
Given User visits the Change Password page
When User enters the invalid credentials to change password
Then Error message appears