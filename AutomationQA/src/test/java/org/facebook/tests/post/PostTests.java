package org.facebook.tests.post;

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
    @Test
    public void SuccessfulStatusPostTest()
    {
        LogInPage logInPage = new LogInPage(driver);
        String username = System.getenv("FB_Email");
        String password = System.getenv("FB_Password");
        HomePage homePage = logInPage.logInToUserAccount(username, password);
        ProfilePage profilePage = homePage.goToProfilePage();
        profilePage = profilePage.createStatusPost(statusMessage);
        try
        {
            Assert.assertEquals(profilePage.getLatestPostMessage(), statusMessage, "The post attempt was " +
                    "not successful");
            profilePage.eraseLatestPost();
        }
        catch (NoSuchElementException noSuchElementException)
        {
            System.out.println("Successful Post Test Failed");
        }
    }
}
