package org.geno.tests.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.geno.com.helpers.EmailHelper_Client;
import org.geno.com.sauce.iTestCasePerksSauce;

import java.util.List;


public class Perks_RegressionTest extends iTestCasePerksSauce {

    private String emailClient;
    private List<String> emailFriends;

    public Perks_RegressionTest() {
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

    @Test (groups = {"perkSmoke","initialize"})
    public void perksSignUp() {
        WebDriver client = getDriver();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);
        getSauceResultsUrl();

        setEmailClient(emailHelper_Client.generateEmailClient());
        setEmailFriends(emailHelper_Client.generateFriendClient(5));

        Reporter.log("Visit Site for first time");
        visitPerksFirstTime();
        //1. client signs up and logs in

        Reporter.log("Sign up step 1");
        signUpPerks_viaNewYorkStep1(getEmailClient(),true);

        Reporter.log("Sign up step 2");
        signUpPerks_viaNewYorkStep2();

        Reporter.log("Sign up step 3");
        signUpPerks_viaNewYorkStep3(getEmailFriends(),true);

        Reporter.log("Sign up step 4");
        signUpPerks_viaNewYorkStep4();

        Reporter.log("Logging out");
        logoutPerks();

    }

    @Test (groups = {"perkSmoke"}, dependsOnGroups = "initialize" )
    public void perksEditAccount() {
        getSauceResultsUrl();

        Reporter.log("Visit Site for first time");
        visitPerksFirstTime();

        Reporter.log("Sign in to perks");
        signInPerks(getEmailClient());

        Reporter.log("Edit settings");
        editSettingsPerks();

        Reporter.log("Return to perks home page");
        returnToPerks();

        Reporter.log("Logout");
        logoutPerks();
    }

    @Test (groups = {"perkSmoke"}, dependsOnGroups = "initialize" )
    public void perksPasswordReset() {
        getSauceResultsUrl();

        Reporter.log("Visit site for the first time");
        visitPerksFirstTime();

        Reporter.log("Reset password");
        resetPasswordPerks(getEmailClient());

    }

    @Test (groups = {"perkSmoke","perkAFriend"}, dependsOnGroups = "initialize")
    public void perkAFriendExistingUser() {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        List<WebElement> editionBlocks;
        List<String> perkLinks;

        Reporter.log("Visit the National site");
        visitPerksFirstTime("national");

        Reporter.log("Sign into perks using" + "qa.test@geno.com");
        signInPerks("qa.test@geno.com");

        // Get the list of perks on the page and navigate to the first perk
        // We're going to perk-a-friend that first perk
        Reporter.log("Attempting to get the list of perks on the page and navigate to the first perk");
        WebDriverWait wait = new WebDriverWait(client,30,3000);
        // Takes a while for that sign in window to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modalbox")));
        editionBlocks = getEditionBlocks();
        perkLinks = getPerksLinks(editionBlocks);
        client.get(perkLinks.get(0));
        Reporter.log("Got> " + perkLinks.get(0));

        // Multiperk type perks don't have the email box on the first page of the perk
        // you have to click into a 2nd level to get to it.
        Reporter.log("Attempting to perk a friend> " + JENKINSEMAIL);
        if ((checkPerkPageType() == 2) || (checkPerkPageType() == 3) || (checkPerkPageType() == 0)) {
            perkAFriend(JENKINSEMAIL);
        } else if (checkPerkPageType() == 1) {
            String multiPerkLink = client.findElements(By.cssSelector(".product>.callout>a")).get(1).getAttribute("href");
            client.get(multiPerkLink);
            perkAFriend(JENKINSEMAIL);
        }
        Reporter.log("Friend perked");
    }

    @Test (dependsOnGroups = {"perkSmoke","initialize"})
    public void perksConfirm() {
        WebDriver client = getDriver();
        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);
        getSauceResultsUrl();

        Reporter.log("Log into Gmail");
        emailHelper_Client.loginToGmail();

        Reporter.log("Checking for 'Welcome to UD' email");
        emailHelper_Client.verifyWelcomeUDEmailReceived(getEmailClient());

        Reporter.log("Checking for 'Invitation' emails");
        emailHelper_Client.verifyInvitationsUDEmailsReceived(getEmailFriends());
        //verifyEditSettingsUDEmailReceived();

        Reporter.log("Checking for 'Password Reset' email");
        emailHelper_Client.verifyResetPasswordUDRequestReceivedandPasswordReset(getEmailClient());

    }
}