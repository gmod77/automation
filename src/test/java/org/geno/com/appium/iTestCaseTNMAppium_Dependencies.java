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
import org.geno.com.sauce.iSauceBase;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

//import org.testng.annotations.Test;


//import static org.junit.Assert.assertTrue;

/**
 * @author Gene Modin
 */

public abstract class iTestCaseTNMAppium_Dependencies extends iSauceBaseAppiumLocal_iOS implements UDBase {
    //private WebDriver driver;
    //private WebDriver driver_web;
    //private List<Integer> values;
    private WebDriverWait wait;
    //private static final int MINIMUM = 0;
    //private static final int MAXIMUM = 10;
    //private int randPlaces = 4; //Number of digits for random email generation

    String DATEFORMAT="HHmmssSSS";
    String email = "udtestergene+"+generateDate(DATEFORMAT)+"@gmail.com";
    int password = 12345;
    String user_state = "";

//    @BeforeMethod
//    public void setUp() throws Exception {
//        // set up appium
//        File classpathRoot = new File(System.getProperty("user.dir"));
//        System.out.print(classpathRoot);
//        //File appDir = new File("/Users/geno/Library/Developer/Xcode/DerivedData/TheMove2-fqqypgygkvufhjdflpdbzhymlofa/Build/Products/Debug-iphonesimulator");
//        File appDir = new File("/Users/gmodin/Library/Developer/Xcode/DerivedData/TheMove2-drwhbwritpwjtxcceiudklahhgkz/Build/Products/Debug-iphonesimulator");
//        System.out.print("App's directory: "+ appDir + "\n");
//        File app = new File(appDir, "TheMove.app");
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
//        capabilities.setCapability(CapabilityType.VERSION, "6.0");
//        capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
//        capabilities.setCapability("app", app.getAbsolutePath());
//        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//        driver_web = new FirefoxDriver();
//        values = new ArrayList<Integer>();
//        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        user_state = "logged out: ";
//    }

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

    @DataProvider(name = "ATL")
    public Object[][] createData2() throws Exception{
        Object[][] retObjArr=getTableArray("src/test/resources/appium/data/Data.xls",
                "DataPool", "atlTestData");
        return(retObjArr);
    }

    public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception{
        String[][] tabArray=null;

        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        Sheet sheet = workbook.getSheet(sheetName);
        int startRow,startCol, endRow, endCol,ci,cj;
        Cell tableStart=sheet.findCell(tableName);
        startRow=tableStart.getRow();
        startCol=tableStart.getColumn();

        Cell tableEnd = sheet.findCell(tableName);  //, startCol+1,startRow+1, 100, 64000, false);

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

    public void waitForInterstitialToEnd()  throws Exception {
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

//    public void performFoodAndDrinksSearch_fromStart() throws Exception {
//
//        //getState();
//
//        // entry:
//        // location: home screen
//        // state: dock up
//        // option[1]: clickFoodAndDrinks
//        // option[2]: clickShortLists
//        // option[3]: clickFeedback
//        // option[4]: clickChooseYourCity
//        // option[5]: clickSignUp
//        // option[6]: clickFavorites
//        // option[7]: clickOutsideDock
//
//        // click "Food and Drinks"
//        System.out.println(user_state + "tap Food and Drinks");
//        WebElement dock_drinks = client_appium.findElement(By.name("dock drinks"));
//        dock_drinks.click();
//        System.out.println(user_state + "done");
//        //List<WebElement> tableCells= client_appium.findElements(By.tagName("tableView"));
//
//        //tap "What Do you Want?"
//        System.out.println(user_state + "tap What Do you Want?");
//        //client_appium.getPageSource();
//        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:136.00, y:244.00})");
//        System.out.println(user_state + "done");
//        Thread.sleep(1000);
//
//        //tap "I'm game for anything"
//        System.out.println(user_state + "tap I'm game for anything");
//        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:136.00, y:244.00})");
//        System.out.println(user_state + "done");
//        Thread.sleep(1000);
//
//        //tap "Who are you with?"
//        System.out.println(user_state + "tap Who are you with");
//        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:136.00, y:294.00})");
//        System.out.println(user_state + "done");
//        Thread.sleep(1000);
//
//        //tap "With nobody in particular"
//        System.out.println(user_state + "tap With nobody in particular");
//        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:136.00, y:294.00})");
//        System.out.println(user_state + "done");
//        Thread.sleep(1000);
//
//        //click submit button
//        System.out.println(user_state + "tap Submit button");
//        WebElement submit = client_appium.findElement(By.name("Submit"));
//        submit.click();
//        System.out.println(user_state + "done");
//
//        //wait till results show up
//        System.out.println(user_state + "wait for results to show up");
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("LIST")));
//
//        System.out.println(user_state + "Search performed, arrived @ Results List\n");
//
//
//        // exit:
//        // location:
//        // state:
//        // options:
//
//    }
    @Test (dependsOnMethods = {"selectCity"})
    public void performFoodAndDrinksSearch_afterCityChoice() throws Exception {

        //getState();

        // entry:
        // location: home screen
        // state: dock up
        // option[1]: clickFoodAndDrinks
        // option[2]: clickShortLists
        // option[3]: clickFeedback
        // option[4]: clickChooseYourCity
        // option[5]: clickSignUp
        // option[6]: clickFavorites
        // option[7]: clickOutsideDock

        //tap "What Do you Want?"
//        System.out.println(user_state + "tap What Do you Want?");
//        //client_appium.getPageSource();
//        //((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().delay(1)");
//        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:136.00, y:235.00})");
//        Thread.sleep(6000);
//        System.out.println(user_state + "done\n");


        //tap "I'm game for anything"
//        System.out.println(user_state + "tap I'm game for anything");
//        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().delay(2)");
//        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:136.00, y:240.00})");
//        Thread.sleep(20000);
//        //((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().delay(1)");
//        System.out.println(user_state + "done\n");


//        //tap "Who are you with?"
//        System.out.println(user_state + "tap Who are you with");
//        Thread.sleep(2000);
//        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:136.00, y:285.00})");
//        //((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().delay(1)");
//        System.out.println(user_state + "done\n");
//
//
//        //tap "With nobody in particular"
//        System.out.println(user_state + "tap With nobody in particular");
//        Thread.sleep(2000);
//        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:136.00, y:285.00})");
//        //((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().delay(4)");
//        System.out.println(user_state + "done\n");


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

        System.out.println(user_state + "Search performed, arrived @ Results List\n");


        // exit:
        // location:
        // state:
        // options:

    }

    @Test (dependsOnMethods = {"performFoodAndDrinksSearch_afterCityChoice"})
    public void switchSearchListToMap() throws Exception {
        //1. switch to Maps View
        System.out.println(user_state + "Attempting to switch to Map View of Search results \n");
        WebElement map = client_appium.findElement(By.name("MAP"));
        map.click();
        //Thread.sleep(3000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("btn search")));

        System.out.println(user_state + "Done, switched to Map View of Search results \n");
    }

    @Test (dependsOnMethods = {"switchSearchListToMap"})
    public void searchForVenueSignedOut(String term, String details) throws Exception {
        term = "Del Posto";
        details = "Del Posto, 85 10th Ave (at 16th St)";
        //do a search for specific venue
        //1. click UI search button
        System.out.println(user_state + "Click Search button \n");
        WebElement btn_search = client_appium.findElement(By.name("btn search"));
        btn_search.click();
        System.out.println(user_state + "done: clicked Search button \n");
        //2. search by name
        //find text-field for entry
        List<WebElement> search_textfield_is_somewhere_here = client_appium.findElements(By.tagName("textField"));
        Thread.sleep(3000);
        //enter term
        search_textfield_is_somewhere_here.get(0).sendKeys(term);
        System.out.println(user_state + "Searching for> " + term);

        // find iPhone's keyboard's "Search" button and tap it
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().buttons()[\"Search\"].tap();");

        //wait for result to show up
        System.out.println(user_state + "Waiting for result to show up...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(details)));
        Thread.sleep(2000);
        System.out.println(user_state + "Result returned for> " + term);

        //assuming a single match came back, click it
        System.out.println(user_state + "Click it! ");
        //((RemoteWebDriver) driver).executeScript("UIATarget.localTarget().tap({x:23.00, y:147.00})");
        WebElement venue_result = client_appium.findElement(By.name(details));
        Thread.sleep(2000);
        venue_result.click();
        System.out.println(user_state + "Done, should be on venue page now... Do basic checks on it... \n");
        //Thread.sleep(2000);

        //confirm venue listing showed up upon last click
        //venue header/name
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

    @Test (dependsOnMethods = {"signInTNM"})
    public void searchForVenueSignedIn(String term, String details) throws Exception {
        term = "Del Posto";
        details = "Del Posto, 85 10th Ave (at 16th St)";
        //do a search for specific venue
        //1. click UI search button
        System.out.println(user_state + "Click Search button \n");
        WebElement btn_search = client_appium.findElement(By.name("btn search"));
        btn_search.click();
        System.out.println(user_state + "done: clicked Search button \n");
        //2. search by name
        //find text-field for entry
        List<WebElement> search_textfield_is_somewhere_here = client_appium.findElements(By.tagName("textField"));
        Thread.sleep(3000);
        //enter term
        search_textfield_is_somewhere_here.get(0).sendKeys(term);
        System.out.println(user_state + "Searching for> " + term);

        // find iPhone's keyboard's "Search" button and tap it
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().buttons()[\"Search\"].tap();");

        //wait for result to show up
        System.out.println(user_state + "Waiting for result to show up...");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(details)));
        Thread.sleep(2000);
        System.out.println(user_state + "Result returned for> " + term);

        //assuming a single match came back, click it
        System.out.println(user_state + "Click it! ");
        //((RemoteWebDriver) driver).executeScript("UIATarget.localTarget().tap({x:23.00, y:147.00})");
        WebElement venue_result = client_appium.findElement(By.name(details));
        Thread.sleep(2000);
        venue_result.click();
        System.out.println(user_state + "Done, should be on venue page now... Do basic checks on it... \n");
        //Thread.sleep(2000);

        //confirm venue listing showed up upon last click
        //venue header/name
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

    @Test (dependsOnMethods = {"searchForVenueSignedOut"})
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

    @Test (dependsOnMethods = {"searchForVenueSignedIn"})
    public void markVenueAsFavoriteSignedIn()  {

        //1. click star to favorite the venue
        System.out.println(user_state + "Clicking the star icon on venue page in attempt to add to Favorite \n");
        WebElement star = client_appium.findElement(By.name("mark favorite off"));
        star.click();

        //2. assert that star is now enabled
        //assertTrue(star.isDisplayed());
    }

    @Test (dependsOnMethods = {"markVenueAsFavoriteSignedIn"})
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

    @Test (dependsOnMethods = {"loginUD"})
    public void confirmTNMFavoriteOnUDSignedIn(String term, String details) throws Exception {

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

    @Test (dependsOnMethods = {"waitForInterstitialToEnd"})
    public void  putAppInBackgroundThenBringItBack() throws Exception{
        //exit the app and return to it in 3 seconds
        System.out.println(user_state + "Putting the app in the background for 3 seconds.... and back up");
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().deactivateAppForDuration(3)");

        waitForInterstitialToEnd();
        System.out.println(user_state + "Put-in-the-background-then-relaunch action performed successfully \n");

    }

    @Test (dependsOnMethods = { "checkForAreYouAMemberDialog" })
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
            Thread.sleep(3000);
            System.out.println(user_state + "Done, Dock is open now, proceeding...\n");
        }
    }

    @Test (dependsOnMethods = { "waitForInterstitialToEnd" })
    public void checkForRatingDialog() throws Exception {

        // Handle notification window
        //check if Ratings dialog is open, if it is, tap "don't ask again"
        System.out.println(user_state + "check if Ratings dialog is open...");
        if (!client_appium.findElements(By.name("Show some love")).isEmpty()) {
            System.out.println(user_state + "Ratings Dialog is open, tapping Don't Ask Again...");
            client_appium.findElement(By.name("Don't ask again")).click();
            System.out.println(user_state + "Done...");
        }
        else {
            System.out.println(user_state + "It's not...\n");
        }
    }

    @Test (dependsOnMethods = { "checkForRatingDialog" })
    public void checkForAreYouAMemberDialog() throws Exception {

        // Handle notification window
        //check if Are You A Member dialog is open
        System.out.println(user_state + "check if Are You A Member dialog is open...");
        if (!client_appium.findElements(By.name("Yes of course")).isEmpty()) {
            System.out.println(user_state + "Are You A Member dialog is open, tapping Yes of course...");
            client_appium.findElement(By.name("Yes of course")).click();
            System.out.println(user_state + "Done...");
        }
        else {
            System.out.println(user_state + "It's not...\n");
        }
    }

    @Test (dependsOnMethods = { "engageDock" })
    public void selectCity (String city) throws Exception{
        //city = "New York";
        //engage Select city screen
        System.out.println(user_state + "Engaging Choose Your City screen...");
        client_appium.findElement(By.name("dock city")).click();
        Thread.sleep(2000);
        System.out.println(user_state + "Done \n");

        //click city
        System.out.println(user_state + "Selecting " + city);
        client_appium.findElement(By.name(city)).click();
        Thread.sleep(2000);
        System.out.println(user_state + "Done \n");

    }

    @Test (dependsOnMethods = {"engageDock"})
    public void signUpTNM (String email, String password) throws Exception{
        //email = "udtestergene+"+generateDate(DATEFORMAT)+"@gmail.com";
        //password = "12345";

          //engageDock();
          WebElement dock_signup = client_appium.findElement(By.name("dock signup"));

        //click Sign up button
            dock_signup.click();
            Thread.sleep(4000);
            //wait for Signup screen to appear (via Submit button)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));

            //enter email
            System.out.println(user_state + "Enter Email");
            //click UI search button
            System.out.println(user_state + "Click Email Field");

           // WebElement email_field = client_appium.findElement(By.name("Email"));
           //WebElement password_field = client_appium.findElement(By.name("Password"));
            //email_field.click();
            ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:76.00, y:170.00})");
            System.out.println(user_state + "done: clicked email field ... keyboard should be up \n");
            //Enter email
            ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().typeString(\"" + email + "\")");
            // find iPhone's keyboard's "Next" button and tap it
            ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().buttons()[\"Next\"].tap();");

            //enter password
            System.out.println(user_state + "Enter Password");
            //password_field.sendKeys("12345");
            ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().typeString(\"" + password + "\")");

            // find iPhone's keyboard's "Done" button and tap it
            ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().buttons()[\"Done\"].tap();");

            //Submit
            //wait Submit button
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

    @Test (dependsOnMethods = {"signUpTNM"})
    public void signOutTNM () throws Exception{

        engageDock();

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

    @Test (dependsOnMethods = {"signUpTNM"})
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
        Thread.sleep(2000);
        System.out.println(user_state + "Done \n");

        //enter email
        System.out.println(user_state + "Enter Email");
        //click UI search button
        System.out.println(user_state + "Click Email Field");
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().tap({x:90.00, y:215.00})");
        Thread.sleep(3000);
        System.out.println(user_state + "done: clicked email field ... keyboard should be up \n");
        //Enter email
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().typeString(\"" + email + "\")");
        // find iPhone's keyboard's "Next" button and tap it
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().buttons()[\"Next\"].tap();");

        //enter password
        System.out.println(user_state + "Enter Password");
        //password_field.sendKeys("12345");
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().typeString(\"" + password + "\")");

        // find iPhone's keyboard's "Done" button and tap it
        ((RemoteWebDriver) client_appium).executeScript("UIATarget.localTarget().frontMostApp().keyboard().buttons()[\"Done\"].tap();");

        //Submit
        //wait Submit button
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
        // click it
        //WebElement submit = client_appium.findElement(By.name("Submit"));
        //submit.click();
        Thread.sleep(7000);

        //wait Submit button on home screen
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Submit")));
        // set user state
        user_state = "logged in: ";
        System.out.println(user_state + "SIGNED IN SUCCESSFULLY: "+email + "\n");

    }


    ///////////////  Login/SignOut methods  for UD part of tests for TheNextMove

    @Test (dependsOnMethods = {"markVenueAsFavoriteSignedIn"})
    public void visitUDFirstTime(){

        // enter UD domain name, hit enter, arrive on homepage
        client.get(UD_DOMAIN);
        client.manage().deleteAllCookies();
        client.get(UD_DOMAIN);
        client.manage().addCookie(new Cookie ("udsubpop", "3",UD_DOMAIN_BASE, "/", null));
    }

    @Test (dependsOnMethods = {"visitUDFirstTime"})
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

    @Test (dependsOnMethods = {"accessNewYorkFromUDHomepage"})
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

    @Test (dependsOnMethods = {"confirmTNMFavoriteOnUDSignedIn"})
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
}
