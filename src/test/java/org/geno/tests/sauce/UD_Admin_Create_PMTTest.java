package org.geno.tests.sauce;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.geno.com.helpers.EmailHelper_Client;
import org.geno.com.sauce.iTestCaseUDSauce;

public class UD_Admin_Create_PMTTest extends iTestCaseUDSauce {
    String date;

    @Test (groups = "Smoke")
    public void createPMTArticle(){
        WebDriver client = getDriver();

        EmailHelper_Client emailHelper_Client = new EmailHelper_Client(client);
        date = emailHelper_Client.generateDate(DATEFORMAT);
        String memberSource = "Member Source " + date;

        getSauceResultsUrl();
        loginUDAdmin();
        createMemberSource(memberSource);
        createPMT(memberSource);
    }

}