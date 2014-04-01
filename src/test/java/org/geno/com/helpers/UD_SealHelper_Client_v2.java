package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

public class UD_SealHelper_Client_v2 extends IHelper {

    public UD_SealHelper_Client_v2(WebDriver client) {
        super(client,"UD_Seal_v2.xml");
    }

    /**
     * Get the Join Now Button
     * found in the Seal Sign Up Box
     * @return
     */
    public WebElement getJoinNowButton() {
        return locateWebElement("JoinNowButton");
    }
    /**
     * Get the email address login text box
     * found in the Seal Sign Up box.
     * @return WebElement
     */
    public WebElement getEmailAddressBox() {
        return locateWebElement("EmailAddress");
    }

    /**
     * Get the password text box
     * found in the Seal Sign Up box.
     * @return WebElement
     */
    public WebElement getEnterPasswordBox() {
        return locateWebElement("Password");
    }

    /**
     * Get the login button of
     * the Seal Sign Up box.
     * @return WebElement
     */
    public WebElement getLoginButton() {
        return locateWebElement("Login");
    }

    /**
     * Get the password reset link
     * found in the Seal Sign Up box.
     * @return WebElement
     */
    public WebElement getResetPasswordLink() {
        return locateWebElement("ResetPasswordLinkSub");
    }

    /**
     * Get the text box for the email address
     * entry.
     * @return WebElement
     */
    public WebElement getEmailToResetBox() {
        return locateWebElement("ResetEmailSub");
    }

    /**
     * Get the send button for
     * the reset password flow.
     * @return WebElement
     */
    public WebElement getSendButton() {
        return locateWebElement("SendSub");
    }

    ///Edit Settings
    /**
     * Get the edit settings link after logging in.
     * Found in the UD Seal box.
     * @return WebElement
     */
    public WebElement getEditSettingsLink() {
        return locateWebElement("EditSettings");
    }

    /**
     * Get the edit settings link found on the My UD page.
     * @return WebElement
     */
    public WebElement getEditSettingsInMyUDLink() {
        return locateWebElement("EditSettingsMyUd");
    }

    /**
     * Get the enter password box on the My UD page.
     * @return WebElement
     */
    public WebElement getEnterPasswordMyUDBox() {
        return locateWebElement("PasswordMyUD");
    }

    /**
     * Get the confirm password box on the My UD page.
     * @return WebElement
     */
    public WebElement getConfirmPasswordMyUDBox() {
        return locateWebElement("PasswordConfirmMyUD");
    }

    /**
     * Get the DC editions check box found in
     * edit settings
     * @return WebElement
     */
    public WebElement getDCEditionSelect() {
        return locateWebElement("EditionsDC");
    }

    /**
     * Get the Philly editions check box found in
     * edit settings
     * @return WebElement
     */
    public WebElement getPhillyEditionSelect() {
        return locateWebElement("EditionsPhilly");
    }

    /**
     * Get the Seattle editions check box found in
     * edit settings
     * @return WebElement
     */
    public WebElement getSeattleEditionSelect() {
        return locateWebElement("EditionsSeattle");
    }

    /**
     * Get the DC perks check box found in
     * edit settings
     * @return WebElement
     */
    public WebElement getDCPerksSelect() {
        return locateWebElement("PerksDC");
    }

    /**
     * Get the update button found in
     * edit settings.
     * @return WebElement
     */
    public WebElement getUpdateButton() {
        return locateWebElement("Update");
    }

    /**
     * Get the close button found in the confirmation
     * box after editing your settings.
     * @return WebElement
     */
    public WebElement getCloseButton() {
        return locateWebElement("Close");
    }

}