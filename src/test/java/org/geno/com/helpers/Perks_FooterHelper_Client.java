package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

/**
 * Contains all the returns for the web elements found in the
 * perks footer.
 * @author geno
 */
public class Perks_FooterHelper_Client extends IHelper {

    public Perks_FooterHelper_Client(WebDriver client) {
        super(client,"UD_Footer_v2.xml");
    }

    /**
     * Gets the About Us link found in the footer
     * @return WebElement
     */
    public WebElement getAboutUsLink() {
        return locateWebElement("AboutUs");
    }

    /**
     * Gets the Archives Link found in the footer
     * @return WebElement
     */
    public WebElement getArchivesLink() {
        return locateWebElement("Archives");
    }

    /**
     * Gets the My Account Link found in the footer
     * @return WebElement
     */
    public WebElement getMyAccountLink() {
        return locateWebElement("MyAccount");
    }

    /**
     * Gets the Contact Link found in the footer
     * @return WebElement
     */
    public WebElement getContactLink() {
        return locateWebElement("Contact");
    }

    /**
     * Gets the Jobs link found in the footer
     * @return WebElement
     */
    public WebElement getJobsLink() {
        return locateWebElement("Jobs");
    }

    /**
     * Gets the Advertise link found in the footer
     * @return WebElement
     */
    public WebElement getAdvertiseLink() {
        return locateWebElement("Advertise");
    }

    /**
     * Gets the Tips link found in the footer
     * @return WebElement
     */
    public WebElement getTipsLink() {
        return locateWebElement("Tips");
    }
    
    public WebElement getHelpLink() {
        return locateWebElement("Help");
    }

    /**
     * Gets the Email Issues link found in the footer
     * @return WebElement
     */
    public WebElement getEmailIssuesLink() {
        return locateWebElement("EmailIssues");
    }

    /**
     * Gets the Privacy Policy Link in the footer
     * @return WebElement
     */
    public WebElement getPrivacyPolicyLink() {
        return locateWebElement("PrivacyPolicy");
    }

    /**
     * Gets the User Agreement Link found in the footer
     * @return WebElement
     */
    public WebElement getUserAgreementLink() {
        return locateWebElement("UserAgreement");
    }

    /**
     * Gets the Unsubscribe Link found in the footer
     * @return WebElement
     */
    public WebElement getUnsubscribeLink() {
        return locateWebElement("Unsubscribe");
    }

    /**
     * Gets the Editorial Policy link found in the footer
     * @return WebElement
     */
    public WebElement getEditorialPolicyLink() {
        return locateWebElement("EditorialPolicy");
    }

    /**
     * Gets the Boston Link found in the footer
     * @return WebElement
     */
    public WebElement getBostonLink() {
        return locateWebElement("Boston");
    }

    /**
     * Gets the Chicago Link found in the footer
     * @return WebElement
     */
    public WebElement getChicagoLink() {
        return locateWebElement("Chicago");
    }

    /**
     * Gets the DC link found in the footer
     * @return WebElement
     */
    public WebElement getDCLink() {
        return locateWebElement("DC");
    }

    /**
     * Gets the Los Angeles link found in the footer
     * @return WebElement
     */
    public WebElement getLosAngelesLink() {
        return locateWebElement("LosAngeles");
    }

    /**
     * Gets the Miami link found in the footer
     * @return WebElement
     */
    public WebElement getMiamiLink() {
        return locateWebElement("Miami");
    }

    /**
     * Gets the National link found in the footer
     * @return WebElement
     */
    public WebElement getNationalLink() {
        return locateWebElement("National");
    }

    /**
     * Gets the New York link found in the footer
     * @return WebElement
     */
    public WebElement getNewYorkLink() {
        return locateWebElement("NewYork");
    }

}