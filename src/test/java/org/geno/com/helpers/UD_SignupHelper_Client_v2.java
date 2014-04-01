package org.geno.com.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

import java.util.List;
import java.util.Random;

/**
 * This class contains all the methods required to complete
 * a sign up on geno. Most methods return a WebElement to
 * be interacted with.
 */
public class UD_SignupHelper_Client_v2 extends IHelper {

    private WebDriver client;

    public UD_SignupHelper_Client_v2(WebDriver client) {
        super(client,"UD_SignUp_v2.xml");
        this.client = client;
    }

//HomePage

    /**
     * Get the UDLogo found on the Home page
     * @return UDLogo WebElement
     */
    public WebElement getUDLogo() {
        return locateWebElement("UDLogo");
    }

    /**
     * Get the SignUp Seal
     * @return SignUpSeal WebElement
     */
    public WebElement getSignUpSeal() {
        return locateWebElement("SignUpSeal");
    }
//Sign In

    /**
     * Get the SignIn Seal Graphic in the SignIn modal
     * Use to confirm the modal window appeared
     * @return
     */
    public WebElement getSignInSealGraphic() {
        return locateWebElement("SignInSealGraphic");
    }

    /**
     * Get the SignIn text box in the SignIn modal
     * @return
     */
    public WebElement getSignInEmailTextBox() {
        locateWebElement("SignInEmailPlaceHolderTextBox").click();
        return locateWebElement("SignInEmailTextBox");
    }

    /**
     * Get the Password text box in the SignIn modal
     * @return
     */
    public WebElement getSignInPasswordTextBox() {
        locateWebElement("SignInPasswordPlaceHolderTextBox").click();
        return locateWebElement("SignInPasswordTextBox");
    }

    /**
     * Get the Sign In Button in the Sign In modal
     * @return
     */
    public WebElement getSignInButton() {
        return locateWebElement("SignInButton");
    }

    /**
     * Get the SignUp here link found at the bottom
     * of the SignIn modal
     * @return
     */
    public WebElement getSignUpHereLink() {
        return locateWebElement("SignUpHereLink");
    }

    /**
     * Get the Forgot Password link found at the bottom
     * of the SignIn modal
     * @return
     */
    public WebElement getSignInForgotPWLink() {
        return locateWebElement("SignInForgotPWLink");
    }

    /**
     * Get the Privacy Policy link found at the bottom of
     * the SignIn modal
     * @return
     */
    public WebElement getSignInPrivacyPolicyLink() {
        return locateWebElement("SignInPrivacyPolicyLink");
    }

    /**
     * Get the User Agreement link found at the bottom of
     * the SignIn modal
     * @return
     */
    public WebElement getSignInUsrAgreementLink() {
        return locateWebElement("SignInUsrAgreementLink");
    }

    /**
     * Get the 'X' close button found at the top right
     * corner of the modal window
     * @return
     */
    public WebElement getSignInCloseButton() {
        return locateWebElement("SignInXButton");
    }

    public WebElement getSignInPWResetEmailTextBox() {
        locateWebElement("SignInPWResetEmailPlaceholder").click();
        return locateWebElement("SignInPWResetEmailTextBox");
    }

    public WebElement getSignInPWResetSubmitButton() {
        return locateWebElement("SignInPWResetSubmitButton");
    }

    public WebElement getSignInPWResetCancelLink() {
        return locateWebElement("SignInPWResetCancelLink");
    }

//Header

//    public WebElement clickSignUpHeaderLink() {
//        locateWebElement("Header.SignUpHeaderLink");
//        //this.WaitForElementPresent(str, 20);
//        WebElement el = findElementAndCheckBy(str);
//        el;
//    }

    /**
     * Get the link to the Member Login found in the header
     * @return WebElement
     */
    public WebElement getMemberLogInHeaderLink() {
        return locateWebElement("MemberLogInHeaderLink");
    }

//Sign up modal 1

    /**
     * Get the email entry box found in Step 1
     * of the sign up flow.
     * @return WebElement
     */
    public WebElement getEnterEmailBox() {
        return locateWebElement("SignUpEmail");
    }

// Select Editions

    /**
     * Gets the checkbox for the National Edition found
     * in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getNationalEditionSelect() {
        return locateWebElement("SelectEditionsNational");
    }

    /**
     * Gets the checkbox for National Perks found
     * in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getNationalPerksSelect() {
        return locateWebElement("SelectEditionsNationalPerks");
    }

    /**
     * Gets the checkbox for the New York Edition found
     * in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getNewYorkEditionSelect() {
        return locateWebElement("SelectEditionsNewYork");
    }

    /**
     * Get the checkbox for New York Perks found
     * in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getNewYorkPerksSelect() {
        return locateWebElement("SelectEditionsNewYorkPerks");
    }

    /**
     * Get the checkbox for Driven found in
     * Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getDrivenSelect() {
        return locateWebElement("SelectEditionsDriven");
    }

    /**
     * Get the checkbox for JetSet found in
     * Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getJetSetSelect() {
        return locateWebElement("SelectEditionsJetset");
    }

    /**
     * Get the checkbox for the Las Vegas Edition
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getLasVegasEditionSelect() {
        return locateWebElement("SelectEditionsLasVegas");
    }

    /**
     * Get the checkbox for SkiBoard
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getSkiBoardSelect() {
        return locateWebElement("SelectEditionsSkiBoard");
    }

    /**
     * Get the link which opens up more options
     * to select more editions in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getMoreLink1() {
        return locateWebElement("MoreLink1");
    }

    /**
     * Get the checkbox of the Atlanta Edition
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getAtlantaEditionSelect() {
        return locateWebElement("SelectEditorialEditionsAtlanta");
    }

    /**
     * Get the checkbox of the Boston Edition
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getBostonEditionSelect() {
        return locateWebElement("SelectEditorialEditionsBoston");
    }

    /**
     * Get the checkbox of the Chicago Edition
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getChicagoEditionSelect() {
        return locateWebElement("SelectEditorialEditionsChicago");
    }

    /**
     * Get the checkbox of the Dallas Edition
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getDallasEditionSelect() {
        return locateWebElement("SelectEditorialEditionsDallas");
    }

    /**
     * Get the checkbox of the DC edition
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getDCEditionSelect() {
        return locateWebElement("SelectEditorialEditionsDC");
    }

    /**
     * Get the Los Angeles Edition
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getLosAngelesEditionSelect() {
        return locateWebElement("SelectEditorialEditionsLosAngeles");
    }

    /**
     * Get the checkbox of the Miami Edition
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getMiamiEditionSelect() {
        return locateWebElement("SelectEditorialEditionsMiami");
    }

    /**
     * Get the checkbox of the San Francisco Edition
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getSanFranciscoEditionSelect() {
        return locateWebElement("SelectEditorialEditionsSanFranciso");
    }

    /**
     * Get the 2nd more link found in
     * Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getMoreLink2() {
        return locateWebElement("MoreLink2");
    }

    /**
     * Get the checkbox of the Boston Perks
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getBostonPerksSelect() {
        return locateWebElement("SelectPerksEditionsBostonPerks");
    }

    /**
     * Get the checkbox of the Chicago Perks
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getChicagoPerksSelect() {
        return locateWebElement("SelectPerksEditionsChicagoPerks");
    }

    /**
     * Get the checkbox of the DC Perks
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getDCPerksSelect() {
        return locateWebElement("SelectPerksEditionsDCPerks");
    }

    /**
     * Get the checkbox of the LA Perks
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getLosAngelesPerksSelect() {
        return locateWebElement("SelectPerksEditionsLosAngelesPerks");
    }

    /**
     * Get the checkbox of the Miami Perks
     * found in Step 1 of the sign up flow.
     * @return WebElement
     */
    public WebElement getMiamiPerksSelect() {
        return locateWebElement("SelectPerksEditionsMiamiPerks");
    }

    /**
     * Get the Join button found in Step 1
     * of the sign up flow.
     * @return WebElement
     */
    public WebElement getJoinButton() {
        return locateWebElement("Join");
    }

//SignUp modal 2 (Help Us Help You)

    /**
     * Get the password text box
     * found in Step 2 of the sign up flow.
     * @return WebElement
     */
    public WebElement getPasswordTextBox() {
        return locateWebElement("EnterPassword");
    }

    /**
     * Get the confirm password text box
     * found in Step 2 of the sign up flow.
     * @return WebElement
     */
    public WebElement getConfirmPasswordTextBox() {
        return locateWebElement("ConfirmPassword");
    }

    /**
     * Get the first name text box
     * found in Step 2 of the sign up flow.
     * @return WebElement
     */
    public WebElement getFirstNameTextBox() {
        return locateWebElement("EnterFirstName");
    }

    /**
     * Get the last name text box
     * found in Step 2 of the sign up flow.
     * @return WebElement
     */
    public WebElement getLastNameTextBox() {
        return locateWebElement("EnterLastName");
    }

    /**
     * Select gender based on targetValue.
     * @param targetValue Gender male/female
     */
    public void selectGender(String targetValue) {
        List <WebElement> options = client.findElements(By.tagName("option"));
        for (WebElement option : options){
            if (targetValue.equalsIgnoreCase(option.getText())){
             option.click();
             break;
            }
        }
    }

    /**
     * Randomly select gender.
     */
    public void selectGenderRandom() {
        Random generator = new Random();
        int rnd = generator.nextInt(2);
        String gender;
        if (rnd == 0) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        WebElement el = locateWebElement("SelectGender");
        List <WebElement> options = el.findElements(By.tagName("option"));
        for (WebElement option : options){
            if (gender.equalsIgnoreCase(option.getText())){
                option.click();
                break;
            }
        }
    }

    /**
     * Select Income Range based on selection.
     * @param targetValue Income Range from Dropdown
     */
    public void selectIncomeRange(String targetValue) {
        WebElement el = locateWebElement("SelectIncomeRange");
        List <WebElement> options = el.findElements(By.tagName("option"));
        for (WebElement option : options){
            if (targetValue.equalsIgnoreCase(option.getText())){
             option.click();
             break;
            }
        }
    }

    /**
     * Randomly select income range.
     */
    public void selectIncomeRangeRandom() {
        Random generator = new Random();
        int rnd = generator.nextInt(9);
        String[] income = {"Less than $30,000","$30,000-$44,999","$45,000-$59,999","$60,000-$74,999",
                "$75,000-$99,999","$100,000-$199,999","$200,000-$299,999","$300,000-$499,999","$500,000+"};
        WebElement el = locateWebElement("SelectIncomeRange");
        List <WebElement> options = el.findElements(By.tagName("option"));
        for (WebElement option : options){
            if (income[rnd].equalsIgnoreCase(option.getText())){
                option.click();
                break;
            }
        }
    }

    /**
     * Get the zip code text box found in
     * Step 2 of the sign up flow.
     * @return WebElement
     */
    public WebElement getZipCodeTextBox() {
        return locateWebElement("EnterZipCode");
    }

    /**
     * Get the birthday text box found in
     * Step 2 of the sign up flow.
     * @return WebElement
     */
    public WebElement getBirtdayTextBox() {
        return locateWebElement("EnterBirthday");
    }

    /**
     * Get the submit button found in
     * Step 2 of the sign up flow.
     * @return WebElement
     */
    public WebElement getSubmitButton() {
        return locateWebElement("ClickSubmit");
    }


// Signup modal 3: Invite Friends

    /**
     * Get the email friend text box. Include
     * a number to get the requested box in Step 3
     * of the sign up flow.
     * @param num 1 through 5
     * @return WebElement
     */
    public WebElement getEmailFriendTextBox(int num) {
        return locateWebElement("InviteEmail" + num);
    }

    /**
     *
     * @return WebElement
     */
    public WebElement getEmailFriend1TextBox() {
        return locateWebElement("InviteEmail1");
    }

    /**
     *
     * @return WebElement
     */
    public WebElement getEmailFriend2TextBox() {
        return locateWebElement("InviteEmail2");
        }

    /**
     *
     * @return WebElement
     */
    public WebElement getEmailFriend3TextBox() {
        return locateWebElement("InviteEmail3");
    }

    /**
     *
     * @return WebElement
     */
    public WebElement getEmailFriend4TextBox() {
        return locateWebElement("InviteEmail4");
    }

    /**
     *
     * @return WebElement
     */
    public WebElement getEmailFriend5TextBox() {
        return locateWebElement("InviteEmail5");
    }

    /**
     * Get the invite button found in
     * Step 3 of the sign up flow.
     * @return WebElement
     */
    public WebElement getInviteButton() {
        return locateWebElement("InviteButton");
    }

    /**
     * Get the skip link found in step 3
     * of the sign up flow.
     * @return WebElement
     */
    public WebElement getSkipLink() {
        return locateWebElement("SkipLink");
            }

    /**
     * Get the close button found in
     * step 4 of the sign up flow.
     * @return WebElement
     */
    public WebElement getCloseButton() {
        return locateWebElement("CloseButton");
    }

    ///error messaging

    /**
     * Get the error message that shows if an invalid
     * email is entered or if invite is clicked without entering
     * at least 1 email address.
     * @return WebElement
     */
    public WebElement getInviteErrorMessage() {
        return locateWebElement("InviteErrorMessage");
    }

}