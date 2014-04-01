package org.geno.tests.sauce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import org.geno.com.helpers.EmailHelper_Client;
import org.geno.com.sauce.iTestCaseUDSauce;

import java.util.List;

public class UD_RegistrationFlowTest extends iTestCaseUDSauce {

    private String emailClient;
    private List<String> emailFriends;

    public UD_RegistrationFlowTest() {

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

    @Test (groups = {"Regression", "Register" })
    public void UDregisterAndEditSettings() {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);

        setEmailClient(emailHelper_Client.generateEmailClient());
        setEmailFriends(emailHelper_Client.generateFriendClient(5));

        WebDriverWait wait = new WebDriverWait(client, 30, 5000);

        Reporter.log("Visiting Home Page for the first time", true);
        visitUDFirstTime();

        Reporter.log("Access New York from the UD Homepage", true);
        accessNewYorkFromUDHomepage();
        // Wait for the logo to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#logoHolder>a>img")));

        Reporter.log("Navigating to non-modal page",true);
        signUpUDModal();

        Reporter.log("Sign up for a new account", true);
        Reporter.log("Sign up Step 1",true);
        signUpUDStep1(getEmailClient());
        Reporter.log("Sign up Step 2",true);
        signUpUDStep2();
        Reporter.log("Sign up Step 3",true);
        signUpUDStep3(getEmailFriends(), false);
        Reporter.log("Sign up Step 4",true);
        signUpUDStep4();

        Reporter.log("Edit settings", true);
        editSettingsUD(0);

        Reporter.log("Log out", true);
        logoutUD();

    }

    @Test (groups = {"Regression", "Register" })
    public void UDNonModalRegister() {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);

        setEmailClient(emailHelper_Client.generateEmailClient());
        setEmailFriends(emailHelper_Client.generateFriendClient(5));

        WebDriverWait wait = new WebDriverWait(client, 30, 5000);

        Reporter.log("Visiting Home Page for the first time", true);
        visitUDFirstTime();

        Reporter.log("Access New York from the UD Homepage", true);
        accessNewYorkFromUDHomepage();
        // Wait for the logo to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#logoHolder>a>img")));

        Reporter.log("Navigating to non-modal page",true);
        signUpUDNonModal();

        Reporter.log("Sign up for a new account", true);
        Reporter.log("Sign up Step 1",true);
        signUpUDStep1(getEmailClient());
        Reporter.log("Sign up Step 2",true);
        signUpUDStep2();
        Reporter.log("Sign up Step 3",true);
        signUpUDStep3(getEmailFriends(), false);

        Reporter.log("Edit settings", true);
        editSettingsUD(1);

        Reporter.log("Log out", true);
        logoutUD();

    }

    @Test (groups = {"Regression", "PWReset"}, dependsOnGroups = {"Register"})
    public void ResetUDAccountPW() {
        getSauceResultsUrl();

        Reporter.log("Visiting Home Page for the first time", true);
        visitUDFirstTime();

        Reporter.log("Access New York from the UD Homepage", true);
        accessNewYorkFromUDHomepage();

        Reporter.log("Do Password Reset", true);
        resetPasswordUD(getEmailClient());
    }

    @Test (groups = {"Regression"}, dependsOnGroups = {"Register"})
    public void verifyUDEmailsReceived() {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);

        String email = getEmailClient();
        List<String> friends = getEmailFriends();

        Reporter.log("Log in to Gmail", true);
        emailHelper_Client.loginToGmail();

        Reporter.log("Verify Welcome to UD Email was received", true);
        emailHelper_Client.verifyWelcomeUDEmailReceived(email);

        Reporter.log("Verify Invitation Email was received", true);
        emailHelper_Client.verifyInvitationsUDEmailsReceived(friends);

        Reporter.log("Verify Password Reset Email was received", true);
        emailHelper_Client.verifyResetPasswordUDRequestReceivedandPasswordReset(email);

        Reporter.log("Verify Edit Settings Email was received", true);
        emailHelper_Client.verifyEditSettingsUDEmailReceived(email);
    }
}
