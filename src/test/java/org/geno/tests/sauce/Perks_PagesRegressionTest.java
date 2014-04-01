package org.geno.tests.sauce;


import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

import org.geno.com.sauce.iTestCasePerksSauce;

import java.util.List;

public class Perks_PagesRegressionTest extends iTestCasePerksSauce {
//    national
//    new-york
//    chicago
//    boston
//    washington-dc
//    los-angeles
//    san-francisco

    Object[][] perkEditions;
    List<String> links;


    @DataProvider(name = "perks-list")
    public Object[][] getPerks(ITestContext context) {

        perkEditions = new Object[links.size()][];
        for (int i = 0; i<links.size();i++) {
            perkEditions[i] = new Object[] {context.getCurrentXmlTest().getParameter("city"),links.get(i)};
        }

        return perkEditions;
    }

    @Parameters ({"city"})
    @Test (groups = {"Start"})
    public void perksLandingPage(String city) throws Exception {
        getSauceResultsUrl();

        visitPerksFirstTime(city);
        Reporter.log("Number of perks> " + getPerksLinkCount());
        links = getPerksLinks(getEditionBlocks());
    }

    @Test (dataProvider = "perks-list", groups = {"PerksRegression"},dependsOnGroups = {"Start"})
    public void perksPageRegressionDataDriven(String city,String pk) throws Exception {
        getSauceResultsUrl();

        checkPerkPage(pk);
    }

}
