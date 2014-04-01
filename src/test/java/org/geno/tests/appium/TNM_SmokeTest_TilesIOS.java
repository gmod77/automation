package org.geno.tests.appium;

//import org.testng.Reporter;
import org.testng.annotations.Test;
import org.geno.com.appium.iTestCaseTNMAppium_iOS;


public class TNM_SmokeTest_TilesIOS extends iTestCaseTNMAppium_iOS {


    public TNM_SmokeTest_TilesIOS() {
    }

    @Test
    public void testTNMBasicFlowAtlanta() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Atlanta+"+generateDate(DATEFORMAT)+"@gmail.com";
        String friendemail1 = "udtestergene+friend1"+"Atlanta+"+generateDate(DATEFORMAT)+"@gmail.com";
        String friendemail2 = "udtestergene+friend2"+"Atlanta+"+generateDate(DATEFORMAT)+"@gmail.com";
        String friendemail3 = "udtestergene+friend3"+"Atlanta+"+generateDate(DATEFORMAT)+"@gmail.com";
        String friendemail4 = "udtestergene+friend4"+"Atlanta+"+generateDate(DATEFORMAT)+"@gmail.com";
        String friendemail5 = "udtestergene+friend5"+"Atlanta+"+generateDate(DATEFORMAT)+"@gmail.com";


        String password = "12345";
        String user_state = "";

        //for(int i =0; i<10; i++)
        {
            //System.out.println("Run #"+ (i+1));

            waitForInterstitialToEnd();
            //Reporter.log("App started, arrived @ Home Screen");

            checkForRatingDialog();
            checkForAreYouAMemberDialog();
            engageDock();

            selectCity("Atlanta");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("The Varsity", "The Varsity, 61 North Ave NW (at Spring St NW)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            // Switch to ShortLists, check if Favorites can be added
            engageDock();
            switchToShortLists();
            checkForShortListsInterstitial();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            inviteFriends(friendemail1, friendemail2, friendemail3, friendemail4, friendemail5);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("The Varsity", "The Varsity, 61 North Ave NW (at Spring St NW)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("The Varsity", "The Varsity, 61 North Ave NW (at Spring St NW)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("The Varsity");

            logoutUD();
        }

    }

}
