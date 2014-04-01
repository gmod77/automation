package org.geno.com.pageObjects.perks;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PerksHeader {

    private final WebDriver driver;

    public PerksHeader(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css=".facebook>a")
    private WebElement FBbutton;

    @FindBy(css=".twitter>a")
    private WebElement twitterButton;

    @FindBy(css=".geno>a")
    private WebElement udHeaderLink;

    @FindBy(css=".perks>a")
    private WebElement perksHeaderLink;

    @FindBy(css=".the-chronicles>a")
    private WebElement chroniclesHeaderLink;

    @FindBy(css=".kempt>a")
    private WebElement kemptHeaderLink;

    @FindBy(css="driven>a")
    private WebElement drivenHeaderLink;

    @FindBy(css=".useOverlay>span")
    private WebElement shoppingCart;

    // document.querySelector("#global-navigation .user .sub ul").style.display = "block" <-- To make dropdown visible
    private final String signInCssLocation = "#global-navigation .user .sub";
    @FindBy(css=signInCssLocation)
    private WebElement signInHover;

    @FindBy(css=".menu-member-login>.useAjaxProxy.useOverlay")
    private WebElement loginButton;

    @FindBy(css=".menu-signup.last>.useAjaxProxy.useOverlay")
    private WebElement signUpButton;

    @FindBy(css=".sub.loggedin>ul>li:nth-child(1)>a")
    private WebElement myPerksLink;

    @FindBy(css=".sub.loggedin>ul>li:nth-child(2)>a")
    private WebElement accountSettingsLink;

    @FindBy(css=".sub.loggedin>ul>li:nth-child(3)>a")
    private WebElement logoutLink;

    @FindBy(css=".header-logo>a>img")
    private WebElement perksLogo;

    @FindBy(id="promotion-header")
    private WebElement promotionalHeader;

    // document.querySelector(".dropdown .contents").style.display = "block" <-- To show the list
    // .dropdown>contents>ul>li>a <-- to get list of all dropdown links
    @FindBy(css=".dropdown")
    private WebElement cityDropdown;

    @FindBy(css=".breadcrumbs p.selected")
    @CacheLookup
    private WebElement breadCrumbCity; // Note: Padded with white space

    @FindBy(css=".invite.useAjaxProxy.useOverlay")
    private WebElement inviteFriendsButton;


    public PerksHeader clickFBButton() {
        return this;
    }

    public PerksHeader clickTwitterButton() {
        return this;
    }

    public PerksHeader clickgenoLink() {
        udHeaderLink.click();
        return this;
    }

    public PerksHomePage clickPerksLink() {
        perksHeaderLink.click();
        return PageFactory.initElements(driver,PerksHomePage.class);
    }

    public PerksHeader clickChroniclesLink() {
        chroniclesHeaderLink.click();
        return this;
    }

    public PerksHeader clickKemptLink() {
        kemptHeaderLink.click();
        return this;
    }

    public PerksHeader clickDrivenLink() {
        drivenHeaderLink.click();
        return this;
    }

    //Perks header link should be highlighted and have a class of active
    public boolean perksLinkActive() {
        return perksHeaderLink.getAttribute("class").equals("active");
    }

    public String shoppingCartCount() {
        return shoppingCart.getText();
    }

    public PerksHeader clickShoppingCart() {
        shoppingCart.click();
        return this;
    }

    public PerksSignUp clickLoginLink() {
        activateSignInDropdown();
        loginButton.click();
        return PageFactory.initElements(driver,PerksSignUp.class);
    }

    public PerksSignUp clickSignUpLink() {
        activateSignInDropdown();
        signUpButton.click();
        return PageFactory.initElements(driver,PerksSignUp.class);
    }

    public PerksHeader clickMyPerks() {
        myPerksLink.click();
        return this;
    }

    public PerksHeader clickAccountSettings() {
        accountSettingsLink.click();
        return this;
    }

    public PerksHeader clickLogout() {
        logoutLink.click();
        return this;
    }

    public String getPromotionalHeaderText() {
        return promotionalHeader.getText().trim();
    }

    public String getBreadCrumbText() {
        return breadCrumbCity.getText();
    }

    public PerksHeader clickInviteFriendsButton() {
        inviteFriendsButton.click();
        return this;
    }

    private void activateSignInDropdown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "document.querySelector('" +
                signInCssLocation +
                " ul').style.display = 'block'";
        js.executeScript(script);
    }



}
