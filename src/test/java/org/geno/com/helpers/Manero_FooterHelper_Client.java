package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

/**
 * Contains all the methods for the WebElements in the Manero footer
 * @author geno
 * @see IHelper
 */
public class Manero_FooterHelper_Client extends IHelper {

    public Manero_FooterHelper_Client(WebDriver client) {
        super(client,"Manero_Footer_v2.xml");
    }

    //HomePage
    /**
     * Gets the About Us link in the footer
     * @return WebElement
     */
    public WebElement getAboutUsLink() {
        return locateWebElement("AboutUs");
    }

    /**
     * Gets the Sign Up link in the footer
     * @return WebElement
     */
    public WebElement getSignUpLink() {
        return locateWebElement("SignUp");
    }

    /**
     * Gets the My Account link in the footer
     * @return WebElement
     */
    public WebElement getMyAccountLink() {
        return locateWebElement("MyAccount");
    }

    /**
     * Gets the Contact link footer
     * @return WebElement
     */
    public WebElement getContactLink() {
        return locateWebElement("Contact");
    }

    /**
     * Gets the Jobs link in the footer
     * @return WebElement
     */
    public WebElement getJobsLink() {
        return locateWebElement("Jobs");
    }

    /**
     * Gets the Advertise link in the footer
     * @return WebElement
     */
    public WebElement getAdvertiseLink() {
        return locateWebElement("Advertise");
    }

    /**
     * Gets the Email Issues link in the footer
     * @return WebElement
     */
    public WebElement getEmailIssuesLink() {
        return locateWebElement("EmailIssues");
    }

    /**
     * Gets the Privacy Policy link in the footer
     * @return WebElement
     */
    public WebElement getPrivacyPolicyLink() {
        return locateWebElement("PrivacyPolicy");
    }

    /**
     * Gets the User Agreement link in the footer
     * @return WebElement
     */
    public WebElement getUserAgreementLink() {
        return locateWebElement("UserAgreement");
    }

    /**
     * Gets the Unsubscribe link in the footer
     * @return WebElement
     */
    public WebElement getUnsubscribeLink() {
        return locateWebElement("Unsubscribe");
    }

    /**
     * Gets the Editorial Policy in the footer
     * @return WebElement
     */
    public WebElement getEditorialPolicyLink() {
        return locateWebElement("EditorialPolicy");
    }
    
    //cities
    /**
     * Gets the Chicago link in the footer
     * @return WebElement
     */
    public WebElement getChicagoLink() {
        return locateWebElement("Chicago");
    }

    /**
     * Gets the Houston link in the footer
     * @return WebElement
     */
    public WebElement getHoustonLink() {
        return locateWebElement("Houston");
    }

    /**
     * Gets the Los Angeles link in the footer
     * @return WebElement
     */
    public WebElement getLosAngelesLink() {
        return locateWebElement("LosAngeles");
    }

    /**
     * Gets the Miami link in the footer
     * @return WebElement
     */
    public WebElement getMiamiLink() {
        return locateWebElement("Miami");
    }

    /**
     * Gets the National link in the footer
     * @return WebElement
     */
    public WebElement getNationalLink() {
        return locateWebElement("National");
    }

    /**
     * Gets the New York link in the footer
     * @return WebElement
     */
    public WebElement getNewYorkLink() {
        return locateWebElement("NewYork");
    }

}
