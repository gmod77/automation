package org.geno.com.sauce;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.geno.com.common.UDBase;
import org.geno.com.helpers.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains all Perks domain specific tests
 */
public class iTestCasePerksSauce extends iSauceBase implements UDBase {

    private UD_SealHelper_Client_v2 ud_sealHelper_Client;

    private Perks_HomepageHelper_Client perks_homepageHelper_Client;
    private Perks_HeaderHelper_Client perks_headerHelper_Client;
    private Perks_FooterHelper_Client perks_footerHelper_Client;
    private Perks_LoginHelper_Client perks_loginHelper_client;
    private Perks_SignupHelper_Client perks_signupHelper_Client;
    private Perks_CheckOutHelper_Client perks_checkOutHelper_client;
    private Perks_ProductPageHelper_Client perks_productPageHelper_client;
    private Perks_MagentoHelper_Client pmhc;
    private EmailHelper_Client emailHelper_Client;

    WebDriverWait wait;


    // Perks
    /**
     * After login navigate to account settings
     */
    public void editSettingsPerks() {
        WebDriver client = getDriver();
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_headerHelper_Client = new Perks_HeaderHelper_Client(client);
        //Reuse methods in UD client
        ud_sealHelper_Client = new UD_SealHelper_Client_v2(client);

        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_headerHelper_Client.getWaitByLocator("MyAccountHover")));

//        displayCssHover();
//        perks_headerHelper_Client.getMyAccountLink().click();

        client.get(UD_DOMAIN + "/myud");

        // Wait for page to load
        wait.until(ExpectedConditions.titleContains("MyUD"));
        Assert.assertTrue(client.getTitle().contains("MyUD"), "Title does not contain MyUD");

        ud_sealHelper_Client.getEditSettingsInMyUDLink().click();
        ud_sealHelper_Client.getEnterPasswordMyUDBox().sendKeys(PASSWORD);
        ud_sealHelper_Client.getConfirmPasswordMyUDBox().sendKeys(PASSWORD);

        ud_sealHelper_Client.getDCPerksSelect().click();

        ud_sealHelper_Client.getUpdateButton().click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_sealHelper_Client.getWaitByLocator("Close")));

        ud_sealHelper_Client.getCloseButton().click();
    }

    /**
     * Navigate to the Perks domain
     *
     */
    public void visitPerksFirstTime() {
        WebDriver client = getDriver();
        // enter UD domain name, hit enter, arrive on homepage
        client.get(PERKS_DOMAIN);
        client.manage().deleteAllCookies();
        client.get(PERKS_DOMAIN);
    }

    /**
     * Navigate to the Perks domain selcting a city
     * national
     * new-york
     * chicago
     * boston
     * washington-dc
     * los-angeles
     * @param city input the city name
     */
    public void visitPerksFirstTime(String city) {
        WebDriver client = getDriver();
        client.get(PERKS_DOMAIN + "/" + city + ".html");
    }


    /**
     * Sign into the perks site
     */
    public void signInPerks(String emailClient) {
        WebDriver client = getDriver();
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_headerHelper_Client = new Perks_HeaderHelper_Client(client);
        perks_loginHelper_client = new Perks_LoginHelper_Client(client);


        //click signup
        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_headerHelper_Client.getWaitByLocator("SignInHover")));

        // Activate the popup
        displayCssHover();

        perks_headerHelper_Client.getSignUpLink().click();
        //b. Enter email address
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("login")));

        perks_loginHelper_client.getEmailAddressBox().sendKeys(emailClient);

        perks_loginHelper_client.getEnterPasswordBox().sendKeys(PASSWORD);

        perks_loginHelper_client.getLoginButton().click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modalContent>h1")));

    }

    public void checkPerksHomepageCityFooter() {
        WebDriver client = getDriver();

        perks_footerHelper_Client = new Perks_FooterHelper_Client(client);

        Assert.assertTrue(perks_footerHelper_Client.getAboutUsLink().getText().contains("About Us"),"About Us Text is Missing");
        Assert.assertTrue(perks_footerHelper_Client.getArchivesLink().getText().contains("Archives"),"Archives Text is Missing");
        Assert.assertTrue(perks_footerHelper_Client.getMyAccountLink().getText().contains("My Account"),"My Account Link is Missing");
        Assert.assertTrue(perks_footerHelper_Client.getContactLink().getText().contains("Contact"),"Contact link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getJobsLink().getText().contains("Jobs"),"Jobs link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getAdvertiseLink().getText().contains("Advertise"),"Advertise link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getTipsLink().getText().contains("Tips"),"Tips link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getHelpLink().getText().contains("Help"),"Help link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getEmailIssuesLink().getText().contains("Email Issues"),"Email Issues link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getPrivacyPolicyLink().getText().contains("Privacy Policy"),"Privacy policy link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getUserAgreementLink().getText().contains("User Agreement"),"User agreement link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getUnsubscribeLink().getText().contains("Unsubscribe"),"Unsubscribe link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getEditorialPolicyLink().getText().contains("Editorial Policy"),"Editorial policy link is missing");

        Assert.assertTrue(perks_footerHelper_Client.getBostonLink().getText().contains("Boston"),"Boston link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getChicagoLink().getText().contains("Chicago"),"Chicago link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getDCLink().getText().contains("Washington DC"),"Washington DC link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getLosAngelesLink().getText().contains("Los Angeles"),"Los Angeles link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getMiamiLink().getText().contains("Miami"),"Miami link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getNationalLink().getText().contains("National"),"National link is missing");
        Assert.assertTrue(perks_footerHelper_Client.getNewYorkLink().getText().contains("New York"),"New York link is missing");

    }

    public void returnToPerks() {
        WebDriver client = getDriver();

        client.get(PERKS_DOMAIN);
    }

    /**
     * Log out of perks
     */
    public void logoutPerks() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_headerHelper_Client = new Perks_HeaderHelper_Client(client);

        //click logout in header
        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_headerHelper_Client.getWaitByLocator("MyAccountHover")));

        displayCssHover();
        perks_headerHelper_Client.getLogoutLink().click();

        //click logout confirmation
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".redBtn.directLink>span")));
        client.findElement(By.cssSelector(".redBtn.directLink>span")).click();
    }

    /**
     * Send a perks password reset email
     *
     */
    public void resetPasswordPerks(String emailClient) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_homepageHelper_Client = new Perks_HomepageHelper_Client(client);
        perks_headerHelper_Client = new Perks_HeaderHelper_Client(client);
        perks_signupHelper_Client = new Perks_SignupHelper_Client(client);

        System.out.println(emailClient);

        //step1, 1st SignUp modal:
        displayCssHover();
        perks_headerHelper_Client.getSignUpLink().click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_signupHelper_Client.getWaitByLocator("ForgotPasswordLink")));

        perks_signupHelper_Client.getForgotPasswordLink().click();

        perks_signupHelper_Client.getForgotPasswordEmailBox().sendKeys(emailClient);

        perks_signupHelper_Client.getSendButton().click();

        wait.until(ExpectedConditions.textToBePresentInElement(By.className("errorfield"),"You'll receive an email shortly allowing you to reset your password."));

    }

    /**
     * Navigate to the perks home page and sign up
     * for perks by clicking editions and editorials.
     */
    public void signUpPerks_viaNewYorkStep1(String email, boolean editions) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_homepageHelper_Client = new Perks_HomepageHelper_Client(client);
        perks_headerHelper_Client = new Perks_HeaderHelper_Client(client);
        perks_signupHelper_Client = new Perks_SignupHelper_Client(client);

        System.out.println(email);

        //step1, 1st SignUp modal:
        //a. Click SignUp Seal
        displayCssHover();
        perks_headerHelper_Client.getSignUpLink().click();

        //b. Enter email address
        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_signupHelper_Client.getWaitByLocator("JoinEmailBox")));
        perks_signupHelper_Client.getEnterEmailBox().sendKeys(email);

        perks_signupHelper_Client.getAcceptButton().click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_signupHelper_Client.getWaitByLocator("MoreLink1")));

        //c. Select Editions
        //New York, New York Perks are selected by default
        if (editions) {
            perks_signupHelper_Client.getBostonPerksSelect().click();
            perks_signupHelper_Client.getChicagoPerksSelect().click();
            perks_signupHelper_Client.getDCPerksSelect().click();
            perks_signupHelper_Client.getLosAngelesPerksSelect().click();
            perks_signupHelper_Client.getMiamiPerksSelect().click();
            perks_signupHelper_Client.getNationalPerksSelect().click();

            //click "more" link to show all Editorials
            perks_signupHelper_Client.getMoreLink1().click();

            // check all of them
            perks_signupHelper_Client.getAtlantaEditionSelect().click();
            perks_signupHelper_Client.getBostonEditionSelect().click();
            perks_signupHelper_Client.getChicagoEditionSelect().click();
            perks_signupHelper_Client.getDallasEditionSelect().click();
            perks_signupHelper_Client.getDCEditionSelect().click();
            perks_signupHelper_Client.getDrivenSelect().click();
            perks_signupHelper_Client.getJetSetSelect().click();
            perks_signupHelper_Client.getLasVegasEditionSelect().click();
            perks_signupHelper_Client.getLosAngelesEditionSelect().click();
            perks_signupHelper_Client.getMiamiEditionSelect().click();
            perks_signupHelper_Client.getNationalEditionSelect().click();
            perks_signupHelper_Client.getSanFranciscoEditionSelect().click();
            perks_signupHelper_Client.getSkiBoardSelect().click();
        }
        //click "JOIN" button

        perks_signupHelper_Client.getSubmitButton1().click();
    }

    /**
     * Fill out the perks registration form providing
     * Name, Gender, Income Range, etc.
     */
    public void signUpPerks_viaNewYorkStep2() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        emailHelper_Client = new EmailHelper_Client(client);

        String var = emailHelper_Client.generateDate(DATEFORMAT);

        //Wait for window to show up
        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_signupHelper_Client.getWaitByLocator("EnterPassword")));

        //step2, 2nd signup modal:
        //enter password
        perks_signupHelper_Client.getPasswordTextBox().sendKeys(PASSWORD);
        //confirm password
        perks_signupHelper_Client.getConfirmPasswordTextBox().sendKeys(PASSWORD);
        //First Name
        perks_signupHelper_Client.getFirstNameTextBox().sendKeys("FN_" + var);
        //Last Name
        perks_signupHelper_Client.getLastNameTextBox().sendKeys("LN_" + var);
        //Gender
        perks_signupHelper_Client.selectGender("Male");
        //perks_signupHelper_Client.selectGender("Female");
        //perks_signupHelper_Client.selectGenderRandom();

        //Income Range
        perks_signupHelper_Client.selectIncomeRange("Less than $30,000");
//        perks_signupHelper_Client.selectIncomeRange("$30,000-$44,999");
//        perks_signupHelper_Client.selectIncomeRange("$45,000-$59,999");
//        perks_signupHelper_Client.selectIncomeRange("$60,000-$74,999");
//        perks_signupHelper_Client.selectIncomeRange("$75,000-$99,999");
//        perks_signupHelper_Client.selectIncomeRange("$100,000-$199,999");
//        perks_signupHelper_Client.selectIncomeRange("$200,000-$299,999");
//        perks_signupHelper_Client.selectIncomeRange("$300,000-$499,999");
//        perks_signupHelper_Client.selectIncomeRange("$500,000+");
//        perks_signupHelper_Client.selectIncomeRangeRandom();

        //Zip Code
        perks_signupHelper_Client.getZipCodeTextBox().sendKeys("10001");
        //Birthday (MM/DD/YYYY)
        perks_signupHelper_Client.getBirtdayTextBox().sendKeys("07/07/1977");
        //click "SUBMIT" button

        perks_signupHelper_Client.getSubmitButton2().click();
    }

    /**
     * Enter friend referrals and print the emails
     * used to the log
     */
    public void signUpPerks_viaNewYorkStep3(List<String> friends, Boolean emailFriends) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_signupHelper_Client.getWaitByLocator("InviteButton")));

        if (!emailFriends) {
            perks_signupHelper_Client.getSkipLink().click();
        } else {
            for (int i = 0; i < friends.size(); i++) {
                perks_signupHelper_Client.getEmailFriendTextBox((i + 1)).sendKeys(friends.get(i));
                System.out.println(friends.get(i));
            }
            perks_signupHelper_Client.getInviteButton().click();
        }
    }

    /**
     * After friend referral submission
     * close the perks signup form
     */
    public void signUpPerks_viaNewYorkStep4() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        //step4, 4th signup modal confirmation, close final confirm signup box
        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_signupHelper_Client.getWaitByLocator("CloseButton")));

        perks_signupHelper_Client.getCloseButton().click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".overlay")));
        //end of registration
    }


    // Perks Page Verification methods

    public int getPerksLinkCount() {
        WebDriver client = getDriver();

        WebElement editionBlocks = client.findElement(By.cssSelector(".edition-blocks"));
        return editionBlocks.findElements(By.cssSelector(".block.item.last")).size();
    }

    public List<WebElement> getEditionBlocks() {
        WebDriver client = getDriver();

        WebElement editionBlock = client.findElement(By.cssSelector(".edition-blocks"));
        return editionBlock.findElements(By.cssSelector(".block.item.last"));
    }

    public List<String> getPerkNames(List<WebElement> editionBlocks) {
        List<String> list = new ArrayList<String>();
        for (WebElement editionBlock : editionBlocks) {
            list.add(editionBlock.findElement(By.className("block-content")).findElement(By.tagName("a")).getAttribute("title"));
        }
        return list;
    }

    public List<String> getPerkText(List<WebElement> editionBlocks) {
        List<String> list = new ArrayList<String>();
        for (WebElement editionBlock : editionBlocks) {
            list.add(editionBlock.findElement(By.className("block-content")).findElement(By.tagName("a")).getText());
        }
        return list;
    }

    public List<String> getPerkNameAndText(List<WebElement> editionBlocks) {
        List<String> list = new ArrayList<String>();
        for (WebElement editionBlock : editionBlocks) {
            String a = editionBlock.findElement(By.className("block-content")).findElement(By.tagName("a")).getAttribute("title");
            String b = editionBlock.findElement(By.className("block-content")).findElement(By.tagName("a")).getText();
            list.add(a + " - " + b + "\n");
        }
        return list;
    }

    public List<String> getPerksLinks(List<WebElement> editionBlocks) {
        List<String> list = new ArrayList<String>();
        for (WebElement editionUrl : editionBlocks) {
            list.add(editionUrl.findElement(By.className("block-content")).findElement(By.tagName("a")).getAttribute("href"));
        }
        return list;
    }

    public String getPerkToPurchase(List<WebElement> editionBlocks, String type) {
        String url = "";
        for (WebElement editionBlock : editionBlocks) {
            WebElement block = editionBlock.findElement(By.className("block-content"));
            if ((block.findElement(By.cssSelector(".widgets>.book-now")).getText().toLowerCase().contains("now")) &&
                (block.findElement(By.tagName("a")).getAttribute("title")).toLowerCase().contains(type.toLowerCase())) {

                url = (block.findElement(By.tagName("a")).getAttribute("href"));
                break;
            }
        }
        return url;
    }

    public int checkPerkPageType() {
        WebDriver client = getDriver();

        WebElement wrapper = client.findElement(By.className("wrapper"));
        int type = 0;
        if (!wrapper.findElements(By.className("products")).isEmpty()) {
            // Multi product
            type = 1;
        } else if (!wrapper.findElements(By.className("service")).isEmpty()) {
            // 3 product
            type = 2;
        } else if (!wrapper.findElements(By.className("product-dashboard")).isEmpty()) {
            // Regular Perk
            type = 3;
        } else {
            // Unknown Perk Type
            Reporter.log("Mystery perk!",true);
        }
        return type;
    }

    public void checkPerksPages(List<String> links) {
        WebDriver client = getDriver();

        for (String link : links) {
            client.get(link);
            WebElement wrapper = client.findElement(By.className("wrapper"));

            if (!wrapper.findElements(By.className("products")).isEmpty()) {
                // Multi product
                waitForPerkHeaderLogo();
                Reporter.log("Checking Perk> " + client.getCurrentUrl(), true);
                Reporter.log("Multi-product perk.",true);
                confirmMultiProductPerk();

                List<WebElement> products = getProductList();
                List<String> productNames = getProductNameList(products);
                int i = 0;
                for (String productName : productNames) {
                    i++;
                    Reporter.log("Found Product " + i + "> " + productName,true);

                }
            } else if (!wrapper.findElements(By.className("service")).isEmpty()) {
                // 3 product
                waitForPerkHeaderLogo();
                Reporter.log("Checking Perk> " + client.getCurrentUrl(), true);
                Reporter.log("3 Product perk.",true);
                confirmThreeProductElements();

                List<WebElement> offers = getOffers();
                List<String> offerNames = getOfferName(offers);
                int i = 0;
                for (String offerName : offerNames) {
                    i++;
                    Reporter.log("Found Offer " + i +"> " + offerName,true);
                }

            } else if (!wrapper.findElements(By.className("product-dashboard")).isEmpty()) {
                waitForPerkHeaderLogo();
                Reporter.log("Checking Perk> " + client.getCurrentUrl(), true);
                Reporter.log("Regular perk");
                confirmRegularPerkElements();
            } else {
                Reporter.log("Mystery perk!",true);
            }
        }
    }

    public void checkPerkPage(String link) {
        WebDriver client = getDriver();

        client.get(link);
        WebElement wrapper = client.findElement(By.className("wrapper"));

        waitForPerkHeaderLogo();
        Reporter.log("Checking Perk> " + client.getCurrentUrl(), true);

        if (!wrapper.findElements(By.className("products")).isEmpty()) {
            // Multi product
            Reporter.log("Multi-product perk.",true);
            confirmMultiProductPerk();

            List<WebElement> products = getProductList();
            List<String> productNames = getProductNameList(products);
            int i = 0;
            for (String productName : productNames) {
                i++;
                Reporter.log("Found Product " + i + "> " + productName,true);

            }
        } else if (!wrapper.findElements(By.className("service")).isEmpty()) {
            // 3 product
            Reporter.log("3 Product perk.",true);
            confirmThreeProductElements();

            List<WebElement> offers = getOffers();
            List<String> offerNames = getOfferName(offers);
            int i = 0;
            for (String offerName : offerNames) {
                i++;
                Reporter.log("Found Offer " + i +"> " + offerName,true);
            }

        } else if (!wrapper.findElements(By.className("product-dashboard")).isEmpty()) {
            Reporter.log("Regular perk");
            confirmRegularPerkElements();

        } else {
            Reporter.log("Mystery perk!",true);
        }
    }

    public void confirmMultiProductPerk() {
        WebDriver client = getDriver();

        SoftAssert m_assert = new SoftAssert();
        m_assert.assertTrue(!client.findElements(By.className("copy")).isEmpty(),"Checked for Copy, not found.");
        m_assert.assertTrue(!client.findElements(By.className("products")).isEmpty(),"Checked for Products, not found.");
        m_assert.assertAll();
    }

    /**
     * Get the list of products on the page
     * @return list
     */
    public List<WebElement> getProductList() {
        WebDriver client = getDriver();

        WebElement products = client.findElement(By.cssSelector(".products"));
        return products.findElements(By.cssSelector(".product"));
    }

    public List<String> getProductNameList(List<WebElement> products) {
        List<String> list = new ArrayList<String>();
        for (WebElement product : products) {
            list.add(product.findElement(By.cssSelector(".title")).getText().trim());
        }
        return list;
    }
    /**
     * Performs asserts on the regular perk style page
     * Checks for the Get It Now button.
     * Checks the exact position of the get it now button
     * Checks for the price
     * Checks for the everyone else price
     * Checks for the counter
     *
     */
    public void confirmRegularPerkElements() {
        SoftAssert m_assert = new SoftAssert();
        m_assert.assertTrue(checkGetItNow(), "Check for Get It Now");
        m_assert.assertTrue(checkGetItPosition(), "Check Get It Now Position");
        m_assert.assertTrue(checkUDPrice(), "Check UD Price");
        // Everyone else price not required
        //m_assert.assertTrue(checkEveryoneElsePrice(), "Check Everyone Else Price");
        // Counters are not required for perks
        //m_assert.assertTrue(checkCounterHolder(), "Check Counter Holder");
        m_assert.assertAll();
    }

    /**
     * Perform asserts on the 3 product perk
     * Currently checks that copy and offers exist on the page
     *
     */
    public void confirmThreeProductElements() {
        WebDriver client = getDriver();

        SoftAssert m_assert = new SoftAssert();
        m_assert.assertTrue(!client.findElements(By.className("copy")).isEmpty(),"Checked for Copy, not found.");
        m_assert.assertTrue(!client.findElements(By.className("offers")).isEmpty(),"Checked for offers, not found.");
        m_assert.assertAll();
    }

    /**
     * Wait for the header logo to appear on the perk page
     */
    public void waitForPerkHeaderLogo() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-logo")));
    }

    /**
     * Get the list of offers found on a multi offer perk
     *
     * @return List of offers as WebElements
     */
    public List<WebElement> getOffers() {
        WebDriver client = getDriver();

        WebElement offerBlock = client.findElement(By.className("offers"));
        return offerBlock.findElements(By.className("offer"));
    }

    /**
     * Get the list of the offer names found on the perk
     * page.
     * @param offers WebElement
     * @return List of offer names
     */
    public List<String> getOfferName(List<WebElement> offers) {
        List<String> list = new ArrayList<String>();
        for (WebElement offer : offers) {
            list.add(offer.findElement(By.className("name")).getText().trim());
        }
        return list;
    }

    /**
     * Check the position of the Get It Now button. Should be 39 as long as copy
     * doesn't wrap it over.
     * @return boolean
     */
    public boolean checkGetItPosition() {
        WebDriver client = getDriver();

        return checkGetItNow() && client.findElement(By.className("book-now")).getAttribute("offsetLeft").equals("39");
    }

    /**
     * Click the Get It Now link
     */
    public void clickGetItNow() {
        WebDriver client = getDriver();

        if (checkGetItNow()) {
            client.findElement(By.className("book-now")).click();
        }
    }

    /**
     * Check for the existence of the UD Member price
     * @return boolean
     */
    public boolean checkUDPrice() {
        WebDriver client = getDriver();

        return client.findElements(By.className("value-block")).get(0).isDisplayed();
    }

    /**
     * Check for the existence of the Everyone Else price
     * @return boolean
     */
    public boolean checkEveryoneElsePrice() {
        WebDriver client = getDriver();

        if (client.findElements(By.className("value-block")).size() > 1) {
            return client.findElements(By.className("value-block")).get(1).isDisplayed();
        }

        return false;
    }

    /**
     * Check that the counter is displayed
     *
     * @return boolean
     */
    public boolean checkCounterHolder() {
        WebDriver client = getDriver();

        return client.findElement(By.className("counter-holder")).isDisplayed();
    }

    /**
     * Check that Get It Now is displayed
     * @return boolean
     */
    public boolean checkGetItNow() {
        WebDriver client = getDriver();

        return client.findElement(By.className("book-now")).isDisplayed();
    }

    /**
     * Check if the Age Popup Displays
     * @return boolean
     */
    public boolean checkAgePopup() {
        WebDriver client = getDriver();

        return client.findElement(By.className("verifyScreen")).isDisplayed();
    }

    public void getToPerkAFriendPerk(int type) {

        if ((type == 1) || (type == 2)) {

        }

    }

    public void perkAFriend(String email) {
        WebDriver client = getDriver();

        perks_productPageHelper_client = new Perks_ProductPageHelper_Client(client);

        perks_productPageHelper_client.getPerkAFriendEmailBox().clear();
        perks_productPageHelper_client.getPerkAFriendEmailBox().sendKeys(email);
        perks_productPageHelper_client.getPerkAFriendEmailSendButton().click();

        Assert.assertTrue(perks_productPageHelper_client.getPerkAFriendConfirmationText().getText().contains("Your Perk has been sent"),"Perk did not get sent");
    }
    /**
     * When purchasing a perk, and not signed in,
     * age verification popup can appear.
     */
    public void handleAgePopup() {
        WebDriver client = getDriver();

        Select mo = new Select(client.findElement(By.xpath(".//*[@id='modalContent']/div[3]/div[2]/div/div[3]/div[1]/div/div[2]/div[1]")));
        mo.selectByVisibleText("January");
        Select day = new Select(client.findElement(By.xpath(".//*[@id='modalContent']/div[3]/div[2]/div/div[3]/div[2]/div/div[2]/div[1]")));
        day.selectByVisibleText("20");
        Select year = new Select(client.findElement(By.xpath(".//*[@id='modalContent']/div[3]/div[2]/div/div[3]/div[3]/div/div[2]/div[1]")));
        year.selectByVisibleText("1970");
    }



    // Perks creation methods below

    public void adminPerksLogin() {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        client.get(PERKS_ADMIN_DOMAIN);
        pmhc.getUserNameTextBox().sendKeys(PERKS_ADMIN_USERNAME);
        pmhc.getPasswordTextBox().sendKeys(PERKS_ADMIN_PW);
        pmhc.getLoginButton().submit();
    }

    public void adminCreatePerk(String perkType) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client, GLOBAL_TIME_OUT, GLOBAL_POLLING);
        pmhc = new Perks_MagentoHelper_Client(client);

        // Handle notification window
        if (!client.findElements(By.id("message-popup-window")).isEmpty()) {
            pmhc.getMessagePopupWindow().findElement(By.xpath("/html/body/div/div[4]/div/a")).click();
        }

        // Click on manage products under Catalog--Get the URL and open that instead.
        client.get(pmhc.getManageProductsLink().getAttribute("href"));

        // click Add Product,
        pmhc.getAddProductButton().click();

        // Change Attribute Set to Perk
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("attribute_set_id")));
        Select attributeSet = new Select(pmhc.getAttributeSetDropDown());
        attributeSet.selectByVisibleText("Perk");

        // Set Product Type to Simple Product,
        Select productType = new Select(pmhc.getProductTypeDropDown());
        productType.selectByVisibleText(perkType);
        // click continue,
        pmhc.getContinueButton().findElement(By.tagName("button")).click();
    }

    public void adminCreatePerkGeneral(String perkType) {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        goBackToTop();
        String paragraph = "No phone no lights no motor car not a single luxury. Like Robinson Crusoe it's primitive as can be. All of them had hair of gold like their mother the youngest one in curls. The Love Boat soon will be making another run. The Love Boat promises something for everyone. All of them had hair of gold like their mother the youngest one in curls. black gold Sunday Monday Happy Days. Tuesday Wednesday Happy Days. Thursday Friday Happy Days.Saturday what a day.";
        String finePrint = "<ul>\n" +
                "<li>This Perk requires a Perk Certificate, which will be generated within 24 hours of purchase. </li>\n" +
                "<li>Orders placed on Friday or over the weekend will be processed the following business day. </li>\n" +
                "<li>Offer cannot be combined with other offers. </li>\n" +
                "<li>The code found on your Perk Certificate will be your unique promo code for the Hickoree&rsquo;s website.</li>\n" +
                "</ul>";
        String desc = "<p>Culpa  carles pinterest stumptown sunt, officia  cosby sweater mustache vegan.</p>\n" +
                "<p>Commodo mlkshk readymade pitchfork.  Marfa laboris  yr, put a bird on it  whatever artisan banksy cray authentic occupy adipisicing anim.</p>\n" +
                "<p>Adipisicing nulla  culpa  raw denim umami photo booth:</p>\n" +
                "<ul>\n" +
                "</ul>\n" +
                "<ul>\n" +
                "<li><strong>Magna swag velit, wolf shoreditch narwhal</strong> stumptown before they sold out  mixtape skateboard american apparel viral squid. </li>\n" +
                "<li><strong>Id  cillum   wayfarers, umami whatever </strong>keytar food truck nulla  odd future pop-up  mixtape VHS quis. </li>\n" +
                "<li><strong>High life lomo biodiesel post-ironic,</strong> direct trade   tempor squid voluptate  mcsweeney's butcher. </li>\n" +
                "<li>Street art keytar occaecat   freegan kale chips, tattooed banksy polaroid next level. </li>\n" +
                "<li>Voluptate    vinyl laboris, <em>mollit  cardigan labore small batch</em> ethical chillwave   gastropub 8-bit truffaut ut pour-over kale chips. </li>\n" +
                "<li>Blog reprehenderit   vice portland, polaroid whatever post-ironic farm-to-table. </li>\n" +
                "<li>Forage  iphone DIY, <span style=\"text-decoration: underline;\">reprehenderit  authentic hella</span> typewriter.</li>\n" +
                "</ul>\n" +
                "<ul>\n" +
                "</ul>";

        // Under GENERAL tab put data in:
        // Business name
        pmhc.getBizNameTextBox().sendKeys("QA Test Biz" + r);

        // Fine Print
        pmhc.getAddtlInfoTextBox().clear();
        pmhc.getAddtlInfoTextBox().sendKeys(finePrint);

        // Description
        pmhc.getDescriptionTextBox().clear();
        pmhc.getDescriptionTextBox().sendKeys(desc);

        // Subject
        String internalName = "QA " + perkType + " Test Subject " + r;
        pmhc.getSubjectTextBox().sendKeys(internalName);

        // Offer
        pmhc.getOfferTextBox().clear();
        pmhc.getOfferTextBox().sendKeys("QA Test Offer " + r);

        // Offer Summation
        pmhc.getOfferSumTextBox().sendKeys(paragraph);

        // Internal name
        pmhc.getInternalNameTextBox().sendKeys("QA Test Internal Name " + r);

        // SKU
        pmhc.getSKUTextBox().sendKeys("QA" + generateDate(DATEFORMAT) + r);

        // Show Qty set to no
        Select showQty = new Select(pmhc.getShowQtyDropDown());
        showQty.selectByVisibleText("No");

        // Module Headline
        pmhc.getModuleHeadlineTextBox().clear();
        pmhc.getModuleHeadlineTextBox().sendKeys("QA " + perkType + " Test Headline " + r);

        // Iphone Headline
        pmhc.getIphoneHeadlineTextBox().clear();
        pmhc.getIphoneHeadlineTextBox().sendKeys("QA Test Iphone Headline " + r);

        // Item Page Headline
        pmhc.getItemPageHeadlineTextBox().clear();
        pmhc.getItemPageHeadlineTextBox().sendKeys("QA " + perkType + " Test IPHeadline " + r);

        // change status to Enabled
        Select status = new Select(pmhc.getStatusDropDown());
        status.selectByVisibleText("Enabled");
    }

    public void adminCreatePerkDashboard () {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        goBackToTop();

        pmhc.getDashboardPageLink().click();

        pmhc.getDB1TextMiddleTextBox().clear();
        pmhc.getDB1TextMiddleTextBox().sendKeys("<sup>$</sup>1");
        pmhc.getDB1TextTopTextBox().clear();
        pmhc.getDB1TextTopTextBox().sendKeys("UD MEMBERS");

        pmhc.getDB2TextMiddleTextBox().clear();
        pmhc.getDB2TextMiddleTextBox().sendKeys("<grey><sup>$</sup><strike>100</strike></grey>");
        pmhc.getDB2TextTopTextBox().clear();
        pmhc.getDB2TextTopTextBox().sendKeys("EVERYONE ELSE");

        pmhc.getDB3TextMiddleTextBox().clear();
        pmhc.getDB3TextMiddleTextBox().sendKeys("99%");
        pmhc.getDB3TextTopTextBox().clear();
        pmhc.getDB3TextTopTextBox().sendKeys("SAVING");

        Select dashPos1 = new Select(pmhc.getDBPos1DropDown());
        dashPos1.selectByVisibleText("Dashboard 1");

        Select dashPos2 = new Select(pmhc.getDBPos2DropDown());
        dashPos2.selectByVisibleText("Dashboard 2");

        Select dashPos3 = new Select(pmhc.getDBPos3DropDown());
        dashPos3.selectByVisibleText("Dashboard 3");

        Select dashPos4 = new Select(pmhc.getDBPos4DropDown());
        dashPos4.selectByVisibleText("Countdown");
    }

    public void adminCreatePerkInventoryCounters() {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        goBackToTop();
        // INVENTORY COUNTERS:
        // Click Inventory Counters
        pmhc.getInvCountersPageLink().click();

        // Choose future date for Timer End Date
        pmhc.getEndDateTextBox().sendKeys(getFutureDate(3));

        // Put 1200 in Timer End Hour
        pmhc.getEndHourTextBox().clear();
        pmhc.getEndHourTextBox().sendKeys("1200");

        // Choose D in Timer Format
        Select timerFormat = new Select(pmhc.getTimerFormatDropDown());
        timerFormat.selectByVisibleText("D");
    }

    public void adminCreatePerkPrice() {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        goBackToTop();
        // PRICES:
        // Click Prices
        pmhc.getPricePageLink().click();

        // cost 0
        pmhc.getCostTextBox().sendKeys("0");

        // Price 1.00
        pmhc.getPriceTextBox().sendKeys("1.00");

        // tax class none
        Select taxClass = new Select(pmhc.getTaxClassDropDown());
        taxClass.selectByVisibleText("None");
    }

    public void adminCreatePerkImages() {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        goBackToTop();
        // IMAGES
        // Click Images
        pmhc.getImagesPageLink().click();
        // click browse files

        // Choose an image
        // Click upload files
        // Select that image as the Large Module
    }

    public void adminCreatePerkAssociatedProducts(List<String> perks) {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        goBackToTop();
        // ASSOCIATED PRODUCTS
        // Click associated products
        JavascriptExecutor js = (JavascriptExecutor) client;
        js.executeScript("document.getElementById(\"product_info_tabs_super\").click()");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("super_product_grid_filter_name")));

        List<WebElement> listElements = client.findElements(By.cssSelector("#super_product_grid_table>tbody>tr"));

        for (String perk : perks) {
            for (WebElement listElement : listElements) {
                if (getPerkToAssociate(listElement).contains(perk)) {
                    clickPerkToAssociate(listElement);
                }
            }
        }

    }

    public String getPerkToAssociate(WebElement listElement) {
        return listElement.findElement(By.cssSelector("td:nth-child(3)")).getText().trim();
    }

    public void clickPerkToAssociate(WebElement listElement) {
        listElement.findElement(By.cssSelector("td:nth-child(1)>input")).click();
    }

    public void adminCreatePerkInventory() {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        goBackToTop();
        // INVENTORY:
        // Click inventory
        pmhc.getInventoryPageLink().click();

        // Change manage stock to Yes
        pmhc.getManageStockCheckBox().click();
        Select manageStock = new Select(pmhc.getManageStockDropDown());
        manageStock.selectByVisibleText("Yes");

        Select inventoryStockAvailability = new Select(pmhc.getStockAvailDropDown());
        inventoryStockAvailability.selectByVisibleText("In Stock");

        // Change Qty to 1000
        if (!client.findElements(By.id("inventory_qty")).isEmpty()) {
            pmhc.getInventoryQtyTextBox().clear();
            pmhc.getInventoryQtyTextBox().sendKeys("1000");
        }


    }

    public void adminCreatePerkWebSite() {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        goBackToTop();
        // WEBSITES:
        // Click Websites
        pmhc.getWebSitePageLink().click();

        // Check Main Website
        pmhc.getMainWebSiteCheckBox().click();
    }

    /**
     * Selects the city/category the perk should appear in.
     */
    public void adminCreatePerkCategories() {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        goBackToTop();
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        // CATEGORIES:
        // Click Categories
        pmhc.getCategoriesPageLink().click();

        // Wait for tree to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(pmhc.getWaitByLocator("ProductCategoryTree")));

    }

    /**
     * Select categories
     * @param city Possible choices: // National, New York, Chicago,
     *                    Miami, Boston, Washington DC, Los Angeles
     */
    public void selectProductCategory(String city) {
        WebElement el = getProductCategories(city);
        el.click();
    }


    /**
     * Used in adminCreatePerkCategories()
     * @param city Possible choices: // National, New York, Chicago,
     *             Miami, Boston, Washington DC, Los Angeles
     * @return WebElement
     */
    private WebElement getProductCategories(String city) {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        WebElement el = null;
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        List<WebElement> categories = pmhc.getProductCategories();
        for (WebElement category : categories) {
            if (category.getText().toLowerCase().contains(city.toLowerCase())) {
                el = category;
            }
        }

        return el;
    }

    public void adminCreatePerkReporting() {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        goBackToTop();
        // REPORTING
        // Click Reporting
        pmhc.getReportingPageLink().click();

        // Input Rev Share
        pmhc.getRevShareTextBox().sendKeys("54321");
    }

    public void adminCreatePerkModuleSize() {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        wait = new WebDriverWait(client,PERKSADMIN_GLOBAL_TIME_OUT,GLOBAL_POLLING);
        goBackToTop();
        // MODULE
        // Click Module
        pmhc.getSizePageLink().click();

        // Pick a random size
        String[] sizes = {"large","medium","small","tall"};
        Random gen = new Random();
        int r = (gen.nextInt(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("module_size")));

        Select moduleSize = new Select(pmhc.getSizeDropDown());
        moduleSize.selectByVisibleText(sizes[r]);
    }

    public void adminCreatePerkSave() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,PERKSADMIN_GLOBAL_TIME_OUT,GLOBAL_POLLING);
        // click Save
        List<WebElement> els = client.findElement(By.cssSelector(".content-buttons")).findElements(By.tagName("button"));
        for (WebElement el : els) {
            if(el.getAttribute("title").equals("Save")) {
                el.click();
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(pmhc.getWaitByLocator("SuccessMessage")));
    }

    public void adminSetDownloadableInfo() {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        wait = new WebDriverWait(client,PERKSADMIN_GLOBAL_TIME_OUT,GLOBAL_POLLING);
        pmhc.getDownloadablePageLink().click();

        goBackToTop(); // So things are visible again

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#add_link_item")));

        Select dd = new Select(pmhc.getPurchaseTypeDropDown());
        dd.selectByVisibleText("No");

        goBackToTop(); // So things are visible again

        pmhc.getLinkItemButton().click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#link_items_body>tr:nth-child(1)>td:nth-child(1)>input:nth-child(3)")));
        client.findElement(By.cssSelector("#link_items_body>tr:nth-child(1)>td:nth-child(1)>input:nth-child(3)")).sendKeys("CERTIFICATE");
        client.findElement(By.cssSelector("#link_items_body>tr:nth-child(1)>td:nth-child(6)>div:nth-child(3)>div:nth-child(2)>label>input")).click();
        client.findElement(By.cssSelector("#link_items_body>tr:nth-child(1)>td:nth-child(6)>div:nth-child(3)>div:nth-child(2)>input")).sendKeys("CERTIFICATE");
    }

    public void makePerkVisible(String city) {
        WebDriver client = getDriver();

        pmhc = new Perks_MagentoHelper_Client(client);

        wait = new WebDriverWait(client,PERKSADMIN_GLOBAL_TIME_OUT,GLOBAL_POLLING);
        // The perk is now created, but to make it appear on the site you must:
        // click on Manage Categories under Catalog,
        // choose the edition you want the perk to show in

        WebElement e = null;
        List<WebElement> editions = client.findElements(By.cssSelector("#tree-div .x-tree-root-node>li:nth-child(1)>ul>.x-tree-node a"));
        for (WebElement edition : editions) {
            if (edition.getText().toLowerCase().contains(city.toLowerCase())) {
                e = edition;
            }
        }
        if (e != null) {
            e.click();
        } else {
            throw new IllegalArgumentException("Could not find an edition to click");
        }

        // Wait for the spinner to go away
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_mask_loader")));

        // Return to the top of the page to remove overlay
        if (getFloaterStatus()) {
            goBackToTop();
        }

        // click on the Category Products tab
        client.findElement(By.id("category_info_tabs_products")).click();
        // wait for table to show up
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#catalog_category_products")));
        // Reorder the positions of all the products listed 1-X
        List<WebElement> rows = client.findElements(By.cssSelector("#catalog_category_products_table>tbody>tr"));
        for (int i = 0; i < rows.size() ;i++) {
            rows.get(i).findElements(By.tagName("td")).get(5).findElement(By.tagName("input")).clear();
            rows.get(i).findElements(By.tagName("td")).get(5).findElement(By.tagName("input")).sendKeys(Integer.toString(i+1));
        }

        // click Save Category
        List<WebElement> els = client.findElement(By.cssSelector(".content-buttons")).findElements(By.tagName("button"));
        for (WebElement el : els) {
            if(el.getAttribute("title").equals("Save Category")) {
                el.click();
                break;
            }
        }
        // Wait for success message
        wait.until(ExpectedConditions.visibilityOfElementLocated(pmhc.getWaitByLocator("SuccessMessage")));
    }

    /**
     * Get the manage categories link
     * Used before makePerkVisible()
     */
    public void getManageCategories() {
        WebDriver client = getDriver();

        client.get(client.findElement(By.xpath("/html/body/div/div/div[3]/ul/li[3]/ul/li[2]/ul/li/a")).getAttribute("href"));
    }

    /**
     * Generate a date in the future for the inventory
     * counters settings. See adminCreatePerkInventoryCounters()
     * @param days
     * @return String
     */
    private String getFutureDate(int days) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,days);
        return dateFormat.format(c.getTime());
    }

    /**
     * Use this hack to get the page to return to the top so
     * elements that randomly get hidden behind the helper bar
     * which appears when you scroll down the page can be seen/used.
     *
     */
    private void goBackToTop() {
        WebDriver client = getDriver();

        client.findElement(By.cssSelector(".header-top")).click();
    }

    /**
     * Use this method to search for perks using the name field
     * on the manage product page
     * @param str Name of Perk
     */
    public void adminSearchByPerkName(String str) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        WebElement nameSearch = client.findElement(By.cssSelector("#productGrid_product_filter_name"));
        nameSearch.sendKeys(str);

        WebElement searchButton = client.findElement(By.cssSelector(".filter-actions.a-right>.scalable.task"));
        searchButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-mask")));

        // Make sure we get the most so we can delete everything
        Select countDD = new Select(client.findElement(By.cssSelector(".pager>select")));
        countDD.selectByVisibleText("200");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-mask")));

    }

    public List<WebElement> adminGetPerkList() {
        WebDriver client = getDriver();

        return client.findElements(By.cssSelector("#productGrid_table tbody>tr"));
    }

    /**
     * Given the webelement locator of the perk, this will navigate to that
     * perk then delete it
     * @param perks List of Perks to delete
     */
    public void adminDeletePerks(List<WebElement> perks) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        List<String> perkUrls = new ArrayList<String>();

        for (WebElement perkUrl : perks) {
            perkUrls.add(perkUrl.getAttribute("title"));
        }

        for (String url : perkUrls) {
            client.get(url);
            Reporter.log("Deleting> " + url,true);

            WebElement delBtn = client.findElement(By.cssSelector(".content-buttons.form-buttons>button:nth-child(3)"));

            if (delBtn.getAttribute("title").equals("Delete")) {
                delBtn.click();
                client.switchTo().alert().accept();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success-msg")));
            } else {
                throw new IllegalArgumentException("Delete button missing or element changes");
            }

        }
    }

    // Perk confirm tests

    // Perk Purchase Tests

    /**
     * Get the URL of the perk you want to purchase
     * @param url Perk URL
     */
    public void navigateToPerkToPurchase(String url) {
        WebDriver client = getDriver();

        client.get(url);
    }

    public void addPerkToCart() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".book-now>h1")));
        client.findElement(By.cssSelector(".book-now>h1")).click();
    }

    public void addGroupedPerkToCart() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".book-now>h1")));
        List<WebElement> groupedPerks = client.findElements(By.cssSelector(".book-now>h1"));

        // Initialize perkToBuy
        WebElement perkToBuy = null;

        // Find the first perk on the item page that's purchasable
        for (WebElement groupedPerk : groupedPerks) {
            if (groupedPerk.getText().toLowerCase().contains("now")) {
                perkToBuy = groupedPerk;
                break;
            }
        }

        // If perkToBuy remains null throw error
        if (perkToBuy != null) {
            perkToBuy.click();
        } else {
            throw new IllegalArgumentException("No purchasable perks on page: " + client.getCurrentUrl());
        }

    }

    public void checkOutWithPerk() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);
        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_checkOutHelper_client.getWaitByLocator("checkoutButton")));
        perks_checkOutHelper_client.getCheckoutButton().click();
    }

    public void fillInPersonalInformation(String fName, String lName) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);

        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_checkOutHelper_client.getWaitByLocator("firstName")));

        // If First Name is blank fill it in.
        if (perks_checkOutHelper_client.getFirstNameBox().getAttribute("Value").equals("")) {
            perks_checkOutHelper_client.getFirstNameBox().sendKeys(fName);
        }

        // If Last Name is blank fill it in.
        if (perks_checkOutHelper_client.getLastNameBox().getAttribute("Value").equals("")) {
            perks_checkOutHelper_client.getFirstNameBox().sendKeys(lName);
        }

    }

    public void fillInPaymentInformation(String CCType, String CCNumber, String CCMonth, String CCYear) {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);

        WebElement card;
        WebElement mo;
        WebElement yr;

        // If Name on Card is blank fill it in
        if (perks_checkOutHelper_client.getNameOnCardBox().getAttribute("Value").equals("")) {
            perks_checkOutHelper_client.getNameOnCardBox().sendKeys("Jenkins Tester");
        }

        // Select Credit Card Type
        JavascriptExecutor js = (JavascriptExecutor) client;
        js.executeScript("document.getElementsByClassName('overthrow')[0].style.setProperty('display','')");

        List<WebElement> ccTypes = perks_checkOutHelper_client.getCreditCardDropDownOptions();
        for (WebElement ccType : ccTypes) {
            if (ccType.getText().toLowerCase().equals(CCType.toLowerCase())) {
                Reporter.log(ccType.getText(),true);
                card = ccType;
                card.click();
                Reporter.log("CLICKED CREDIT CARD",true);
                break;
            }
        }

        // Enter the credit card number
        perks_checkOutHelper_client.getCreditCardNumberBox().sendKeys(CCNumber);

        // Select Expiration Month
        js.executeScript("document.getElementsByClassName('overthrow')[1].style.setProperty('display','')");

        List<WebElement> months = perks_checkOutHelper_client.getExpMonthDropDownOptions();
        for (WebElement month : months) {
            if (month.getText().toLowerCase().contains(CCMonth.toLowerCase())) {
                Reporter.log(month.getText(),true);
                mo = month;
                mo.click();
                Reporter.log("CLICKED MONTH",true);
                break;
            }
        }

        // Select Expiration Year
        js.executeScript("document.getElementsByClassName('overthrow')[2].style.setProperty('display','')");

        List<WebElement> years = perks_checkOutHelper_client.getExpYearDropDownOptions();
        for (WebElement year : years) {
            if (year.getText().equals(CCYear)) {
                Reporter.log(year.getText(),true);
                yr = year;
                yr.click();
                Reporter.log("CLICKED YEAR",true);
                break;
            }
        }
    }

    public void fillInBillingInformation() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);

        // If First Name is blank fill it in
        if (perks_checkOutHelper_client.getNameOnCardBox().getAttribute("Value").equals("")) {
            perks_checkOutHelper_client.getBillingFirstNameBox().sendKeys("Jenkins");
        }

        // If Last Name is blank fill it in
        if (perks_checkOutHelper_client.getNameOnCardBox().getAttribute("Value").equals("")) {
            perks_checkOutHelper_client.getBillingLastNameBox().sendKeys("Tester");
        }

        perks_checkOutHelper_client.getBillingStreetAddress1Box().sendKeys("123 Tester Lane");
        perks_checkOutHelper_client.getBillingStreetAddress2Box().sendKeys("Apt 321");
        perks_checkOutHelper_client.getBillingCityBox().sendKeys("New York");

        // Select Billing State
        WebElement st;

        JavascriptExecutor js = (JavascriptExecutor) client;
        js.executeScript("document.getElementsByClassName(\"overthrow\")[3].style.setProperty(\"display\",\"\")");

        List<WebElement> states = perks_checkOutHelper_client.getBillingStateDropDownOptions();
        for (WebElement state : states) {
            if (state.getText().equals("New York")) {
                Reporter.log(state.getText(),true);
                st = state;
                st.click();
                break;
            }
        }
        // Must use Javascript to fill in the Zip Code and Telephone numbers because of auto-formatting
        js.executeScript("document.getElementById('billing_zip').value = 10001");
        js.executeScript("document.getElementById('billing_telephone').value = '(212) 123-4321'");
    }

    public void clickShippingSameAsBilling() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);

        perks_checkOutHelper_client.getShippingSameAsBillingCheckbox().click();
    }

    public void fillInShippingInformation() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);

        // If First Name is blank fill it in
        if (perks_checkOutHelper_client.getNameOnCardBox().getAttribute("Value").equals("")) {
            perks_checkOutHelper_client.getShippingFirstNameBox().sendKeys("Jenkins");
        }

        // If Last Name is blank fill it in
        if (perks_checkOutHelper_client.getNameOnCardBox().getAttribute("Value").equals("")) {
            perks_checkOutHelper_client.getShippingLastNameBox().sendKeys("Tester");
        }

        perks_checkOutHelper_client.getShippingStreetAddress1Box().sendKeys("900 Long Drive");
        perks_checkOutHelper_client.getShippingStreetAddress2Box().sendKeys("Suite 1");
        perks_checkOutHelper_client.getShippingCityBox().sendKeys("Beverly Hills");

        //How to select the state, someday maybe they'll make the dropdowns better
        JavascriptExecutor js = (JavascriptExecutor) client;
        js.executeScript("document.getElementsByClassName('overthrow')[4].style.setProperty('display','')");

        String state = "California";
        js.executeScript(
                "var a = document.getElementsByClassName('scroll-content')[4].getElementsByClassName('option');" +
                "var b = '" + state + "';" +
                "for (i=0;i<a.length;i++) {" +
                "    if (a[i].textContent == b) {" +
                "        a[i].click()" +
                "    }" +
                "};"
                );

        js.executeScript("document.getElementById('shipping_zip').value = 90210");
        js.executeScript("document.getElementById('shipping_telephone').value = '(212) 123-4321'");
    }

    public void firstTimeInfoContinueClick() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);

        perks_checkOutHelper_client.getContinueButton().click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".redBtn.cancelBtn.submitBtn>span")));
    }

    public void purchasePerks() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);
        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_checkOutHelper_client.getWaitByLocator("LeftSubmitOrderButton")));
        perks_checkOutHelper_client.getLeftSubmitOrderButton().click();
    }

    /**
     * Use this method on the order summary page to change the shipping info.
     * It will check for an option that is unchecked and select that to use instead.
     */
    public void changeShippingInfo() {
        WebDriver client = getDriver();

        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);
        perks_checkOutHelper_client.getChangeShipToAddressButton().click();
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        String checkOutAddress;
        String newAddress = "";
        // Wait for the modal to show
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modalContent>h1")));
        // Extra confirmation for the select button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".redBtn.directLink.selectBtn>span")));

        // Loop through available addresses find the one that isn't checked and select it
        List<WebElement> options = client.findElements(By.cssSelector(".options>.option"));
            for (WebElement option : options) {
                if (!option.findElement(By.tagName("input")).isSelected()) {
                    // Click the button
                    option.findElement(By.tagName("input")).click();
                    // Get the address text
                    newAddress = option.findElement(By.tagName("p")).getText().trim();
                    Reporter.log("ADDRESS>" + newAddress,true);
                    break;
                }
            }

        // Click the select button
        client.findElement(By.cssSelector(".redBtn.directLink.selectBtn>span")).click();

        // Wait for the modal to go away
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modalContent>h1")));
        checkOutAddress = client.findElement(By.cssSelector(".ship-to>p:nth-child(2)")).getText().trim();

        if (!newAddress.equals("")) {
            Assert.assertTrue(checkOutAddress.contains(newAddress),"Address didn't update.");
        } else {
            throw new IllegalArgumentException("Need a new address here");
        }

    }

    /**
     * Use this method on the order summary page to change the billing info.
     * It will check for an option that is unchecked and select that to use instead.
     */
    public void changeBillingInfo() {
        WebDriver client = getDriver();
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);
        perks_checkOutHelper_client.getChangeBillToAddressButton().click();
        JavascriptExecutor js = (JavascriptExecutor) client;

        String checkOutAddress;
        String newAddress = "";
        // Wait for the modal to show
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modalContent>h1")));
        // Extra confirmation for the select button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".redBtn.directLink.selectBtn>span")));

        // Loop through available addresses find the one that isn't checked and select it
        List<WebElement> options = client.findElements(By.cssSelector(".options>.option"));
        for (WebElement option : options) {
            if (!option.findElement(By.tagName("input")).isSelected()) {
                // Click the button
                option.findElement(By.tagName("input")).click();
                // Get the address text
                newAddress = option.findElement(By.tagName("p")).getText().trim();
//                Reporter.log("ADDRESS>" + newAddress,true);
                break;
            }
        }

        // Click the select button
        client.findElement(By.cssSelector(".redBtn.directLink.selectBtn>span")).click();

        // Wait for the modal to go away
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modalContent>h1")));
        checkOutAddress = client.findElement(By.cssSelector(".ship-to.last>p:nth-child(1)")).getText().trim();

//        Reporter.log("New Billing> \n" + newAddress,true);
//        Reporter.log("Compare to> \n" + checkOutAddress,true);

        if (!newAddress.equals("")) {
            Assert.assertTrue(checkOutAddress.contains(newAddress),"Address didn't update.");
        } else {
            throw new IllegalArgumentException("Need a new address here");
        }

    }

    public void addNewShippingAddress() {
        WebDriver client = getDriver();
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);
        perks_checkOutHelper_client.getChangeShipToAddressButton().click();
        JavascriptExecutor js = (JavascriptExecutor) client;
        String checkOutAddress;
        String newAddress = "";
        // Wait for the modal to show
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modalContent>h1")));

        client.findElement(By.cssSelector(".greyBtnBig.newBtn>span")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname")));

        client.findElement(By.id("firstname")).clear();
        client.findElement(By.id("firstname")).sendKeys("NewShippingFirst");

        client.findElement(By.id("lastname")).clear();
        client.findElement(By.id("lastname")).sendKeys("NewShippingLast");

        client.findElement(By.id("street_1")).sendKeys("55 Saint Lane");
        client.findElement(By.id("street_2")).sendKeys("Apt 2");

        client.findElement(By.id("city")).sendKeys("Kansas City");

        //Script exclusive for selecting state on add billing address page
        js.executeScript("document.getElementsByClassName('overthrow')[0].style.setProperty('display','')");

        String state = "Kansas";
        js.executeScript(
                "var a = document.getElementsByClassName('scroll-content')[0].getElementsByClassName('option');" +
                        "var b = '" + state + "';" +
                        "for (i=0;i<a.length;i++) {" +
                        "    if (a[i].textContent == b) {" +
                        "        a[i].click()" +
                        "    }" +
                        "};"
        );

        js.executeScript("document.getElementById('zip').value = 64101");
        js.executeScript("document.getElementById('telephone').value = '(717) 312-5342'");

        client.findElement(By.cssSelector(".redBtn.formSubmitLink>span")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".redBtn.directLink.selectBtn>span")));

        selectAddress("Kansas City");

        client.findElement(By.cssSelector(".redBtn.directLink.selectBtn>span")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".redBtn.cancelBtn.submitBtn>span")));

    }

    public void addNewBillingAddress() {
        WebDriver client = getDriver();
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);
        perks_checkOutHelper_client.getChangeBillToAddressButton().click();
        JavascriptExecutor js = (JavascriptExecutor) client;
        String checkOutAddress;
        String newAddress = "";
        // Wait for the modal to show
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modalContent>h1")));

        client.findElement(By.cssSelector(".greyBtnBig.newBtn>span")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname")));

        client.findElement(By.id("firstname")).clear();
        client.findElement(By.id("firstname")).sendKeys("NewBillingFirst");

        client.findElement(By.id("lastname")).clear();
        client.findElement(By.id("lastname")).sendKeys("NewBillingLast");

        client.findElement(By.id("street_1")).sendKeys("55 Saint Lane");
        client.findElement(By.id("street_2")).sendKeys("Apt 2");

        client.findElement(By.id("city")).sendKeys("Kansas City");

        //Script exclusive for selecting state on add billing address page
        js.executeScript("document.getElementsByClassName('overthrow')[0].style.setProperty('display','')");

        String state = "Kansas";
        js.executeScript(
                "var a = document.getElementsByClassName('scroll-content')[0].getElementsByClassName('option');" +
                        "var b = '" + state + "';" +
                        "for (i=0;i<a.length;i++) {" +
                        "    if (a[i].textContent == b) {" +
                        "        a[i].click()" +
                        "    }" +
                        "};"
        );

        js.executeScript("document.getElementById('zip').value = 64101");
        js.executeScript("document.getElementById('telephone').value = '(717) 312-5342'");

        client.findElement(By.cssSelector(".redBtn.formSubmitLink>span")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".redBtn.directLink.selectBtn>span")));

        selectAddress("Kansas City");

        client.findElement(By.cssSelector(".redBtn.directLink.selectBtn>span")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".redBtn.cancelBtn.submitBtn>span")));

    }

    /**
     * Used to select an address after clicking the change button in checkout.
     * @param str Search String
     */
    private void selectAddress(String str) {
        WebDriver client = getDriver();
        // Loop through available addresses find the one that isn't checked and select it
        List<WebElement> options = client.findElements(By.cssSelector(".options>.option"));
        for (WebElement option : options) {
            if (option.findElement(By.tagName("p")).getText().contains(str)) {
                option.findElement(By.tagName("input")).click();
            }
        }
    }

    public void addCreditCard() {
//        MasterCard 5555555555554444
//        American Express 378282246310005
//
//
        WebDriver client = getDriver();
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);

        perks_checkOutHelper_client.getChangeCCButton().click();

        wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(".modalContent>h1"),"Credit Card"));

        client.findElement(By.cssSelector(".greyBtnBig.newBtn>span")).click();

        wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(".modalContent>h1"),"Add New"));

        client.findElement(By.id("cc_cc_owner")).sendKeys("NewCard Person");

        client.findElement(By.id("cc_cc_number")).sendKeys("5555555555554444");

        JavascriptExecutor js = (JavascriptExecutor) client;
        js.executeScript("document.getElementsByClassName('overthrow')[0].style.setProperty('display','')");

        List<WebElement> ccTypes = perks_checkOutHelper_client.getCreditCardDropDownOptions();
        for (WebElement ccType : ccTypes) {
            if (ccType.getText().toLowerCase().equals("mastercard")) {
                Reporter.log(ccType.getText(),true);
                ccType.click();
                Reporter.log("CLICKED CREDIT CARD",true);
                break;
            }
        }

        // Select Expiration Month
        js.executeScript("document.getElementsByClassName('overthrow')[1].style.setProperty('display','')");

        List<WebElement> months = perks_checkOutHelper_client.getExpMonthDropDownOptions();
        for (WebElement month : months) {
            if (month.getText().toLowerCase().contains("march")) {
                Reporter.log(month.getText(),true);
                month.click();
                Reporter.log("CLICKED MONTH",true);
                break;
            }
        }

        // Select Expiration Year
        js.executeScript("document.getElementsByClassName('overthrow')[2].style.setProperty('display','')");

        List<WebElement> years = perks_checkOutHelper_client.getExpYearDropDownOptions();
        for (WebElement year : years) {
            if (year.getText().equals("2017")) {
                Reporter.log(year.getText(),true);
                year.click();
                Reporter.log("CLICKED YEAR",true);
                break;
            }
        }

        client.findElement(By.cssSelector(".redBtn.formSubmitLink>span")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".redBtn.directLink.selectBtn>span")));

        client.findElement(By.cssSelector(".redBtn.directLink.selectBtn>span")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modalContent")));

    }

    public void deleteCreditCard() {
        WebDriver client = getDriver();
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);

        perks_checkOutHelper_client.getChangeCCButton().click();

        wait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(".modalContent>h1"),"Credit Card"));

        List<WebElement> options = client.findElements(By.cssSelector(".options>.option"));
        for (WebElement option : options) {
            if (option.findElement(By.tagName("input")).isSelected()) {
                option.findElement(By.cssSelector(".button:nth-child(4)>a")).click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".button:nth-child(4)>a")));
            }
        }

        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("greyBtnBig processingLink")));

        client.findElement(By.cssSelector(".redBtn.directLink.selectBtn>span")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modalContent")));
    }

    public String confirmPurchase() {
        WebDriver client = getDriver();

        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        perks_checkOutHelper_client = new Perks_CheckOutHelper_Client(client);
        wait.until(ExpectedConditions.visibilityOfElementLocated(perks_checkOutHelper_client.getWaitByLocator("thePerkIsYoursText")));
        Reporter.log("CC Processed Amount> " + perks_checkOutHelper_client.getCCProcessedAmount().getText(), true);

        String url = client.getCurrentUrl();
        Reporter.log(url,true);

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(url);

        String orderNumber = "";

        if (m.find()) {
            orderNumber = m.group(0);
            Reporter.log("Order Number> " + m.group(0),true);
        }

        return orderNumber;
    }

    public String generateDate(String dateFormat) {
        //    DDD_HH_mm_SSS <-- should pass this format
        Date now = new java.util.Date();
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(now);
    }

    private String r;

    public String getR() {
        return this.r;
    }

    public void setR(String r) {
        this.r = r;
    }

    /**
     * Use this to display the CSS Hover for SignUp/My Account
     * in the header
     */
    public void displayCssHover() {
        WebDriver client = getDriver();

        JavascriptExecutor js = (JavascriptExecutor) client;
        js.executeScript("$j('#global-navigation .user .sub ul').css({'display': 'block'});");
    }

    public boolean getFloaterStatus() {
        WebDriver client = getDriver();

        JavascriptExecutor js = (JavascriptExecutor) client;
        return (Boolean) js.executeScript("return document.getElementsByClassName(\"content-header-floating\")[0].visible()");
    }
}