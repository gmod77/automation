package org.geno.tests.appium;

//import org.testng.Reporter;
import org.geno.com.appium.iTestCaseTNMAppium_Dependencies;


public class TNM_SmokeTest_Dependencies extends iTestCaseTNMAppium_Dependencies {

    String DATEFORMAT="HHmmssSSS";
    String email = "udtestergene+"+generateDate(DATEFORMAT)+"@gmail.com";
    String password = "12345";
    String user_state = "";

    public TNM_SmokeTest_Dependencies() throws Exception{
    }

    //@Test void waitForInterstitialToEnd ()

//    @Test
//    public void testTNMBasicFlow() throws Exception {

        //for(int i =0; i<10; i++)
        {
            //System.out.println("Run #"+ (i+1));

//            waitForInterstitialToEnd();
//            //Reporter.log("App started, arrived @ Home Screen");
//
//            checkForRatingDialog();
//            checkForAreYouAMemberDialog();
//            engageDock();
//
//            selectCity("New York");
//
//            performFoodAndDrinksSearch_afterCityChoice();
//            //Reporter.log("Search performed, arrived @ Results List");
//
//            switchSearchListToMap();
//            //Reporter.log("arrived @ Maps view of search results");
//
//            searchForVenueSignedOut("Del Posto", "Del Posto, 85 10th Ave (at 16th St)");
//            // searchForVenue("Sunflower", "Sunflower, 284 Ofarrell Street (between Elwood St & Mason St) , 0.2 mi");
//            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");
//
//            markVenueAsFavoriteSignedOut();
//
//            putAppInBackgroundThenBringItBack();
//            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");
//
//            signUpTNM(email,password);
//
//            signOutTNM();
//
//            signInTNM(email,password);
//
//            searchForVenueSignedIn("Del Posto", "Del Posto, 85 10th Ave (at 16th St)");
//
//            markVenueAsFavoriteSignedIn();
//
//            checkVenueIsFavoriteSignedIn("Del Posto", "Del Posto, 85 10th Ave (at 16th St)");
//
//            signOutTNM();
//
//            // confirm that the user account created via TNM is working on UD
//
//            visitUDFirstTime();
//            accessNewYorkFromUDHomepage();
//            loginUD(email,password);
//
//            confirmTNMFavoriteOnUDSignedIn("Del Posto");
//
//            logoutUD();

            ///

            waitForInterstitialToEnd();
            performFoodAndDrinksSearch_afterCityChoice();
            switchSearchListToMap();
            searchForVenueSignedOut("Del Posto", "Del Posto, 85 10th Ave (at 16th St)");
            searchForVenueSignedIn("Del Posto", "Del Posto, 85 10th Ave (at 16th St)");
            markVenueAsFavoriteSignedOut();
            markVenueAsFavoriteSignedIn();
            checkVenueIsFavoriteSignedIn("Del Posto", "Del Posto, 85 10th Ave (at 16th St)");
            confirmTNMFavoriteOnUDSignedIn("Del Posto", "Del Posto, 85 10th Ave (at 16th St)");
            putAppInBackgroundThenBringItBack();
            engageDock();
            checkForRatingDialog();
            checkForAreYouAMemberDialog();
            selectCity("New York");
            signUpTNM(email,password);
            signOutTNM();
            signInTNM(email,password);
            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);
            logoutUD();
        }

   // }

}
