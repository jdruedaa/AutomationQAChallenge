package org.wallethub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmReviewPage extends BasePage {

    private final By confirmReviewButton = By.cssSelector(".btn.rvc-continue-btn");

    public ConfirmReviewPage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage confirmReview()
    {
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        explicitWait.until(ExpectedConditions.elementToBeClickable(confirmReviewButton)).click();
        return new ProfilePage(driver);
    }
}
