package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.geno.com.common.IHelper;

public class Perks_HeaderHelper_Client extends IHelper {
    
    private WebDriver client;
    
    public Perks_HeaderHelper_Client(WebDriver client) {
        super(client,"Perks_Header_v2.xml");
        this.client = client;
    }

    public WebElement getTwitterLink() {
        return locateWebElement("TwitterLink");
    }

    public WebElement getFaceBookLink() {
        return locateWebElement("FacebookLink");
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

    public WebElement getSignUpLink() {
        return locateWebElement("SignUp");
    }

    public WebElement getMemberLoginLink() {
        return locateWebElement("MemberLogIn");
    }


    public WebElement getCartLink() {
        return locateWebElement("Cart");
    }


    public WebElement getMyAccountHover() {
        return locateWebElement("MyAccountHover");
    }

    public WebElement getMyPerksLink() {
        return locateWebElement("MyPerksLink");
    }

    public WebElement getMyAccountLink() {
        return locateWebElement("AccountSettingsLink");
    }

    public WebElement getLogoutLink() {
        return locateWebElement("LogOutLink");
    }



    public WebElement getChangeCityLink() {
        return locateWebElement("ChangeCity");
    }


    public void clickChangeCityNewYork() {

        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement link = locateWebElement("NewYork");
        
        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);    
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        link.click();
    }    
    
    public void clickChangeCityNational() {

        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement link = locateWebElement("National");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        link.click();
    }    
    
    public void clickChangeCityChicago() {

        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement link = locateWebElement("Chicago");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        link.click();
    }    

    public void clickChangeCityMiami() {

        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement link = locateWebElement("Miami");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        link.click();
    }
    
    public void clickChangeCityBoston() {

        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement link = locateWebElement("Boston");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        link.click();
    }

    public void clickChangeCityDC() {

        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement link = locateWebElement("DC");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        link.click();
    }

    public void clickChangeCityLosAngeles() {

        // find DropdownToggle menu
        WebElement menu = locateWebElement("ChangeCity");
        // find SignOut element
        WebElement link = locateWebElement("LosAngeles");

        //build and perform the mouseOver with Advanced User Interactions API
        Actions builder = new Actions(client);
        builder.moveToElement(menu).build().perform();

        //then click when menu option is visible
        link.click();
    }

    public WebElement getPerksLogo() {
        return locateWebElement("PerksLogo");
    }

    public WebElement getInviteFriendsButton() {
        return locateWebElement("InviteFriends");
    }

    public WebElement getCitiesBreadCrumbLink() {
        return locateWebElement("Cities");
    }

}
