package org.geno.com.sauce;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.geno.com.common.UDBase;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class iLocalMobileBase implements UDBase {

    private WebDriver client;
    private final String userAgent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 5_1_1 like Mac OS X; da-dk) AppleWebKit/534.46.0 (KHTML, like Gecko) CriOS/19.0.1084.60 Mobile/9B206 Safari/7534.48.3";
    public static final int DEFAULT_WAIT_4_PAGE = 12;

    @BeforeClass
    public void prodCheck() {
        if (System.getProperty("testLocation") != null) {
            setDomain(System.getProperty("testLocation"));
        } else {
            setDomain("scratch1");
        }
    }

    @Parameters({"testLocation"})
    @BeforeMethod
    public void setup(@Optional("release") String testLocation,
                                Method method) {

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override", userAgent);

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("firefox_profile", profile);

        client = new FirefoxDriver(caps);
        client.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS);
        //Emulate the iphone width a bit
        Dimension dim = new Dimension(360,1136);
        client.manage().window().setSize(dim);
    }

    /**
     * Execute at the end of a test method. Just print out the name of the method. Used for
     * tracking in console out.
     *
     * @param result Result comes from TestNG
     */
    @AfterMethod (alwaysRun = true)
    public void tearDown(ITestResult result) {
        System.out.println("METHOD END " + result.getMethod().getMethodName());
        client.quit();
    }

    //Declare strings
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
        if (testLocation.equals("scratch1")) {
            Reporter.log("Testing SCRATCH1");
            this.PERKS_DOMAIN           = PERKS_SCRATCH1_DOMAIN;
            this.PERKS_ADMIN_DOMAIN     = PERKS_SCRATCH1_ADMIN_DOMAIN;
            this.PERKS_ADMIN_USERNAME   = PERKS_SCRATCH1_ADMIN_USERNAME;
            this.PERKS_ADMIN_PW         = PERKS_SCRATCH1_ADMIN_PW;

            this.UD_DOMAIN              = UD_SCRATCH1_DOMAIN;
            this.UD_ADMIN_DOMAIN        = UD_SCRATCH1_ADMIN_DOMAIN;
            this.UD_ADMIN_USERNAME      = UD_SCRATCH1_ADMIN_USERNAME;
            this.UD_ADMIN_PW            = UD_SCRATCH1_ADMIN_PW;
            this.UD_DOMAIN_BASE         = UD_SCRATCH1_DOMAIN_BASE;

            this.DRIVEN_DOMAIN          = DRIVEN_SCRATCH1_DOMAIN;
            this.DRIVEN_ADMIN_DOMAIN    = DRIVEN_SCRATCH1_ADMIN_DOMAIN;
            this.DRIVEN_ADMIN_USERNAME  = DRIVEN_SCRATCH1_ADMIN_USERNAME;
            this.DRIVEN_ADMIN_PW        = DRIVEN_SCRATCH1_ADMIN_PW;

            this.KEMPT_DOMAIN           = KEMPT_SCRATCH1_DOMAIN;
            this.KEMPT_ADMIN_DOMAIN     = KEMPT_SCRATCH1_ADMIN_DOMAIN;
            this.KEMPT_ADMIN_USERNAME   = KEMPT_SCRATCH1_ADMIN_USERNAME;
            this.KEMPT_ADMIN_PW         = KEMPT_SCRATCH1_ADMIN_PW;

            this.MANERO_DOMAIN          = MANERO_SCRATCH1_DOMAIN;

        } else if (testLocation.equals("scratch2")) {
            Reporter.log("Testing SCRATCH2");
            this.PERKS_DOMAIN           = PERKS_SCRATCH2_DOMAIN;
            this.PERKS_ADMIN_DOMAIN     = PERKS_SCRATCH2_ADMIN_DOMAIN;
            this.PERKS_ADMIN_USERNAME   = PERKS_SCRATCH2_ADMIN_USERNAME;
            this.PERKS_ADMIN_PW         = PERKS_SCRATCH2_ADMIN_PW;

            this.UD_DOMAIN              = UD_SCRATCH2_DOMAIN;
            this.UD_ADMIN_DOMAIN        = UD_SCRATCH2_ADMIN_DOMAIN;
            this.UD_ADMIN_USERNAME      = UD_SCRATCH2_ADMIN_USERNAME;
            this.UD_ADMIN_PW            = UD_SCRATCH2_ADMIN_PW;
            this.UD_DOMAIN_BASE         = UD_SCRATCH2_DOMAIN_BASE;

            this.DRIVEN_DOMAIN          = DRIVEN_SCRATCH2_DOMAIN;
            this.DRIVEN_ADMIN_DOMAIN    = DRIVEN_SCRATCH2_ADMIN_DOMAIN;
            this.DRIVEN_ADMIN_USERNAME  = DRIVEN_SCRATCH2_ADMIN_USERNAME;
            this.DRIVEN_ADMIN_PW        = DRIVEN_SCRATCH2_ADMIN_PW;

            this.KEMPT_DOMAIN           = KEMPT_SCRATCH2_DOMAIN;
            this.KEMPT_ADMIN_DOMAIN     = KEMPT_SCRATCH2_ADMIN_DOMAIN;
            this.KEMPT_ADMIN_USERNAME   = KEMPT_SCRATCH2_ADMIN_USERNAME;
            this.KEMPT_ADMIN_PW         = KEMPT_SCRATCH2_ADMIN_PW;

            this.MANERO_DOMAIN          = MANERO_SCRATCH2_DOMAIN;

        } else if (testLocation.equals("release")) {
            Reporter.log("Testing RELEASE");
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
            Reporter.log("Testing HOTFIX");
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
            Reporter.log("Testing PRODUCTION");
            this.PERKS_DOMAIN           = PERKS_PROD_DOMAIN;

            this.UD_DOMAIN              = UD_PROD_DOMAIN;
            this.UD_DOMAIN_BASE         = UD_PROD_DOMAIN_BASE;

            this.DRIVEN_DOMAIN          = DRIVEN_PROD_DOMAIN;

            this.KEMPT_DOMAIN           = KEMPT_PROD_DOMAIN;

            this.MANERO_DOMAIN          = MANERO_PROD_DOMAIN;

        }
    }

    /**
     * Get webdriver instance
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return client;
    }

}
