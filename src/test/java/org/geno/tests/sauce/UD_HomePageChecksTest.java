package org.geno.tests.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseUDSauce;

public class UD_HomePageChecksTest extends iTestCaseUDSauce {

    public UD_HomePageChecksTest() {
    }

    @Test (groups = {"Regression"})
    public void cityHomePageChecksLoggedOut() {
        getSauceResultsUrl();

        Reporter.log("Visiting Home Page for the first time", true);
        visitUDFirstTime();

        Reporter.log("Access New York from the UD Homepage", true);
        accessNewYorkFromUDHomepage();

        Reporter.log("Doing Header Checks", true);
        checkUDHomepageHeader();

        Reporter.log("Spidering Header Links", true);
        spiderUDHomepageLinks();

        Reporter.log("Doing Footer Checks", true);
        checkUDHomepageFooter();
    }

    @Test (groups = {"Regression"})
    public void cityHomePageChecksLoggedIn() {
        getSauceResultsUrl();

        Reporter.log("Visiting Home Page for the first time", true);
        visitUDFirstTime();

        Reporter.log("Access New York from the UD Homepage", true);
        accessNewYorkFromUDHomepage();

        Reporter.log("Logging into site", true);
        loginUD(JENKINSEMAIL,PASSWORD);

        Reporter.log("Doing Header Checks", true);
        checkUDHomepageHeader();

        Reporter.log("Spidering Header Links", true);
        spiderUDHomepageLinks();

        Reporter.log("Doing Footer Checks", true);
        checkUDHomepageFooter();
    }

    @Test (groups = {"Regression"})
    public void nationalHomePageChecksLoggedOut() {
        getSauceResultsUrl();

        Reporter.log("Visiting Home Page for the first time", true);
        visitUDFirstTime();

        Reporter.log("Access New York from the UD Homepage", true);
        accessNationalFromUDHomepage();

        Reporter.log("Doing Header Checks", true);
        checkUDHomepageHeader();

        Reporter.log("Spidering Header Links", true);
        spiderUDHomepageLinks();

        Reporter.log("Doing Footer Checks", true);
        checkUDHomepageFooter();
    }

    @Test
    public void triggerSubscriptionModal() {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        Reporter.log("Visiting UD and setting cookies naturally",true);
        client.get(UD_DOMAIN);

        Reporter.log("Navigating 3 links to generate popup",true);
        generateSignupModal();

        Reporter.log("Running modal popup asserts",true);
        Assert.assertTrue(client.findElement(By.cssSelector(".modalContent")).isDisplayed(), "The join modal window didn't show");
        Assert.assertTrue(client.findElement(By.cssSelector(".inputContainer>#email")).isDisplayed(),"Email input is not displayed");
        Assert.assertTrue(client.findElement(By.cssSelector(".modalContent>p")).getText().contains("Sign up for a free membership"), "Sign up text is missing");

        Reporter.log("Popup success",true);
    }

}
