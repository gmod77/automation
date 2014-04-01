package org.geno.tests.appium;

//import org.testng.Reporter;
import org.testng.annotations.Test;
import org.geno.com.appium.iTestCaseTNMAppium_iOS;


public class TNM_SmokeTest_QA_iOS extends iTestCaseTNMAppium_iOS {

//    String DATEFORMAT="HHmmssSSS";
//    String email = "udtestergene+"+generateDate(DATEFORMAT)+"@gmail.com";
//    String password = "12345";
//    String user_state = "";

    public TNM_SmokeTest_QA_iOS() {
    }


   // @Test (dataProvider = "NY")
public void testTNMBasicFlowNY(String venueTitle, String venueDetails) throws Exception {



    //for(int i =0; i<10; i++)
    {
        //System.out.println("Run #"+ (i+1));

        waitForInterstitialToEnd();
        //Reporter.log("App started, arrived @ Home Screen");

        checkForRatingDialog();
        checkForAreYouAMemberDialog();
        engageDock();

        selectCity("New York");

        performFoodAndDrinksSearch_afterCityChoice("","");
        //Reporter.log("Search performed, arrived @ Results List");

        switchSearchListToMap();
        //Reporter.log("arrived @ Maps view of search results");

        searchForVenue(venueTitle, venueDetails);
        //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

        putAppInBackgroundThenBringItBack();
        //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");
    }


    //change device's orientation to Landscape
    //((RemoteWebDriver) driver).executeScript("UIATarget.localTarget().setDeviceOrientation(UIA_DEVICE_ORIENTATION_LANDSCAPELEFT)");

    //change device's orientation to Portrait
    //((RemoteWebDriver) driver).executeScript("UIATarget.localTarget().setDeviceOrientation(UIA_DEVICE_ORIENTATION_PORTRAIT)");

}


   // @Test (dataProvider = "ATL")
    public void testTNMBasicFlowATL(String venueTitle, String venueDetails) throws Exception {

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

            searchForVenue(venueTitle, venueDetails);
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");
        }

    }

    @Test
    public void testTNMBasicFlowAtlanta() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Atlanta+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

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

    //@Test
    public void testTNMBasicFlowAtlanticCity() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"AC+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Atlantic City");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Cabana Club", "Cabana Club, 111 S Chelsea Ave (at the Chelsea Hotel)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Cabana Club", "Cabana Club, 111 S Chelsea Ave (at the Chelsea Hotel)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Cabana Club", "Cabana Club, 111 S Chelsea Ave (at the Chelsea Hotel)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Cabana Club");

            logoutUD();
        }

    }

    //@Test
    public void testTNMBasicFlowAustin() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Austin+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Austin");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Bess Bistro", "Bess Bistro, 500 W 6th St (at San Antonio St)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Bess Bistro", "Bess Bistro, 500 W 6th St (at San Antonio St)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Bess Bistro", "Bess Bistro, 500 W 6th St (at San Antonio St)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Bess Bistro");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowBoston() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Boston+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Boston");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("The North Star", "The North Star, 222 Friend St");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("The North Star", "The North Star, 222 Friend St");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("The North Star", "The North Star, 222 Friend St");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("The North Star");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowChicago() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Chicago+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Chicago");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Everest", "Everest, 440 S La Salle St (between Congress Pky & Van Buren St)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Everest", "Everest, 440 S La Salle St (between Congress Pky & Van Buren St)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Everest", "Everest, 440 S La Salle St (between Congress Pky & Van Buren St)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Everest");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowDallas() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Dallas+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Dallas");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Naan Sushi", "Naan Sushi, 2600 Cedar Springs Rd (at Gables)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Naan Sushi", "Naan Sushi, 2600 Cedar Springs Rd (at Gables)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Naan Sushi", "Naan Sushi, 2600 Cedar Springs Rd (at Gables)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Naan Sushi");

            logoutUD();
        }

    }

    //@Test
    public void testTNMBasicFlowDetroit() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Detroit+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Detroit");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("The Majestic", "The Majestic, 4120 Woodward Ave (near W Willis St)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("The Majestic", "The Majestic, 4120 Woodward Ave (near W Willis St)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("The Majestic", "The Majestic, 4120 Woodward Ave (near W Willis St)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("The Majestic");

            logoutUD();
        }

    }

    //@Test
    public void testTNMBasicFlowHouston() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Houston+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Houston");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Straits", "Straits, 800 W Sam Houston Pkwy N (at Citycentre Way)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Straits", "Straits, 800 W Sam Houston Pkwy N (at Citycentre Way)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Straits", "Straits, 800 W Sam Houston Pkwy N (at Citycentre Way)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Straits");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowLasVegas() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"LV+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Las Vegas");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Revolver", "Revolver, 4949 N Rancho Rd");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Revolver", "Revolver, 4949 N Rancho Rd");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Revolver", "Revolver, 4949 N Rancho Rd");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Revolver");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowLosAngeles() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"LA+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Los Angeles");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Providence", "Providence, 5955 Melrose Ave");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Providence", "Providence, 5955 Melrose Ave");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Providence", "Providence, 5955 Melrose Ave");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Providence");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowMiami() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Miami+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Miami");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Mesazul", "Mesazul, 4400 NW 87th Ave");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Mesazul", "Mesazul, 4400 NW 87th Ave");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Mesazul", "Mesazul, 4400 NW 87th Ave");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Mesazul");

            logoutUD();
        }

    }

    //@Test
    public void testTNMBasicFlowMSP() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"MSP+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Minneapolis-St. Paul");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Prohibition", "Prohibition, 821 Marquette Ave (at 9th St S)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Prohibition", "Prohibition, 821 Marquette Ave (at 9th St S)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Prohibition", "Prohibition, 821 Marquette Ave (at 9th St S)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Prohibition");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowNewOrleans() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"NewOrleans+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("New Orleans");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Domenica", "Domenica, 123 Baronne St (near Tulane Ave)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Domenica", "Domenica, 123 Baronne St (near Tulane Ave)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Domenica", "Domenica, 123 Baronne St (near Tulane Ave)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Domenica");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowNY() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"NY+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("New York");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Del Posto", "Del Posto, 85 10th Ave (at 16th St)");
            // searchForVenue("Sunflower", "Sunflower, 284 Ofarrell Street (between Elwood St & Mason St) , 0.2 mi");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Del Posto", "Del Posto, 85 10th Ave (at 16th St)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Del Posto", "Del Posto, 85 10th Ave (at 16th St)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Del Posto");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowPhiladelphia() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Philadelphia+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Philadelphia");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Isabella", "Isabella, 382 E Elm St (near Cherry St)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Isabella", "Isabella, 382 E Elm St (near Cherry St)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Isabella", "Isabella, 382 E Elm St (near Cherry St)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Isabella");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowSanFrancisco() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"SanFran+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("San Francisco");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Sunflower", "Sunflower, 284 Ofarrell Street (between Elwood St & Mason St)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Sunflower", "Sunflower, 284 Ofarrell Street (between Elwood St & Mason St)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Sunflower", "Sunflower, 284 Ofarrell Street (between Elwood St & Mason St)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Sunflower");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowSeattle() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Seattle+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Seattle");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Bookstore Bar", "Bookstore Bar, 92 Madison St (near Catherine St)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Bookstore Bar", "Bookstore Bar, 92 Madison St (near Catherine St)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Bookstore Bar", "Bookstore Bar, 92 Madison St (near Catherine St)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Bookstore Bar");

            logoutUD();
        }

    }

    @Test
    public void testTNMBasicFlowDC() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"DC+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Washington DC");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Urbana", "Urbana, 2121 P St NW (between N 21st St & N Twining Ct");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Urbana", "Urbana, 2121 P St NW (between N 21st St & N Twining Ct");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Urbana", "Urbana, 2121 P St NW (between N 21st St & N Twining Ct");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Urbana");

            logoutUD();
        }

    }

    //@Test
    public void testTNMBasicFlowHamptons() throws Exception {

        String DATEFORMAT="HHmmssSSS";
        String email = "udtestergene+"+"Hamptons+"+generateDate(DATEFORMAT)+"@gmail.com";
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

            selectCity("Hamptons");

            performFoodAndDrinksSearch_afterCityChoice("","");
            //Reporter.log("Search performed, arrived @ Results List");

            switchSearchListToMap();
            //Reporter.log("arrived @ Maps view of search results");

            searchForVenue("Georgica", "Georgica, 108 Montauk Hwy (near Georgica Dr)");
            //Reporter.log("Search for Venue Performed successfully, arrived @ a Venue View");

            markVenueAsFavoriteSignedOut();

            putAppInBackgroundThenBringItBack();
            //Reporter.log("Put-in-the-background-then-relaunch action performed successfully, arrived @ Home Screen");

            signUpTNM(email,password);

            signOutTNM();

            signInTNM(email,password);

            searchForVenue("Georgica", "Georgica, 108 Montauk Hwy (near Georgica Dr)");

            markVenueAsFavoriteSignedIn();

            checkVenueIsFavoriteSignedIn("Georgica", "Georgica, 108 Montauk Hwy (near Georgica Dr)");

            signOutTNM();

            // confirm that the user account created via TNM is working on UD

            visitUDFirstTime();
            accessNewYorkFromUDHomepage();
            loginUD(email,password);

            confirmTNMFavoriteOnUDSignedIn("Georgica");

            logoutUD();
        }

    }

}
