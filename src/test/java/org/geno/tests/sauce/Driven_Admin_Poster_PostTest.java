package org.geno.tests.sauce;

import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseDrivenSauce;


public class Driven_Admin_Poster_PostTest extends iTestCaseDrivenSauce {

    @Test (groups = {"Smoke"})
    public void createPoster_Post(){
        getSauceResultsUrl();
        loginDrivenAdmin();
        createPosterPost();
    }
}
