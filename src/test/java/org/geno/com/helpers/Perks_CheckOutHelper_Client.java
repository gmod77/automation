package org.geno.com.helpers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

import java.util.List;

/**
 * This class contains all the methods required to complete
 * a sign up on geno Perks. Most methods return a WebElement to
 * be interacted with.
 */
public class Perks_CheckOutHelper_Client extends IHelper {

    public Perks_CheckOutHelper_Client(WebDriver client) {
        super(client,"Perks_Checkout_v2.xml");
    }

//My Cart

    /**
     * My Cart
     * Gets the web element for the title of My Cart
     * @return WebElement
     */
    public WebElement getYourCartTitle() {
        return locateWebElement("YourCartTitle");
    }

    /**
     * My Cart
     * Get the item name list
     * @return WebElement
     */
    public List<WebElement> getItemName() {
        return locateWebElements("ItemName");
    }

    /**
     * My Cart
     * Get the item offer name found below the item name
     * @return WebElement
     */
    public List<WebElement> getItemOffer() {
        return locateWebElements("ItemOffer");
    }

    /**
     * My Cart
     * Get the item's delivery method
     * @return WebElement
     */
    public List<WebElement> getItemDeliveryType() {
        return locateWebElements("DeliveryType");
    }

    /**
     * My Cart
     * Get the item's quantity dropdown
     * @return WebElement
     */
    public List<WebElement> getQtyDropDown() {
        return locateWebElements("qtyDropDown");
    }

    /**
     * My Cart
     * Get the item's regular price
     * @return WebElement
     */
    public List<WebElement> getCartPrice() {
        return locateWebElements("cartPrice");
    }

    /**
     * My Cart
     * Get the item's subtotal amount (price x quantity)
     * @return WebElement
     */
    public List<WebElement> getItemSubTotal() {
        return locateWebElements("itemSubTotal");
    }

    /**
     * My Cart
     * Get the item's remove button
     * @return WebElement
     */
    public WebElement getItemRemoveButton() {
        return locateWebElement("removeButton");
    }

    /**
     * My Cart
     * Get the Coupon Code text box
     * @return WebElement
     */
    public WebElement getCouponCodeBox() {
        return locateWebElement("couponCodeBox");
    }

    /**
     * My Cart
     * Get the Coupon Code Apply link
     * @return WebElement
     */
    public WebElement getCouponCodeApplyLink() {
        return locateWebElement("applyLink");
    }

    /**
     * My Cart
     * Get the grand total amount of the order
     * @return WebElement
     */
    public WebElement grandTotal() {
        return locateWebElement("grandTotal");
    }

    /**
     * My Cart
     * Get the continue shopping button
     * @return WebElement
     */

    public WebElement getContinueShoppingButton() {
        return locateWebElement("continueShoppingButton");
    }

    /**
     * My Cart
     * Get the checkout button to continue purchase
     * @return WebElement
     */
    public WebElement getCheckoutButton() {
        return locateWebElement("checkoutButton");
    }

    /**
     * First Time Purchase
     * Get the first name text box under personal info
     * @return WebElement
     */
    public WebElement getFirstNameBox() {
        return locateWebElement("firstName");
    }

    public WebElement getLastNameBox() {
        return locateWebElement("lastName");
    }

    public WebElement getNameOnCardBox() {
        return locateWebElement("nameOnCardBox");
    }

    public WebElement getCreditCardTypeDropdown() {
        return locateWebElement("creditCardTypeDropdown");
    }

    public List<WebElement> getCreditCardDropDownOptions() {
        return locateWebElements("creditCardList");
    }

    public WebElement getCreditCardNumberBox() {
        return locateWebElement("cardNumberBox");
    }

    public WebElement getExpMonthDropdown() {
        return locateWebElement("expMonthDropdown");
    }

    public List<WebElement> getExpMonthDropDownOptions() {
        return locateWebElements("expMonthList");
    }

    public WebElement getExpYearDropdown() {
        return locateWebElement("expYearDropdown");
    }

    public List<WebElement> getExpYearDropDownOptions() {
        return locateWebElements("expYearList");
    }

    public WebElement getBillingFirstNameBox() {
        return locateWebElement("billFirstNameBox");
    }

    public WebElement getBillingLastNameBox() {
        return locateWebElement("billLastNameBox");
    }

    public WebElement getBillingStreetAddress1Box() {
        return locateWebElement("billStreetAddressBox");
    }

    public WebElement getBillingStreetAddress2Box() {
        return locateWebElement("billStreetAddress2Box");
    }

    public WebElement getBillingCityBox() {
        return locateWebElement("billCityBox");
    }
    
    public WebElement getBillingStateDropdown() {
        return locateWebElement("billStateDropdown");
    }

    public List<WebElement> getBillingStateDropDownOptions() {
        return locateWebElements("billStateList");
    }

    public WebElement getBillingZipcodeBox() {
        return locateWebElement("billZipcodeBox");
    }

    public WebElement getBillingPhoneNumberBox() {
        return locateWebElement("billPhoneNumberBox");
    }

    public WebElement getShippingSameAsBillingCheckbox() {
        return locateWebElement("shipSameAsBillCheckbox");
    }

    public WebElement getShippingFirstNameBox() {
        return locateWebElement("shipFirstNameBox");
    }

    public WebElement getShippingLastNameBox() {
        return locateWebElement("shipLastNameBox");
    }

    public WebElement getShippingStreetAddress1Box() {
        return locateWebElement("shipStreetAddressBox");
    }

    public WebElement getShippingStreetAddress2Box() {
        return locateWebElement("shipStreetAddress2Box");
    }

    public WebElement getShippingCityBox() {
        return locateWebElement("shipCityBox");
    }

    public WebElement getShippingStateDropdown() {
        return locateWebElement("shipStateDropdown");
    }

    public List<WebElement> getShippingStateDropDownOptions() {
        return locateWebElements("shipStateList");
    }

    public WebElement getShippingZipcodeBox() {
        return locateWebElement("shipZipcodeBox");
    }

    public WebElement getShippingPhoneNumberBox() {
        return locateWebElement("shipPhoneNumberBox");
    }

    public WebElement getContinueButton() {
        return locateWebElement("continueButton");
    }

// Final Order Submission Page
    public List<WebElement> getFinalItemList() {
        return locateWebElements("finalItemList");
    }

    public List<WebElement> getFinalItemQtyList() {
        return locateWebElements("finalItemQtyList");
    }

    public List<WebElement> getItemPriceList() {
        return locateWebElements("finalItemPriceList");
    }

    public List<WebElement> getItemSubtotalList() {
        return locateWebElements("finalItemSubtotalList");
    }

    public WebElement getFinalOrderSubtotalAmount() {
        return locateWebElement("finalOrderSubtotalAmount");
    }

    public WebElement getFinalOrderShippingAmount() {
        return locateWebElement("finalOrderShippingAmount");
    }

    public WebElement getFinalOrderTaxAmount() {
        return locateWebElement("finalOrderTaxAmount");
    }

    public WebElement getFinalOrderTotalAmount() {
        return locateWebElement("finalOrderTotalAmount");
    }

    public WebElement getCheckoutContinueShoppingButton() {
        return locateWebElement("checkoutContinueShoppingButton");
    }

    public WebElement getLeftSubmitOrderButton() {
        return locateWebElement("LeftSubmitOrderButton");
    }

    public WebElement getEditCartLink() {
        return locateWebElement("editCartLink");
    }

    public WebElement getSummaryOrderSubtotalAmount() {
        return locateWebElement("summaryOrderSubtotalAmount");
    }

    public WebElement getSummaryOrderShippingAmount() {
        return locateWebElement("summaryOrderShippingAmount");
    }

    public WebElement getSummaryOrderTaxAmount() {
        return locateWebElement("summaryOrderTaxAmount");
    }

    public WebElement getSummaryOrderTotalAmount() {
        return locateWebElement("summaryOrderTotalAmount");
    }

    public WebElement getRightSubmitOrderButton() {
        return locateWebElement("RightSubmitOrderButton");
    }

    public WebElement getShipToAddress() {
        return locateWebElement("shipToAddress");
    }

    public WebElement getChangeShipToAddressButton() {
        return locateWebElement("changeShippingButton");
    }

    public WebElement getBillToAddress() {
        return locateWebElement("billToAddress");
    }

    public WebElement getChangeBillToAddressButton() {
        return locateWebElement("changeBillingButton");
    }

    public WebElement getCreditCardType() {
        return locateWebElement("creditType");
    }

    public WebElement getChangeCCButton() {
        return locateWebElement("changeCCButton");
    }

    public WebElement getAuthroizeSealImage() {
        return locateWebElement("authorizeSealImage");
    }

// Receipt Page
    public WebElement getThePerkIsYoursText() {
        return locateWebElement("thePerkIsYoursText");
    }

    public WebElement getMyPerksButton() {
        return locateWebElement("myPerksButton");
    }
    
    public WebElement getReceiptShipToAddress() {
        return locateWebElement("receiptShipToAddress");
    }
    
    public WebElement getReceiptCreditType() {
        return locateWebElement("receiptCreditType");
    }
    
    public WebElement getCCProcessedAmount() {
        return locateWebElement("ccProcessedAmount");
    }
}