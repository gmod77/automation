package org.geno.com.pageObjects.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.geno.com.pageObjects.perks.PerksHomePage;
import org.geno.com.pageObjects.perks.PerksSignUp;

import java.util.concurrent.TimeUnit;


public class PerksLandingPageTest {

    public static final int DEFAULT_WAIT_4_PAGE = 12;

    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS);

        driver.get("http://perks-release.thedaddy.co/");

        PerksHomePage page = PageFactory.initElements(driver, PerksHomePage.class);

        Assert.assertEquals(page.header.getBreadCrumbText(),"New York");
        System.out.println("\nSuccessfully navigated to> " + page.header.getBreadCrumbText());

        Assert.assertTrue(page.header.perksLinkActive());

        System.out.println(page.header.getPromotionalHeaderText());

        //Navigating to National Now
        PerksHomePage newPage = page.footer.clickPerkEditionLink(1);

        Assert.assertEquals(newPage.header.getBreadCrumbText(),"National");
        System.out.println("\nSuccessfully navigated to> " + newPage.header.getBreadCrumbText());

        newPage.header.getBreadCrumbText();

        PerksSignUp signUp = page.header.clickSignUpLink();

        signUp.enterSignUpEmail("qa.test+1424k238@geno.com");
        //Assert.assertTrue(signUp.over21boxIsChecked());

        //signUp.clickOver21Box();

        signUp.clickAcceptButton();

        signUp.clickEdition("Chicago Perks",true);

        signUp.clickMoreEditions();

        signUp.clickEdition("Chicago",false);

        signUp.clickEdition("Driven",false);

        signUp.clickEditionsSubmitButton();

        signUp.enterPassword("12345");
        signUp.reEnterPassword("12345");
        signUp.enterFirstName("Joe");
        signUp.enterLastName("Schmoe");
        signUp.selectGender(1);
        signUp.selectIncome(2);
        signUp.enterBirthday("06/01/1999");
        signUp.enterZip("12345");

        //signUp.clickEditionsSubmitButton();

        //driver.quit();

    }


}
