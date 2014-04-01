package org.geno.tests.sauce;

import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseDrivenSauce;


public class Driven_Admin_Bulgari_PostTest extends iTestCaseDrivenSauce {

    @Test (groups = {"Smoke"})
    public void createBulgari_Post(){
        getSauceResultsUrl();
        loginDrivenAdmin();
        createBulgariPost();
    }
}
