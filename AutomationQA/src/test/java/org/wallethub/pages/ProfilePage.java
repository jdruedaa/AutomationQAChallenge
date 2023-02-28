package org.wallethub.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProfilePage extends BasePage {

    private final By fourthStarReviewButton = By.cssSelector(".rv.review-action.ng-enter-element [aria-label=\"4 star rating\"]");

    private final By reviewMessages = By.cssSelector(".rvtab-content article [itemprop=\"description\"]");

    private final By reviewUsers = By.cssSelector(".rvtab-content article .rvtab-ci-nickname");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ReviewCreationPage create4StarReview()
    {
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        WebElement fourthStarReviewButtonWE = explicitWait.until(ExpectedConditions.presenceOfElementLocated(fourthStarReviewButton));
        new Actions(driver)
                .scrollToElement(fourthStarReviewButtonWE);
        fourthStarReviewButtonWE = explicitWait.until(ExpectedConditions.elementToBeClickable(fourthStarReviewButton));
        fourthStarReviewButtonWE.click();
        //Color fillColor = Color.fromString(fourthStarReviewButtonWE.findElements(By.cssSelector("path")).get(0).getCssValue("fill"));
        //System.out.println(fillColor.toString());
        return new ReviewCreationPage(driver);
    }

    public String getLatestReviewMessage()
    {
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        List<WebElement> reviewMessagesWEs = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(reviewMessages));
        return reviewMessagesWEs.get(0).getText();
    }

    public boolean hasReviewByUser(String username)
    {
        boolean userReviewed = false;
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> reviewUsersWEs = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(reviewUsers));
        if(reviewUsersWEs.get(0).getText().contains(username))
        {
            userReviewed = true;
        }
        return userReviewed;
    }
}
