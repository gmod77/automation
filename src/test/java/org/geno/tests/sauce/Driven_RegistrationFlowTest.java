package org.geno.tests.sauce;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.geno.com.common.Analyzer;
import org.geno.com.helpers.EmailHelper_Client;
import org.geno.com.sauce.iTestCaseDrivenSauce;

import java.util.List;


public class Driven_RegistrationFlowTest extends iTestCaseDrivenSauce {

    private String emailClient;
    private List<String> emailFriends;

    public Driven_RegistrationFlowTest() {
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
    public void DrivenRegistrationCheck() throws Exception {
        WebDriver client = getDriver();

        getSauceResultsUrl();
        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);

        String date = emailHelper_Client.generateDate(DATEFORMAT);
        setEmailClient(emailHelper_Client.generateEmailClient());
        setEmailFriends(emailHelper_Client.generateFriendClient(5));

        Reporter.log("Visiting Driven Home Page", true);
        visitDriven();

        Reporter.log("Sign up for a new account", true);
        Reporter.log("Sign up Step 1",true);
        signUpDrivenStep1(getEmailClient());
        Reporter.log("Sign up Step 2",true);
        signUpDrivenStep2(date);
        Reporter.log("Sign up Step 3",true);
        signUpDrivenStep3(getEmailFriends());
        Reporter.log("Sign up Step 4",true);
        signUpDrivenStep4();

    }

    @Test (groups = {"Regression"})
    public void DrivenHomePageChecks() throws Exception {
        getSauceResultsUrl();
        Reporter.log("Visiting Driven Home Page", true);
        visitDriven();
        checkDrivenHomepage();

    }

    @Test (groups = {"Regression"}, dependsOnGroups = {"Register"})
    public void verifyUDEmailsReceived(){
        WebDriver client = getDriver();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);

        getSauceResultsUrl();

        Reporter.log("Log in to Gmail", true);
        emailHelper_Client.loginToGmail();

        Reporter.log("Verify Welcome to UD Email was received", true);
        emailHelper_Client.verifyWelcomeUDEmailReceived(getEmailClient());

        Reporter.log("Verify Invitation Email was received", true);
        emailHelper_Client.verifyInvitationsUDEmailsReceived(getEmailFriends());

        Reporter.log("Verify Password Reset Email was received", true);
        emailHelper_Client.verifyResetPasswordUDRequestReceivedandPasswordReset(emailClient);

        Reporter.log("Verify Edit Settings Email was received", true);
        emailHelper_Client.verifyEditSettingsUDEmailReceived(emailClient);
    }

}
