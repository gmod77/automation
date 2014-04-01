package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.geno.com.common.IHelper;

public class UD_HeaderHelper_Client_v2 extends IHelper {

    private WebDriver client;

    public UD_HeaderHelper_Client_v2(WebDriver client) {
        super(client,"UD_Header_v2.xml");
        this.client = client;
    }

// Start Top-Header Methods

    public WebElement getTwitterLink() {
        return locateWebElement("UDTwitterLink");
    }

    public WebElement getFacebookLink() {
        return locateWebElement("UDFacebookLink");
    }

    public WebElement getgenoLink() {
        return locateWebElement("genoLink");
    }

    /**
     * Get Perks link
     *
     * @return WebElement
     */
    public WebElement getPerksLink() {
        return locateWebElement("PerksLink");
    }

    /**
     * Get The Chronicles link
     *
     * @return WebElement
     */
    public WebElement getTheChroniclesLink() {
        return locateWebElement("TheChroniclesLink");
    }

    /**
     * Get Kempt link
     *
     * @return WebElement
     */
    public WebElement getKemptLink() {
        return locateWebElement("KemptLink");
    }

    /**
     * Get Driven link
     *
     * @return WebElement
     */
    public WebElement getDrivenLink() {
        return locateWebElement("DrivenLink");
    }

    public WebElement getSignInHover() {
        return locateWebElement("SignInHover");
    }

    /**
     * Get the sign up link
     *
     * @return WebElement
     */
    public WebElement getSignUpLink() {
        return locateWebElement("SignUp");
    }

    /**
     * Get the Member Login Link
     *
     * @return WebElement
     */
    public WebElement getMemberLoginLink() {
        return locateWebElement("MemberLogIn");
    }

    public WebElement getMyAccountHover() {
        return locateWebElement("MyAccountHover");
    }

    public WebElement getMyUDLink() {
        return locateWebElement("AccountSettings");
    }

    /**
     * Get the log out link found when logged in
     *
     * @return WebElement
     */
    public WebElement getLogoutLink() {
        return locateWebElement("Logout");
    }
// End Top-Header Methods

// Start Mid-Header Methods
    public WebElement getUDHeaderLogo() {
        return locateWebElement("UDHeaderLogo");
    }

    /**
     * Get the Change City Link
     *
     * @return WebElement
     */
    public WebElement getChangeCityLink() {
        return locateWebElement("ChangeCity");
    }

    /**
     * Change city to Atlanta after opening the
     * Change City Link
     *
     */
    public void clickChangeCityAtlanta() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("Atlanta");
        
        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change city to Boston after opening the
     * Change City Link
     *
     */
    public void clickChangeCityBoston() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("Boston");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change city to Chicago after opening the
     * Change City Link
     *
     */
    public void clickChangeCityChicago() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("Chicago");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change city to Dallas after opening the
     * Change City Link
     *
     */
    public void clickChangeCityDallas() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("Dallas");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change city to DC after opening the
     * Change City Link
     *
     */
    public void clickChangeCityDC() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("DC");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change site to JetSet after opening the
     * Change City Link
     *
     */
    public void clickChangeCityJetset() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("Jetset");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change city to LasVegas after opening the
     * Change City Link
     *
     */
    public void clickChangeCityLasVegas() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("LasVegas");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change city to LosAngeles after opening the
     * Change City Link
     *
     */
    public void clickChangeCityLosAngeles() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("LosAngeles");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change city to Miami after opening the
     * Change City Link
     *
     */
    public void clickChangeCityMiami() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("Miami");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change city to National after opening the
     * Change City Link
     *
     */
    public void clickChangeCityNational() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("National");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change city to New York after opening the
     * Change City Link
     *
     */
    public void clickChangeCityNewYork() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("NewYork");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change city to San Francisco after opening the
     * Change City Link
     *
     */
    public void clickChangeCitySanFrancisco() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("SanFrancisco");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }

    /**
     * Change site to SkiBoard after opening the
     * Change City Link
     *
     */
    public void clickChangeCitySkiBoard() {
        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement signout = locateWebElement("SkiBoard");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();
    }
// End Mid-Header Methods

// Start Sub-Header Methods
    /**
     * Get the search box
     *
     * @return WebElement
     */
    public WebElement getSearchBox() {
        return locateWebElement("SearchBox");
    }

    /**
     * Get the search box go button
     *
     * @return WebElement
     */
    public WebElement getSearchBoxGoButton() {
        return locateWebElement("SearchBoxGoButton");
    }

    /**
     * Get NightLife Link
     *
     * @return WebElement
     */
    public WebElement getNightlifeLink() {
        return locateWebElement("NightlifeLink");
    }

    /**
     * Get Food Link found on City pages
     *
     * @return WebElement
     */
    public WebElement getFoodLink() {
        return locateWebElement("FoodLink");
    }

    /**
     * Get Food&Drink link found only on the National page
     *
     * @return WebElement
     */
    public WebElement getFoodDrinkNationalLink() {
        return locateWebElement("FoodDrinkLink");
    }

    /**
     * Get Entertainment link
     *
     * @return WebElement
     */
    public WebElement getEntertainmentLink() {
        return locateWebElement("EntertainmentLink");
    }

    /**
     * Get the Travel link
     *
     * @return WebElement
     */
    public WebElement getTravelLink() {
        return locateWebElement("TravelLink");
    }

    /**
     * Get Style link
     *
     * @return WebElement
     */
    public WebElement getStyleLink() {
        return locateWebElement("StyleLink");
    }

    /**
     * Get Gear Link
     *
     * @return WebElement
     */
    public WebElement getGearLink() {
        return locateWebElement("GearLink");
    }

    /**
     * Get Leisure Link
     *
     * @return WebElement
     */
    public WebElement getLeisureLink() {
        return locateWebElement("LeisureLink");
    }

    /**
     * Get Mobile link
     *
     * @return WebElement
     */
    public WebElement getMobileLink() {
        return locateWebElement("MobileLink");
    }
// End Sub-Header Methods

}
