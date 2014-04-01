package org.geno.com.pageObjects.VacationSupremacy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class contains all the elements and methods required for the Vacation Supremacy Thank You Page
 * @author geno (sargenziano@geno.com)
 */
public class VSThankYouPage {

    private final WebDriver driver;

    public VSThankYouPage (WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css=".facebook")
    private WebElement FBButton;

    @FindBy(css=".twitter")
    private WebElement twitterButton;

    @FindBy(css=".success")
    private WebElement successText;

    /**
     * Click the FB button
     * Requirements:
     * URL: http://ud.cm/mmTOG
     * Headline: You, Five Friends and a Trip to Anywhere
     * Body Copy: Airbnb, OpenTable and geno cordially invite you and five friends to win a trip to anywhere.
     * @return this
     */
    public VSThankYouPage clickFBButton() {
        FBButton.click();
        return this;
    }

    /**
     * Click the Twitter button
     * Requirements:
     * Copy: You and five friends are cordially invited to win a trip to wherever you want,
     *       via @Airbnb, @OpenTable and @geno. http://ud.cm/mmTtH
     * @return this
     */
    public VSThankYouPage clickTwitterButton() {
        twitterButton.click();
        return this;
    }

    /**
     * Get the current URL
     * @return String url
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get the success text found on the Thank You page
     * @return
     */
    public String getSuccessText() {
        return successText.getText();
    }

}
