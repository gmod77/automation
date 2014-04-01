package org.geno.com.pageObjects.VacationSupremacy;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains all the elements and methods required for the Vacation Supremacy Entrance Page
 * @author geno (sargenziano@geno.com)
*/
public class VSEntrancePage {

    private final WebDriver driver;

    public VSEntrancePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy (id="email")
    private WebElement emailAddressField;

    @FindBy (id="zip")
    private WebElement zipCodeField;

    @FindBy (css=".button_wrapper>button")
    private WebElement countMeInButton;

    @FindBy (css=".subscribe_checkbox")
    private WebElement optInCheckBox;

    @FindBy (css=".error_messaging")
    private WebElement emailError;

    @FindBy (css=".error_messaging")
    private WebElement zipCodeError;

    /**
     * Enter email address
     * @param email address
     * @return this
     */
    public VSEntrancePage enterEmail(String email) {
        emailAddressField.sendKeys(email);
        return this;
    }

    /**
     * Clicking into the email address field will make the element 'glow'
     * Requirement: 1d.) When a user clicks into either field, there will be a glow around it.
     * @return boolean
     */
    public boolean emailAddressGlow() {
        emailAddressField.click();
        //Find the attribute that makes the glow
        String script = "var element = document.getElementById('email'), " +
                "style = window.getComputedStyle(element); " +
                "return style.getPropertyValue('background-image');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsReturn = js.executeScript(script).toString();

        return jsReturn.contains("input_error_sprite.png");
    }

    /**
     * Get the error text found on the page if an invalid or no email address is entered
     * Requirements: 1a.) The email address field is required. If the user does not enter an email address,
     *                    upon click, the user will receive the error, "You must enter an email address to enter."
     *               1b.) The email address must be a valid email format. If the user does not enter a
     *                    valid email address, upon click, the user will receive the error,
     *                    "Please enter a valid email address."
     * @return String email error text
     */
    public String emailErrorText() {
        return emailError.getText();
    }

    /**
     * Enter the zip code
     * @param zipCode Zip code
     * @return this
     */
    public VSEntrancePage enterZip(String zipCode) {
        zipCodeField.sendKeys(zipCode);
        return this;
    }

    /**
     * Get the error text found on the page if an invalid or no zip code is entered
     * Requirements: 2a.) The zip code field is required. If the user does not enter a zip code, upon click,
     *                    the user will receive the error, "You must enter a zip code to enter."
     *               2b.) The zip code must be a domestic zip code. If the user does not enter a valid,
     *                    domestic zip code, upon click, the user will receive the error,
     *                    "Please enter valid domestic zip code like 10001 or 10001-1234."
     * @return String zip code error text
     */
    public String zipCodeErrorText() {
        return zipCodeError.getText();
    }

    /**
     * Clicking into the email address field will make the element 'glow'
     * Requirement: 2d.) When a user clicks into either field, there will be a glow around it.
     * @return boolean
     */
    public boolean zipCodeGlow() {
        zipCodeField.click();
        //Find the attribute that makes the glow
        String script = "var element = document.getElementById('zip'), " +
                "style = window.getComputedStyle(element); " +
                "return style.getPropertyValue('background-image');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsReturn = js.executeScript(script).toString();

        return jsReturn.contains("input_error_sprite.png");
    }

    /**
     * Click the Opt In check box
     * @return this
     */
    public VSEntrancePage checkOptIn() {
        optInCheckBox.click();
        return this;
    }

    /**
     * Return the status of the Opt In check box
     * @return boolean
     */
    public boolean optInCheckStatus() {
        return optInCheckBox.getAttribute("class").contains("checked");
    }

    /**
     * Click the count me in button and go to the next step
     * @return VSInviteFriends
     */
    public VSInviteFriends clickCountMeInButton() {
        countMeInButton.click();
        return PageFactory.initElements(driver, VSInviteFriends.class);
    }

    /**
     * Get the current URL
     * @return String url
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

}
