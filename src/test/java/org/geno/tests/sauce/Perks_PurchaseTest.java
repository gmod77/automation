package org.geno.tests.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.geno.com.helpers.EmailHelper_Client;
import org.geno.com.sauce.iTestCasePerksSauce;

import java.util.ArrayList;
import java.util.List;

public class Perks_PurchaseTest extends iTestCasePerksSauce {

    private String orderNumber;

    private String emailClient;
    private List<String> emailFriends;

    List<String> links;

    String groupedPerkUrl;
    String simplePerkUrl;
    String downLoadablePerkUrl;

    public Perks_PurchaseTest() {
    }


    private void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    private String getEmailClient() {
        return emailClient;
    }

    private void setEmailFriends(List<String> emailFriends) {
        this.emailFriends = emailFriends;
    }

    private List<String> getEmailFriends() {
        return emailFriends;
    }

//    @Parameters ({"city"})
//    @Test (groups = {"Start"})
//    public void getPurchasablePerks(String city) {
//        getSauceResultsUrl();
//
//        links = new ArrayList<String>();
//
//        visitPerksFirstTime(city);
//        Reporter.log("Number of perks> " + getPerksLinkCount());
//        simplePerkUrl = getPerkToPurchase(getEditionBlocks(),"simple");
//        groupedPerkUrl = getPerkToPurchase(getEditionBlocks(),"grouped");
//        downLoadablePerkUrl = getPerkToPurchase(getEditionBlocks(),"downloadable");
//
//    }

    @Parameters ({"city"})
    @Test (groups = {"purchase"})
    public void simplePerkPurchase(String city) {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);
        String email = emailHelper_Client.generateEmailClient();

        Reporter.log("Visiting " + city + " Page", true);
        visitPerksFirstTime(city);

        String simplePerkUrl = getPerkToPurchase(getEditionBlocks(),"simple");

        Reporter.log("Sign up as new user> " + email,true);
        signUpPerks_viaNewYorkStep1(email,false);
        signUpPerks_viaNewYorkStep2();
        signUpPerks_viaNewYorkStep3(getEmailFriends(), false);
        signUpPerks_viaNewYorkStep4();

        Reporter.log("Purchasing Perk on the following page:\n" + simplePerkUrl,true);
        navigateToPerkToPurchase(simplePerkUrl);

        Reporter.log("Adding perk to cart",true);
        addPerkToCart();

        Reporter.log("Checking out with perks",true);
        checkOutWithPerk();

        Reporter.log("Filling In Personal Information",true);
        fillInPersonalInformation("Jenkins","Tester");

        Reporter.log("Entering Payment Information",true);
        fillInPaymentInformation("Visa","4111111111111111","12","2015");

        Reporter.log("Entering Billing Information",true);
        fillInBillingInformation();

        Reporter.log("Entering Shipping Information",true);
        fillInShippingInformation();

        Reporter.log("Click proceeding to order confirmation page",true);
        firstTimeInfoContinueClick();

        Reporter.log("Purchasing perk",true);
        purchasePerks();

        Reporter.log("Confirming perk purchase",true);
        setOrderNumber(confirmPurchase());

    }

    @Parameters ({"city"})
    @Test (groups = {"purchase"})
    public void downloadablePerkPurchase(String city) {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);
        String email = emailHelper_Client.generateEmailClient();

        Reporter.log("Visiting " + city + " Page", true);
        visitPerksFirstTime(city);

        String downLoadablePerkUrl = getPerkToPurchase(getEditionBlocks(),"downloadable");

        Reporter.log("Sign up as new user> " + email,true);
        signUpPerks_viaNewYorkStep1(email,false);
        signUpPerks_viaNewYorkStep2();
        signUpPerks_viaNewYorkStep3(getEmailFriends(), false);
        signUpPerks_viaNewYorkStep4();

        Reporter.log("Purchasing Perk on the following page:\n" + downLoadablePerkUrl,true);
        navigateToPerkToPurchase(downLoadablePerkUrl);

        Reporter.log("Adding perk to cart",true);
        addPerkToCart();

        Reporter.log("Checking out with perks",true);
        checkOutWithPerk();

        Reporter.log("Filling In Personal Information",true);
        fillInPersonalInformation("Jenkins","Tester");

        Reporter.log("Entering Payment Information",true);
        fillInPaymentInformation("Visa","4111111111111111","12","2015");

        Reporter.log("Entering Billing Information",true);
        fillInBillingInformation();

        Reporter.log("Click proceeding to order confirmation page",true);
        firstTimeInfoContinueClick();

        Reporter.log("Purchasing perk",true);
        purchasePerks();

        Reporter.log("Confirming perk purchase",true);
        setOrderNumber(confirmPurchase());

    }

    @Parameters ({"city"})
    @Test (groups = {"purchase"})
    public void groupedPerkPurchase(String city) {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);
        String email = emailHelper_Client.generateEmailClient();

        Reporter.log("Visiting " + city + " Page", true);
        visitPerksFirstTime(city);

        String groupedPerkUrl = getPerkToPurchase(getEditionBlocks(),"grouped");

        Reporter.log("Sign up as new user> " + email,true);
        signUpPerks_viaNewYorkStep1(email,false);
        signUpPerks_viaNewYorkStep2();
        signUpPerks_viaNewYorkStep3(getEmailFriends(), false);
        signUpPerks_viaNewYorkStep4();

        Reporter.log("Purchasing Perk on the following page:\n" + groupedPerkUrl,true);
        navigateToPerkToPurchase(groupedPerkUrl);

        Reporter.log("Adding perk to cart",true);
        addGroupedPerkToCart();

        Reporter.log("Checking out with perks",true);
        checkOutWithPerk();

        Reporter.log("Filling In Personal Information",true);
        fillInPersonalInformation("Jenkins","Tester");

        Reporter.log("Entering Payment Information",true);
        fillInPaymentInformation("Visa","4111111111111111","12","2015");

        Reporter.log("Entering Billing Information",true);
        fillInBillingInformation();

        if (client.findElements(By.name("same-as-billing-checkbox")).size() > 0) {
            Reporter.log("Check billing same as shipping",true);
            clickShippingSameAsBilling();
        }

        Reporter.log("Click proceeding to order confirmation page",true);
        firstTimeInfoContinueClick();

        Reporter.log("Purchasing perk",true);
        purchasePerks();

        Reporter.log("Confirming perk purchase",true);
        setOrderNumber(confirmPurchase());

    }

    @Parameters ({"city"})
    @Test
    public void editShippingInfo(String city) {
        getSauceResultsUrl();

        Reporter.log("Visiting " + city + " Page", true);
        visitPerksFirstTime(city);

        String simplePerkUrl = getPerkToPurchase(getEditionBlocks(),"simple");

        Reporter.log("Logging in as existing user> " + JENKINSEMAIL,true);
        signInPerks(JENKINSEMAIL);

        Reporter.log("Purchasing Perk on the following page:\n" + simplePerkUrl,true);
        navigateToPerkToPurchase(simplePerkUrl);

        Reporter.log("Adding perk to cart",true);
        addPerkToCart();

        Reporter.log("Checking out with perks",true);
        checkOutWithPerk();

        Reporter.log("Change shipping info",true);
        changeShippingInfo();

    }

    @Parameters ({"city"})
    @Test
    public void editBillingInfo(String city) {

        Reporter.log("Visiting " + city + " Page", true);
        visitPerksFirstTime(city);

        Reporter.log("Getting simple perk for test", true);
        String simplePerkUrl = getPerkToPurchase(getEditionBlocks(),"simple");

        Reporter.log("Logging in as existing user> " + JENKINSEMAIL,true);
        signInPerks(JENKINSEMAIL);

        Reporter.log("Purchasing Perk on the following page:\n" + simplePerkUrl,true);
        navigateToPerkToPurchase(simplePerkUrl);

        Reporter.log("Adding perk to cart",true);
        addPerkToCart();

        Reporter.log("Checking out with perks",true);
        checkOutWithPerk();

        Reporter.log("Change Billing info",true);
        changeBillingInfo();

    }

    @Parameters ({"city"})
    @Test
    public void addBillingAddress(String city) {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);
        String email = emailHelper_Client.generateEmailClient();

        Reporter.log("Visiting " + city + " Page", true);
        visitPerksFirstTime(city);

        String simplePerkUrl = getPerkToPurchase(getEditionBlocks(),"simple");

        Reporter.log("Sign up as new user> " + email,true);
        signUpPerks_viaNewYorkStep1(email,false);
        signUpPerks_viaNewYorkStep2();
        signUpPerks_viaNewYorkStep3(getEmailFriends(), false);
        signUpPerks_viaNewYorkStep4();

        Reporter.log("Purchasing Perk on the following page:\n" + simplePerkUrl,true);
        navigateToPerkToPurchase(simplePerkUrl);

        Reporter.log("Adding perk to cart",true);
        addPerkToCart();

        Reporter.log("Checking out with perks",true);
        checkOutWithPerk();

        Reporter.log("Filling In Personal Information",true);
        fillInPersonalInformation("Jenkins","Tester");

        Reporter.log("Entering Payment Information",true);
        fillInPaymentInformation("Visa","4111111111111111","12","2015");

        Reporter.log("Entering Billing Information",true);
        fillInBillingInformation();

        Reporter.log("Entering Shipping Information",true);
        fillInShippingInformation();

        Reporter.log("Click proceeding to order confirmation page",true);
        firstTimeInfoContinueClick();

        Reporter.log("Adding New Billing Address",true);
        addNewBillingAddress();
    }

    @Parameters ({"city"})
    @Test
    public void addShippingAddress(String city) {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);
        String email = emailHelper_Client.generateEmailClient();

        Reporter.log("Visiting " + city + " Page", true);
        visitPerksFirstTime(city);

        Reporter.log("Getting simple perk for test", true);
        String simplePerkUrl = getPerkToPurchase(getEditionBlocks(),"simple");

        Reporter.log("Sign up as new user> " + email,true);
        signUpPerks_viaNewYorkStep1(email,false);
        signUpPerks_viaNewYorkStep2();
        signUpPerks_viaNewYorkStep3(getEmailFriends(), false);
        signUpPerks_viaNewYorkStep4();

        Reporter.log("Purchasing Perk on the following page:\n" + simplePerkUrl,true);
        navigateToPerkToPurchase(simplePerkUrl);

        Reporter.log("Adding perk to cart",true);
        addPerkToCart();

        Reporter.log("Checking out with perks",true);
        checkOutWithPerk();

        Reporter.log("Filling In Personal Information",true);
        fillInPersonalInformation("Jenkins","Tester");

        Reporter.log("Entering Payment Information",true);
        fillInPaymentInformation("Visa","4111111111111111","12","2015");

        Reporter.log("Entering Billing Information",true);
        fillInBillingInformation();

        Reporter.log("Entering Shipping Information",true);
        fillInShippingInformation();

        Reporter.log("Click proceeding to order confirmation page",true);
        firstTimeInfoContinueClick();

        Reporter.log("Adding New Shipping Address",true);
        addNewShippingAddress();
    }

    @Parameters ({"city"})
    @Test
    public void addAndDeleteCreditCard(String city) {
        WebDriver client = getDriver();
        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);
        String email = emailHelper_Client.generateEmailClient();

        Reporter.log("Visiting " + city + " Page", true);
        visitPerksFirstTime(city);

        Reporter.log("Getting simple perk for test", true);
        String simplePerkUrl = getPerkToPurchase(getEditionBlocks(),"simple");

        Reporter.log("Sign up as new user> " + email,true);
        signUpPerks_viaNewYorkStep1(email,false);
        signUpPerks_viaNewYorkStep2();
        signUpPerks_viaNewYorkStep3(getEmailFriends(), false);
        signUpPerks_viaNewYorkStep4();

        Reporter.log("Purchasing Perk on the following page:\n" + simplePerkUrl,true);
        navigateToPerkToPurchase(simplePerkUrl);

        Reporter.log("Adding perk to cart",true);
        addPerkToCart();

        Reporter.log("Checking out with perks",true);
        checkOutWithPerk();

        Reporter.log("Filling In Personal Information",true);
        fillInPersonalInformation("Jenkins","Tester");

        Reporter.log("Entering Payment Information",true);
        fillInPaymentInformation("Visa","4111111111111111","12","2015");

        Reporter.log("Entering Billing Information",true);
        fillInBillingInformation();

        Reporter.log("Entering Shipping Information",true);
        fillInShippingInformation();

        Reporter.log("Click proceeding to order confirmation page",true);
        firstTimeInfoContinueClick();

        Reporter.log("Adding new credit card",true);
        addCreditCard();

        Reporter.log("Deleting new credit card",true);
        deleteCreditCard();

    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

//    @Test (dependsOnGroups = {"perkSmoke"})
//    public void perksConfirm() throws Exception {
//        emailHelper_Client = new EmailHelper_Client(client);
//
//        emailHelper_Client.loginToGmail();
//
//        emailHelper_Client.verifyWelcomeUDEmailReceived(emailClient);
//        emailHelper_Client.verifyInvitationsUDEmailsReceived(emailFriends);
//        //verifyEditSettingsUDEmailReceived();
//        emailHelper_Client.verifyResetPasswordUDRequestReceivedandPasswordReset(emailClient);
//
//    }
}