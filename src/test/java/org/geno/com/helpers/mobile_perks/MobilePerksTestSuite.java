package org.geno.com.helpers.mobile_perks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.geno.com.sauce.iLocalMobileBase;

import java.util.concurrent.TimeUnit;

/**
 * By extending iLocalBase this suite will run a local Firefox instance
 */
public class MobilePerksTestSuite extends iLocalMobileBase {

    WebDriver driver;
    static final String url = "http://perks-scratch1.thedaddy.co";

    @Test
    public void checkAllCitiesFromDropDown() {
        driver = getDriver();

        driver.get(url);

        MobilePerksHomePage homePage = PageFactory.initElements(driver,MobilePerksHomePage.class);

        homePage.header.clickEditionDropdown();
        homePage.header.clickBostonEdition();
        Assert.assertEquals(homePage.header.getCurrentEdition(),"Boston");

        homePage.header.clickEditionDropdown();
        homePage.header.clickNewYorkEdition();
        Assert.assertEquals(homePage.header.getCurrentEdition(),"New York");

        homePage.header.clickEditionDropdown();
        homePage.header.clickChicagoEdition();
        Assert.assertEquals(homePage.header.getCurrentEdition(),"Chicago");

        homePage.header.clickEditionDropdown();
        homePage.header.clickMiamiEdition();
        Assert.assertEquals(homePage.header.getCurrentEdition(),"Miami");

        homePage.header.clickEditionDropdown();
        homePage.header.clickWashingtonDCEdition();
        Assert.assertEquals(homePage.header.getCurrentEdition(),"Washington DC");

        homePage.header.clickEditionDropdown();
        homePage.header.clickLosAngelesEdition();
        Assert.assertEquals(homePage.header.getCurrentEdition(),"Los Angeles");

        homePage.header.clickEditionDropdown();
        homePage.header.clickSanFranciscoEdition();
        Assert.assertEquals(homePage.header.getCurrentEdition(),"San Francisco");

        homePage.header.clickEditionDropdown();
        homePage.header.clickNationalEdition();
        Assert.assertEquals(homePage.header.getCurrentEdition(),"National");

    }

    @Test
    public void expandProductDetails() {
        driver = getDriver();

        driver.get(url);

        MobilePerksHomePage homePage = PageFactory.initElements(driver,MobilePerksHomePage.class);

        MobilePerksProductPage pp = homePage.clickGetItNow(0);
        pp.clickLogisticsAccordion();
        System.out.println(pp.getLogisticsAccordionText());

    }

    @Test
    public void expandFinePrintDetails() {
        driver = getDriver();

        driver.get(url);

        MobilePerksHomePage homePage = PageFactory.initElements(driver,MobilePerksHomePage.class);

        MobilePerksProductPage pp = homePage.clickGetItNow(0);
        pp.clickFinePrintAccordion();
        System.out.println(pp.getFinePrintAccordionText());

    }

    @Test
    public void loginFromProductPageTest() {
        driver = getDriver();

        driver.get(url);

        MobilePerksHomePage homePage = PageFactory.initElements(driver,MobilePerksHomePage.class);

        MobilePerksProductPage pp = homePage.clickGetItNow(0);

        final String currentUrl = driver.getCurrentUrl();

        MobilePerksLoginPage login = pp.clickGetItNowLoggedOut();

        login.enterEmail(JENKINSEMAIL);
        login.enterPw(PASSWORD);
        login.clickSigninButton();

        //Always set implict wait to 0 before using Explicit wait
        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver,12,3000);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().equals(currentUrl);  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE,TimeUnit.SECONDS);

        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), currentUrl);
    }

    @Test
    public void loginFromHomePage() {
        driver = getDriver();

        driver.get(url);

        MobilePerksHomePage homePage = PageFactory.initElements(driver,MobilePerksHomePage.class);

        final String currentUrl = driver.getCurrentUrl();

        MobilePerksLoginPage login =  homePage.header.clickLoginButton();

        login.enterEmail(JENKINSEMAIL);
        login.enterPw(PASSWORD);
        login.clickSigninButton();

        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver,12,3000);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().equals(currentUrl);  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        Assert.assertEquals(driver.getCurrentUrl(), currentUrl);
    }

}
