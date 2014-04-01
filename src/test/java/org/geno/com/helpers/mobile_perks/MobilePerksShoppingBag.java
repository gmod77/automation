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
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class MobilePerksShoppingBag {
    private final WebDriver driver;
    protected MobilePerksHeader header;

    public MobilePerksShoppingBag (WebDriver driver) {
        this.driver = driver;
        this.header = PageFactory.initElements(driver, MobilePerksHeader.class);
    }

    @FindBy(css="#cart>form>div")
    private List<WebElement> cartItems;

    @FindBy(css="h4>.price")
    private WebElement subTotal;

    @FindBy(css=".checkout")
    private WebElement checkoutButton;

    @FindBy(css="#have-code")
    private WebElement memberCodeLink;

    @FindBy(css="#coupon_code")
    private WebElement couponCodeBox;

    @FindBy(css="#member-code>form>button")
    private WebElement couponApplyButton;

    // Is this unused? Try inputting an incorrect code or leave blank & hit apply nothing happens 7/15
    @FindBy(css=".error")
    private WebElement couponErrorText;


    public MobilePerksCheckoutPage clickCheckoutButton() {
        checkoutButton.click();
        return PageFactory.initElements(driver,MobilePerksCheckoutPage.class);
    }

}
