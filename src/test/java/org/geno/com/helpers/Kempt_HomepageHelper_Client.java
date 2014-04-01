package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

/**
 * Contains all the methods for the WebElements on the Manero Homepage
 * @author geno
 * @see org.geno.com.common.IHelper
 */
public class Kempt_HomepageHelper_Client extends IHelper {

    public Kempt_HomepageHelper_Client(WebDriver client) {
        super(client,"Kempt_HomePage_v2.xml");
    }

    //HomePage

    /**
     * Get the Kempt logo on the top of the homepage
     * @return WebElement
     */

    public WebElement getTopBanner() {
        return locateWebElement("TopBanner");
    }

    public WebElement getKemptLogo() {
        return locateWebElement("KemptLogo");
    }

}