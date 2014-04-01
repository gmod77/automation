package org.geno.tests.sauce;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.geno.com.helpers.EmailHelper_Client;
import org.geno.com.sauce.iTestCaseManeroSauce;

import java.util.List;


public class Manero_RegistrationFlowTest extends iTestCaseManeroSauce {

    private String emailClient;
    private List<String> emailFriends;

    public Manero_RegistrationFlowTest() {
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

    @Test(groups = {"Regression", "Register" })
    public void ManeroRegistrationCheck() {
        WebDriver client = getDriver();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);

        String date = emailHelper_Client.generateDate(DATEFORMAT);
        setEmailClient(emailHelper_Client.generateEmailClient());
        setEmailFriends(emailHelper_Client.generateFriendClient(5));

        getSauceResultsUrl();

        Reporter.log("Visiting Home Page for the first time", true);
        visitManeroFirstTime();

        Reporter.log("Sign up for a new account", true);
        Reporter.log("Sign up Step 1",true);
        signUpManeroStep1(getEmailClient());
        Reporter.log("Sign up Step 2",true);
        signUpManeroStep2(date);
        Reporter.log("Sign up Step 3",true);
        signUpManeroStep3(getEmailFriends());
        Reporter.log("Sign up Step 4",true);
        signUpManeroStep4();

        Reporter.log("Log out", true);
        logoutManero();

    }

    @Test (groups = {"Regression"})
    public void ManeroHomePageChecksLoggedOut() {
        getSauceResultsUrl();

        Reporter.log("Visiting Home Page for the first time", true);
        visitManeroFirstTime();

        Reporter.log("Do checks while logged out", true);

        Reporter.log("Check Header", true);
        checkManeroHomepageHeaderLoggedOut();

        Reporter.log("Check Footer", true);
        checkManeroHomepageFooterLoggedOut();

    }

    @Test (groups = {"Regression"}, dependsOnGroups = {"Register"})
    public void homePageChecksLoggedIn() {
        getSauceResultsUrl();

        Reporter.log("Visiting Home Page for the first time", true);
        visitManeroFirstTime();

        Reporter.log("Login");
        loginManero(getEmailClient(),PASSWORD);

        Reporter.log("Check Header",true);
        checkManeroHomepageHeaderLoggedIn();

        //client.navigate().refresh();

        Reporter.log("Check Footer", true);
        checkManeroHomePageFooterLoggedIn();

    }

    @Test (groups = {"Regression"}, dependsOnGroups = {"Register"})
    public void verifyUDEmailsReceived() {
        WebDriver client = getDriver();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);
        getSauceResultsUrl();

        Reporter.log("Log in to Gmail", true);
        emailHelper_Client.loginToGmail();

        Reporter.log("Verify Welcome to UD Email was received", true);
        emailHelper_Client.verifyWelcomeManeroEmailReceived(getEmailClient());

        Reporter.log("Verify Invitation Email was received", true);
        emailHelper_Client.verifyInvitationsManeroEmailsReceived(getEmailFriends());

    }
}
