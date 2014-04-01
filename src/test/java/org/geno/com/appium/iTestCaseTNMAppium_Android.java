package org.geno.com.appium;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.geno.com.common.UDBase;
import org.geno.com.helpers.UD_HeaderHelper_Client_v2;
import org.geno.com.helpers.UD_HomepageHelper_Client_v2;
import org.geno.com.helpers.UD_SealHelper_Client_v2;
import org.geno.com.helpers.UD_SignupHelper_Client_v2;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import java.net.URL;
//import static org.junit.Assert.assertFalse;
//import org.geno.com.sauce.iSauceBase;


//import static org.junit.Assert.assertTrue;

/**
 * @author Gene Modin
 */

public abstract class iTestCaseTNMAppium_Android extends iSauceBaseAppiumLocal_Android implements UDBase {
    //private WebDriver driver;
    //private WebDriver driver_web;
    //private List<Integer> values;
    private WebDriverWait wait;
    //private static final int MINIMUM = 0;
    //private static final int MAXIMUM = 10;
    //private int randPlaces = 4; //Number of digits for random email generation

    String DATEFORMAT="HHmmssSSS";
    String server = "geno-release.thedaddy.co";
    //String email = "udtestergene+"+generateDate(DATEFORMAT)+"@gmail.com";
    //int password = 12345;
    String user_state = "";


    public String generateDate(String dateFormat) {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(now);
    }



    @DataProvider(name = "NY")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src/test/resources/appium/data/Data.xls",
                "DataPool", "nyTestData");
        return(retObjArr);
    }

//    @DataProvider(name = "ATL")
//    public Object[][] createData2() throws Exception{
//        Object[][] retObjArr=getTableArray("src/test/resources/appium/data/Data.xls",
//                "DataPool", "atlTestData");
//        return(retObjArr);
//    }

    public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception{
        String[][] tabArray=null;

        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        Sheet sheet = workbook.getSheet(sheetName);
        int startRow,startCol, endRow, endCol,ci,cj;
        Cell tableStart=sheet.findCell(tableName);
        startRow=tableStart.getRow();
        startCol=tableStart.getColumn();

        Cell tableEnd = sheet.findCell(tableName, startCol+1, startRow+1, 100, 64000, false);

        endRow=tableEnd.getRow();
        endCol=tableEnd.getColumn();
        System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                "startCol="+startCol+", endCol="+endCol);
        tabArray=new String[endRow-startRow-1][endCol-startCol-1];
        ci=0;

        for (int i=startRow+1;i<endRow;i++,ci++){
            cj=0;
            for (int j=startCol+1;j<endCol;j++,cj++){
                tabArray[ci][cj]=sheet.getCell(j,i).getContents();
            }
        }

        return(tabArray);
    }

//    @AfterMethod
//    public void tearDown() throws Exception {
//        driver.quit();
//    }


    public void waitForInterstitialToEnd() throws Exception {
        user_state = "logged out: ";

        // entry:
        // location: interstitial , state: playing

        wait = new WebDriverWait(client_appium, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Food & Drinks")));

        //confirmHomeScreen();

        //sleep for the rest of the insterstitial video to stop playing, since the "Food & Drinks" renders before video stops playing
        Thread.sleep(5000);

        //set user state

        System.out.println(user_state + "Arrived @ Home Screen...\n");


        // exit:
        // location: home screen , state: dock up

    }

    public void performFoodAndDrinksSearch_afterCityChoice(String what, String who) throws Exception {

        //tap "What Do you Want?"
        System.out.println(user_state + "tap What Do you Want?");
        client_appium.findElement(By.name(">What do you want?")).click();
        Thread.sleep(2000);
        System.out.println(user_state + "done\n");


        //tap "I'm game for anything"
        System.out.println(user_state + what);
        client_appium.findElement(By.name(what)).click();
        Thread.sleep(2000);
        System.out.println(user_state + "done\n");


        //tap "Who are you with?"
        System.out.println(user_state + "tap Who are you with");
        client_appium.findElement(By.name(">Who are you with?")).click();
        Thread.sleep(2000);
        System.out.println(user_state + "done\n");


        //tap "With nobody in particular"
        System.out.println(user_state + who);
        client_appium.findElement(By.name(who)).click();
        Thread.sleep(2000);
        System.out.println(user_state + "done\n");


        // wait for submit button to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));

        putAppInBackgroundThenBringItBack();

        // wait for submit button to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));

        //click it
        System.out.println(user_state + "tap Submit button");
        WebElement submit = client_appium.findElement(By.name("Submit"));
        submit.click();
        System.out.println(user_state + "done");

        //wait till results show up
        System.out.println(user_state + "wait for results to show up");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("LIST")));

        putAppInBackgroundThenBringItBack();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("LIST")));


        System.out.println(user_state + "Search performed, arrived @ Results List\n");


        // exit:
        // location:
        // state:
        // options:

    }

    public void switchSearchListToMap() throws Exception {
        //1. switch to Maps View
        System.out.println(user_state + "Attempting to switch to Map View of Search results \n");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("MAP")));
        WebElement map = client_appium.findElement(By.name("MAP"));
        map.click();
        //Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("btn search")));

        putAppInBackgroundThenBringItBack();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("btn search")));

        System.out.println(user_state + "Done, switched to Map View of Search results \n");
    }

    public void searchForVenue(String term, String details) throws Exception {
        //do a search for specific venue
        //1. click UI search button
        System.out.println(user_state + "Click Search button \n");
        WebElement btn_search = client_appium.findElement(By.name("btn search"));
        btn_search.click();
        System.out.println(user_state + "done: clicked Search button \n");
        //2. search by name
        //enter term
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().typeString(\"" + term + "\")");
        System.out.println(user_state + "Searching for> " + term);
        // find iPhone's keyboard's "Search" button and tap it
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().buttons()[\"Search\"].tap();");

        //wait for result to show up
        System.out.println(user_state + "Waiting for result to show up...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(details)));

        putAppInBackgroundThenBringItBack();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(details)));

        Thread.sleep(2000);
        System.out.println(user_state + "Result returned for> " + term);

        //assuming a single match came back, click it
        System.out.println(user_state + "Click it! ");
        WebElement venue_result = client_appium.findElement(By.name(details));
        Thread.sleep(2000);
        venue_result.click();
        System.out.println(user_state + "Done, should be on venue page now... Do basic checks on it... \n");
        //Thread.sleep(2000);

        //confirm venue listing showed up upon last click
        //venue header/name
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(term)));

        putAppInBackgroundThenBringItBack();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(term)));

        System.out.println(user_state + "venue's header/name is here... \n");
        // toggled off favorite icon
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("mark favorite off")));
        //2. assert that star is now enabled
        WebElement star = client_appium.findElement(By.name("mark favorite off"));
        //assertFalse(star.isDisplayed());
        System.out.println(user_state + "Favorite button is here");

        System.out.println(user_state + "The " + term + " venue appears to be fine  \n");
        //take a screen shot of the venue  with date_venueName
        //System.out.println(user_state + "Take a screen shot of the venue...");
        //((RemoteWebDriver) driver).executeScript("UIATarget.localTarget().captureScreenWithName(\"" + generateDate(DATEFORMAT) + "_" + term + "\")");
//        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(scrFile, new File("/Users/gmodin/Documents/TNM/"+ generateDate(DATEFORMAT) + "_" + term + ".jpg"));
    }

    public void switchToShortLists() throws Exception {
        //click Perks
        System.out.println(user_state + "Engaging ShortLists...");
        client_appium.findElement(By.name("dock shortlist")).click();
        Thread.sleep(2000);
        System.out.println(user_state + "Done \n");
    }

    public void markVenueAsFavoriteSignedOut() throws Exception {

        //1. click star to favorite the venue
        Thread.sleep(1000);
        System.out.println(user_state + "Clicking the star icon on venue page in attempt to add to Favorite \n");
        WebElement star = client_appium.findElement(By.name("mark favorite off"));
        star.click();

        //2. wait for dialog to yell at you  (look for the Cancel button)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Cancel")));

        System.out.println(user_state + "Done, confirmed it is impossible to add to Favorites while logged out \n");

        //3. click cancel button
        WebElement cancel_button_favorites = client_appium.findElement(By.name("Cancel"));
        cancel_button_favorites.click();

        //4. arrive back at venu detail page
        //WebElement star = client_appium.findElement(By.name("mark favorite off"));

        //System.out.println(user_state + "venue's header/name is here... \n");
    }

    public void markVenueAsFavoriteSignedIn()  {

        //1. click star to favorite the venue
        System.out.println(user_state + "Clicking the star icon on venue page in attempt to add to Favorite \n");
        WebElement star = client_appium.findElement(By.name("mark favorite off"));
        star.click();

        //2. assert that star is now enabled
        //assertTrue(star.isDisplayed());
    }

    public void checkVenueIsFavoriteSignedIn(String term, String details) throws Exception {

        //click Favorites
        engageDock();
        WebElement favorites = client_appium.findElement(By.name("dock favorites"));
        favorites.click();

        //wait for Favorites to show up
        System.out.println(user_state + "Waiting for Favorites to show up...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(details)));
        Thread.sleep(2000);
        System.out.println(user_state + "Favorite Found> " + term);

        //assuming a single match came back, click it
        System.out.println(user_state + "Click it! ");
        //((RemoteWebDriver) driver).executeScript("UIATarget.localTarget().tap({x:23.00, y:147.00})");
        WebElement venue_result = client_appium.findElement(By.name(details));
        Thread.sleep(2000);
        venue_result.click();
        System.out.println(user_state + "Done, should be on Favorite venue page now... Do basic checks on it... \n");
        //Thread.sleep(2000);

        //confirm venue listing showed up upon last click
        //venue header/name
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(term)));
        System.out.println(user_state + "Favorite venue's header/name is here... \n");
        System.out.println(user_state + "This Favorite " + term + " venue appears to be fine  \n");
    }

    public void confirmTNMFavoriteOnUDSignedIn(String term) throws Exception {

        // Navigate to MyUD
        System.out.println("Navigate to MyUD  \n");
        client.get(UD_DOMAIN + "/myud");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[3]/div/div/div[2]/div[2]/div/div[2]/div/ul/li/a")));
        System.out.println("Arrived @ MyUD  \n");

        // Check that the venue appears under Favorite
        System.out.println(user_state + "Checking if " + term + "was added to Favorites \n");
        WebElement venue_link = client.findElement(By.xpath("/html/body/div/div[3]/div/div/div[2]/div[2]/div/div[2]/div/ul/li/a"));
        assertTrue(venue_link.getText().contains(term));
        System.out.println(user_state + "Confirmed: " + term + "was added to Favorites \n");

    }

    public void  putAppInBackgroundThenBringItBack() throws Exception{

        //exit the app and return to it in 3 seconds
        System.out.println(user_state + "Putting the app in the background for 1 second.... and back up");
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().deactivateAppForDuration(1)");

        waitForInterstitialToEnd();
        //Reporter.log("App started, arrived @ Home Screen");

        checkForAreYouAMemberDialog();
        checkForRatingDialog();

        //confirm arrival @ homeScreen
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Food & Drinks")));
        Thread.sleep(6000);
        System.out.println(user_state + "Put-in-the-background-then-relaunch action performed successfully \n");

    }

    public void engageDock() throws Exception {

        WebElement dock_city = client_appium.findElement(By.name("dock city"));
        //check if dock is open, if it is go on with the test
        System.out.println(user_state + "Dock check...");
        if (dock_city.isDisplayed() == true){
            System.out.println(user_state + "Dock is open, proceeding...");
        }
        else {
            //if dock is closed, click the ^ symbol to engage dock
            System.out.println(user_state + "Dock is closed, opening Dock");
            ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:160.00, y:465.00})");
//            ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().mainWindow().buttons()[\"dock.png\"].tap();");
            //client_appium.findElement(By.id("Dock")).click();
            Thread.sleep(3000);
            System.out.println(user_state + "Done, Dock is open now, proceeding...\n");
        }
    }

    public void checkForRatingDialog() throws Exception {

        // Handle notification window
        //check if Ratings dialog is open, if it is, tap "don't ask again"
        System.out.println(user_state + "check if Ratings dialog is open...");
        if (!client_appium.findElements(By.name("Remind me later")).isEmpty()) {
            System.out.println(user_state + "Ratings Dialog is open, tapping Remind me later...");
            client_appium.findElement(By.name("Remind me later")).click();
            System.out.println(user_state + "Done...");
        }
        else {
            System.out.println(user_state + "It's not...\n");
        }
    }

    public void checkForAreYouAMemberDialog() throws Exception {

        // Handle notification window
        //check if Are You A Member dialog is open
        System.out.println(user_state + "check if Are You A Member dialog is open...");
        if (!client_appium.findElements(By.name("YES, OF COURSE")).isEmpty()) {
            System.out.println(user_state + "Are You A Member dialog is open, tapping Yes of course...");
            client_appium.findElement(By.name("YES, OF COURSE")).click();
            Thread.sleep(2000);
            System.out.println(user_state + "Done...");
        }
        else {
            System.out.println(user_state + "It's not...\n");
        }
    }

    public void checkForGeoLocationDialog() throws Exception {

        // Handle notification window
        //check if Are You A Member dialog is open
//        System.out.println(user_state + "check if GeoLocation dialog is open...");
//        if (!client_appium.findElements(By.name("Yes")).isEmpty()) {
//            System.out.println(user_state + "GeoLocation dialog is open, tapping Yes...");
//            client_appium.findElement(By.name("Yes")).click();
//            Thread.sleep(2000);
//            System.out.println(user_state + "Done...");
//        }
//        else {
//            System.out.println(user_state + "It's not...\n");
//        }
        ((RemoteWebDriver) client_appium).executeScript(
        "UIATarget.onAlert = function onAlert(alert) {" +
            "return false;"+
        "}");
    }

    public void checkAppArivedOnHomePage() throws Exception {


        wait = new WebDriverWait(client_appium, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.ImageButton")));

//        client_appium.findElement(By.className("android.widget.ImageButton")).click();

    }

    public void checkForShortListsInterstitial() throws Exception {

        // Handle notification window
        //check if Ratings dialog is open, if it is, tap "don't ask again"
        System.out.println(user_state + "check if ShortLists Interstitial is open...");
        // check for the Interstitial's Close button
        if (!client_appium.findElements(By.name("Button")).isEmpty()) {
            System.out.println(user_state + "ShortLists Interstitial is open, let's play it!...");
            client_appium.findElement(By.name("sp play")).click();
            Thread.sleep(4000);
            System.out.println(user_state + "Enough is enough! Closing Interstitial now...");
            client_appium.findElement(By.name("Button")).click();
            System.out.println(user_state + "Done...");
        }
        else {
            System.out.println(user_state + "It's not...\n");
        }
    }

    public void selectPerks () throws Exception{
        //engage Perks
        System.out.println(user_state + "Engaging Perks...");
        client_appium.findElement(By.name("dock perks")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("geno Perks")));
        System.out.println(user_state + "@ Perks \n");

    }

    public void clickNav () throws Exception{
        //click Nav
        System.out.println(user_state + "Clicking Nav...");
        client_appium.findElement(By.name("Nav")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("HOME")));
        System.out.println(user_state + "@ Nav menu \n");

    }

    public void selectCity (String city) throws Exception{
        //engage Select city screen
        System.out.println(user_state + "Engaging Choose Your City screen...");
        client_appium.findElement(By.className("android.widget.Button")).click();
        //wait for select your city's "Find me" button
        wait = new WebDriverWait(client_appium, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Find me")));
        System.out.println(user_state + "Done \n");


        //click city
        System.out.println(user_state + "Selecting " + city);
        client_appium.findElement(By.name(city)).click();
        Thread.sleep(2000);
        System.out.println(user_state + "Done \n");

    }

    public void inviteFriends(String friendemail1,String friendemail2,String friendemail3,String friendemail4,String friendemail5) throws Exception {
        engageDock();

        //Click Invite Friends
        WebElement dock_invite_friends = client_appium.findElement(By.name("Invite Friends"));
        dock_invite_friends.click();
        //wait for Invitations screen to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Invitations")));
        System.out.println(user_state + "@ Invitations page \n");
        //Enter  emails
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().mainWindow().textFields()[0].setValue(\"" + friendemail1 + "\")");
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().mainWindow().textFields()[1].setValue(\"" + friendemail2 + "\")");
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().mainWindow().textFields()[2].setValue(\"" + friendemail3 + "\")");
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().mainWindow().textFields()[3].setValue(\"" + friendemail4 + "\")");
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().mainWindow().textFields()[4].setValue(\"" + friendemail5 + "\")");

        //Hit Next
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().buttons()[\"Next\"].tap();");


        //Hit Submit
        WebElement submit = client_appium.findElement(By.name("Submit"));
        submit.click();

        //wait Submit button on home screen
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
        Thread.sleep(3000);

    }

    public void  signUpTNM (String email, String password) throws Exception{

          engageDock();
          WebElement dock_signup = client_appium.findElement(By.name("dock signup"));

        //click Sign up button
            dock_signup.click();
            Thread.sleep(4000);
            //wait for Signup screen to appear (via Submit button)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));


        System.out.println(user_state + "Enter Email");
            //click UI search button
            System.out.println(user_state + "Click Email Field");

           WebElement email_field = client_appium.findElement(By.name("Email"));
           WebElement password_field = client_appium.findElement(By.name("Password"));

         //Enter email
            email_field.sendKeys(email);
         //enter password
            System.out.println(user_state + "Enter Password");
            password_field.sendKeys(password);

            // find iPhone's keyboard's "Done" button and tap it
            ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().buttons()[\"Done\"].tap();");



        //Select editions
                // Second Edition from the left
                //((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:160.00, y:295.00})");
            //page 1
            //New York
            client_appium.findElement(By.name("New York")).click();
            //Los Angeles
            client_appium.findElement(By.name("Los Angeles")).click();
            //National
            client_appium.findElement(By.name("National")).click();
            //page 2
            //Jetset
            client_appium.findElement(By.name("Jetset")).click();
            //San Francisco
            client_appium.findElement(By.name("San Francisco")).click();
            //Chicago
            client_appium.findElement(By.name("Chicago")).click();
            //page 3
            //Boston
            client_appium.findElement(By.name("Boston")).click();
            //Dallas
            client_appium.findElement(By.name("Dallas")).click();
            //Las Vegas
            client_appium.findElement(By.name("Las Vegas")).click();
            //page 4
            //Miami
            client_appium.findElement(By.name("Miami")).click();
            //D.C.
            client_appium.findElement(By.name("D.C.")).click();
            //Atlanta
            client_appium.findElement(By.name("Atlanta")).click();
            //page 5
            //Seattle
            client_appium.findElement(By.name("Seattle")).click();
            //Philadelphia
            client_appium.findElement(By.name("Philadelphia")).click();
            //Ski & Board
            client_appium.findElement(By.name("Ski & Board")).click();


        //Submit
            //wait Submit button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));

            putAppInBackgroundThenBringItBack();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));

            // click it
            WebElement submit = client_appium.findElement(By.name("Submit"));
            submit.click();

            //wait Submit button on home screen
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
            Thread.sleep(10000);
            //set state
            user_state = "logged in: ";
            System.out.println(user_state + "SIGNED UP SUCCESSFULLY: "+email + "\n");

    }

    public void signOutTNM () throws Exception{

        WebElement dock_signup = client_appium.findElement(By.name("dock signup"));
        //check if dock is open, and Signup button is there. if true go on with the test
        System.out.println(user_state + "SIGN OUT");
        System.out.println(user_state + "Signup button check...");
        if (dock_signup.isDisplayed() == true){
            System.out.println(user_state + "Ready to Signup, proceeding...");
            Thread.sleep(2000);
        }
        else {
            //if dock is closed, click the ^ symbol to engage dock
            System.out.println(user_state + "Dock is closed, opening Dock");
            Thread.sleep(2000);
            ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:160.00, y:465.00})");
            //client_appium.findElement(By.name("Dock")).click();
            Thread.sleep(2000);
            System.out.println(user_state + "Done, Dock is open now, proceeding...\n");
        }

        //engage Select city screen
        System.out.println(user_state + "Engaging Choose Your City screen...");
        client_appium.findElement(By.name("dock city")).click();
        Thread.sleep(2000);
        System.out.println(user_state + "Done \n");

        //click Sign Out
        System.out.println(user_state + "Clicking Sign Out button...");
        client_appium.findElement(By.name("Sign Out")).click();
        Thread.sleep(2000);
        // set user state
        user_state = "logged out: ";
        System.out.println(user_state + "SIGNED OUT \n");
    }

    public void signInTNM (String email, String password) throws Exception{

        WebElement dock_signup = client_appium.findElement(By.name("dock signup"));
        //check if dock is open, and Signup button is there. if true go on with the test
        System.out.println(user_state + "SIGN IN");
        System.out.println(user_state + "Signup button check...");
        if (dock_signup.isDisplayed() == true){
            System.out.println(user_state + "Ready to Signup, proceeding...");
        }
        else {
            //if dock is closed, click the ^ symbol to engage dock
            System.out.println(user_state + "Dock is closed, opening Dock");
            Thread.sleep(2000);
            ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:160.00, y:465.00})");
            //client_appium.findElement(By.name("Dock")).click();
            Thread.sleep(2000);
            System.out.println(user_state + "Done, Dock is open now, proceeding...\n");
        }

        //click Signup
        System.out.println(user_state + "Clicking Sign up button...");
        client_appium.findElement(By.name("dock signup")).click();
        //wait for Sign In button
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("btn signin")));
        Thread.sleep(2000);
        System.out.println(user_state + "Done \n");

        //click Sign In
        System.out.println(user_state + "Clicking Sign In button...");
        client_appium.findElement(By.name("btn signin")).click();
        //wait for Sign Up button
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("btn signup")));

        putAppInBackgroundThenBringItBack();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("btn signup")));

        Thread.sleep(2000);
        System.out.println(user_state + "Done \n");


        WebElement email_field = client_appium.findElement(By.name("Email"));
        WebElement password_field = client_appium.findElement(By.name("Password"));
        //enter email
        System.out.println(user_state + "Enter Email");
        //Enter email
        email_field.sendKeys(email);
        //enter password
        System.out.println(user_state + "Enter Password");
        password_field.sendKeys(password);

        // find iPhone's keyboard's "Done" button and tap it
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().buttons()[\"Done\"].tap();");

        //Submit action is activated by above step .... wait till the search is over
        Thread.sleep(7000);

        //wait Submit button on home screen
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
        // set user state
        user_state = "logged in: ";
        System.out.println(user_state + "SIGNED IN SUCCESSFULLY: "+email + "\n");

    }

    ///////////////  Login/SignOut methods  for UD part of tests for TheNextMove

    public void visitUDFirstTime(){

        // enter UD domain name, hit enter, arrive on homepage
        client.get(UD_DOMAIN);
        client.manage().deleteAllCookies();
        client.get(UD_DOMAIN);
        client.manage().addCookie(new Cookie ("udsubpop", "3",UD_DOMAIN_BASE, "/", null));
    }

    public void accessNewYorkFromUDHomepage() {
        ud_homepageHelper_Client = new UD_HomepageHelper_Client_v2(client);

        ud_homepageHelper_Client.getNewYorkLink().click();
    }

    /**
     * Login class, use to pass email and PASSWORD in via
     * other tests.
     * ex. currently used in UD_Unsubscribe_EditionsPerks
     * @param email Email address
     * @param pw PASSWORD
     * @throws org.openqa.selenium.TimeoutException thrown if Ajax call takes too long
     */
    public void loginUD(String email, String pw) throws TimeoutException {
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);
        ud_headerHelper_Client = new UD_HeaderHelper_Client_v2(client);
        ud_sealHelper_Client = new UD_SealHelper_Client_v2(client);
        ud_signupHelper_Client = new UD_SignupHelper_Client_v2(client);
        client.get(UD_DOMAIN);

        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_headerHelper_Client.getWaitByLocator("SignInHover")));

        displayCssHover();
        ud_headerHelper_Client.getMemberLoginLink().click();

        ud_signupHelper_Client.getSignInEmailTextBox().sendKeys(email);
        ud_signupHelper_Client.getSignInPasswordTextBox().sendKeys(pw);
        ud_signupHelper_Client.getSignInButton().click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(ud_signupHelper_Client.getWaitByLocator("SignInSpinner")));
        user_state = "logged into UD: ";
        System.out.println(user_state);

        // Depricated login
//        ud_sealHelper_Client.getEmailAddressBox().sendKeys(email);
//        ud_sealHelper_Client.getEnterPasswordBox().sendKeys(pw);
//        ud_sealHelper_Client.getLoginButton().click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".homeMyUDUserInfo>b")));
        } catch (TimeoutException TE) {
            throw new TimeoutException("POST TOOK TOO LONG");
        }
    }

    /**
     * Logout of UD and do checks on the footer
     *
     */
    public void logoutUD() {
        ud_headerHelper_Client = new UD_HeaderHelper_Client_v2(client);
        wait = new WebDriverWait(client,GLOBAL_TIME_OUT,GLOBAL_POLLING);

        wait.until(ExpectedConditions.visibilityOfElementLocated(ud_headerHelper_Client.getWaitByLocator("MyAccountHover")));

        displayCssHover();
        ud_headerHelper_Client.getLogoutLink().click();

        user_state = "logged out of UD: ";
        System.out.println(user_state);

    }

    public void displayCssHover() {
        JavascriptExecutor js = (JavascriptExecutor) client;
        js.executeScript("$j('#global-navigation .user .sub ul').css({'display': 'block'});");
    }

    /**
     * Use this to switch to the WebView from the main app
     *
     */
    public void switchToWebView(){

        Set<?> s_add2 =client_appium.getWindowHandles();

        List list_s_add2 = new ArrayList(s_add2);
        for (int i = 0; i < list_s_add2.size(); i++) {
            Object windowHandle = list_s_add2.get(i);
            client_appium.switchTo().window(windowHandle.toString());
        }
    }

    public void switchFromWebView() throws Exception{

        ((RemoteWebDriver)client_appium).executeScript("mobile: leaveWebView");

    }
}
