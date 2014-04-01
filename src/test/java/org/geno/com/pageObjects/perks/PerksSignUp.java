package org.geno.com.pageObjects.perks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PerksSignUp {

    private WebDriver driver;

    public PerksSignUp (WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css=".modalContent>h1")
    private WebElement modalHeaderText;

    @FindBy(css=".modalContent>p")
    private WebElement modalSubHeader;

    @FindBy(id="email")
    private WebElement joinEmailBox;

    @FindBy(css=".over21Holder")
    private WebElement over21checkbox;

    @FindBy(css=".redBtn.acceptBtn.directLink.formSubmitLink>span")
    private WebElement acceptButton;

    @FindBy(css=".errorfield.slideDown>div")
    private WebElement errorText;

    @FindBy(id="loginEmail")
    private WebElement loginEmailBox;

    @FindBy(id="flowPassword")
    private WebElement loginPassword;

    @FindBy(css=".smallLink.forgotPass.directLink")
    private WebElement forgotPWLink;

    @FindBy(css=".redBtn.signInBtn.directLink.formSubmitLink>span")
    private WebElement signInButton;

    @FindBy(css=".rememberMeHolder>span")
    private WebElement rememberMeCheckbox;

    @FindBy(css=".legalNotices>.directLink:nth-child(1)")
    private WebElement privacyPolicyLink;

    @FindBy(css=".legalNotices>.directLink:nth-child(2)")
    private WebElement userAgreementLink;

    @FindBy(css=".pageTitle.no-cufon")
    private WebElement selectEditionsTitleText;

    @FindBy(css="#signupEditions>.editionsCheckboxes>.editionListItem")
    private List<WebElement> signUpEditions;

    @FindBy(css=".directLink.clickHere")
    private WebElement moreEditionsLink;

    @FindBy(css="#signupMoreEditions>.editionsCheckboxes>.editionListItem")
    private List<WebElement> signUpEditorialEditions;

    @FindBy(css=".redBtn.acceptBtn.directLink.formSubmitLink.formSubmitLink.submitLink>span")
    private WebElement editionsSubmitButton;

    @FindBy(id="flowPassword")
    private WebElement enterPasswordBox;

    @FindBy(id="flowPasswordRetype")
    private WebElement confirmPasswordBox;

    @FindBy(id="flowFirstName")
    private WebElement firstNameBox;

    @FindBy(id="flowLastName")
    private WebElement lastNameBox;

    @FindBy(id="flowGender")
    private WebElement genderDropDown;

    @FindBy(id="flowIncome")
    private WebElement incomeRangeDropDown;

    @FindBy(id="flowZip")
    private WebElement zipCodeBox;

    @FindBy(id="flowBirthday")
    private WebElement birthdayBox;

    @FindBy(css=".miscEmailFields input")
    private List<WebElement> frndEmailBox;

    @FindBy(css=".redBtn.acceptBtn.directLink.formSubmitLink.submitLink>span")
    private WebElement inviteButton;

    @FindBy(css="#additionalSkipLink>span")
    private WebElement skipButton;

    @FindBy(css=".pageTitle.no-cufon")
    private WebElement signUpConfTitle;

    @FindBy(css=".media.fb-media")
    private WebElement confFBSection;

    @FindBy(css=".media.tw-media")
    private WebElement confTwitSection;

    @FindBy(css=".media.ud-media")
    private WebElement udAppSection;

    @FindBy(css=".close-button.no-fade")
    private WebElement closeButton;


    public String signUpModalTitle() {
        return modalHeaderText.getText();
    }

    public String signUpModalSubheaderText() {
        return modalSubHeader.getText();
    }

    public Boolean outLookSignUpLinkExists() {
        return modalSubHeader.findElement(By.tagName("a")).isDisplayed();
    }

    public PerksSignUp enterSignUpEmail(String email) {
        joinEmailBox.clear();
        joinEmailBox.sendKeys(email);
        return this;
    }

    public Boolean over21boxIsChecked() {
        return over21checkbox.getAttribute("class").contains("checked");
    }

    public PerksSignUp clickOver21Box() {
        over21checkbox.click();
        return this;
    }

    public PerksSignUp clickAcceptButton() {
        acceptButton.click();
        return this;
    }

    /**
     * Gets the error text found when attempting to join
     * Possible Values:
     * You must enter an email address.
     * The email address {email} is invalid.
     * Please enter your password.
     * Please enter your email address.
     * Please enter your email address and password.
     *
     * @return String error text
     */
    public String getJoinErrorText() {
        return errorText.getText();
    }

    public PerksSignUp enterSignInEmail(String email) {
        loginEmailBox.clear();
        loginEmailBox.sendKeys(email);
        return this;
    }

    public PerksSignUp enterSignInPassword(String pw) {
        loginPassword.click();
        loginPassword.sendKeys(pw);
        return this;
    }

    public PerksSignUp clickForgotPWLink(){
        forgotPWLink.click();
        return this;
    }

    public PerksSignUp clickSignInButton() {
        signInButton.click();
        return this;
    }

    public boolean rememberMeIsEnabled() {
        return rememberMeCheckbox.isSelected();
    }

    public PerksSignUp clickRememberMe() {
        rememberMeCheckbox.click();
        return this;
    }

    public PerksSignUp clickPrivacyPolicyLink() {
        privacyPolicyLink.click();
        return this;
    }

    public PerksSignUp clickUserAgreementLink() {
        userAgreementLink.click();
        return this;
    }

    public String getSelectEditionsTitleText() {
        return selectEditionsTitleText.getText();
    }

    /**
     * Single check box for a specific Perk or Editorial edition
     * @param name National Perks, National Editorial, Chicago Perks, etc.
     * @param isPerks true if selecting perk editions, false after clicking "see more editions"
     * @return PerksSignUp
     */
    public PerksSignUp clickEdition(String name, boolean isPerks) {
        boolean gotChecked = false;

        List<WebElement> editions;

        if (isPerks) {
            editions = signUpEditions;
        } else {
            editions = signUpEditorialEditions;
        }

        for (WebElement edition : editions) {
            if (edition.findElement(By.cssSelector(".editionsHolderLabel")).getText().trim().equals(name)) {
                edition.findElement(By.tagName("input")).click();
                gotChecked = true;
            }
        }

        if (!gotChecked) throw new IllegalArgumentException("\"" + name + "\" Perk edition not found. Check name");

        return this;
    }

    public PerksSignUp clickMoreEditions() {
        moreEditionsLink.click();
        return this;
    }

    public PerksSignUp clickEditionsSubmitButton() {
        editionsSubmitButton.click();
        return this;
    }

    public PerksSignUp enterPassword(String pw) {
        enterPasswordBox.clear();
        enterPasswordBox.sendKeys(pw);
        return this;
    }

    public PerksSignUp reEnterPassword(String pw) {
        confirmPasswordBox.clear();
        confirmPasswordBox.sendKeys(pw);
        return this;
    }

    public PerksSignUp enterFirstName(String name) {
        firstNameBox.clear();
        firstNameBox.sendKeys(name);
        return this;
    }

    public PerksSignUp enterLastName(String name) {
        lastNameBox.clear();
        lastNameBox.sendKeys(name);
        return this;
    }

    public PerksSignUp selectGender(int index) {
        Select gender = new Select(genderDropDown);
        gender.selectByIndex(index);
        return this;
    }

    public PerksSignUp selectIncome(int index) {
        Select income = new Select(incomeRangeDropDown);
        income.selectByIndex(index);
        return this;
    }

    public PerksSignUp enterZip(String zip) {
        zipCodeBox.clear();
        zipCodeBox.sendKeys(zip);
        return this;
    }

    public PerksSignUp enterBirthday(String birthday) {
        birthdayBox.clear();
        birthdayBox.sendKeys(birthday);
        return this;
    }

    public PerksSignUp enterFriendEmail(int index, String email) {
        frndEmailBox.get(index).clear();
        frndEmailBox.get(index).sendKeys(email);
        return this;
    }

    public PerksSignUp clickInviteButton() {
        inviteButton.click();
        return this;
    }

    public PerksSignUp clickSkipButton() {
        skipButton.click();
        return this;
    }

    public String getSignUpConfirmationText() {
        return signUpConfTitle.getText();
    }

    public boolean confFBSectionExists() {
        return confFBSection.isDisplayed();
    }

    public boolean confTwitSectionExists() {
        return confTwitSection.isDisplayed();
    }

    public boolean confUDAppSectionExists() {
        return udAppSection.isDisplayed();
    }

    public PerksSignUp clickCloseButton() {
        closeButton.click();
        return this;
    }
}
