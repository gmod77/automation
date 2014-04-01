package org.geno.tests.sauce;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCasePerksSauce;

import java.util.List;

public class Perks_HotmailPagesRegressionTest extends iTestCasePerksSauce {
//    national
//    new-york
//    chicago
//    boston
//    washington-dc
//    los-angeles

//    Object[][] perkEditions;
//    List<String> links;
//
//
//    @DataProvider(name = "perks-list")
//    public Object[][] getPerks(ITestContext context) {
//
//        perkEditions = new Object[links.size()][];
//        for (int i = 0; i<links.size();i++) {
//            perkEditions[i] = new Object[] {context.getCurrentXmlTest().getParameter("city"),links.get(i)};
//        }
//
//        return perkEditions;
//    }

    String bannerLink;
    private void setBannerLink(String link) {
        this.bannerLink = link;
    }

    @Parameters ({"city"})
    @Test (groups = {"Start"})
    public void perksLandingPage(String city) throws Exception {
        WebDriver client = getDriver();

        WebDriverWait wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        getSauceResultsUrl();

        Reporter.log("Opening Perks City:" + city,true);
        visitPerksFirstTime(city);

        Reporter.log("Checking banner display", true);
        Assert.assertTrue(client.findElement(By.id("promotion-banner"))
                .isDisplayed(),"Banner is not displayed");
        WebElement banner = client.findElement(By.id("promotion-banner"));

        Reporter.log("Confirming banner text displayed",true);
        Reporter.log(banner.getText(),true);
        Assert.assertTrue(banner.getText()
                .equals("Use Outlook.com. Get More Out of Perks. Get Going Here.\nOutlook.com Is the Official Inbox of Perks.")
                ,"Banner text is missing");

        setBannerLink(banner.findElement(By.tagName("a")).getAttribute("href"));

        Reporter.log("Clicking banner", true);
        banner.findElement(By.tagName("a")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".category-image>img")));

        Reporter.log("Page loaded test finished", true);

    }

    @Test (dependsOnGroups = {"Start"})
    public void hotmailPerkLandingPage() throws Exception {
        WebDriver client = getDriver();

        WebDriverWait wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        getSauceResultsUrl();

        Reporter.log("Opening to Hotmail Promo page",true);
        client.get(bannerLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".category-image>img")));

        Assert.assertTrue(client.findElement(By.cssSelector(".top>.copy>h1")).getText()
                .equals("Use Outlook.com. Get More Out of Perks."),"Copy header text incorrect/changed.");

        Assert.assertTrue(client.findElement(By.cssSelector(".top>.copy>a"))
                .isDisplayed(),"Link to sign up in copy is missing");

        List<WebElement> hotmailPerks = client.findElement(By.className("edition-blocks")).findElements(By.cssSelector(".block"));

        for (WebElement hotmailPerk : hotmailPerks) {
            if (!hotmailPerk.getAttribute("class").contains("no_link")) {
                Reporter.log("Hotmail Perk found> " + hotmailPerk.findElement(By.cssSelector(".block-inner>.block-content>div>.copy>p")).getText(),true);
            }
        }

    }

//    @Test (dataProvider = "perks-list", groups = {"PerksRegression"},dependsOnGroups = {"Start"})
//    public void perksPageRegressionDataDriven(String city,String pk) throws Exception {
//
//        Reporter.log("SauceResultsUrl> " + getResultsUrl(getSessionId()));
//
//        checkPerkPage(pk);
//
//    }

}
