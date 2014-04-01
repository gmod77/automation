package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

/**
 * Contains all the methods for the WebElements on the Manero Homepage
 * @author geno
 * @see org.geno.com.common.IHelper
 */
public class Driven_HomepageHelper_Client extends IHelper {

    public Driven_HomepageHelper_Client(WebDriver client) {
        super(client,"Driven_HomePage_v2.xml");
    }

    //HomePage

    /**
     * Get the Driven logo on the top of the homepage
     * @return WebElement
     */
    public WebElement getDrivenLogo() {
        return locateWebElement("DrivenLogo");
    }

    /**
     * Get the Home button on the homepage sidebar
     * @return WebElement
     */
    public WebElement getHomeButton() {
        return locateWebElement("Home");
    }

    /**
     * Get the Search button on the homepage sidebar
     * @return WebElement
     */
    public WebElement getSearchButton() {
        return locateWebElement("Search");
    }

    /**
     * Get the Archive button on the homepage sidebar
     * @return WebElement
     */
    public WebElement getArchiveButton() {
        return locateWebElement("Archive");
    }

    /**
     * Get the Previous button on the homepage sidebar
     * @return WebElement
     */
    public WebElement getPreviousButton() {
        return locateWebElement("Previous");
    }

    /**
     * Get the Next button on the homepage sidebar
     * @return WebElement
     */
    public WebElement getNextButton() {
        return locateWebElement("Next");
    }

    /**
     * Get the About button on the homepage sidebar
     * @return WebElement
     */
    public WebElement getAboutButton() {
        return locateWebElement("About");
    }

    /**
     * Get the Sign Up button on the homepage sidebar
     * @return WebElement
     */
    public WebElement getSignUpButton() {
        return locateWebElement("Signup");
    }

}