Feature: Capbook Registration Page
Using this feature user is able to register in Capbook
 
Scenario Outline: User wants to register
Given User visits registration page
When User enters form details
	|firstname	|lastname	|emailId	|password |age			|Gender			|Birthdate	|FirstCar	|
	|<fname>	|<lname>	|<email>|<pword>	|<age>	|<gender>|<bdate>	|<fcar>		|
Then Display login page
	Examples:
	|fname	|lname	|email	|pword	|age	|gender	|bdate	|fcar	|
	|sara	|singh		|s@gmail.com	|sara	|23	|female	|12/12/2018	|corsa	|
	|roopam	|roopam	||roopam	|22	|female	|10/12/1995	|alto	|
#	|kajan	|randhawa		|k@gmail.com	|kajan	|23	|female	|08/12/1995	|alto	|
#	|sundar	|bishnoi	|sb@gmail.com	|sundar	|23	|male	|06/12/1995	|van	|
 
Scenario Outline: User wants to register with invalid details
Given User visits registration page
When User enters invalid details
|firstname	|lastname	|emailId	|password |age			|Gender			|Birthdate	|FirstCar	|
|<fname>	|<lname>	|<email>|<pword>	|<age>	|<gender>|<bdate>	|<fcar>		|
Then Display validation message	