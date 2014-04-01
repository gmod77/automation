package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

public class Perks_LoginHelper_Client extends IHelper {

    public Perks_LoginHelper_Client(WebDriver client) {
        super(client,"Perks_SignUp_v2.xml");
    }

    /**
     * Get the email address login text box
     * found in the Seal Sign Up box.
     * @return WebElement
     */
    public WebElement getEmailAddressBox() {
        return locateWebElement("SigninEmail");

        // driver.findElement(By.id("foo")).click()
    }

    /**
     * Get the password text box
     * found in the Seal Sign Up box.
     * @return WebElement
     */
    public WebElement getEnterPasswordBox() {
        return locateWebElement("SigninPassword");
    }

    /**
     * Get the login button of
     * the Seal Sign Up box.
     * @return WebElement
     */
    public WebElement getLoginButton() {
        return locateWebElement("SignIn");
    }

    /**
     * Get the password reset link
     * found in the Seal Sign Up box.
     * @return WebElement
     */
    public WebElement getResetPasswordLink() {
        return locateWebElement("ForgotPasswordLink");
    }

    /**
     * Get the text box for the email address
     * entry.
     * @return WebElement
     */
    public WebElement getEmailToResetBox() {
        return locateWebElement("ForgotEmail");
    }

    /**
     * Get the send button for
     * the reset password flow.
     * @return WebElement
     */
    public WebElement getSendButton() {
        return locateWebElement("SendButton");
    }

}