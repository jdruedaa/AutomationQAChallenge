# AutomationQAChallenge
## Framework
The user may run the maven command mvn clean verify to run the tests via testng. They may also use the command allure serve to view a test report. In order to do these the user must have maven and allure correctly installed with the versions mentioned in the pom.xml file.
## Facebook
As part of the requirements it was requested to be able to change the user and password for the test case. To do so the user must create the environment variables FB_Username and FB_password. I created a test account for this challenge and I could share its credentials via email if need be.

The first test case comprehends the posting of a status message. Due to Facebook policies a user can't post the same status message more than once in fast succession so I added extra cleanup steps to erase the new post after each execution.