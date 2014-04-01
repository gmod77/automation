package org.geno.com.sauce;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.geno.com.common.UDBase;
import org.geno.com.helpers.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

//Workflow specific imports

/**
 *    Domain Test Methods
 *
 *
 */
public class iTestCaseManeroSauce extends iSauceBase implements UDBase {

    String lastURL;
    String maneroCity;

    WebDriverWait wait;

    private Manero_FooterHelper_Client manero_footerHelper_client;
    private Manero_HeaderHelper_Client manero_headerHelper_client;
    private Manero_SignupHelper_Client manero_signupHelper_client;
    private Manero_HomepageHelper_Client manero_HomepageHelper_Client;
    private Manero_LoginHelper_Client manero_loginHelper_client;
    private EmailHelper_Client emailHelper_Client;

///// Sign-up methods

    // UD

    /**
     *  Navigate to the home page and perform a series of checks
     *  to make sure certain links are present
     *
     */
    public void visitManeroFirstTime(){
        WebDriver client = getDriver();

        // enter Manero domain name, hit enter, arrive on homepage
        client.get(MANERO_DOMAIN);
        Assert.assertTrue(client.getCurrentUrl().equals(MANERO_DOMAIN + "/home/ntl"),"Manero URL didn't redirect to National Page");
        Assert.assertTrue(client.findElement(By.xpath("//*[@id=\"header\"]/div[2]/h1")).isDisplayed(),"Logo did not showup");

    }

    /**
     * Share an article via email
     * PreRequisite: visitUDFirstTime()
     *
     */
    public void shareArticle(String mailClient, String[] friends){
        WebDriver client = getDriver();
        //TODO update for Manero

        // Grab the first article under "The Five You Need To Read"
        client.findElement(By.xpath(".//*[@id='content']/div/div[1]/div[2]/div/div[2]/div[1]/p/a")).click();

        // Grab article title
        //String articleTitle = iHelper_client.findElementAndCheckBy("xpath","//html/body/div/div[3]/div/div/h1/span",5).getText();
        //System.out.println("Article Title> " + articleTitle);
        // Click on the Forward button
        //iHelper_client.findElementAndCheckBy("xpath",".//*[@id='weekenderContentHolder']/div[1]/div[8]/a[1]",5).click();

        // Handle popup window
        String handler = popUpHandler("Forward to a Friend",client);

        // Confirm Name of Article Appears
        String confirmTitle = client.findElement(By.xpath("/html/body/div[1]/div/div/div/p")).getText();
        System.out.println("Confirm Title> " + confirmTitle);
//        Assert.assertTrue(confirmTitle.contains(articleTitle), "Article title was not found on popup.");

        // Fill in email addresses
        for (int i = 0; i < friends.length; i++) {
            client.findElement(By.id("invite_email_" + i)).sendKeys(friends[i]);
        }

        // If visible fill in Name
        if (client.findElement(By.id("name")).isDisplayed()) {
            WebElement name = client.findElement(By.id("name"));
            name.sendKeys("QA TESTER");
        }

        // If visible fill in Email
        if (client.findElement(By.id("email")).isDisplayed()) {
            WebElement email = client.findElement(By.id("email"));
            email.sendKeys(mailClient);
        }


        //Fill in personal message
        client.findElement(By.id("msg")).sendKeys("QA Tester, personal message.\nLorem ipsum lorem ipsum.");

        //Click Submit
        client.findElement(By.id("myaccount_invite_users_form")).submit();

        //Confirmation screen check
//        String str = iHelper_client.findElementAndCheckBy("xpath","//html/body/div/div/div/div[1]/p",5).getText();
//        Assert.assertTrue(str.contains("Thank you. The article has been forwarded to your friend(s)."), "Popup failed to submit");

        //Close Popup
        client.findElement(By.tagName("input")).click();

        //Return to Parent Window
        returnToParentWindow(handler,client);

    }

    public void saveArticleToAccount () {
        //TODO update for Manero

    }


    /**
     * Check the Manero header and homepage while logged out
     * Confirm each link by clicking on it
     *
     */
    public void checkManeroHomepageHeaderLoggedOut() {
        WebDriver client = getDriver();

        lastURL = client.getCurrentUrl();
        //do all homepage header checks
        manero_HomepageHelper_Client = new Manero_HomepageHelper_Client(client);
        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        Assert.assertTrue(manero_headerHelper_client.getSignUpLink().isDisplayed(),"Sign up link is not displayed.");
        Assert.assertTrue(manero_HomepageHelper_Client.getCarouselModule().isDisplayed(),"Carousel is not displayed.");
        Assert.assertTrue(manero_HomepageHelper_Client.getManeroLogo().isDisplayed(),"Manero Logo is not displayed.");
        Assert.assertTrue(manero_HomepageHelper_Client.getShortListModule().isDisplayed(),"Short List is not displayed.");

        manero_HomepageHelper_Client.getCulturaLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getEntertainmentLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getGadgetsLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getMusicaLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getNightLifeLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getSportsLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getStyleLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getTravelLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));
    }

    /**
     * Check the Manero header and homepage while logged in
     * Confirm each link by clicking on it
     *
     */
    public void checkManeroHomepageHeaderLoggedIn() {
        WebDriver client = getDriver();

        lastURL = client.getCurrentUrl();
        //do all homepage header checks
        manero_HomepageHelper_Client = new Manero_HomepageHelper_Client(client);
        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        Assert.assertTrue(manero_headerHelper_client.getLogoutLink().isDisplayed(),"Logout link is not displayed.");
        Assert.assertTrue(manero_HomepageHelper_Client.getCarouselModule().isDisplayed(),"Carousel is not displayed.");
        Assert.assertTrue(manero_HomepageHelper_Client.getManeroLogo().isDisplayed(),"Manero Logo is not displayed.");
        Assert.assertTrue(manero_HomepageHelper_Client.getShortListModule().isDisplayed(),"Short List is not displayed.");

        manero_HomepageHelper_Client.getCulturaLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getEntertainmentLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getGadgetsLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getMusicaLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getNightLifeLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getSportsLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getStyleLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));

        manero_HomepageHelper_Client.getTravelLink().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_HomepageHelper_Client.getWaitByLocator("ManeroLogo")));
    }

    /**
     * Check the Manero footer
     * Checks to see the links exist
     *
     */
    public void checkManeroHomepageFooterLoggedOut() {
        WebDriver client = getDriver();

        //do all page footer checks
        manero_footerHelper_client = new Manero_FooterHelper_Client(client);
        Assert.assertTrue(manero_footerHelper_client.getAboutUsLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getAdvertiseLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getChicagoLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getContactLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getEditorialPolicyLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getEmailIssuesLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getHoustonLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getJobsLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getLosAngelesLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getMiamiLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getNationalLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getNewYorkLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getPrivacyPolicyLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getSignUpLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getUnsubscribeLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getUserAgreementLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getSignUpLink().isDisplayed());
    }

    /**
     * Check the   footer while logged in
     * Checks to see the links exist
     *
     */
    public void checkManeroHomePageFooterLoggedIn() {
        WebDriver client = getDriver();

        //do all city homepage footer checks for logged in state
        manero_footerHelper_client = new Manero_FooterHelper_Client(client);
        Assert.assertTrue(manero_footerHelper_client.getAboutUsLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getAdvertiseLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getChicagoLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getContactLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getEditorialPolicyLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getEmailIssuesLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getHoustonLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getJobsLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getLosAngelesLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getMiamiLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getNationalLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getNewYorkLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getPrivacyPolicyLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getSignUpLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getUnsubscribeLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getUserAgreementLink().isDisplayed());
        Assert.assertTrue(manero_footerHelper_client.getMyAccountLink().isDisplayed());

    }

    /**
     * Run this method after logging in to unsubscribe
     * from all perks and editorial emails
     *
     */
    public void unSubscribeFromEmails() {
        //TODO Finish this
    }

    /**
     * Confirm the unsubscribe email contains the text
     * "successfully unsubscribed"
     */
    public void unSubscribeMailConfirm(){
        WebDriver client = getDriver();

        //TODO
        emailHelper_Client = new EmailHelper_Client(client);

        emailHelper_Client.searchEmail("successfully unsubscribed");
    }


    public boolean StaleElementHandleByXpath (String email) throws Exception {
        WebDriver client = getDriver();

        UD_SignupHelper_Client_v2 ud_signupHelper_Client = new UD_SignupHelper_Client_v2(client);
        int count = 0;
        boolean flag = false;
        do {
            try {
                ud_signupHelper_Client.getEnterEmailBox().sendKeys(email);
                count++;
                flag = true;
            } catch (StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element: " + e.getMessage());
            }
        } while (count < 4 && !flag);
        if (count >= 4) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Sign up and register for perks & editorial
     * via New York UD edition
     *
     */
    public void signUpManeroStep1(String email) {
        WebDriver client = getDriver();

        manero_signupHelper_client = new Manero_SignupHelper_Client(client);
        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        //step1, 1st signup modal:

        manero_headerHelper_client.getSignUpLink().click();

        //b. Enter email address

        WebDriverWait waitForBox = new WebDriverWait(client,30);
        waitForBox.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("box_header")));

        System.out.println("MANERO EMAIL CLIENT> " + email);

        //c. Select Editions

        manero_signupHelper_client.getSignInEmailBox().sendKeys(email);

        manero_signupHelper_client.getChicagoCheckBox().click();
        manero_signupHelper_client.getHoustonCheckBox().click();
        manero_signupHelper_client.getLosAngelesCheckBox().click();
        manero_signupHelper_client.getMiamiCheckBox().click();
        manero_signupHelper_client.getNationalCheckBox().click();
        manero_signupHelper_client.getNewYorkCheckBox().click();

        //Click Join
        manero_signupHelper_client.getJoinButton().click();

    }

    /**
     * Complete registration form by filling out
     * name, gender, income range, zip code, etc.
     */
    public void signUpManeroStep2(String var) {
        WebDriver client = getDriver();

//        manero_signupHelper_client = new Manero_SignupHelper_Client(client);
        wait = new WebDriverWait(client,10);

        //step2, 2nd signup modal:
        //enter PASSWORD
        wait.until(ExpectedConditions.visibilityOfElementLocated(manero_signupHelper_client.getWaitByLocator("EnterPassword")));
        manero_signupHelper_client.getPasswordBox().clear();
        manero_signupHelper_client.getPasswordBox().sendKeys(PASSWORD);

        //confirm PASSWORD
        manero_signupHelper_client.getConfirmPasswordBox().sendKeys(PASSWORD);

        //First Name
        manero_signupHelper_client.getFirstNameBox().sendKeys("FN_" + var);

        //Last Name
        manero_signupHelper_client.getLastNameBox().sendKeys("LN_" + var);

        //Gender
        manero_signupHelper_client.selectGender("Male");
        //ud_signupHelper_Client.selectGender("Female");
        //ud_signupHelper_Client.selectGenderRandom();

        //Zip Code
        manero_signupHelper_client.getZipCodeBox().sendKeys("10001");

        //Birthday (MM/DD/YYYY)
        manero_signupHelper_client.getBirthdayBox().sendKeys("07/07/1977");

        //click "SUBMIT" button
        manero_signupHelper_client.getSubmitButton().click();

    }

    /**
     * Fills out the refer a friend form.
     */
    public void signUpManeroStep3(List<String> friend) {
        WebDriver client = getDriver();

        manero_signupHelper_client = new Manero_SignupHelper_Client(client);

    //step3, 3rd signup modal: Invite Friends

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("sign_up_invites")));

        for (int i = 0; i < friend.size(); i++) {
            manero_signupHelper_client.getEmailFriendBox((i+1)).sendKeys(friend.get(i));
            System.out.println(friend.get(i));
        }

        // Click Invite
        manero_signupHelper_client.getInviteButton().click();
    }

    /**
     * Close the sign up form.
     */
    public void signUpManeroStep4() {
        WebDriver client = getDriver();

        //step4, 4th signup modal confirmation, close final confirm signup box
        manero_signupHelper_client = new Manero_SignupHelper_Client(client);
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signup-close")));

        Assert.assertTrue(manero_signupHelper_client.getFbFriendUsButton().isDisplayed(),"FB Friend us link missing");
        Assert.assertTrue(manero_signupHelper_client.getTwitterFollowButton().isDisplayed(),"Twitter follow us link missing");
        Assert.assertTrue(manero_signupHelper_client.getPrivacyPolicyLink().isDisplayed(),"Privacy Policy link is missing");
        Assert.assertTrue(manero_signupHelper_client.getUserAgreementLink().isDisplayed(),"User Agreement link is missing");

        manero_signupHelper_client.getCloseButton().click();
    }

    /**
     * Click the change city link from the homepage
     */
    public void changeCityFromManeroHomepage() throws Exception {
        WebDriver client = getDriver();

        //click on "Change City" from any city home page, navigate back to home page
        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        manero_headerHelper_client.getChangeCityLink().click();

    }

    /**
     * Click the Chicago link from the homepage
     */
    public void accessChicagoFromManeroHomepage() throws Exception {
        WebDriver client = getDriver();

        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        manero_headerHelper_client.clickChangeCityChicago();
        maneroCity = "chi";

    }

    /**
     * Click the Houston link from the homepage
     */
    public void accessHoustonFromManeroHomepage() throws Exception {
        WebDriver client = getDriver();

        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        manero_headerHelper_client.clickChangeCityHouston();
        maneroCity = "hou";

    }

    /**
     * Click the LA link from the homepage
     */
    public void accessLosAngelesFromManeroHomepage() throws Exception {
        WebDriver client = getDriver();

        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        manero_headerHelper_client.clickChangeCityLosAngeles();
        maneroCity = "la";
    }

    /**
     * Click the Miami link from the homepage
     */
    public void accessMiamiFromManeroHomepage() throws Exception {
        WebDriver client = getDriver();

        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        manero_headerHelper_client.clickChangeCityMiami();
        maneroCity = "mia";
    }

    /**
     * Click the National edition link from the homepage
     */
    public void accessNationalFromNationalHomepage() throws Exception {
        WebDriver client = getDriver();

        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        manero_headerHelper_client.clickChangeCityNational();
        maneroCity = "ntl";
    }

    /**
     * Click the New York link from the homepage
     */
    public void accessNewYorkFromNewYorkHomepage() throws Exception {
        WebDriver client = getDriver();

        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        manero_headerHelper_client.clickChangeCityNewYork();
        maneroCity = "nyc";
    }

///////////// Sign-up methods end here		

///////////////  Login/SignOut methods

    /**
     * Login method, use to pass email and PASSWORD in via
     * other tests.
     * ex. currently used in UD_Unsubscribe_EditionsPerks
     * @param email Email address
     * @param pw PASSWORD
     */
    public void loginManero(String email, String pw) {
        WebDriver client = getDriver();

        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);
        manero_loginHelper_client = new Manero_LoginHelper_Client(client);

        manero_headerHelper_client.getMemberLoginLink().click();
        manero_loginHelper_client.getEmailAddressBox().sendKeys(email);
        manero_loginHelper_client.getPasswordBox().sendKeys(pw);
        manero_loginHelper_client.getLoginButton().click();

    }

    /**
     * Logout of UD and do checks on the footer
     *
     */
    public void logoutManero() {
        WebDriver client = getDriver();

        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);
        manero_headerHelper_client.getLogoutLink().click();
    }

    /**
     * Perform a PASSWORD reset.
     */
    public void resetPasswordUD(String emailClient) {
        WebDriver client = getDriver();

        manero_loginHelper_client = new Manero_LoginHelper_Client(client);
        manero_headerHelper_client = new Manero_HeaderHelper_Client(client);

        manero_headerHelper_client.getMemberLoginLink().click();
        manero_loginHelper_client.getResetPasswordLink().click();
        manero_loginHelper_client.getEmailResetBox().sendKeys(emailClient);
        manero_loginHelper_client.getSendButton().click();
    }

    /**
     * Login to the UD site then update your account
     * by checking some editions and perks
     *
     */
    public void editSettingsUD() throws Exception {
        WebDriver client = getDriver();

        manero_loginHelper_client = new Manero_LoginHelper_Client(client);

        manero_loginHelper_client.getEditSettingsLink().click();
        manero_loginHelper_client.getEditPasswordBox().sendKeys(PASSWORD);
        manero_loginHelper_client.getConfirmEditPasswordBox().sendKeys(PASSWORD);

        manero_loginHelper_client.getEditionChicagoCheckBox().click();
        manero_loginHelper_client.getEditionNewYorkCheckBox().click();
        manero_loginHelper_client.getEditionNationalCheckBox().click();

        manero_loginHelper_client.getUpdateButton().click();

        WebDriverWait messageWait = new WebDriverWait(client, 30);
        messageWait.until(ExpectedConditions.visibilityOfElementLocated(manero_loginHelper_client.getWaitByLocator("MessageBox")));

        manero_loginHelper_client.getCloseButton().click();

    }

    /**
     * Use this to handle popups
     * @param winTitle
     * @param client
     * @return
     */
    public String popUpHandler(String winTitle, WebDriver client) {
        String parentWindowHandle=client.getWindowHandle();
        WebDriver popup = null;
        //handle pop-up window
        Set<?> s_add2=client.getWindowHandles();
        //this method will you handle of all opened windows

        Iterator<?> windowIterator=s_add2.iterator();

        while(windowIterator.hasNext()) {
            String windowHandle = windowIterator.next().toString();
            popup = client.switchTo().window(windowHandle);
            if (popup.getTitle().contains(winTitle)) {
                break;
            }
        }
        return parentWindowHandle;
    }

    /**
     * Use this to return to parent window
     * @param parent
     * @param client
     */
    public void returnToParentWindow(String parent, WebDriver client) {
        client.switchTo().window(parent);
    }
}
