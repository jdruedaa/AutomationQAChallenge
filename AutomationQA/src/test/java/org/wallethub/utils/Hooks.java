package org.wallethub.utils;

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
        driver = new ChromeDriver(op);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.wallethub.com");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
