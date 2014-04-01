package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

/**
 * Contains all the methods for the WebElements on the Manero Homepage
 * @author geno
 * @see IHelper
 */
public class Manero_HomepageHelper_Client extends IHelper {

    public Manero_HomepageHelper_Client(WebDriver client) {
        super(client,"Manero_HomePage_v2.xml");
    }

    //HomePage

    /**
     * Get the Manero logo on the top of the homepage
     * @return WebElement
     */
    public WebElement getManeroLogo() {
        return locateWebElement("ManeroLogo");
    }

    /**
     * Get the Facebook Like button on the homepage
     * @return WebElement
     */
    public WebElement getFBLikeButton() {
        return locateWebElement("FBLike");
    }

    /**
     * Get the Facebook counter found on the homepage
     * @return WebElement
     */
    public WebElement getFBCounter() {
        return locateWebElement("FBLikeCounter");
    }

    /**
     * Get the twitter button on the homepage
     * @return WebElement
     */
    public WebElement getTweetButton() {
        return locateWebElement("TweetButton");
    }

    /**
     * Get the Cultura link found on the homepage
     * @return WebElement
     */
    public WebElement getCulturaLink() {
        return locateWebElement("Cultura");
    }

    /**
     * Get the Musica link found on the homepage
     * @return WebElement
     */
    public WebElement getMusicaLink() {
        return locateWebElement("Musica");
    }

    /**
     * Get the Sports link found on the homepage
     * @return WebElement
     */
    public WebElement getSportsLink() {
        return locateWebElement("Sports");
    }

    /**
     * Get the Style link found on the homepage
     * @return WebElement
     */
    public WebElement getStyleLink() {
        return locateWebElement("Style");
    }

    /**
     * Get the Entertainment link found on the homepage
     * @return WebElement
     */
    public WebElement getEntertainmentLink() {
        return locateWebElement("Entertainment");
    }

    /**
     * Get the Gadgets link found on the homepage
     * @return WebElement
     */
    public WebElement getGadgetsLink() {
        return locateWebElement("Gadgets");
    }

    /**
     * Get the Travel link found on the homepage
     * @return WebElement
     */
    public WebElement getTravelLink() {
        return locateWebElement("Travel");
    }

    /**
     * Get the Nightlife link found on the homepage
     * @return WebElement
     */
    public WebElement getNightLifeLink() {
        return locateWebElement("NightLife");
    }

    /**
     * Get the Carousel module found on the homepage
     * @return WebElement
     */
    public WebElement getCarouselModule() {
        return locateWebElement("Carousel");
    }

    /**
     * Get the short list module found on the homepage
     * @return WebElement
     */
    public WebElement getShortListModule() {
        return locateWebElement("ShortList");
    }

}