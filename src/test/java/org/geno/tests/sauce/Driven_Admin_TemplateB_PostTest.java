package org.geno.tests.sauce;

import org.testng.annotations.Test;
import org.geno.com.sauce.iTestCaseDrivenSauce;


public class Driven_Admin_TemplateB_PostTest extends iTestCaseDrivenSauce {

    @Test (groups = {"Smoke"})
    public void createTemplateB_Post(){
        getSauceResultsUrl();
        loginDrivenAdmin();
        createTemplateBPost();
    }
}
