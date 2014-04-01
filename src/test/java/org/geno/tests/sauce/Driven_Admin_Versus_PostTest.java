package org.geno.tests.sauce;

import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseDrivenSauce;


public class Driven_Admin_Versus_PostTest extends iTestCaseDrivenSauce {

    @Test (groups = {"Smoke"})
    public void createVersus_Post(){
        getSauceResultsUrl();
        loginDrivenAdmin();
        createVersusPost();
    }
}
