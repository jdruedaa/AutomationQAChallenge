package org.facebook.tests.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.facebook.pages.HomePage;
import org.facebook.pages.LogInPage;
import org.facebook.pages.ProfilePage;
import org.facebook.utils.Hooks;

import java.util.List;
import java.util.NoSuchElementException;

public class PostTests extends Hooks {

    private final String statusMessage = "Hello World";

    private static final Logger logger = LoggerFactory.getLogger(PostTests.class);

    @Test
    public void SuccessfulStatusPostTest()
    {
        LogInPage logInPage = new LogInPage(driver);
        String username = System.getenv("FB_Email");
        String password = System.getenv("FB_Password");
        logger.info("Logging into account with email " + username);
        HomePage homePage = logInPage.logInToUserAccount(username, password);
        logger.info("Logged into account with email " + username);
        ProfilePage profilePage = homePage.goToProfilePage();
        logger.info("Posting status with message " + statusMessage + " ...");
        profilePage = profilePage.createStatusPost(statusMessage);
        try
        {
            Assert.assertEquals(profilePage.getLatestPostMessage(), statusMessage, "The post attempt was " +
                    "not successful");
            logger.info("Status with message: " + statusMessage + " - Posted.");
            logger.info("Deleting latest post with message: " + statusMessage + " ...");
            profilePage.eraseLatestPost();
            logger.info("Latest post with message: " + statusMessage + " - Deleted.");
        }
        catch (NoSuchElementException noSuchElementException)
        {
            System.out.println("Successful Post Test Failed");
        }
    }
}
