package org.wallethub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage extends BasePage {

    private final By emailTextInputField = By.id("email");

    private final By passwordTextInputField = By.id("password");

    private final By submitLogInFormButton = By.cssSelector(".btns span[ng-if=\"!pending\"]");

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public HomePage logInToUserAccount(String username, String password)
    {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(emailTextInputField).sendKeys(username);
        driver.findElement(passwordTextInputField).sendKeys(password);
        WebElement submitLogInFormButtonWE = explicitWait.until(ExpectedConditions.elementToBeClickable(submitLogInFormButton));
        submitLogInFormButtonWE.click();
        explicitWait.until(ExpectedConditions.stalenessOf(submitLogInFormButtonWE));
        return new HomePage(driver);
    }
}
