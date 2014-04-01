package org.geno.tests.phantom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UD_HomePageChecksTest {

    public UD_HomePageChecksTest() {
    }

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "/Users/sargenziano/Development/phantomjs/bin/phantomjs");

        driver = new PhantomJSDriver(caps);
    }

    @Test
    public void triggerSubscriptionModal() {

        Reporter.log("Visiting UD and setting cookies naturally",true);
        driver.get("http://ud-branch.thedaddy.co");

        Reporter.log("Checking for image", true);
        Assert.assertFalse(driver.findElement(By.cssSelector(".splashMainHead>img")).isDisplayed(), "Main image is there");

    }

    @AfterMethod (alwaysRun = true)
    public void breakdown() {
        driver.quit();
    }

}
