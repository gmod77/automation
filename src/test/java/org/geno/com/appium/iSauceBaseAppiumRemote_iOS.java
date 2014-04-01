package org.geno.com.appium;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.testng.SauceOnDemandTestListener;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.geno.com.common.UDBase;
import org.geno.com.helpers.*;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Listeners({SauceOnDemandTestListener.class})
public class iSauceBaseAppiumRemote_iOS implements UDBase {

    public SauceOnDemandAuthentication authentication;

    protected RemoteWebDriver client;
    protected RemoteWebDriver client_appium;
    private List<Integer> values;

    @BeforeClass
    public void prodCheck() {
        if (System.getProperty("testLocation") != null) {
            setDomain(System.getProperty("testLocation"));
        } else {
            setDomain("release");
        }
    }

    /**
     * If the tests can rely on the username/key to be supplied by environment variables or the existence
     * of a ~/.sauce-ondemand file, then we don't need to specify them as parameters, just create a new instance
     * of {@link com.saucelabs.common.SauceOnDemandAuthentication} using the no-arg constructor.
     *
     * @param username Sauce Labs User Name
     * @param key Sauce Labs User Key
     * @param os Requested Operating System
     * @param browser Requested Browser Type
     * @param version Requested Browser Version
     * @param method Test Method
     * @throws Exception
     */
    @Parameters({"username", "key", "os", "browser", "version","testLocation","profileUpload"})
    @BeforeMethod
    public void setUp(@Optional("") String username,
                      @Optional("") String key,
                      @Optional("") String os,
                      @Optional("") String browser,
                      @Optional("") String version,
                      @Optional("release") String testLocation,
                      @Optional("true") String profileUpload,
                      Method method) throws Exception {

        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(key)) {
            authentication = new SauceOnDemandAuthentication(username, key);
        } else {
            authentication = new SauceOnDemandAuthentication();
        }

        DesiredCapabilities capabilities;

        // The below is for axis tests. The listener works find for single one shot tests.
        String browserProp  = System.getProperty("SELENIUM_BROWSER");
        String versionProp  = System.getProperty("SELENIUM_VERSION");
        String platformProp = System.getProperty("SELENIUM_PLATFORM");

        if (StringUtils.isNotBlank(browserProp) && StringUtils.isNotBlank(platformProp)) {
            capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browserProp);
            capabilities.setCapability("version", versionProp);
            capabilities.setCapability("platform", platformProp);
            capabilities.setCapability("tags","Axis_Test");


            //System.out.println("AXIS TEST");

        } else if (StringUtils.isNotBlank(browser) && StringUtils.isNotBlank(os)) {
            capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            capabilities.setCapability("version", version);
            capabilities.setCapability("platform", os);
            capabilities.setCapability("tags","Single_Test");
            //System.out.println("SINGLE/NON-AXIS TEST");

            //We don't want to upload extensions for admin tests check for 'create'
            //But if it's firefox, upload the extensions
            if (browser.equals("firefox") && !method.getName().toLowerCase().contains("create") && profileUpload.equals("true")) {
                // Reporter.log("Not an Admin test> " + method.getName());
                File fireBugExt = new File("FFExtensions/firebug-1.11.4.xpi");
                File ySlowExt = new File("FFExtensions/yslow-firefox-3.1.6.xpi");
                File netExportExt = new File ("FFExtensions/netExport-0.9b3.xpi");
                File pageSpeedExt = new File ("FFExtensions/page-speed.xpi");

                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.addExtension(fireBugExt);
                firefoxProfile.addExtension(ySlowExt);
                firefoxProfile.addExtension(netExportExt);
                firefoxProfile.addExtension(pageSpeedExt);

                firefoxProfile.setPreference("app.update.auto",false);
                firefoxProfile.setPreference("app.update.enabled",false);
                firefoxProfile.setPreference("browser.search.update",false);

                firefoxProfile.setPreference("extensions.firebug.currentVersion","1.11.4");
                firefoxProfile.setPreference("extensions.firebug.allPagesActivation","on");
                firefoxProfile.setPreference("extensions.firebug.defaultPanelName", "YSlow");
                firefoxProfile.setPreference("extensions.firebug.net.enableSites", true);
                firefoxProfile.setPreference("extensions.firebug.addonBarOpened", true);
                firefoxProfile.setPreference("extensions.firebug.commandEditor", true);
                firefoxProfile.setPreference("extensions.firebug.firepath.showParentToolbar", true);
                firefoxProfile.setPreference("extensions.firebug.previousPlacement", 1);
                firefoxProfile.setPreference("extensions.firebug.showInfoTips",false);

                firefoxProfile.setPreference("extensions.yslow.autorun",true);
                firefoxProfile.setPreference("extensions.yslow.beaconInfo","grade");
                firefoxProfile.setPreference("extensions.yslow.beaconUrl","http://showslow.thedaddy.co/showslow/beacon/yslow/");
                firefoxProfile.setPreference("extensions.yslow.optinBeacon",true);

                firefoxProfile.setPreference("extensions.firebug.netexport.autoExportToServer",true);
                firefoxProfile.setPreference("extensions.firebug.netexport.beaconServerURL","http://showslow.thedaddy.co/showslow/beacon/har/");
                firefoxProfile.setPreference("extensions.firebug.netexport.sendToConfirmation",false);
                firefoxProfile.setPreference("extensions.firebug.netexport.showPreview",false);
                firefoxProfile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport",true);

                firefoxProfile.setPreference("extensions.PageSpeed.beacon.minimal.url","http://showslow.thedaddy.co/showslow/beacon/pagespeed/");
                firefoxProfile.setPreference("extensions.PageSpeed.beacon.minimal.enabled",true);
                firefoxProfile.setPreference("extensions.PageSpeed.autorun",true);
                firefoxProfile.setPreference("extensions.PageSpeed.beacon.minimal.autorun",true);

                capabilities.setCapability("firefox_profile", firefoxProfile);
            }
            //Reporter.log("Admin Test> " + method.getName(),true);

        } else if (browser.equals("chrome") && StringUtils.isBlank(version) && StringUtils.isNotBlank(os)){
            capabilities = DesiredCapabilities.chrome(); // Sauce doesn't want us to pass a browser version with chrome
            System.out.println("CHROME TEST");

        } else {
//            capabilities = DesiredCapabilities.firefox();
//            capabilities.setCapability("platform", "Windows 2008");
//            capabilities.setCapability("version","20");
//            capabilities.setCapability("idle-timeout","400");

            // set up remote appium driver for iphone

            DesiredCapabilities capabilities_appium = new DesiredCapabilities();

            capabilities_appium.setCapability("app","sauce-storage:TheMove.zip");
            capabilities_appium.setCapability("device","iPhone Simulator");
            capabilities_appium.setCapability("os","iOS 6.0");

            this.client_appium = new RemoteWebDriver(new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"), capabilities_appium);
            values = new ArrayList<Integer>();


            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Admin tests have 'create' in it's name.
            if (!method.getName().toLowerCase().contains("create") && profileUpload.equals("true")) {
                File fireBugExt = new File("FFExtensions/firebug-1.11.4.xpi");
                File ySlowExt = new File("FFExtensions/yslow-firefox-3.1.6.xpi");
                File netExportExt = new File ("FFExtensions/netExport-0.9b3.xpi");
                File pageSpeedExt = new File ("FFExtensions/page-speed.xpi");

                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.addExtension(fireBugExt);
                firefoxProfile.addExtension(ySlowExt);
                firefoxProfile.addExtension(netExportExt);
                firefoxProfile.addExtension(pageSpeedExt);

                firefoxProfile.setPreference("app.update.auto",false);
                firefoxProfile.setPreference("app.update.enabled",false);
                firefoxProfile.setPreference("browser.search.update",false);

                firefoxProfile.setPreference("extensions.firebug.currentVersion","1.11.4");
                firefoxProfile.setPreference("extensions.firebug.allPagesActivation","on");
                firefoxProfile.setPreference("extensions.firebug.defaultPanelName", "YSlow");
                firefoxProfile.setPreference("extensions.firebug.net.enableSites", true);
                firefoxProfile.setPreference("extensions.firebug.addonBarOpened", true);
                firefoxProfile.setPreference("extensions.firebug.commandEditor", true);
                firefoxProfile.setPreference("extensions.firebug.firepath.showParentToolbar", true);
                firefoxProfile.setPreference("extensions.firebug.previousPlacement", 1);
                firefoxProfile.setPreference("extensions.firebug.showInfoTips",false);

                firefoxProfile.setPreference("extensions.yslow.autorun",true);
                firefoxProfile.setPreference("extensions.yslow.beaconInfo","grade");
                firefoxProfile.setPreference("extensions.yslow.beaconUrl","http://showslow.thedaddy.co/showslow/beacon/yslow/");
                firefoxProfile.setPreference("extensions.yslow.optinBeacon",true);

                firefoxProfile.setPreference("extensions.firebug.netexport.autoExportToServer",true);
                firefoxProfile.setPreference("extensions.firebug.netexport.beaconServerURL","http://showslow.thedaddy.co/showslow/beacon/har/");
                firefoxProfile.setPreference("extensions.firebug.netexport.sendToConfirmation",false);
                firefoxProfile.setPreference("extensions.firebug.netexport.showPreview",false);
                firefoxProfile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport",true);

                firefoxProfile.setPreference("extensions.PageSpeed.beacon.minimal.url","http://showslow.thedaddy.co/showslow/beacon/pagespeed/");
                firefoxProfile.setPreference("extensions.PageSpeed.beacon.minimal.enabled",true);
                firefoxProfile.setPreference("extensions.PageSpeed.autorun",true);
                firefoxProfile.setPreference("extensions.PageSpeed.beacon.minimal.autorun",true);

                //capabilities.setCapability("firefox_profile", firefoxProfile);
            }

        }
//        capabilities.setCapability("name", "Test Method: " + method.getName());
//        capabilities.setCapability("selenium-version", "2.32.0");

//        this.client = new RemoteWebDriver(
//                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
//                capabilities);
//        ((RemoteWebDriver) client).setFileDetector(new LocalFileDetector());

        //Reporter.log(method.getName() + " SauceResultsUrl> " + getResultsUrl(getSessionId()));
        //Reporter.log(String.format("SauceOnDemandSessionID=%1$s job-name=%2$s", getSessionId(), method.getName()),true);

    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        //System.out.println("METHOD END " + result.getMethod().getMethodName());
        client_appium.quit();
    }

    //Declare helpers
    protected UD_HomepageHelper_Client_v2 ud_homepageHelper_Client;
    protected UD_HeaderHelper_Client_v2 ud_headerHelper_Client;
    protected UD_FooterHelper_Client_v2 ud_footerHelper_Client;
    protected UD_SealHelper_Client_v2 ud_sealHelper_Client;
    protected UD_SignupHelper_Client_v2 ud_signupHelper_Client;
    protected UD_RoundUP_Client_v2 ud_roundUP_client;
    protected UD_UnSubscribeHelper_Client_v2 ud_unSubscribeHelper_client;

    protected EmailHelper_Client emailHelper_Client;

    protected UD_AdminHelper_Client ud_adminHelper_client;
    protected Driven_AdminHelper_Client driven_adminHelper_client;

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
    public RemoteWebDriver getDriver() {
        return client;
    }

    /**
     * Get appium-webdriver instance
     *
     * @return WebDriver
     */
    public RemoteWebDriver getAppiumDriver() {
        return client_appium;
    }

//    public void getSauceResultsUrl() {
//        SauceUrlGenerator sauceUrlGenerator = new SauceUrlGenerator(authentication.getUsername(),authentication.getAccessKey(),getSessionId());
//        Reporter.log("SauceResultsUrl> " + sauceUrlGenerator.getResultsUrl());
//    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
//    @Override
//    public SauceOnDemandAuthentication getAuthentication() {
//        return authentication;
//    }

    /**
     * Grab the sessionid
     *
     * @return sessionId to string
     */
//    @Override
//    public String getSessionId() {
//        SessionId sessionId = ((RemoteWebDriver) client).getSessionId();
//        return (sessionId == null) ? null : sessionId.toString();
//    }

}
