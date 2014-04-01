package org.geno.tests.sauce.saucetesting;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.geno.com.common.SauceUrlGenerator;
import org.geno.com.common.UDBase;
import org.geno.com.helpers.EmailHelper_Client;
import org.geno.com.helpers.UD_SignupHelper_Client_v2;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

@Listeners({SauceOnDemandTestListener.class})
public class NewYorkSignUpTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider, UDBase {

    public SauceOnDemandAuthentication authentication;

    protected WebDriver client;

    String date;
    String emailClient;
    List<String> friend;

    public NewYorkSignUpTest() {
        emailHelper_Client = new EmailHelper_Client(client);

        date = emailHelper_Client.generateDate(DATEFORMAT);
        emailClient = emailHelper_Client.generateEmailClient();
        friend = emailHelper_Client.generateFriendClient(5);
    }


    @Parameters({"username", "key", "os", "browser", "version"})
    @BeforeMethod
    public void setUp(@Optional("") String username,
                      @Optional("") String key,
                      @Optional("") String os,
                      @Optional("") String browser,
                      @Optional("") String version,
                      Method method) throws Exception {

//        System.out.println("\nSTARTING METHOD: " + method.getName() + "\n");
//        System.out.println("browser HERE> " + browser);
//        System.out.println("version HERE> " + version);
//        System.out.println("os HERE> " + Platform.extractFromSysProperty(os));
//
//        System.out.println("env SELENIUM_BROWSER> " + System.getenv("SELENIUM_BROWSER"));
//        System.out.println("env SELENIUM_VERSION> " + System.getenv("SELENIUM_VERSION"));
//        System.out.println("env SELENIUM_PLATFORM> " + System.getenv("SELENIUM_PLATFORM"));
//        System.out.println("env SELENIUM_DRIVER> " + System.getenv("SELENIUM_DRIVER"));
//        System.out.println("env SELENIUM_STARTING_URL> " + System.getenv("SELENIUM_STARTING_URL"));
//
//        System.out.println("prop SELENIUM_BROWSER> " + System.getProperty("SELENIUM_BROWSER"));
//        System.out.println("prop SELENIUM_VERSION> " + System.getProperty("SELENIUM_VERSION"));
//        System.out.println("prop SELENIUM_PLATFORM> " + System.getProperty("SELENIUM_PLATFORM"));
//        System.out.println("prop SELENIUM_DRIVER> " + System.getProperty("SELENIUM_DRIVER"));

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(key)) {
            authentication = new SauceOnDemandAuthentication(username, key);
        } else {
            authentication = new SauceOnDemandAuthentication();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();

        // The below is for axis tests. The listener works find for single one shot tests.
        String browserProp = System.getProperty("SELENIUM_BROWSER");
        String versionProp = System.getProperty("SELENIUM_VERSION");
        String platformProp = System.getProperty("SELENIUM_PLATFORM");
        //&& StringUtils.isNotBlank(versionProp)
        if (StringUtils.isNotBlank(browserProp) && StringUtils.isNotBlank(platformProp)) {
            capabilities.setBrowserName(browserProp);
            capabilities.setCapability("version", versionProp);
            capabilities.setCapability("platform", Platform.extractFromSysProperty(platformProp));
            capabilities.setCapability("tags","Axis_Test");
            System.out.println("AXIS TEST");
        //&& StringUtils.isNotBlank(version)
        } else if (StringUtils.isNotBlank(browser) && StringUtils.isNotBlank(os)) {
            capabilities.setBrowserName(browser);
            capabilities.setCapability("version", version);
            capabilities.setCapability("platform", Platform.extractFromSysProperty(os));
            capabilities.setCapability("tags","Single_Test");
            System.out.println("SINGLE/NON-AXIS TEST");

        } else {
            capabilities = DesiredCapabilities.firefox();
            System.out.println("Failed to pass caps from Jenkins");
        }

        capabilities.setCapability("name", "Test Method: " + method.getName());
        this.client = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        ((RemoteWebDriver) client).setFileDetector(new LocalFileDetector());
    }


    /**
     * Grab the sessionid
     *
     * @return sessionId to string
     */
    @Override
    public String getSessionId() {
        SessionId sessionId = ((RemoteWebDriver) client).getSessionId();
        return (sessionId == null) ? null : sessionId.toString();
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(ITestResult result) throws Exception {
        System.out.println("METHOD END\n");
        client.quit();
    }

    public void getSauceResultsUrl() {
        SauceUrlGenerator sauceUrlGenerator = new SauceUrlGenerator(authentication.getUsername(),authentication.getAccessKey(),getSessionId());
        Reporter.log("SauceResultsUrl> " + sauceUrlGenerator.getResultsUrl());
    }

    @Test
    public void SignUp() throws Exception{

        ud_signupHelper_Client = new UD_SignupHelper_Client_v2(client);


        String email;
        email = emailClient;
        String var;
        var = date;

        client.get(UD_RELEASE_DOMAIN);
        // client.manage().addCookie(new Cookie("udsubpop", "3","geno-release.thedaddy.co", "/", null));

        // Click NYC Link
        WebElement el1 = client.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/a[12]"));
        el1.click();

        // Click SignUp
        WebDriverWait signUpWait = new WebDriverWait(client, 30);
        try {
            signUpWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/a[2]")));
        } catch (TimeoutException TE) {
            throw new TimeoutException("Sign Up Link Didn't show up in time");
        }

        WebElement el2 = client.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/a[2]"));
        el2.click();

        //b. Enter email address
        WebDriverWait wait = new WebDriverWait(client, 30);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        } catch (TimeoutException TE) {
            System.out.println("EMAIL BOX NOT FOUND");
        }
        // Stale element seems to happen here

        if (!StaleElementHandleByXpath(email)) {
            throw new ElementNotVisibleException("Email didn't show up");
        }
        System.out.println("UD EMAIL CLIENT> " + email);

        List<WebElement> EditionCheckBoxes = client.findElement(By.id("signupEditions")).findElement(By.className("editionsCheckboxes")).findElements(By.className("editionListItem"));
        for (int i = 0; i<EditionCheckBoxes.size();i++) {
            if (!EditionCheckBoxes.get(i).findElement(By.tagName("input")).isSelected()) {
                EditionCheckBoxes.get(i).findElement(By.tagName("input")).click();
            }
        }

        //click "more" link to show all Editorials
        WebElement el3 = client.findElement(By.xpath("/html/body/div[5]/div/div/div/form/div[4]/label/a"));
        el3.click();

        List<WebElement> EditorialCheckBoxes = client.findElement(By.id("signupMoreEditions")).findElement(By.className("editionsCheckboxes")).findElements(By.className("editionListItem"));
        for (int i = 0; i<EditorialCheckBoxes.size();i++) {
            if (!EditorialCheckBoxes.get(i).findElement(By.tagName("input")).isSelected()) {
                EditorialCheckBoxes.get(i).findElement(By.tagName("input")).click();
            }
        }

        //click "more" link to see the Perks editions
        WebElement el4 = client.findElement(By.xpath("/html/body/div[5]/div/div/div/form/div[5]/label/a"));
        el4.click();

        List<WebElement> PerksCheckBoxes = client.findElement(By.id("signupPerksEditions")).findElement(By.className("editionsCheckboxes")).findElements(By.className("editionListItem"));
        for (int i = 0; i<PerksCheckBoxes.size();i++) {
            if (!PerksCheckBoxes.get(i).findElement(By.tagName("input")).isSelected()) {
                PerksCheckBoxes.get(i).findElement(By.tagName("input")).click();
            }
        }


        //Click Join Button
        WebElement el5 = client.findElement(By.className("select_editions")).findElement(By.className("buttonHolder")).findElement(By.tagName("input"));
        el5.click();


        WebDriverWait accountVisible = new WebDriverWait(client, 60);
        try {
            accountVisible.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div[3]/div[2]/div[2]/div/div[2]/div")));
        } catch (TimeoutException TE) {
            throw new TimeoutException("POST TOOK TOO LONG");
        }
        String theEmail = client.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[2]/div[2]/div/div[2]/div")).getText();
        System.out.println("THE EMAIL> " + theEmail);

        //step2, 2nd signup modal:
        //enter PASSWORD
        WebElement enterPassword = client.findElement(By.id("flowPassword"));
        enterPassword.clear();
        enterPassword.sendKeys(PASSWORD);


        //confirm PASSWORD
        WebElement confirmPassword = client.findElement(By.id("flowPasswordRetype"));
        confirmPassword.clear();
        confirmPassword.sendKeys(PASSWORD);

        //First Name
        WebElement firstName = client.findElement(By.id("flowFirstName"));
        firstName.clear();
        firstName.sendKeys("FN_"+var);


        //Last Name
        WebElement lastName = client.findElement(By.id("flowLastName"));
        lastName.clear();
        lastName.sendKeys("LN_"+var);

        //Gender
        WebElement selectGender = client.findElement(By.id("flowGender"));
        List <WebElement> gender = selectGender.findElements(By.tagName("option"));
        for (WebElement option : gender){
            if (option.getText().equals("Male")){
                option.click();
                break;
            }
        }

        //Income Range
        WebElement selectIncome = client.findElement(By.id("flowIncome"));
        List <WebElement> income = selectIncome.findElements(By.tagName("option"));
        for (WebElement option : income){
            if (option.getText().equals("Less than $30,000")){
                option.click();
                break;
            }
        }

        //Zip Code
        WebElement enterZipCode = client.findElement(By.id("flowZip"));
        enterZipCode.clear();
        enterZipCode.sendKeys("10001");

        //Birthday (MM/DD/YYYY)
        WebElement enterBirthday = client.findElement(By.id("flowBirthday"));
        enterBirthday.clear();
        enterBirthday.sendKeys("07/07/1977");


        //click "SUBMIT" button
        WebElement submit = client.findElement(By.className("demos")).findElement(By.className("submitButtons")).findElement(By.tagName("input"));
        submit.click();


        //step3, 3rd signup modal: Invite Friends

        WebDriverWait InviteFriendsWindow = new WebDriverWait(client, 30);
        InviteFriendsWindow.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("ajaxSignupHolder")));

        for (int i = 0; i < friend.size(); i++) {
            ud_signupHelper_Client.getEmailFriendTextBox((i+1)).sendKeys(friend.get(i));
            System.out.println(friend.get(i));
        }

        // Try submitting the form instead of clicking the invite button
        client.findElement(By.xpath("/html/body/div[5]/div/div/div/form[1]/div/div[1]/input")).click();

        //step4, 4th signup modal confirmation, close final confirm signup box

        WebDriverWait ThankYouWindow = new WebDriverWait(client, 30);
        ThankYouWindow.until(ExpectedConditions.visibilityOfElementLocated(By.className("thanks")));

        WebElement thanks = client.findElement(By.className("thanks"));
        thanks.findElement(By.className("ajaxClose")).click();

        //end of registration
    }


    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }

    //Declare helpers
    protected UD_SignupHelper_Client_v2 ud_signupHelper_Client;
    protected EmailHelper_Client emailHelper_Client;

    /**
     * Set your own pause time
     * @param time Time in ms
     */
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean StaleElementHandleByXpath (String email) throws Exception {
        ud_signupHelper_Client = new UD_SignupHelper_Client_v2(client);
        int count = 0;
        boolean flag = false;
        do {
            try {
                ud_signupHelper_Client.getEnterEmailBox().sendKeys(email);
                count++;
                flag = true;
            } catch (StaleElementReferenceException e) {
                e.toString();
                System.out.println("Trying to recover from a stale element: " + e.getMessage());
            }
        } while (count < 4 && !flag);
        if (count >= 4) {
            return false;
        } else {
            return true;
        }
    }

}
