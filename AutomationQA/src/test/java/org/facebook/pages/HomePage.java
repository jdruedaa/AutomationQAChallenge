package org.facebook.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private final By profileNavigationButton = By.cssSelector("[data-pagelet=\"LeftRail\"] a[href*=\"profile\"]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage goToProfilePage()
    {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        explicitWait.until(ExpectedConditions.elementToBeClickable(profileNavigationButton)).click();
        return new ProfilePage(driver);
    }
}
