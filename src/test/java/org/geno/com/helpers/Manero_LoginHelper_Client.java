package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

/**
 * Contains all the methods for the WebElements for logging into Manero
 * @author geno
 * @see IHelper
 */
public class Manero_LoginHelper_Client extends IHelper {

    public Manero_LoginHelper_Client(WebDriver client) {
        super(client,"Manero_Login_v2.xml");
    }

    /**
     * Get the login email address box
     * @return WebElement
     */
    public WebElement getEmailAddressBox() {
        return locateWebElement("EmailAddress");
    }

    /**
     * Get the login password box
     * @return WebElement
     */
    public WebElement getPasswordBox() {
        return locateWebElement("Password");
    }

    /**
     * Get the Login button
     * @return WebElement
     */
    public WebElement getLoginButton() {
        return locateWebElement("Login");
    }

    /**
     * Get the reset password link
     * @return WebElement
     */
    public WebElement getResetPasswordLink() {
        return locateWebElement("ResetPasswordLink");
    }

    /**
     * Get the reset password email box
     * @return WebElement
     */
    public WebElement getEmailResetBox() {
        return locateWebElement("ResetEmail");
    }

    /**
     * Get the send button for the reset password email
     * @return WebElement
     */
    public WebElement getSendButton() {
        return locateWebElement("Send");
    }

    /**
     * Get the reset password cancel button
     * @return WebElement
     */
    public WebElement getResetCancelButton() {
        return locateWebElement("Cancel");
    }

    
    ///Edit Settings

    /**
     * Get the edit settings link
     * @return WebElement
     */
    public WebElement getEditSettingsLink() {
        return locateWebElement("EditSettings");
    }

    /**
     * Get the edit email box
     * @return WebElement
     */
    public WebElement getEditEmailBox() {
        return locateWebElement("EditEmail");
    }

    /**
     * Get the edit password box
     * @return WebElement
     */
    public WebElement getEditPasswordBox() {
        return locateWebElement("EditPassword");
    }

    /**
     * Get the confirm edit password box
     * @return WebElement
     */
    public WebElement getConfirmEditPasswordBox() {
        return locateWebElement("ConfirmPassword");
    }

    /**
     * Get the Chicago edition check box
     * @return WebElement
     */
    public WebElement getEditionChicagoCheckBox() {
        return locateWebElement("EditionChicago");
    }

    /**
     * Get the Miami edition check box
     * @return WebElement
     */
    public WebElement getEditionMiamiCheckBox() {
        return locateWebElement("EditionMiami");
    }

    /**
     * Get the New York edition check box
     * @return WebElement
     */
    public WebElement getEditionNewYorkCheckBox() {
        return locateWebElement("EditionNewYork");
    }

    /**
     * Get the LA edition checkbox
     * @return WebElement
     */
    public WebElement getEditionLACheckBox() {
        return locateWebElement("EditionLA");
    }

    /**
     * Get the National edition checkbox
     * @return WebElement
     */
    public WebElement getEditionNationalCheckBox() {
        return locateWebElement("EditionNational");
    }

    /**
     * Get the Houston edition checkbox
     * @return WebElement
     */
    public WebElement getEditionHoustonCheckBox() {
        return locateWebElement("EditionHouston");
    }

    /**
     * Get the update button checkbox
     * @return WebElement
     */
    public WebElement getUpdateButton() {
        return locateWebElement("Update");
    }

    /**
     * Get the confirmation message box
     * @return WebElement
     */
    public WebElement getMessageBox() {
        return locateWebElement("MessageBox");
    }

    /**
     * Get the close button
     * @return WebElement
     */
    public WebElement getCloseButton() {
        return locateWebElement("Close");
    }

}