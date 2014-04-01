package org.geno.com.pageObjects.VacationSupremacy;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * This class contains all the elements and methods required for the Vacation Supremacy Invite Friends Page
 * @author geno (sargenziano@geno.com)
 */
public class VSInviteFriends {

    private final WebDriver driver;

    public VSInviteFriends (WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css=".input_wrapper>input")
    private List<WebElement> emailFriendFields;

    @FindBy(css=".button_wrapper>button")
    private WebElement inviteButton;

    @FindBy(css=".skip")
    private WebElement skipLink;

    @FindBy()
    private WebElement invalidEmailError;

    @FindBy(css=".error_messaging")
    private WebElement missingEmailError;

    /**
     * Input an email address into one of the Friend Email invite boxes
     * @param friendBox Possible values 0-3
     * @param email Email Address
     * @return this
     */
    public VSInviteFriends enterFriendEmail(int friendBox, String email) {
        if (friendBox < 0 || friendBox > 3) throw new IllegalArgumentException("Allowable values: 0 - 3");

        emailFriendFields.get(friendBox).sendKeys(email);
        return this;
    }

    /**
     * Select a specific invite box and show that it glows when clicked.
     * @param friendBox Possible values 0-3
     * @return boolean
     */
    public boolean checkFriendEmailGlow(int friendBox) {
        if (friendBox < 0 || friendBox > 3) throw new IllegalArgumentException("Allowable values: 0 - 3");

        emailFriendFields.get(friendBox).click();

        String script = "var element = document.getElementById('referrals_" + (friendBox + 1) + "'), " +
                "style = window.getComputedStyle(element); " +
                "return style.getPropertyValue('background-image');";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsReturn = js.executeScript(script).toString();

        return jsReturn.contains("input_error_sprite.png");
    }

    /**
     * Get the text found when no email address is entered but invite was clicked.
     * Requirement: 1a.) The email address fields are not required. However, if the user clicks "invite",
     *                   without entering anything in the fields,
     *                   they will receive an error that says, "Please enter an email address."
     * @return String error text
     */
    public String missingEmailErrorText() {
        return missingEmailError.getText();
    }

    /**
     * Get the text found when an invalid email address is used for an invite friend email.
     * Requirement:     1b.) The email address must be a valid email format.
     *                       If the user does not enter a valid email address, upon click,
     *                       the user will receive the error, "Please enter a valid email address."
     * @return String error text
     */
    public String invalidEmailErrorText() {
        return invalidEmailError.getText();
    }

    /**
     * Click the invite button. Used when no invite email addresses are entered to generate error text.
     * @return this
     */
    public VSInviteFriends clickInviteNoEmail() {
        inviteButton.click();
        return this;
    }

    /**
     * Click the invite button and proceed to the thank you page.
     * @return VSThankYouPage
     */
    public VSThankYouPage clickInvite() {
        inviteButton.click();
        return PageFactory.initElements(driver, VSThankYouPage.class);
    }

    /*
    3.) 'Skip' Button:
    3a.) Upon click of "Skip", the user will move to the "Thank you" page, having not invited any friends to enter the contest.
    3b.) Please see attached for the hover state of the "skip" link, as well as the processing state.
     */

    /**
     * Clicks the skip link and proceed to the thank you page.
     * @return VSThankYouPage
     */
    public VSThankYouPage clickSkip() {
        skipLink.click();
        return PageFactory.initElements(driver, VSThankYouPage.class);
    }

    /**
     * Get the current URL
     * @return String url
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

}
