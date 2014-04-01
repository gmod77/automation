package org.geno.com.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

import java.util.List;

/**
 * Contains all the methods for the WebElements for Signing up on Manero
 * @author geno
 * @see org.geno.com.common.IHelper
 */
public class Driven_SignupHelper_Client extends IHelper {

    public Driven_SignupHelper_Client(WebDriver client) {
        super(client,"Driven_SignUp_v2.xml");
    }

    // Step1

    /**
     * Get sign up email box
     * @return WebElement
     */
    public WebElement getSignInEmailBox() {
        locateWebElement("EmailPlaceHolder").click();
        return locateWebElement("Email");
    }

    // Select Editions

    /**
     * Get the Chicago edition checkbox
     * @return WebElement
     */
    public WebElement getLasVegasCheckBox() {
        return locateWebElement("SelectEditionsLasVegas");
    }

    /**
     * Get the Miami edition checkbox
     * @return WebElement
     */
    public WebElement getJetsetCheckBox() {
        return locateWebElement("SelectEditionsJetset");
    }

    /**
     * Get the Los Angeles edition checkbox
     * @return WebElement
     */
    public WebElement getSkiBoardCheckBox() {
        return locateWebElement("SelectEditionsSkiBoard");
    }

    /**
     * Get the National edition checkbox
     * @return WebElement
     */
    public WebElement getNationalCheckBox() {
        return locateWebElement("SelectEditionsNational");
    }

    /**
     * Get the Houston edition checkbox
     * @return WebElement
     */
    public WebElement getHoustonCheckBox() {
        return locateWebElement("SelectEditionsHouston");
    }

    /**
     * Get the New York edition checkbox
     * @return WebElement
     */
    public WebElement getNewYorkCheckBox() {
        return locateWebElement("SelectEditionsNewYork");
    }

    /**
     * Get the Join Button
     * @return WebElement
     */
    public WebElement getJoinButton() {
        return locateWebElement("Join");
    }

    
    //Signup modal 2

    /**
     * Get the password box
     * @return WebElement
     */
    public WebElement getPasswordBox() {
        locateWebElement("EnterPasswordPlaceHolder").click();
        return locateWebElement("EnterPassword");
    }

    /**
     * Get the confirm password box
     * @return WebElement
     */
    public WebElement getConfirmPasswordBox() {
        locateWebElement("ConfirmPasswordPlaceHolder").click();
        return locateWebElement("ConfirmPassword");
    }

    /**
     * Get the First Name box
     * @return WebElement
     */
    public WebElement getFirstNameBox() {
        locateWebElement("EnterFirstNamePlaceHolder").click();
        return locateWebElement("EnterFirstName");
    }

    /**
     * Get the Last Name box
     * @return WebElement
     */
    public WebElement getLastNameBox() {
        locateWebElement("EnterLastNamePlaceHolder").click();
        return locateWebElement("EnterLastName");
    }

    /**
     * Select gender based on target value
     * @param targetValue options are Male/Female
     * @throws IllegalArgumentException if Male or Female is not passed
     */
    public void selectGender(String targetValue) {
        if (targetValue.equals("Male") || targetValue.equals("Female")) {
            List <WebElement> options = locateWebElement("SelectGender").findElements(By.tagName("option"));
            for (WebElement option : options) {
                if (targetValue.equalsIgnoreCase(option.getText())) {
                    option.click();
                    break;
                }
            }
        } else {
            throw new IllegalArgumentException("Options to pass should either be 'Male' or 'Female'");
        }
    }

    /**
     * Get zip code box
     * @return WebElement
     */
    public WebElement getZipCodeBox() {
        locateWebElement("EnterZipCodePlaceHolder").click();
        return locateWebElement("EnterZipCode");
    }

    /**
     * Get Birthday box
     * @return WebElement
     */
    public WebElement getBirthdayBox() {
        locateWebElement("EnterBirthdayPlaceHolder").click();
        return locateWebElement("EnterBirthday");
    }

    /**
     * Get the submit button
     * @return WebElement
     */
    public WebElement getSubmitButton() {
        return locateWebElement("ClickSubmit");
    }

    // Signup modal 3: Invite Friends

    /**
     * Get the email a friend text box based on value passed
     * @param num Legal values are 1 through 6
     * @throws IllegalArgumentException
     * @return WebElement
     */
    public WebElement getEmailFriendBox(int num) {
        if (num < 1 || num > 6) {
            throw new IllegalArgumentException("Enter a number between 1 and 6");
        } else {
            locateWebElement("InviteEmail" + num + "PlaceHolder").click();
            return locateWebElement("InviteEmail" + num);
        }
    }

    /**
     * Get Invite button for email friends
     * @return WebElement
     */
    public WebElement getInviteButton() {
        return locateWebElement("ClickInvite");
    }

    /**
     * Get skip link to skip emailing friends
     * @return WebElement
     */
    public WebElement getSkipLink() {
        return locateWebElement("ClickSkip");
    }

    // Step 4

    /**
     * Get the FB friend us button
     * @return WebElement
     */
    public WebElement getFbFriendUsButton() {
        return locateWebElement("FBFriendUs");
    }

    /**
     * Get the Twitter follow button
     * @return WebElement
     */
    public WebElement getTwitterFollowButton() {
        return locateWebElement("TwitterFollowUs");
    }

    /**
     * Get the privacy policy link
     * @return WebElement
     */
    public WebElement getPrivacyPolicyLink() {
        return locateWebElement("PrivacyPolicyLink");
    }

    /**
     * Get the user agreement link
     * @return WebElement
     */
    public WebElement getTermsConditionsLink() {
        return locateWebElement("TermsConditionsLink");
    }

    /**
     * Get the close button on the final modal window
     * @return WebElement
     */
    public WebElement getCloseButton() {
        return locateWebElement("CloseButton");
    }

}