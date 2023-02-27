package org.facebook.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage extends BasePage {

    //CSS option choose first element: "[data-pagelet="ProfileComposer"] [role="button"]"
    private final By postContentButtons = By.cssSelector("[data-pagelet=\"ProfileComposer\"] [role=\"button\"]");
    private final By createPostTextInput = By.cssSelector("[method=\"POST\"] [role=\"textbox\"]");
    private final By submitPostFormButton = By.cssSelector("[method=\"POST\"] [aria-label=\"Post\"]");
    private final By postMessages = By.cssSelector(".x11i5rnm.xat24cr.x1mh8g0r.x1vvkbs.xdj266r");

    private final By postThreeDotsButton = By.cssSelector("[aria-label=\"Actions for this post\"]");

    private final By postActions = By.cssSelector("[role=\"menu\"] .xu06os2.x1ok221b [dir=\"auto\"]");

    private final By confirmMoveToTrashButton = By.cssSelector("[aria-label=\"Move\"]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage createStatusPost(String statusMessage)
    {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement postButton = explicitWait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(postContentButtons, 1)).get(0);
        explicitWait.until(ExpectedConditions.elementToBeClickable(postButton)).click();
        WebElement postTextInput = explicitWait.until(ExpectedConditions.presenceOfElementLocated(createPostTextInput));
        postTextInput.sendKeys(statusMessage);
        explicitWait.until(ExpectedConditions.elementToBeClickable(submitPostFormButton)).click();
        explicitWait.until(ExpectedConditions.stalenessOf(postTextInput));
        return new ProfilePage(driver);
    }

    public String getLatestPostMessage()
    {
        String message = "";
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        List<WebElement> allPostsMessages = explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(postMessages,0));
        new Actions(driver)
                .scrollToElement(allPostsMessages.get(0))
                .perform();
        message = allPostsMessages.get(0).getText();
        return message;
    }

    public void eraseLatestPost()
    {
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        List<WebElement> allPostsThreeDots = explicitWait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(postThreeDotsButton,1));
        new Actions(driver)
                .scrollToElement(allPostsThreeDots.get(0))
                .perform();
        allPostsThreeDots.get(0).click();
        List<WebElement> allPostActions = explicitWait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(postActions,1));
        WebElement currentElement;
        int indexStartingAtEnd = allPostActions.size() - 1;
        while(indexStartingAtEnd >= 0)
        {
            currentElement = allPostActions.get(indexStartingAtEnd);
            if(currentElement.getText().contains("trash"))
            {
                currentElement.click();
                break;
            }
            indexStartingAtEnd--;
        }
        explicitWait.until(ExpectedConditions.elementToBeClickable(confirmMoveToTrashButton)).click();
    }
}
