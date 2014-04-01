package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

public class UD_ResetEmailHelper_Client_v2 extends IHelper {

    public UD_ResetEmailHelper_Client_v2(WebDriver client) {
        super(client,"UD_ResetPassword_v2.xml");
    }

    /**
     * Get the Enter New Password box.
     *
     * @return WebElement
     */
    public WebElement getEnterNewPasswordBox() {
        return locateWebElement("NewPassword");
    }

    /**
     * Get the confirm new password box.
     *
     * @return WebElement
     */
    public WebElement getConfirmNewPasswordBox() {
        return locateWebElement("ConfirmNewPassword");
    }

    /**
     * Get the submit button.
     *
     * @return WebElement
     */
    public WebElement getSubmitButton() {
        return locateWebElement("Submit");
    }

}
