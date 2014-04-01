package org.geno.tests.sauce;

import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseUDSauce;


public class UD_Admin_Article_Three_ColumnTest extends iTestCaseUDSauce {

    @Test (groups = {"Smoke"})
    public void createThreeColumnArticle(){
        getSauceResultsUrl();

        loginUDAdmin();

        // Run Defaults
        adminDefaults("Three-Column",0);

        createArticleThreeColumn();
    }


    @Test (groups = "Smoke")
    public void createThreeColumnBlackBGTakeoverArticle(){
        getSauceResultsUrl();

        loginUDAdmin();

        adminDefaults("Background Takeover",0);

        backgroundTakeoverSettings(0);

        createArticleThreeColumnBackgrounder();
    }

    @Test (groups = "Smoke")
    public void createThreeColumnWhiteBGTakeoverArticle(){
        getSauceResultsUrl();

        loginUDAdmin();

        adminDefaults("Background Takeover",0);

        backgroundTakeoverSettings(1);

        createArticleThreeColumnBackgrounder();
    }
}
