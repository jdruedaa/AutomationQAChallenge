package org.wallethub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewCreationPage extends BasePage {

    private final By policyDropDownMenu = By.cssSelector(".md-write-a-review .dropdown-placeholder");

    private final By reviewTextInputField = By.cssSelector(".android.textarea-user textarea");

    private final By submitReviewButton = By.cssSelector(".sub-nav-ct .sbn-action");

    public ReviewCreationPage(WebDriver driver) {
        super(driver);
    }

    public ConfirmReviewPage writeAReview(String review)
    {
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        WebElement policyDropDownMenuWE = explicitWait.until(ExpectedConditions.elementToBeClickable(policyDropDownMenu));
        policyDropDownMenuWE.click();
        explicitWait.until(ExpectedConditions.attributeToBe(policyDropDownMenuWE,"aria-expanded","true"));
        List<WebElement> policyOptionsWEs = driver.findElements(By.cssSelector(".md-write-a-review [role=\"option\"]"));
        WebElement healthInsuranceOption = policyOptionsWEs.stream()
                .filter(policyWE -> policyWE.getText().contains("Health Insurance")).toList().get(0);
        explicitWait.until(ExpectedConditions.elementToBeClickable(healthInsuranceOption));
        healthInsuranceOption.click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reviewTextInputField)).sendKeys(review);
        WebElement submitReviewButtonWE = explicitWait.until(ExpectedConditions.elementToBeClickable(submitReviewButton));
        submitReviewButtonWE.click();
        explicitWait.until(ExpectedConditions.stalenessOf(submitReviewButtonWE));
        return new ConfirmReviewPage(driver);
    }
}
