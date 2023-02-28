package org.wallethub.tests.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.wallethub.pages.*;
import org.wallethub.utils.Hooks;

import java.util.NoSuchElementException;

public class ReviewTests extends Hooks {

    private final String reviewMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi vel auctor purus. Maecenas eu sodales erat, ac consectetur elit. Donec sit amet nisl efficitur, tincidunt odio non, malesuada magna. Aliquam fringilla ante at nunc tincidunt porttitor.";

    private static final Logger logger = LoggerFactory.getLogger(ReviewTests.class);

    @Test
    public void SuccessfulReviewTest()
    {
        HomePage homePage = new HomePage(driver);
        LogInPage logInPage = homePage.goToLogInPage();
        String email = System.getenv("WH_Email");
        String password = System.getenv("WH_Password");
        logger.info("Logging into account with email " + email);
        homePage = logInPage.logInToUserAccount(email, password);
        if(homePage.isUserLoggedIn())
        {
            logger.info("Logged into account with email " + email);
            driver.navigate().to("https://wallethub.com/profile/13732055i");
            ProfilePage profilePage = new ProfilePage(driver);
            logger.info("Posting review");
            String username = email.substring(0,email.lastIndexOf('@'));
            if(!profilePage.hasReviewByUser(username))
            {
                ReviewCreationPage reviewCreationPage = profilePage.create4StarReview();
                ConfirmReviewPage confirmReviewPage = reviewCreationPage.writeAReview(reviewMessage);
                profilePage = confirmReviewPage.confirmReview();
                try
                {
                    Assert.assertEquals(profilePage.getLatestReviewMessage(), reviewMessage, "The review attempt was " +
                            "not successful");
                    logger.info("Review with message: " + reviewMessage + " - Posted.");
                }
                catch (NoSuchElementException noSuchElementException)
                {
                    logger.error("Successful Post Test Failed");
                    Assert.fail();
                }
            }
            else
            {
                logger.error("A review by the user " + username + " for this profile already exists.");
                Assert.fail();
            }
        }
        else
        {
            logger.error("User couldn't log in.");
            Assert.fail();
        }
    }
}
