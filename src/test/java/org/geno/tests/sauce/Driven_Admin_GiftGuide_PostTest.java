package org.geno.tests.sauce;

import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseDrivenSauce;


public class Driven_Admin_GiftGuide_PostTest extends iTestCaseDrivenSauce {

    @Test (groups = {"Smoke"})
    public void createGiftGuide_Post(){
        getSauceResultsUrl();
        loginDrivenAdmin();
        createGiftGuidePost();
    }
}
