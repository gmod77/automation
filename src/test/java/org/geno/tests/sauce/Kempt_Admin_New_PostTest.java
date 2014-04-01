package org.geno.tests.sauce;

import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseKemptSauce;


public class Kempt_Admin_New_PostTest extends iTestCaseKemptSauce {

    @Test (groups = {"Smoke"})
    public void createNew_Post(){

        getSauceResultsUrl();
        loginKemptAdmin();
        createNewPost();
    }
}
