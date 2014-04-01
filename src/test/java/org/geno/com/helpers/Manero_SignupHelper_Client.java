package org.geno.com.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

import java.util.List;

/**
 * Contains all the methods for the WebElements for Signing up on Manero
 * @author geno
 * @see IHelper
 */
public class Manero_SignupHelper_Client extends IHelper {

    public Manero_SignupHelper_Client(WebDriver client) {
        super(client,"Manero_SignUp_v2.xml");
    }

    // Step1

    /**
     * Get sign up email box
     * @return WebElement
     */
    public WebElement getSignInEmailBox() {
        return locateWebElement("Email");
    }

    // Select Editions

    /**
     * Get the Chicago edition checkbox
     * @return WebElement
     */
    public WebElement getChicagoCheckBox() {
        return locateWebElement("SelectEditionsChicago");
    }

    /**
     * Get the Miami edition checkbox
     * @return WebElement
     */
    public WebElement getMiamiCheckBox() {
        return locateWebElement("SelectEditionsMiami");
    }

    /**
     * Get the Los Angeles edition checkbox
     * @return WebElement
     */
    public WebElement getLosAngelesCheckBox() {
        return locateWebElement("SelectEditionsLosAngeles");
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
        return locateWebElement("EnterPassword");
    }

    /**
     * Get the confirm password box
     * @return WebElement
     */
    public WebElement getConfirmPasswordBox() {
        return locateWebElement("ConfirmPassword");
    }

    /**
     * Get the First Name box
     * @return WebElement
     */
    public WebElement getFirstNameBox() {
        return locateWebElement("EnterFirstName");
    }

    /**
     * Get the Last Name box
     * @return WebElement
     */
    public WebElement getLastNameBox() {
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
        return locateWebElement("EnterZipCode");
    }

    /**
     * Get Birthday box
     * @return WebElement
     */
    public WebElement getBirthdayBox() {
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
    public WebElement getUserAgreementLink() {
        return locateWebElement("UserAgreementLink");
    }

    /**
     * Get the close button on the final modal window
     * @return WebElement
     */
    public WebElement getCloseButton() {
        return locateWebElement("CloseButton");
    }

}