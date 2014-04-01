package org.geno.tests.sauce;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.geno.com.helpers.EmailHelper_Client;
import org.geno.com.sauce.iTestCaseUDSauce;

import java.util.List;


public class UD_SaveArticleTest extends iTestCaseUDSauce {

    private String emailClient;
    private List<String> emailFriends;

    public UD_SaveArticleTest() {
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

    @Test (groups = {"saveArticle", "saveArticleloggedIn"})
    public void UDsaveArticleLoggedIn() throws Exception {
        WebDriver client = getDriver();

        getSauceResultsUrl();

        EmailHelper_Client  emailHelper_Client = new EmailHelper_Client(client);

        setEmailClient(emailHelper_Client.generateEmailClient());
        setEmailFriends(emailHelper_Client.generateFriendClient(5));

        Reporter.log("Visit UD for the first time", true);
        visitUDFirstTime();

        Reporter.log("Access New York Edition", true);
        accessNewYorkFromUDHomepage();

        Reporter.log("Create an account",true);
        Reporter.log("Sign up Step 1", true);
        signUpUDModal();
        signUpUDStep1(getEmailClient());
        Reporter.log("Sign up Step 2", true);
        signUpUDStep2();
        Reporter.log("Sign up Step 3", true);
        signUpUDStep3(getEmailFriends(), true);
        Reporter.log("Sign up Step 4", true);
        signUpUDStep4();

        Reporter.log("Returning to the homepage",true);
        goBackToUDHomepage();

        Reporter.log("Saving article",true);
        saveArticleToAccount();
    }

}