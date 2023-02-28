package org.wallethub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private final By navigateToLogInButton = By.cssSelector(".login-join [href*=\"login\"]");

    private final By loggedUserNameBurgerMenu = By.cssSelector(".brgm-button.brgm-user .brgm-list-title");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LogInPage goToLogInPage()
    {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        explicitWait.until(ExpectedConditions.elementToBeClickable(navigateToLogInButton)).click();
        return new LogInPage(driver);
    }

    public boolean isUserLoggedIn()
    {
        boolean userLoggedIn = true;
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try
        {
            explicitWait.until(ExpectedConditions.presenceOfElementLocated(loggedUserNameBurgerMenu));
        }
        catch (NoSuchElementException e)
        {
            userLoggedIn = false;
        }
        return userLoggedIn;
    }
}
