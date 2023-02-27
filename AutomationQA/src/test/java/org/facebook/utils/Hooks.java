package org.facebook.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Hooks {
    protected WebDriver driver;

    @BeforeMethod
    public void setWebDriver()
    {
        ChromeOptions op = new ChromeOptions();
        //disable notification parameter
        op.addArguments("--disable-notifications");
        // configure options parameter to Chrome driver
        driver = new ChromeDriver(op);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.facebook.com");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
