package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

/**
 * UD_UnSubscribeHelper_Client is used in the unsubscribing
 * of perks and editorials.
 */

public class UD_UnSubscribeHelper_Client_v2 extends IHelper {

    public UD_UnSubscribeHelper_Client_v2(WebDriver client) {
        super(client,"UD_SignUp_v2.xml");
    }

    /**
     * Get the Email box.
     *
     * @return WebElement
     */
    public WebElement getEmailBox() {
        return locateWebElement("Email");
    }

    /**
     * Get the Update Button to the
     * unsubscribe email.
     *
     * @return WebElement
     */
    public WebElement getUpdateButton() {
        return locateWebElement("UpdateButton");
    }

    /**
     * Get the confirmation button
     * to the unsubscribe email.
     *
     * @return WebElement
     */
    public WebElement getConfirmButton() {
        return locateWebElement("ConfirmButton");
    }
}
