package org.geno.com.sauce;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.geno.com.common.UDBase;
import org.geno.com.helpers.*;

import java.util.List;

/**
 *    Driven Domain Test Methods
 *
 *
 */
public class iTestCaseDrivenSauce extends iSauceBase implements UDBase {

    String lastURL;

    WebDriverWait wait;

    private Driven_SignupHelper_Client driven_signupHelper_client;
    private Driven_HomepageHelper_Client driven_homepageHelper_Client;
    private Driven_AdminHelper_Client driven_adminHelper_client;
    private EmailHelper_Client emailHelper_Client;

///// Sign-up methods

    // UD

    /**
     *  Navigate to the home page and perform a series of checks
     *  to make sure certain links are present
     *
     */
    public void visitDriven(){
        WebDriver client = getDriver();

        // enter Driven domain name, hit enter, arrive on homepage
        client.get(DRIVEN_DOMAIN);
        System.out.println(client.getCurrentUrl());
        Assert.assertTrue(client.getCurrentUrl().equals(DRIVEN_DOMAIN),"Driven URL was redirected somewhere else");
        Assert.assertTrue(client.findElement(By.id("logo")).isDisplayed(),"Logo did not showup");

    }

    /**
     * Share an article via FB
     * PreRequisite: visitDriven()
     *
     */

    public void shareArticleViaFB () {
        //TODO share Driven articles via FB

    }

    /**
     * Share an article via Twitter
     * PreRequisite: visitDriven()
     *
     */

    public void shareArticleViaTwitter () {
        //TODO share Driven articles via Twitter

    }


    /**
     * Check the Driven homepage and sidebar
     * Confirm each link by clicking on it
     *
     */
    public void checkDrivenHomepage() throws Exception {
        WebDriver client = getDriver();

        lastURL = client.getCurrentUrl();
        //do all homepage header checks
        driven_homepageHelper_Client = new Driven_HomepageHelper_Client(client);

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        Reporter.log("Running asserts on homepage");
        Assert.assertTrue(driven_homepageHelper_Client.getSignUpButton().isDisplayed(),"Sign up button is not displayed.");
        Assert.assertTrue(driven_homepageHelper_Client.getDrivenLogo().isDisplayed(),"Driven Logo is not displayed.");
        Assert.assertTrue(driven_homepageHelper_Client.getHomeButton().isDisplayed(),"Home button is not displayed.");
        Assert.assertTrue(driven_homepageHelper_Client.getSearchButton().isDisplayed(),"Search Button is not displayed.");
        Assert.assertTrue(driven_homepageHelper_Client.getArchiveButton().isDisplayed(), "Archive Button is not displayed.");
        Assert.assertTrue(driven_homepageHelper_Client.getPreviousButton().isDisplayed(), "Previous Button is not displayed.");
        Assert.assertTrue(driven_homepageHelper_Client.getNextButton().isDisplayed(), "Next Button is not displayed.");
        Assert.assertTrue(driven_homepageHelper_Client.getAboutButton().isDisplayed(), "About Button is not displayed.");

        Reporter.log("Clicking Driven logo");
        driven_homepageHelper_Client.getDrivenLogo().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(driven_homepageHelper_Client.getWaitByLocator("DrivenLogo")));

        Reporter.log("Clicking Home Button");
        driven_homepageHelper_Client.getHomeButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(driven_homepageHelper_Client.getWaitByLocator("DrivenLogo")));

        Reporter.log("Clicking Search button");
        driven_homepageHelper_Client.getSearchButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(driven_homepageHelper_Client.getWaitByLocator("DrivenLogo")));

        Reporter.log("Clicking Archive button");
        driven_homepageHelper_Client.getArchiveButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(driven_homepageHelper_Client.getWaitByLocator("DrivenLogo")));

        Reporter.log("Clicking Previous button");
        driven_homepageHelper_Client.getPreviousButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(driven_homepageHelper_Client.getWaitByLocator("DrivenLogo")));

        Reporter.log("Clicking Next button");
        driven_homepageHelper_Client.getNextButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(driven_homepageHelper_Client.getWaitByLocator("DrivenLogo")));

        Reporter.log("Clicking About button");
        driven_homepageHelper_Client.getAboutButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(driven_homepageHelper_Client.getWaitByLocator("DrivenLogo")));
    }


    /**
     * Sign up and register for Driven
     *
     *
     */
    public void signUpDrivenStep1(String email) throws Exception {
        WebDriver client = getDriver();

        driven_signupHelper_client = new Driven_SignupHelper_Client(client);
        driven_homepageHelper_Client = new Driven_HomepageHelper_Client(client);

        //step1, 1st signup modal:

        driven_homepageHelper_Client.getSignUpButton().click();

        //b. Enter email address

        WebDriverWait waitForBox = new WebDriverWait(client,30);
        waitForBox.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("html.js body.home div.ajaxPopup div.ajaxPopupContent div.signup-bg-top div.signup-bg-bottom div.signup div.flow_page a.closeButton")));

        System.out.println("DRIVEN EMAIL CLIENT> " + email);

        //c. Select Editions

        driven_signupHelper_client.getSignInEmailBox().sendKeys(email);

        driven_signupHelper_client.getLasVegasCheckBox().click();
        //driven_signupHelper_client.getHoustonCheckBox().click();
        driven_signupHelper_client.getJetsetCheckBox().click();
        driven_signupHelper_client.getSkiBoardCheckBox().click();
        //driven_signupHelper_client.getNationalCheckBox().click();
        ///driven_signupHelper_client.getNewYorkCheckBox().click();

        //Click Join
        driven_signupHelper_client.getJoinButton().click();

    }

    /**
     * Complete registration form by filling out
     * name, gender, income range, zip code, etc.
     */
    public void signUpDrivenStep2(String var) throws Exception {
        WebDriver client = getDriver();

        driven_signupHelper_client = new Driven_SignupHelper_Client(client);
        wait = new WebDriverWait(client,10);

        //step2, 2nd signup modal:
        //enter PASSWORD
        driven_signupHelper_client.getPasswordBox().sendKeys(PASSWORD);

        //confirm PASSWORD
        driven_signupHelper_client.getConfirmPasswordBox().sendKeys(PASSWORD);

        //First Name
        driven_signupHelper_client.getFirstNameBox().sendKeys("FN_" + var);

        //Last Name
        driven_signupHelper_client.getLastNameBox().sendKeys("LN_" + var);

        //Gender
        driven_signupHelper_client.selectGender("Male");
        //ud_signupHelper_Client.selectGender("Female");
        //ud_signupHelper_Client.selectGenderRandom();

        //Zip Code
        driven_signupHelper_client.getZipCodeBox().sendKeys("10001");

        //Birthday (MM/DD/YYYY)
        driven_signupHelper_client.getBirthdayBox().sendKeys("07/07/1977");

        //click "SUBMIT" button
        driven_signupHelper_client.getSubmitButton().click();

    }

    /**
     * Fills out the refer a friend form.
     */
    public void signUpDrivenStep3(List<String> friend) {
        WebDriver client = getDriver();

        driven_signupHelper_client = new Driven_SignupHelper_Client(client);

    //step3, 3rd signup modal: Invite Friends

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("misc_email_1")));

        for (int i = 0; i < friend.size(); i++) {
            driven_signupHelper_client.getEmailFriendBox((i+1)).sendKeys(friend.get(i));
            System.out.println(friend.get(i));
        }

        // Click Invite
        driven_signupHelper_client.getInviteButton().click();

    }

    /**
     * Close the sign up form.
     */
    public void signUpDrivenStep4() throws Exception {
        WebDriver client = getDriver();

        //step4, 4th signup modal confirmation, close final confirm signup box
        driven_signupHelper_client = new Driven_SignupHelper_Client(client);
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("html.js body.home div.ajaxPopup div.ajaxPopupContent div.signup-bg-top div.signup-bg-bottom div.signup div.flow_page a.closeButton")));

        Assert.assertTrue(driven_signupHelper_client.getFbFriendUsButton().isDisplayed(),"FB Friend us link missing");
        Assert.assertTrue(driven_signupHelper_client.getTwitterFollowButton().isDisplayed(),"Twitter follow us link missing");
        Assert.assertTrue(driven_signupHelper_client.getPrivacyPolicyLink().isDisplayed(),"Privacy Policy link is missing");
        Assert.assertTrue(driven_signupHelper_client.getTermsConditionsLink().isDisplayed(),"User Agreement link is missing");

        driven_signupHelper_client.getCloseButton().click();
    }

///////////// Sign-up methods end here

    /**
     * Log into the Driven Admin site
     * This is to create posts
     *
     */
    public void loginDrivenAdmin(){
        WebDriver client = getDriver();

        client.manage().deleteAllCookies();
        client.get(DRIVEN_ADMIN_DOMAIN + "/wp-login.php");
        client.findElement(By.id("user_login")).clear();
        client.findElement(By.id("user_login")).sendKeys(DRIVEN_ADMIN_USERNAME);
        client.findElement(By.id("user_pass")).clear();
        client.findElement(By.id("user_pass")).sendKeys(DRIVEN_ADMIN_PW);
        client.findElement(By.id("wp-submit")).click();
    }

    /**
     * Create New Posts
     *
     */
    public void createStandardAPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        Reporter.log("Entering article title",true);
        driven_adminHelper_client.getPostTitleBox().sendKeys("Standard A Post Test Title "+ date);

        //Enter Main Copy
        //Reporter.log("Switching to iFrame",true);
        //client.switchTo().frame("content_ifr");

        Reporter.log("Entering text",true);
        //client.findElement(By.id("tinymce")).sendKeys("Standard A Post Test Main Copy "+ date);
        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Standard A Post Test Main Copy "+ date);
        //client.switchTo().defaultContent();
        //Reporter.log("Switching back to window",true);

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Standard A Post Test Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dialog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void createTemplateBPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        driven_adminHelper_client.getPostTitleBox().sendKeys("Template B Post Test Title "+ date);

        //Enter Main Copy
        //client.switchTo().frame("content_ifr");

        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Template B Post Test Main Copy "+ date);
        //client.switchTo().defaultContent();

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Template B Post Test Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        //Select Standard B in Template select
        dropDownSelector(driven_adminHelper_client.getTemplateDropdown(),"Template B");

        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dailog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void createStandardABPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        driven_adminHelper_client.getPostTitleBox().sendKeys("Standard AB Post Test Title "+ date);

        //Enter Main Copy
        //client.switchTo().frame("content_ifr");

        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Standard AB Post Test Main Copy "+ date);
        //client.switchTo().defaultContent();

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Standard AB Post Test Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        //Select Standard B in Template select
        dropDownSelector(driven_adminHelper_client.getTemplateDropdown(),"Standard AB");

        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dailog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void createPosterSolidColorPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        driven_adminHelper_client.getPostTitleBox().sendKeys("Poster Solid Color Post Test Title "+ date);

        //Enter Main Copy
        //client.switchTo().frame("content_ifr");

        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Poster Solid Color Post Main Copy "+ date);
        //client.switchTo().defaultContent();

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Poster Solid Color Post Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        //Select Standard B in Template select
        dropDownSelector(driven_adminHelper_client.getTemplateDropdown(),"Poster Solid Color");

        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dailog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void createPosterPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        driven_adminHelper_client.getPostTitleBox().sendKeys("Poster Post Test Title "+ date);

        //Enter Main Copy
        //client.switchTo().frame("content_ifr");

        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Poster Post Main Copy "+ date);
        //client.switchTo().defaultContent();

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Poster Post Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        //Select Standard B in Template select
        dropDownSelector(driven_adminHelper_client.getTemplateDropdown(),"Poster");

        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dailog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void createVideoPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        driven_adminHelper_client.getPostTitleBox().sendKeys("Video Post Test Title "+ date);

        //Enter Main Copy
        //client.switchTo().frame("content_ifr");

        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Video Post Main Copy "+ date);
        //client.switchTo().defaultContent();

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Video Post Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        //Select Standard B in Template select
        dropDownSelector(driven_adminHelper_client.getTemplateDropdown(),"Video");

        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dailog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void createVersusPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        driven_adminHelper_client.getPostTitleBox().sendKeys("Versus Post Test Title "+ date);

        //Enter Main Copy
        //client.switchTo().frame("content_ifr");

        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Versus Post Main Copy "+ date);
        //client.switchTo().defaultContent();

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Versus Post Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        //Select Standard B in Template select
        dropDownSelector(driven_adminHelper_client.getTemplateDropdown(),"Versus Post");

        //Enter Two Column Post Options

        //Left Box Title
        driven_adminHelper_client.getLeftBoxTitleBox().sendKeys("The Left Image "+ date);

        //Left Box Image
        //click Select From Media link
        //driven_adminHelper_client.getSelectFromMediaLeftLink().click();
        //wait for popup to appear, based on presence of Media Library link
        //wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaLibraryLink")));

        // click Media Library Link
        //driven_adminHelper_client.getMediaLibraryLink().click();
        //wait for Media Library to load, based on "Show" link
        //wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MLShowLink")));

        //click Show on the first item
        //driven_adminHelper_client.getMLShowLink().click();
        //wait for Image view based on "Select Image Link" button
        //wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MLSelectImageLinkButton")));

        //click "Select Image Link" button
        //driven_adminHelper_client.getMLSelectImageLinkButton().click();
        driven_adminHelper_client.getLeftBoxImageBox().sendKeys("/wp-content/uploads/2013/02/20130207_072730_04.jpg");


        //Left Box Photo Credits - Title
        driven_adminHelper_client.getLeftBoxPhotoCreditsTitleBox().sendKeys("Photo Author 1 "+ date);
        //Left Box Photo Credits - URL
        driven_adminHelper_client.getLeftBoxPhotoCreditsURLBox().sendKeys("www."+ date +".com");
        //Middle Text
        driven_adminHelper_client.getMiddleTextBox().sendKeys("VS");
        //Right Box Title
        driven_adminHelper_client.getRightBoxTitleBox().sendKeys("The Right Image "+ date);

        //Right Box Image
        driven_adminHelper_client.getRightBoxImageBox().sendKeys("/wp-content/uploads/2013/02/20130207_072730_04.jpg");

        //click Select From Media link
        //driven_adminHelper_client.getSelectFromMediaRightLink().click();
        //wait for popup to appear, based on presense of Media Library link
        //wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaLibraryLink")));

        // click Media Library Link
        //driven_adminHelper_client.getMediaLibraryLink().click();
        //wait for Media Library to load, based on "Show" link
        //wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MLShowLink")));

        //click Show on the first item
        //driven_adminHelper_client.getMLShowLink().click();
        //wait for Image view based on "Select Image Link" button
        //wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MLSelectImageLinkButton")));

        //click "Select Image Link" button
        //driven_adminHelper_client.getMLSelectImageLinkButton().click();



        //Right Box Photo Credits - Title
        driven_adminHelper_client.getRightBoxPhotoCreditsBox().sendKeys("Photo Author 2 "+ date);
        //Right Box Photo Credits - URL
        driven_adminHelper_client.getRightBoxPhotoCreditsURL().sendKeys("www. "+ date +".org");


        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dailog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void createHeroPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        driven_adminHelper_client.getPostTitleBox().sendKeys("Hero Post Test Title "+ date);

        //Enter Main Copy
        //client.switchTo().frame("content_ifr");

        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Hero Post Main Copy "+ date);
        //client.switchTo().defaultContent();

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Hero Post Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        //Select Standard B in Template select
        dropDownSelector(driven_adminHelper_client.getTemplateDropdown(),"Hero");

        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dailog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void createGalleryPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        driven_adminHelper_client.getPostTitleBox().sendKeys("Gallery Post Test Title "+ date);

        //Enter Main Copy
        //client.switchTo().frame("content_ifr");

        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Gallery Post Main Copy "+ date);
        //client.switchTo().defaultContent();

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Gallery Post Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        //Select Standard B in Template select
        dropDownSelector(driven_adminHelper_client.getTemplateDropdown(),"Gallery");

        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dailog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void createBulgariPost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        driven_adminHelper_client.getPostTitleBox().sendKeys("Bulgari Post Test Title "+ date);

        //Enter Main Copy
        //client.switchTo().frame("content_ifr");

        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Bulgari Post Main Copy "+ date);
        //client.switchTo().defaultContent();

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Bulgari Post Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        //Select Standard B in Template select
        dropDownSelector(driven_adminHelper_client.getTemplateDropdown(),"Bulgari");

        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dailog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void createGiftGuidePost() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);
        driven_adminHelper_client = new Driven_AdminHelper_Client(client);
        String date = emailHelper_Client.generateDate(DATEFORMAT);

        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/post-new.php");

        //Enter Article Title:
        driven_adminHelper_client.getPostTitleBox().sendKeys("Gift Guide Post Test Title "+ date);

        //Enter Main Copy
        //client.switchTo().frame("content_ifr");

        driven_adminHelper_client.getPostMainCopyBox().sendKeys("Gift Guide Post Main Copy "+ date);
        //client.switchTo().defaultContent();

        //Enter Short Text
        driven_adminHelper_client.getPostShortCopyBox().sendKeys("Gift Guide Post Short Text "+ date);

        //Select Category: Accelerator
        driven_adminHelper_client.getCategoryAcceleratorSelect().click();

        //Select Standard B in Template select
        dropDownSelector(driven_adminHelper_client.getTemplateDropdown(),"Gift Guide");

        // Click "Set featured image"
        driven_adminHelper_client.getSetFeaturedImageLink().click();

        //wait for Set Featured Image Dialog to appear
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));

        //click image thumbnail
        driven_adminHelper_client.getImageThumbnail().click();

        //wait for MediaModalIcon to appear at the corner of selected image as in indicative that it was indeed selected.
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("MediaModalIcon")));

        //Click "Set Featured Image"
        driven_adminHelper_client.getSetFeaturedImageButton().click();

        //wait for Set Featured Image Dailog to disappear
        wait.until(ExpectedConditions.invisibilityOfElementWithText(driven_adminHelper_client.getWaitByLocator("SetFeaturedImageDialogTitle"),"Set Featured Image"));
        //and "Remove featured image" link to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("RemoveFeaturedImageLink")));

        //Save all changes
        driven_adminHelper_client.getSaveDraftButton().click();

        // Go to View All articles
        client.get(DRIVEN_ADMIN_DOMAIN+"/wp-admin/edit.php");
        // wait for the page to load: based on presence of "Add New" link on /wp-admin/edit.php
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("AddNewLink")));


        //Find and Click the article
        client.findElement(By.partialLinkText(date)).click();

        // Publish
        wait.until(ExpectedConditions.presenceOfElementLocated(driven_adminHelper_client.getWaitByLocator("PublishButton")));
        driven_adminHelper_client.getPublishButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(driven_adminHelper_client.getWaitByLocator("PublishedConfirmation"), "Post published"));

        //View Published Post
        client.get(DRIVEN_ADMIN_DOMAIN);
        client.findElement(By.partialLinkText(date)).click();

        //Confirm stuff
        //Assert.assertTrue(ud_headerHelper_Client.getFoodDrinkNationalLink().isDisplayed(),"Food & Drink Link is missing.");

    }

    public void dropDownSelector (WebElement element, String value) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(value);
    }
}
