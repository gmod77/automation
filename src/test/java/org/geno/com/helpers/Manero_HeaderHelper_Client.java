package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.geno.com.common.IHelper;

/**
 * Contains all the methods for the WebElements in the Manero header
 * @author geno
 * @see IHelper
 */
public class Manero_HeaderHelper_Client extends IHelper {

    /**
     * Declare client for below methods that require it.
     */
    private WebDriver client;

    public Manero_HeaderHelper_Client(WebDriver client) {
        super(client, "Manero_Header_v2.xml");
        this.client = client;
    }

    /**
     * Gets the logout link in the header
     * @return WebElement
     */
    public WebElement getLogoutLink() {
        return locateWebElement("Logout");
    }

    /**
     * Gets the Member Login link in the header
     * @return WebElement
     */
    public WebElement getMemberLoginLink() {
        return locateWebElement("MemberLogIn");
    }

    /**
     * Gets the Signup link in the header
     * @return WebElement
     */
    public WebElement getSignUpLink() {
        return locateWebElement("SignUp");
    }

    /**
     * Get the Change City link in the header
     * @return WebElement
     */
    public WebElement getChangeCityLink() {
        return locateWebElement("ChangeCity");
    }

    /**
     * Action to change the Manero edition to Chicago
     */
    public void clickChangeCityChicago() {
        // find DropdownToggle menu
        WebElement menu = getChangeCityLink();
        // find City element
        WebElement city = locateWebElement("Chicago");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        city.click();
    }

    /**
     * Action to change the Manero edition to Los Angeles
     */
    public void clickChangeCityLosAngeles() {
        // find DropdownToggle menu
        WebElement menu = getChangeCityLink();
        // find City element
        WebElement city = locateWebElement("LosAngeles");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        city.click();
    }

    /**
     * Action to change the Manero edition to Miami
     */

    public void clickChangeCityMiami() {
        // find DropdownToggle menu
        WebElement menu = getChangeCityLink();
        // find City element
        WebElement city = locateWebElement("Miami");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        city.click();
    }

    /**
     * Action to change the Manero edition to National
     */
    public void clickChangeCityNational() {
        // find DropdownToggle menu
        WebElement menu = getChangeCityLink();
        // find City element
        WebElement city = locateWebElement("National");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        city.click();
    }

    /**
     * Action to change the Manero edition to New York
     */
    public void clickChangeCityNewYork() {
        // find DropdownToggle menu
        WebElement menu = getChangeCityLink();
        // find City element
        WebElement city = locateWebElement("NewYork");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        city.click();
    }

    /**
     * Action to change the Manero edition to Houston
     */
    public void clickChangeCityHouston() {
        // find DropdownToggle menu
        WebElement menu = getChangeCityLink();
        // find SignOut element
        WebElement city = locateWebElement("Houston");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        city.click();
    }

}
