package org.geno.com.sauce;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.geno.com.common.UDBase;
import org.geno.com.helpers.*;

/**
 *    Kempt Domain Test Methods
 *
 *
 */
public class iTestCaseKemptSauce extends iSauceBase implements UDBase {

    String lastURL;

    WebDriverWait wait;

    private Kempt_AdminHelper_Client kempt_adminHelper_client;
    private Kempt_HomepageHelper_Client kempt_homepageHelper_client;
    private EmailHelper_Client emailHelper_Client;

///// Sign-up methods

    // UD

    /**
     *  Navigate to the home page and perform a series of checks
     *  to make sure certain links are present
     *
     */
    public void visitKempt(){
        WebDriver client = getDriver();

        // enter Kempt domain name, hit enter, arrive on homepage
        client.get(KEMPT_DOMAIN);
        System.out.println(client.getCurrentUrl());
        Assert.assertTrue(client.getCurrentUrl().equals(KEMPT_DOMAIN),"Kempt URL was redirected somewhere else");
        Assert.assertTrue(client.findElement(By.id("logo")).isDisplayed(),"Logo did not showup");

    }

    /**
     * Share an article via FB
     * PreRequisite: visitKempt()
     *
     */

    public void shareArticleViaFB () {
        //TODO share Kempt articles via FB

    }

    /**
     * Share an article via Twitter
     * PreRequisite: visitKempt()
     *
     */

    public void shareArticleViaTwitter () {
        //TODO share Kempt articles via Twitter

    }


    /**
     * Check the Kempt homepage and sidebar
     * Confirm each link by clicking on it
     *
     */
    public void checkKemptHomepage() throws Exception {
        WebDriver client = getDriver();

        lastURL = client.getCurrentUrl();
        //do all homepage header checks
        kempt_homepageHelper_client = new Kempt_HomepageHelper_Client(client);

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        Assert.assertTrue(kempt_homepageHelper_client.getTopBanner().isDisplayed(),"Sign up button is not displayed.");
//        Assert.assertTrue(kempt_homepageHelper_client.getKemptLogo().isDisplayed(),"Kempt Logo is not displayed.");
//        Assert.assertTrue(kempt_homepageHelper_client.getHomeButton().isDisplayed(),"Home button is not displayed.");
//        Assert.assertTrue(kempt_homepageHelper_client.getSearchButton().isDisplayed(),"Search Button is not displayed.");
//        Assert.assertTrue(kempt_homepageHelper_client.getArchiveButton().isDisplayed(), "Archive Button is not displayed.");
//        Assert.assertTrue(kempt_homepageHelper_client.getPreviousButton().isDisplayed(), "Previous Button is not displayed.");
//        Assert.assertTrue(kempt_homepageHelper_client.getNextButton().isDisplayed(), "Next Button is not displayed.");
//        Assert.assertTrue(kempt_homepageHelper_client.getAboutButton().isDisplayed(), "About Button is not displayed.");
//
//        kempt_homepageHelper_Client.getKemptLogo().click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(kempt_homepageHelper_client.getWaitByLocator("KemptLogo")));
//
//        kempt_homepageHelper_Client.getHomeButton().click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(kempt_homepageHelper_client.getWaitByLocator("KemptLogo")));
//
//        kempt_homepageHelper_Client.getSearchButton().click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(kempt_homepageHelper_client.getWaitByLocator("KemptLogo")));
//
//        kempt_homepageHelper_Client.getArchiveButton().click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(kempt_homepageHelper_client.getWaitByLocator("KemptLogo")));
//
//        kempt_homepageHelper_Client.getPreviousButton().click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(kempt_homepageHelper_client.getWaitByLocator("KemptLogo")));
//
//        kempt_homepageHelper_Client.getNextButton().click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(kempt_homepageHelper_client.getWaitByLocator("KemptLogo")));
//
//        kempt_homepageHelper_Client.getAboutButton().click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(kempt_homepageHelper_client.getWaitByLocator("KemptLogo")));
    }


    /**
     * Sign up for Kempt Updates
     *
     *
     */
    public void signUpKempt(String email) throws Exception {
        WebDriver client = getDriver();

        kempt_homepageHelper_client = new Kempt_HomepageHelper_Client(client);

        //Enter email address

        WebDriverWait waitForBox = new WebDriverWait(client,30);
        waitForBox.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("html.js body.home div.ajaxPopup div.ajaxPopupContent div.signup-bg-top div.signup-bg-bottom div.signup div.flow_page a.closeButton")));

        System.out.println("KEMPT EMAIL CLIENT> " + email);

        //Click OK
//        kempt_homepageHelper_Client.getOKButton().click();

    }

///////////// Sign-up methods end here

    /**
     * Log into the Kempt Admin site
     * This is to create articles like RoundUP
     *
     */
    public void loginKemptAdmin(){
        WebDriver client = getDriver();

        //client.manage().deleteAllCookies();
        client.get(KEMPT_ADMIN_DOMAIN + "/wp-login.php");
        client.findElement(By.id("user_login")).clear();
        client.findElement(By.id("user_login")).sendKeys(KEMPT_ADMIN_USERNAME);
        client.findElement(By.id("user_pass")).clear();
        client.findElement(By.id("user_pass")).sendKeys(KEMPT_ADMIN_PW);
        client.findElement(By.id("wp-submit")).click();
    }

    /**
     * Create a New Post
     *
     */
    public void createNewPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        kempt_adminHelper_client = new Kempt_AdminHelper_Client(client);
        kempt_homepageHelper_client = new Kempt_HomepageHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(KEMPT_ADMIN_DOMAIN+"wp-admin/post-new.php");

        //1. Enter Article Title:
        kempt_adminHelper_client.getPostTitleBox().sendKeys("New Post Test Title "+ date);

        //switch to Text Mode of the editor

        kempt_adminHelper_client.getTextTab().click();

        //2. Enter Main Copy

        kempt_adminHelper_client.getPostMainCopyBox().sendKeys("New Post Test Main Copy " + date);

          //2.1 Link Copy
            //a. Select Text just entered

        kempt_adminHelper_client.getPostMainCopyBox().sendKeys(Keys.chord(Keys.CONTROL, "a"));

            //b. Click Link button of the editor

        kempt_adminHelper_client.getLinkButton().click();

        //wait for Insert/edit link Dialog to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(kempt_adminHelper_client.getWaitByLocator("ArticleMainCopyLinkModalTitle")));
            //c. Enter URL
        kempt_adminHelper_client.getURLBox().clear();
        kempt_adminHelper_client.getURLBox().sendKeys("http://www.bbc.com");

            //d. Enter Title
        kempt_adminHelper_client.getTitleBox().clear();
        kempt_adminHelper_client.getTitleBox().sendKeys("New Kempt Post Title " + date);

            //e. Check "Open link in a new window/tab" box
        kempt_adminHelper_client.getOLINWCheck().click();

            //f. Click "Add Link" button
        kempt_adminHelper_client.getAddLinkButton().click();

        //4. Tag it
            //a. enter text in Tag box
        kempt_adminHelper_client.getTagBox().sendKeys("Kempt Test Tag " + date);
            //b. click Add button
        kempt_adminHelper_client.getTagAddButton().click();

        //5. Click Add Media  button
        kempt_adminHelper_client.getAddMediaButton().click();
        //wait for Insert Media Dialog to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(kempt_adminHelper_client.getWaitByLocator("AddMediaDialogTitle")));

        // Click Upload Files   AddMediaDialogTitle
        //Click Select Files

        //6. Select your image
        client.findElement(By.cssSelector(".media-router>.media-menu-item:nth-child(2)")).click();

        kempt_adminHelper_client.getImageThumbnail().get(0).click();
        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".attachment.save-ready.details.selected")));
        // save image name
        //String imageName = kempt_adminHelper_client.getImageNameLabel().getText();

        //7. Click Insert into post
        kempt_adminHelper_client.getInsertIntoPostButton().click();

        //3. Select Category: Across the Sea
        kempt_adminHelper_client.getCategoryAcrossTheSeaSelect().click();

        //9. Click Publish
        kempt_adminHelper_client.getPublishButton().click();

        //View Published Post
        //go to home page
        client.get(KEMPT_ADMIN_DOMAIN);

        //wait for Kempt Logo to show up
        wait.until(ExpectedConditions.presenceOfElementLocated(kempt_homepageHelper_client.getWaitByLocator("KemptLogo")));
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }
}
