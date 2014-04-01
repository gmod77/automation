package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

public class Perks_ProductPageHelper_Client extends IHelper {

    public Perks_ProductPageHelper_Client(WebDriver client) {
        super(client,"Perks_ProductPages.xml");
    }

    /**
     * Get the email address login text box
     * found in the Seal Sign Up box.
     * @return WebElement
     */
    public WebElement getPerkAFriendEmailBox() {
        return locateWebElement("EmailYourFriendBox");
    }

    /**
     * Get the password text box
     * found in the Seal Sign Up box.
     * @return WebElement
     */
    public WebElement getPerkAFriendEmailSendButton() {
        return locateWebElement("EmailYourFriendSendButton");
    }

    public WebElement getPerkAFriendConfirmationText() {
        return locateWebElement("PerkAFriendConfirmationText");
    }

}