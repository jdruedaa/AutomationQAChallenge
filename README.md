# AutomationQAChallenge
## Framework
The user may run the maven command mvn clean verify to run the tests via testng. They may also use the command allure serve to view a test report. In order to do these the user must have maven and allure correctly installed with the versions mentioned in the pom.xml file. There is logging and most of the used messages are either INFO or ERROR.
## Facebook
As part of the requirements it was requested to be able to change the user and password for the test case. To do so the user must create the environment variables FB_Email and FB_Password. I created a test account for this challenge and I could share its credentials via email if need be.

The first test case comprehends the posting of a status message. Due to Facebook policies a user can't post the same status message more than once in fast succession so I added extra cleanup steps to erase the new post after each execution.
## WalletHub
Using the environment variables WH_Email and WH_Password the user may add their credentials.

The test case verifies posting a review for a profile for which the user hasn't posted a review before. First the user logs in, then they visit the profile's page to leave a review, after creating and confirming it, it is verified that the review was posted. There is also detection for the case where the user has already posted a review, in this case the TC will fail with a logged error informing the problem.
