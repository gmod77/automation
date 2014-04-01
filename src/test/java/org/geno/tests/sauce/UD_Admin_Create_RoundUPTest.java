package org.geno.tests.sauce;

import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseUDSauce;

public class UD_Admin_Create_RoundUPTest extends iTestCaseUDSauce {

    //Declare articleID to pass to the checker
    String articleID;

    @Test (groups = {"RoundUpCreate"})
    public void RoundUPArticleCreate() {
        getSauceResultsUrl();

        loginUDAdmin();

        adminDefaults("Roundup",0);

        //return the articleid that was created
        articleID = getArticleID();

        createRoundUP();
    }

    // This will check only the regular round up
    @Test (dependsOnGroups = {"RoundUpCreate"})
    public void checkCreatedRoundUp() {
        getSauceResultsUrl();

        confirmRoundUP(articleID);
    }

    @Test
    public void roundUpBlackBGTakeOverArticleCreate() {
        getSauceResultsUrl();

        loginUDAdmin();

        adminDefaults("Background Takeover Roundup",0);

        backgroundTakeoverSettings(0);

        createRoundUP();
    }

    @Test
    public void roundUpWhiteBGTakeOverArticleCreate() {
        getSauceResultsUrl();

        loginUDAdmin();

        adminDefaults("Background Takeover Roundup",0);

        backgroundTakeoverSettings(1);

        createRoundUP();
    }
}