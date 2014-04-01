package org.geno.com.helpers.mobile_perks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sargenziano
 * Date: 7/17/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class MobilePerksLoginPage {
    private final WebDriver driver;
    protected MobilePerksHeader header;

    public MobilePerksLoginPage (WebDriver driver) {
        this.driver = driver;
        this.header = PageFactory.initElements(driver, MobilePerksHeader.class);
    }

    @FindBy(css="#loginEmail")
    private WebElement emailBox;

    @FindBy(css="#password")
    private WebElement passwordBox;

    @FindBy(css="#signin>fieldset>button")
    private WebElement signInButton;

    @FindBy(css="#remember_me")
    private WebElement rememberMeCheckbox;

    @FindBy(css=".checkbox>.switcher.directLink")
    private WebElement forgotPWLink;

    @FindBy(css="#forgotEmail")
    private WebElement forgotEmailBox;

    @FindBy(css="#forgotpass>fieldset>button")
    private WebElement resetButton;

    @FindBy(css=".errorfield.slideDown>div")
    private WebElement forgotPWErrorTxt;

    @FindBy(css=".errorfield.slideDown:")
    private WebElement errorText;

    public MobilePerksLoginPage enterEmail(String email) {
        emailBox.sendKeys(email);
        return this;
    }

    public MobilePerksLoginPage enterPw(String pw) {
        passwordBox.sendKeys(pw);
        return this;
    }

    public boolean rememberMeIsChecked() {
        return rememberMeCheckbox.isSelected();
    }

    public MobilePerksLoginPage checkRememberMe() {
        rememberMeCheckbox.click();
        return this;
    }

    public MobilePerksLoginPage clickForgotPwLink() {
        forgotPWLink.click();
        return this;
    }

    public MobilePerksLoginPage enterForgotPwEmail(String email) {
        forgotEmailBox.sendKeys(email);
        return this;
    }

    public MobilePerksLoginPage clickResetButton() {
        resetButton.click();
        return this;
    }

    public String getForgotPWErrorTxt() {
        return forgotPWErrorTxt.getText().trim();
    }

    public String getErrorTxt() {
        return errorText.getText().trim();
    }

    public MobilePerksLoginPage clickSigninButton() {
        signInButton.click();
        return this;
    }


}
