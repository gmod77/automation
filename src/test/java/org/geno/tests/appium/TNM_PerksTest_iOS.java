package org.geno.tests.appium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.geno.com.appium.iTestCaseTNMAppium_iOS;
import org.geno.com.helpers.mobile_perks.MobilePerksHeader;
import org.geno.com.helpers.mobile_perks.MobilePerksHomePage;


/**
 * Created with IntelliJ IDEA.
 * User: gmodin
 * Date: 7/11/13
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class TNM_PerksTest_iOS extends iTestCaseTNMAppium_iOS {

    WebDriver driver;
    static final String url = "http://geno-release.thedaddy.co/vacationsupremacy";
    static final int DEFAULT_WAIT_4_PAGE = 12;


    @Test
    public void startApp() throws Exception{

        driver = getAppiumDriver();

        waitForInterstitialToEnd();
        checkForRatingDialog();
        checkForAreYouAMemberDialog();

        engageDock();
        selectCity("New York");

        engageDock();
        selectPerks();

        switchToWebView();

        MobilePerksHomePage homePage = PageFactory.initElements(driver,MobilePerksHomePage.class);
        Assert.assertEquals(homePage.header.getCurrentEdition(),"New York");

        MobilePerksHomePage page = PageFactory.initElements(driver, MobilePerksHomePage.class);
        page.printPerkList();

//        ((RemoteWebDriver) driver).executeScript("mobile.leaveWebView");
//        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().doubleTap({x:278.00, y:33.00})");


        MobilePerksHeader header = PageFactory.initElements(driver, MobilePerksHeader.class);
        header.clickMenue();

        //clickNav();

        //switchFromWebView(); g



    }
}
