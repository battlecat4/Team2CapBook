Feature: Capbook Forget Password 
Using this feature user is able to reset the password and recover access to user profile
 
Scenario: User wants to recover the profile after he/she forgets the password
Given User visits the forget password option on Login Page
When User enters the valid details
Then Display user account
 
Scenario: User wants to recover the profile after he/she forgets the password
Given User visits the forget password option on Login Page
When User enters the invalid details
Then Display error message