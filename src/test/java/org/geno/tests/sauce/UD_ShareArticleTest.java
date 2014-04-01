package org.geno.tests.sauce;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.geno.com.helpers.EmailHelper_Client;
import org.geno.com.sauce.iTestCaseUDSauce;

import java.util.List;


public class UD_ShareArticleTest extends iTestCaseUDSauce {

    private String emailClientA;
    private List<String> emailFriendsA;

    private String emailClientB;
    private List<String> emailFriendsB;


    public UD_ShareArticleTest() {
    }

    @Test (groups = {"shareArticle", "shareloggedOut"})
    public void UDshareArticleLoggedOut() {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);

        setEmailClientA(emailHelper_Client.generateEmailClient());
        setEmailFriendsA(emailHelper_Client.generateFriendClient(5));

        Reporter.log(getEmailClientA(),true);

        Reporter.log("Visit UD for the first time",true);
        visitUDFirstTime();

        Reporter.log("Access New York Edition",true);
        accessNewYorkFromUDHomepage();

        Reporter.log("Sharing an article",true);
        shareArticle(getEmailClientA(), getEmailFriendsA());
    }

    @Test (groups = {"shareArticle"}, dependsOnGroups = {"shareloggedOut"})
    public void checkSharedArticleLoggedOut() {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);

        Reporter.log("Logging into Gmail",true);
        emailHelper_Client.loginToGmail();

        Reporter.log("Verifying articles were received",true);
        emailHelper_Client.verifySharedArticleLoggedOutReceived(getEmailFriendsA());

        Reporter.log("Logging out of Gmail",true);
        emailHelper_Client.logoutGmail();

    }

    @Test (groups = {"shareArticle", "shareloggedIn"})
    public void UDshareArticleLoggedIn() {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);

        setEmailClientB(emailHelper_Client.generateEmailClient());
        setEmailFriendsB(emailHelper_Client.generateFriendClient(5));

        Reporter.log("Visit UD for the first time",true);
        visitUDFirstTime();

        Reporter.log("Access New York Edition",true);
        accessNewYorkFromUDHomepage();

        Reporter.log("Create an account",true);
        Reporter.log("Sign up Step 1",true);
        signUpUDModal();
        signUpUDStep1(getEmailClientB());
        Reporter.log("Sign up Step 2",true);
        signUpUDStep2();
        Reporter.log("Sign up Step 3",true);
        signUpUDStep3(getEmailFriendsB(),true);
        Reporter.log("Sign up Step 4",true);
        signUpUDStep4();
        Reporter.log("Returning to the homepage",true);
        goBackToUDHomepage();

        Reporter.log("Sharing article",true);
        shareArticle(emailClientB, emailFriendsB);
    }

    @Test (groups = {"shareArticle"}, dependsOnGroups = {"shareloggedIn"})
    public void checkSharedArticleLoggedIn() {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);

        Reporter.log("Logging into Gmail",true);
        emailHelper_Client.loginToGmail();

        Reporter.log("Verifying articles were shared",true);
        emailHelper_Client.verifySharedArticleLoggedInReceived(getEmailClientB(), getEmailFriendsB());

        Reporter.log("Logging out of Gmail",true);
        emailHelper_Client.logoutGmail();
    }

    private void setEmailClientA(String emailClient) {
        this.emailClientA = emailClient;
    }

    private String getEmailClientA() {
        return emailClientA;
    }

    private void setEmailFriendsA(List<String> emailFriends) {
        this.emailFriendsA = emailFriends;
    }

    private List<String> getEmailFriendsA() {
        return emailFriendsA;
    }

    private void setEmailClientB(String emailClient) {
        this.emailClientB = emailClient;
    }

    private String getEmailClientB() {
        return emailClientB;
    }

    private void setEmailFriendsB(List<String> emailFriends) {
        this.emailFriendsB = emailFriends;
    }

    private List<String> getEmailFriendsB() {
        return emailFriendsB;
    }
}