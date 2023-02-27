package org.facebook.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LogInPage extends BasePage {

    private final By emailTextInputField = By.id("email");

    private final By passwordTextInputField = By.id("pass");

    private final By submitLogInFormButton = By.cssSelector("button[type=\"submit\"]");

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public HomePage logInToUserAccount(String username, String password)
    {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(emailTextInputField).sendKeys(username);
        driver.findElement(passwordTextInputField).sendKeys(password);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(submitLogInFormButton)).click();
        return new HomePage(driver);
    }
}
