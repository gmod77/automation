package org.geno.tests.sauce;

import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseUDSauce;

public class UD_Admin_Article_WeekenderTest extends iTestCaseUDSauce {

    @Test (groups = "Smoke")
    public void createWeekenderArticle(){
        getSauceResultsUrl();

        loginUDAdmin();

        // Run Defaults
        adminDefaults("Weekender",0);

        createArticleWeekender();
    }

    @Test (groups = "Smoke")
    public void createWeekenderBlackBGTakeoverArticle(){
        getSauceResultsUrl();

        loginUDAdmin();

        // Run Defaults
        adminDefaults("Background Takeover Weekender",0);

        backgroundTakeoverSettings(0);

        createArticleWeekenderBGTakeOver();
    }

    @Test (groups = "Smoke")
    public void createWeekenderWhiteBGTakeoverArticle(){
        getSauceResultsUrl();

        loginUDAdmin();

        // Run Defaults
        adminDefaults("Background Takeover Weekender",0);

        backgroundTakeoverSettings(1);

        createArticleWeekenderBGTakeOver();
    }

}