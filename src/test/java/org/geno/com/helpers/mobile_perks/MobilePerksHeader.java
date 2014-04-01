package org.geno.com.helpers.mobile_perks;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created with IntelliJ IDEA.
 * User: gmodin
 * Date: 7/10/13
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class MobilePerksHeader {
    private final WebDriver driver;

    public MobilePerksHeader(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css="#header>h1>a")
    private WebElement perksLogo;

    @FindBy(css=".nav")
    private WebElement nav;

    @FindBy(css=".cart")
    private WebElement cart;

    @FindBy(css="#edition-picker")
    private WebElement editionPicker;

    @FindBy(css=".level0.nav-1>a>span")
    private WebElement national;

    @FindBy(css=".level0.nav-2>a>span")
    private WebElement newyork;

    @FindBy(css=".level0.nav-3>a>span")
    private WebElement chicago;

    @FindBy(css=".level0.nav-4>a>span")
    private WebElement miami;

    @FindBy(css=".level0.nav-5>a>span")
    private WebElement boston;

    @FindBy(css=".level0.nav-6>a>span")
    private WebElement washingtonDC;

    @FindBy(css=".level0.nav-7>a>span")
    private WebElement losAngeles;

    @FindBy(css=".level0.nav-8>a>span")
    private WebElement sanFrancisco;

    @FindBy(css=".home>a")
    private WebElement homeButton;

    @FindBy(css=".myperks>a")
    private WebElement myPerksButton;

    @FindBy(css=".mycart>a")
    private WebElement myCartButton;

    @FindBy(css=".help>a")
    private WebElement helpButton;

    @FindBy(css=".logout>a")
    private WebElement logoutButton;

    @FindBy(css=".login>a")
    private WebElement loginButton;

    @FindBy(css=".close")
    private WebElement xCloseButton;


    public MobilePerksHomePage clickPerksLogoLink() {
        perksLogo.click();
        return PageFactory.initElements(driver,MobilePerksHomePage.class);
    }

    public MobilePerksHeader clickMenue() {
        nav.click();
        return this;
    }

    public MobilePerksHeader clickHomeButton() {
        nav.click();
        homeButton.click();
        return this;
    }

    public MobilePerksHeader clickMyPerksButton() {
        nav.click();
        myPerksButton.click();
        return this;
    }

    public MobilePerksHeader clickMyCartButton() {
        nav.click();
        myCartButton.click();
        return this;
    }

    public MobilePerksHeader clickHelpButton() {
        nav.click();
        helpButton.click();
        return this;
    }

    public MobilePerksLoginPage clickLoginButton() {
        nav.click();
        loginButton.click();
        return PageFactory.initElements(driver,MobilePerksLoginPage.class);
    }

    public MobilePerksHeader clickLogoutButton() {
        nav.click();
        logoutButton.click();
        return this;
    }

    public MobilePerksHeader clickXCloseButton() {
        nav.click();
        xCloseButton.click();
        return this;
    }

    public String getCurrentEdition(){
        return editionPicker.getText().trim();
    }

    public MobilePerksHeader clickEditionDropdown() {
        editionPicker.click();
        return this;
    }

    public MobilePerksHeader clickNationalEdition() {
        national.click();
        return this;
    }

    public MobilePerksHeader clickNewYorkEdition() {
        newyork.click();
        return this;
    }

    public MobilePerksHeader clickChicagoEdition() {
        chicago.click();
        return this;
    }

    public MobilePerksHeader clickMiamiEdition() {
        miami.click();
        return this;
    }

    public MobilePerksHeader clickBostonEdition() {
        boston.click();
        return this;
    }

    public MobilePerksHeader clickWashingtonDCEdition() {
        washingtonDC.click();
        return this;
    }

    public MobilePerksHeader clickLosAngelesEdition() {
        losAngeles.click();
        return this;
    }

    public MobilePerksHeader clickSanFranciscoEdition() {
        sanFrancisco.click();
        return this;
    }


    public MobilePerksHeader clickCart() {
        cart.click();
        return this;
    }

    public String getCartCount() {
        return cart.getText().trim();
    }


//
//    //Perks header link should be highlighted and have a class of active
//    public boolean perksLinkActive() {
//        return perksHeaderLink.getAttribute("class").equals("active");
//    }

}
