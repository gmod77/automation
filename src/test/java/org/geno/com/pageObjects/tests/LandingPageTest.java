package org.geno.com.pageObjects.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.geno.com.pageObjects.udweb.UdwebLandingPage;

/**
 * Proof of concept for PageFactory tests
 * Uses main method as opposed to TestNG
 */
public class LandingPageTest {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://geno-release.thedaddy.co/");

        UdwebLandingPage page = PageFactory.initElements(driver, UdwebLandingPage.class);

        page.getUrl();

        page.getPageTitle();

        page.getLinkElementCount();

        Assert.assertTrue(page.checkSignUpSealDisplayed());

        page.clickSignUpSeal();

        driver.quit();

    }


}
