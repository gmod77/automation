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
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.geno.com.common.UDBase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;

import junit.framework.Assert;

public class UD_Mobile_SmokeTest implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider, UDBase {

    public SauceOnDemandAuthentication authentication;

    protected WebDriver client;

    String date;
    String emailClient;
    String[] friend;

    public UD_Mobile_SmokeTest() {
//        emailHelper_Client = new EmailHelper_Client(client);

        friend = new String[5];
//        date = emailHelper_Client.generateDate(DATEFORMAT);
//        emailClient = emailHelper_Client.generateEmailClient();
//        friend = emailHelper_Client.generateFriendClient(5);
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
        Reporter.log(String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", getSessionId(), method.getName()));
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
        System.out.println("METHOD END " + result.getMethod().getMethodName());
        client.quit();
    }

    @Test
    public void ud_mobile_smoke_1() {

      //start afresh
        client.manage().deleteAllCookies();
        
        //assertTrue(client.findElement(By.xpath("//html/body/div/div[2]/div/div[2]/ul/li[2]/a")));
        //Assert.assertTrue(login(resource, emailResource));
        
        // go to homepage
        client.get(UD_DOMAIN);
        
//click Chicago
        client.findElement(By.xpath("//html/body/div/div[2]/div/div[2]/ul/li[2]/a")).click();
        
        //search for Jellyfish restaurant in Chicago
        
        //enter search text
        client.findElement(By.id("query")).sendKeys("Jellyfish");
        //click go
        client.findElement(By.xpath("//html/body/div/div[2]/form/div/a/img")).click();

        
        //verify displayed results
        Assert.assertTrue(client.findElement(By.xpath("//html/body/div/div[2]/div[3]/ul/li/div/div[3]/a")).isDisplayed());
        
        // use back button to go back to the citi's featured article 
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();

        
        //use the City icon on the upper-right corner to navigate to the main page
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();

        
//click Las Vegas
        client.findElement(By.xpath("//html/body/div/div[2]/div/div[2]/ul/li[3]/a")).click();

        
        //search for Allegro restaurant in Las Vegas
        
        //enter search text
        client.findElement(By.id("query")).sendKeys("Allegro");
        //click go
        client.findElement(By.xpath("//html/body/div/div[2]/form/div/a/img")).click();

                
        //verify displayed results
        Assert.assertTrue(client.findElement(By.xpath("//html/body/div/div[2]/div[3]/ul/li/div/div[3]/a")).isDisplayed());
                
        // use back button to go back to the citi's featured article 
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();

                
        //use the City icon on the upper-right corner to navigate to the main page
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();
            
//click Los Angeles
        client.findElement(By.xpath("//html/body/div/div[2]/div/div[2]/ul/li[4]/a")).click();

        
        //search for Nobu Malibu restaurant in LA
        
        //enter search text
        client.findElement(By.id("query")).sendKeys("Nobu Malibu");
        //click go
        client.findElement(By.xpath("//html/body/div/div[2]/form/div/a/img")).click();

                
        //verify displayed results
        Assert.assertTrue(client.findElement(By.xpath("//html/body/div/div[2]/div[3]/ul/li/div/div[3]/a")).isDisplayed());
                
        // use back button to go back to the citi's featured article 
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();

                
        //use the City icon on the upper-right corner to navigate to the main page
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();
        
        
//click Miami
        client.findElement(By.xpath("//html/body/div/div[2]/div/div[2]/ul/li[5]/a")).click();

        
        //search for Doma Polo Bistro restaurant in Miami
        
        //enter search text
        client.findElement(By.id("query")).sendKeys("Doma Polo Bistro");
        //click go
        client.findElement(By.xpath("//html/body/div/div[2]/form/div/a/img")).click();

                        
        //verify displayed results
        Assert.assertTrue(client.findElement(By.xpath("//html/body/div/div[2]/div[3]/ul/li/div/div[3]/a")).isDisplayed());
                        
        // use back button to go back to the citi's featured article 
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();

                        
        //use the City icon on the upper-right corner to navigate to the main page
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();

//click NYC
        client.findElement(By.xpath("//html/body/div/div[2]/div/div[2]/ul/li[6]/a")).click();
        //search for Tomoka restaurant in NYC
        
        //enter search text
        client.findElement(By.id("query")).sendKeys("Tomoka");
        //click go
        client.findElement(By.xpath("//html/body/div/div[2]/form/div/a/img")).click();

                                
        //verify displayed results
        Assert.assertTrue(client.findElement(By.xpath("//html/body/div/div[2]/div[3]/ul/li/div/div[3]/a")).isDisplayed());
                                
        // use back button to go back to the citi's featured article 
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();

                                
        //use the City icon on the upper-right corner to navigate to the main page
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();
        

        
//click San Fran
        client.findElement(By.xpath("//html/body/div/div[2]/div/div[2]/ul/li[7]/a")).click();
        
        //search for The Corner Store restaurant in SF
        
        //enter search text
        client.findElement(By.id("query")).sendKeys("The Corner Store");
        //click go
        client.findElement(By.xpath("//html/body/div/div[2]/form/div/a/img")).click();

                                
        //verify displayed results
        Assert.assertTrue(client.findElement(By.xpath("//html/body/div/div[2]/div[3]/ul/li/div/div[3]/a")).isDisplayed());
                                
        // use back button to go back to the citi's featured article 
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();

                                
        //use the City icon on the upper-right corner to navigate to the main page
        client.findElement(By.xpath("//html/body/div/div/a/img")).click();
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
//    protected UD_SignupHelper_Client ud_signupHelper_Client;
//    protected EmailHelper_Client emailHelper_Client;

    @BeforeTest
    public void prodCheck() { //@Optional("release") String testLocation) {
//        setDomain(testLocation);
        if (System.getProperty("testLocation") != null) {
            setDomain(System.getProperty("testLocation"));
        } else {
            setDomain("release");
        }
    }

    protected String UD_DOMAIN;
    protected String UD_DOMAIN_BASE;
    protected String UD_ADMIN_DOMAIN;
    protected String UD_ADMIN_USERNAME;
    protected String UD_ADMIN_PW;

    protected String PERKS_DOMAIN;
    protected String PERKS_ADMIN_DOMAIN;
    protected String PERKS_ADMIN_USERNAME;
    protected String PERKS_ADMIN_PW;

    protected String DRIVEN_DOMAIN;
    protected String DRIVEN_ADMIN_DOMAIN;
    protected String DRIVEN_ADMIN_USERNAME;
    protected String DRIVEN_ADMIN_PW;

    protected String KEMPT_DOMAIN;
    protected String KEMPT_ADMIN_DOMAIN;
    protected String KEMPT_ADMIN_USERNAME;
    protected String KEMPT_ADMIN_PW;

    protected String MANERO_DOMAIN;

    public void setDomain(String testLocation) {
        if (testLocation.equals("release")) {
            Reporter.log("Testing RELEASE",true);
            this.PERKS_DOMAIN           = PERKS_RELEASE_DOMAIN;
            this.PERKS_ADMIN_DOMAIN     = PERKS_RELEASE_ADMIN_DOMAIN;
            this.PERKS_ADMIN_USERNAME   = PERKS_RELEASE_ADMIN_USERNAME;
            this.PERKS_ADMIN_PW         = PERKS_RELEASE_ADMIN_PW;

            this.UD_DOMAIN              = UD_RELEASE_DOMAIN;
            this.UD_ADMIN_DOMAIN        = UD_RELEASE_ADMIN_DOMAIN;
            this.UD_ADMIN_USERNAME      = UD_RELEASE_ADMIN_USERNAME;
            this.UD_ADMIN_PW            = UD_RELEASE_ADMIN_PW;
            this.UD_DOMAIN_BASE         = UD_RELEASE_DOMAIN_BASE;

            this.DRIVEN_DOMAIN          = DRIVEN_RELEASE_DOMAIN;
            this.DRIVEN_ADMIN_DOMAIN    = DRIVEN_RELEASE_ADMIN_DOMAIN;
            this.DRIVEN_ADMIN_USERNAME  = DRIVEN_RELEASE_ADMIN_USERNAME;
            this.DRIVEN_ADMIN_PW        = DRIVEN_RELEASE_ADMIN_PW;

            this.KEMPT_DOMAIN           = KEMPT_RELEASE_DOMAIN;
            this.KEMPT_ADMIN_DOMAIN     = KEMPT_RELEASE_ADMIN_DOMAIN;
            this.KEMPT_ADMIN_USERNAME   = KEMPT_RELEASE_ADMIN_USERNAME;
            this.KEMPT_ADMIN_PW         = KEMPT_RELEASE_ADMIN_PW;

            this.MANERO_DOMAIN          = MANERO_RELEASE_DOMAIN;
        } else if (testLocation.equals("hotfix")) {
            Reporter.log("Testing HOTFIX",true);
            this.PERKS_DOMAIN           = PERKS_HOTFIX_DOMAIN;
            this.PERKS_ADMIN_DOMAIN     = PERKS_HOTFIX_ADMIN_DOMAIN;
            this.PERKS_ADMIN_USERNAME   = PERKS_HOTFIX_ADMIN_USERNAME;
            this.PERKS_ADMIN_PW         = PERKS_HOTFIX_ADMIN_PW;

            this.UD_DOMAIN              = UD_HOTFIX_DOMAIN;
            this.UD_ADMIN_DOMAIN        = UD_HOTFIX_ADMIN_DOMAIN;
            this.UD_ADMIN_USERNAME      = UD_HOTFIX_ADMIN_USERNAME;
            this.UD_ADMIN_PW            = UD_HOTFIX_ADMIN_PW;
            this.UD_DOMAIN_BASE         = UD_HOTFIX_DOMAIN_BASE;

            this.DRIVEN_DOMAIN          = DRIVEN_HOTFIX_DOMAIN;
            this.DRIVEN_ADMIN_DOMAIN    = DRIVEN_HOTFIX_ADMIN_DOMAIN;
            this.DRIVEN_ADMIN_USERNAME  = DRIVEN_HOTFIX_ADMIN_USERNAME;
            this.DRIVEN_ADMIN_PW        = DRIVEN_HOTFIX_ADMIN_PW;

            this.KEMPT_DOMAIN           = KEMPT_HOTFIX_DOMAIN;
            this.KEMPT_ADMIN_DOMAIN     = KEMPT_HOTFIX_ADMIN_DOMAIN;
            this.KEMPT_ADMIN_USERNAME   = KEMPT_HOTFIX_ADMIN_USERNAME;
            this.KEMPT_ADMIN_PW         = KEMPT_HOTFIX_ADMIN_PW;

            this.MANERO_DOMAIN          = MANERO_HOTFIX_DOMAIN;
        } else if (testLocation.equals("prod")) {
            Reporter.log("Testing PRODUCTION",true);
            this.PERKS_DOMAIN           = PERKS_PROD_DOMAIN;

            this.UD_DOMAIN              = UD_PROD_DOMAIN;
            this.UD_DOMAIN_BASE         = UD_PROD_DOMAIN_BASE;

            this.DRIVEN_DOMAIN          = DRIVEN_PROD_DOMAIN;

            this.KEMPT_DOMAIN           = KEMPT_PROD_DOMAIN;

            this.MANERO_DOMAIN          = MANERO_PROD_DOMAIN;

        }
    }

}
