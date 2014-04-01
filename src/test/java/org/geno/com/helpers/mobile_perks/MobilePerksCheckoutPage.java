package org.geno.com.helpers.mobile_perks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sargenziano
 * Date: 7/15/13
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class MobilePerksCheckoutPage {
    private final WebDriver driver;
    protected MobilePerksHeader header;

    public MobilePerksCheckoutPage (WebDriver driver) {
        this.driver = driver;
        this.header = PageFactory.initElements(driver, MobilePerksHeader.class);
    }

    @FindBy(css=".products-list>tbody>tr")
    private List<WebElement> productList;

    @FindBy(css=".cost tr:nth-child(1) .price>span")
    private WebElement orderSubtotal;

    @FindBy(css=".cost tr:nth-child(2) .price>span")
    private WebElement shippingTotal;

    @FindBy(css=".cost tr:nth-child(3) .price>span")
    private WebElement taxTotal;

    @FindBy(css=".cost tr:nth-child(4) .price>span")
    private WebElement orderTotal;

    @FindBy(css="#order>form>a")
    private WebElement continueShoppingButton;

    @FindBy(css="#order>form>button")
    private WebElement submitOrderButton;

    @FindBy(css=".credit-card>p")
    private WebElement payWithText;

    @FindBy(css=".credit-card>a")
    private WebElement changeCCLink;

    @FindBy(css=".shipping>address")
    private WebElement shippingAddressText;

    @FindBy(css=".shipping>a")
    private WebElement changeShippingAddressLink;

    @FindBy(css=".billing>address")
    private WebElement billingAddressText;

    @FindBy(css=".billing>a")
    private WebElement changeBillingAddressLink;

    @FindBy(css="#confirmation>a")
    private WebElement myPerksButton;



}
