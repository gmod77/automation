package org.geno.tests.appium;

//import org.testng.Reporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.geno.com.appium.iTestCaseTNMAppium_iOS;
import org.geno.com.helpers.mobile_perks.MobilePerksHomePage;


public class TNM_SmokeTest_Short_iOS extends iTestCaseTNMAppium_iOS {

//    String DATEFORMAT="HHmmssSSS";
//    String email = "udtestergene+"+generateDate(DATEFORMAT)+"@gmail.com";
//    String password = "12345";
//    String user_state = "";

    WebDriver driver;


    public TNM_SmokeTest_Short_iOS() {
    }



    @Test
    public void testTNMBasicFlowAtlanta() throws Exception {

        driver = getAppiumDriver();

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Atlanta+"+generateDate(DATEFORMAT)+"@gmail.com";
        String password = "12345";
        String user_state = "";

        //for(int i =0; i<10; i++)
        {
            //System.out.println("Run #"+ (i+1));
            Thread.sleep(8000);

//            checkForGeoLocationDialog();

            waitForInterstitialToEnd();
            //Reporter.log("App started, arrived @ Home Screen");

            checkForRatingDialog();
            checkForAreYouAMemberDialog();
            engageDock();

            selectCity("Atlanta");

            performFoodAndDrinksSearch_afterCityChoice("Coffee","with nobody in particular.");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Lure", "Lure, 1106 Crescent Ave NE");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            //putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Lure", "Lure, 1106 Crescent Ave NE");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Lure", "Lure, 1106 Crescent Ave NE");

            signOutTNM();

            engageDock();
            selectPerks();
            switchToWebView();

            MobilePerksHomePage homePage = PageFactory.initElements(driver, MobilePerksHomePage.class);
            Assert.assertEquals(homePage.header.getCurrentEdition(), "National");


            // confirm that the user account created via TNM is working on UD

//            visitUDFirstTime();
//            accessNewYorkFromUDHomepage();
//            loginUD(email,password);
//
//            confirmTNMFavoriteOnUDSignedIn("Lure");
//
//            logoutUD();
        }

    }

}
