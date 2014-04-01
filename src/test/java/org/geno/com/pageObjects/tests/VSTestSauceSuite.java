package org.geno.com.pageObjects.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.geno.com.helpers.EmailHelper_Client;
import org.geno.com.pageObjects.VacationSupremacy.VSEntrancePage;
import org.geno.com.pageObjects.VacationSupremacy.VSInviteFriends;
import org.geno.com.pageObjects.VacationSupremacy.VSThankYouPage;
import org.geno.com.sauce.iSauceBase;

/**
 * By extending iSauceBase this suite will run a remote FireFox session on Sauce Labs
 */
public class VSTestSauceSuite extends iSauceBase {

    WebDriver driver;
    static final String url = "http://geno-release.thedaddy.co/vacationsupremacy";

    @Test
    public void simpleSignupTest() {
        driver = getDriver();

        driver.get(url);
        EmailHelper_Client emailHelper = new EmailHelper_Client(driver);
        String email = emailHelper.generateEmailClient();
        VSEntrancePage vsEntrancePage = PageFactory.initElements(driver, VSEntrancePage.class);

        vsEntrancePage.enterEmail(email);
        vsEntrancePage.enterZip("10001");
        vsEntrancePage.checkOptIn();

        VSInviteFriends vsInviteFriends = vsEntrancePage.clickCountMeInButton();

        VSThankYouPage vsThankYouPage = vsInviteFriends.clickSkip();

        //System.out.println(vsThankYouPage.getUrl());
        Assert.assertTrue(vsThankYouPage.getSuccessText().contains("SUCCESS"));
        //System.out.println(vsThankYouPage.getSuccessText());
    }

    @Test
    public void friendInviteSignupTest() {
        driver = getDriver();

        driver.get(url);
        EmailHelper_Client emailHelper = new EmailHelper_Client(driver);
        String email = emailHelper.generateEmailClient();
        VSEntrancePage vsEntrancePage = PageFactory.initElements(driver, VSEntrancePage.class);

        vsEntrancePage.enterEmail(email);
        vsEntrancePage.enterZip("10001");
        vsEntrancePage.checkOptIn();

        VSInviteFriends vsInviteFriends = vsEntrancePage.clickCountMeInButton();

        vsInviteFriends.enterFriendEmail(0,emailHelper.generateEmailClient());
        vsInviteFriends.enterFriendEmail(1,emailHelper.generateEmailClient());
        vsInviteFriends.enterFriendEmail(2,emailHelper.generateEmailClient());
        vsInviteFriends.enterFriendEmail(3,emailHelper.generateEmailClient());

        VSThankYouPage vsThankYouPage = vsInviteFriends.clickInvite();

        //System.out.println(vsThankYouPage.getUrl());

        Assert.assertTrue(vsThankYouPage.getSuccessText().contains("SUCCESS"));
        //System.out.println(vsThankYouPage.getSuccessText());
    }

    @Test
    public void invalidEmailErrorTest() {
        driver = getDriver();

        driver.get(url);

        VSEntrancePage vsEntrancePage = PageFactory.initElements(driver, VSEntrancePage.class);

        vsEntrancePage.enterEmail("oogabooga");
        vsEntrancePage.enterZip("11111");
        vsEntrancePage.clickCountMeInButton();

        Assert.assertEquals(vsEntrancePage.emailErrorText(),"Please enter a valid email address.");
    }

    @Test
    public void missingEmailErrorTest() {
        driver = getDriver();

        driver.get(url);

        VSEntrancePage vsEntrancePage = PageFactory.initElements(driver, VSEntrancePage.class);

        vsEntrancePage.enterZip("11111");
        vsEntrancePage.clickCountMeInButton();

        Assert.assertEquals(vsEntrancePage.emailErrorText(),"You must enter an email address to enter.");
    }

    @Test
    public void emailFieldGlowTest() {
        driver = getDriver();

        driver.get(url);

        VSEntrancePage vsEntrancePage = PageFactory.initElements(driver, VSEntrancePage.class);

        Assert.assertTrue(vsEntrancePage.emailAddressGlow());
    }

    @Test
    public void zipFieldGlowTest() {
        driver = getDriver();

        driver.get(url);

        VSEntrancePage vsEntrancePage = PageFactory.initElements(driver, VSEntrancePage.class);

        Assert.assertTrue(vsEntrancePage.zipCodeGlow());
    }

    @Test
    public void missingZipCodeTest() {
        driver = getDriver();

        driver.get(url);

        VSEntrancePage vsEntrancePage = PageFactory.initElements(driver, VSEntrancePage.class);

        vsEntrancePage.enterEmail("the.test@gmail.com");
        vsEntrancePage.clickCountMeInButton();

        Assert.assertEquals(vsEntrancePage.emailErrorText(),"You must enter a zip code to enter.");
        Assert.assertTrue(vsEntrancePage.emailAddressGlow());
    }

    @Test
    public void invalidZipCodeTest() {
        driver = getDriver();

        driver.get(url);

        VSEntrancePage vsEntrancePage = PageFactory.initElements(driver, VSEntrancePage.class);

        vsEntrancePage.enterEmail("the.test@gmail.com");
        vsEntrancePage.enterZip("ABCED");

        vsEntrancePage.clickCountMeInButton();

        Assert.assertEquals(vsEntrancePage.emailErrorText(),"Please enter valid domestic zip code like 10001 or 10001-1234.");
        Assert.assertTrue(vsEntrancePage.zipCodeGlow());
    }

    @Test
    public void inviteFriendGlowTest() {
        driver = getDriver();

        driver.get(url);
        EmailHelper_Client emailHelper = new EmailHelper_Client(driver);
        String email = emailHelper.generateEmailClient();
        VSEntrancePage vsEntrancePage = PageFactory.initElements(driver, VSEntrancePage.class);

        vsEntrancePage.enterEmail(email);
        vsEntrancePage.enterZip("10001");
        vsEntrancePage.checkOptIn();

        VSInviteFriends vsInviteFriends = vsEntrancePage.clickCountMeInButton();

        Assert.assertTrue(vsInviteFriends.checkFriendEmailGlow(0));
        Assert.assertTrue(vsInviteFriends.checkFriendEmailGlow(1));
        Assert.assertTrue(vsInviteFriends.checkFriendEmailGlow(2));
        Assert.assertTrue(vsInviteFriends.checkFriendEmailGlow(3));
    }

}
