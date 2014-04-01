package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

import java.util.List;

/**
 * Contains all the returns for the web elements found
 * in Magento--Perks Admin Tool.
 * @author geno
 */
public class Perks_MagentoHelper_Client extends IHelper {

    public Perks_MagentoHelper_Client(WebDriver client) {
        super(client,"Perks_Magento.xml");
    }

    /**
     * Get the user name text box found on the login page
     * @return
     */
    public WebElement getUserNameTextBox() {
        return locateWebElement("UserName");
    }

    /**
     * Get the password text box found on the login page
     * @return
     */
    public WebElement getPasswordTextBox() {
        return locateWebElement("Password");
    }

    /**
     * Get the login button found on the login page
     * @return
     */
    public WebElement getLoginButton() {
        return locateWebElement("LoginButton");
    }

    /**
     * Get the message popup window that may appear
     * after login
     * @return
     */
    public WebElement getMessagePopupWindow() {
        return locateWebElement("PopupWindow");
    }

    /**
     * Get the message popup window close button
     * @return
     */
    public WebElement getMessageWindowCloseButton() {
        return locateWebElement("ClosePopupButton");
    }

    /**
     * Get the Manage Products link found in the header
     * @return
     */
    public WebElement getManageProductsLink() {
        return locateWebElement("ManageProductsLink");
    }

    /**
     * Get the Add Product button found on the Manage
     * Products page in the top right.
     * @return
     */
    public WebElement getAddProductButton() {
        return locateWebElement("AddProductButton");
    }

    /**
     * Get the Attribute Set dropdown found on the
     * New Product page after clicking Add Product
     * @return
     */
    public WebElement getAttributeSetDropDown() {
        return locateWebElement("AttributeSetDropDown");
    }

    /**
     * Get the Product Type dropdown found on the
     * New Product page after clicking Add Product
     * @return
     */
    public WebElement getProductTypeDropDown() {
        return locateWebElement("ProductTypeDropDown");
    }

    /**
     * Get the continue button found on the New Product
     * page after clicking Add Product
     * @return
     */
    public WebElement getContinueButton() {
        return locateWebElement("ContinueButton");
    }

    /**
     * Get the Business Name text box found on the
     * General tab
     * @return
     */
    public WebElement getBizNameTextBox() {
        return locateWebElement("BizNameTextBox");
    }

    /**
     * Get the Addiional Info text box found on the
     * General tab
     * @return
     */
    public WebElement getAddtlInfoTextBox() {
        return locateWebElement("AddtlInfoTextBox");
    }

    /**
     * Get the Description text box found on the
     * General tab
     * @return
     */
    public WebElement getDescriptionTextBox() {
        return locateWebElement("DescriptionTextBox");
    }

    /**
     * Get the subject text box found on the General
     * tab
     * @return
     */
    public WebElement getSubjectTextBox() {
        return locateWebElement("SubjectTextBox");
    }

    /**
     * Get the offer text box found on the
     * general tab
     * @return
     */
    public WebElement getOfferTextBox() {
        return locateWebElement("OfferTextBox");
    }

    /**
     * Get the offer summary text box found
     * on the general tab
     * @return
     */
    public WebElement getOfferSumTextBox() {
        return locateWebElement("OfferSumTextBox");
    }

    /**
     * Get the internal name text box found on the
     * general tab
     * @return
     */
    public WebElement getInternalNameTextBox() {
        return locateWebElement("InternalNameTextBox");
    }

    /**
     * Get the SKU text box found on the general
     * tab
     * @return
     */
    public WebElement getSKUTextBox() {
        return locateWebElement("SKUTextBox");
    }

    /**
     * Get the Show QTY drop down found on the
     * general tab
     * @return
     */
    public WebElement getShowQtyDropDown() {
        return locateWebElement("ShowQtyDropDown");
    }

    /**
     * Get the module headline text box found
     * on the general tab
     * @return
     */
    public WebElement getModuleHeadlineTextBox() {
        return locateWebElement("ModuleHeadlineTextBox");
    }

    /**
     * Get the iPhone headline text box found
     * on the general tab
     * @return
     */
    public WebElement getIphoneHeadlineTextBox() {
        return locateWebElement("IphoneHeadlineTextBox");
    }

    /**
     * Get the item page headline text box found on the
     * general tab
     * @return
     */
    public WebElement getItemPageHeadlineTextBox() {
        return locateWebElement("ItemPageHeadlineTextBox");
    }

    /**
     * Get the status drop down found on the general tab
     * @return
     */
    public WebElement getStatusDropDown() {
        return locateWebElement("StatusDropDown");
    }

    /**
     * Get the Dashboard Page link
     * @return
     */
    public WebElement getDashboardPageLink() {
        return locateWebElement("DashboardPage");
    }

    /**
     * Get the Dashboard 1 Middle Text box
     * found on the Dashboard tab
     * @return
     */
    public WebElement getDB1TextMiddleTextBox() {
        return locateWebElement("DB1TextMiddleTextBox");
    }

    /**
     * Get the Dashboard 1 Top Text box
     * found on the Dashboard tab
     * @return
     */
    public WebElement getDB1TextTopTextBox() {
        return locateWebElement("DB1TextTopTextBox");
    }

    /**
     * Get the Dashboard 2 Middle Text box
     * found on the Dashboard tab
     * @return
     */
    public WebElement getDB2TextMiddleTextBox() {
        return locateWebElement("DB2TextMiddleTextBox");
    }

    /**
     * Get the Dashboard 2 Top Text box
     * found on the Dashboard tab
     * @return
     */
    public WebElement getDB2TextTopTextBox() {
        return locateWebElement("DB2TextTopTextBox");
    }

    /**
     * Get the Dashboard 3 Middle Text box
     * found on the Dashboard tab
     * @return
     */
    public WebElement getDB3TextMiddleTextBox() {
        return locateWebElement("DB3TextMiddleTextBox");
    }

    /**
     * Get the Dashboard 3 Top Text box
     * found on the Dashboard tab
     * @return
     */
    public WebElement getDB3TextTopTextBox() {
        return locateWebElement("DB3TextTopTextBox");
    }

    /**
     * Get the Dashboard 1 position drop down
     * found on the Dashboard tab
     * @return
     */
    public WebElement getDBPos1DropDown() {
        return locateWebElement("DBPos1DropDown");
    }

    /**
     * Get the Dashboard 2 position drop down
     * found on the Dashboard tab
     * @return
     */
    public WebElement getDBPos2DropDown() {
        return locateWebElement("DBPos2DropDown");
    }

    /**
     * Get the Dashboard 3 position drop down
     * found on the Dashboard tab
     * @return
     */
    public WebElement getDBPos3DropDown() {
        return locateWebElement("DBPos3DropDown");
    }

    /**
     * Get the Dashboard 4 position drop down
     * found on the Dashboard tab
     * @return
     */
    public WebElement getDBPos4DropDown() {
        return locateWebElement("DBPos4DropDown");
    }

    /**
     * Get the Inventory Counters page
     * @return
     */
    public WebElement getInvCountersPageLink() {
        return locateWebElement("InventoryCountersPage");
    }

    /**
     * Get the End Date text box found on the
     * inventory counters tab
     * @return
     */
    public WebElement getEndDateTextBox() {
        return locateWebElement("EndDate");
    }

    /**
     * Get the End hour text box found on the
     * inventory counters tab
     * @return
     */
    public WebElement getEndHourTextBox() {
        return locateWebElement("EndHour");
    }

    /**
     * Get the time format drop down found
     * on the inventory counters tab
     * @return
     */
    public WebElement getTimerFormatDropDown() {
        return locateWebElement("FormatDropDown");
    }

    /**
     * Get the Prices page link
     * @return
     */
    public WebElement getPricePageLink() {
        return locateWebElement("PricePage");
    }

    /**
     * Get the cost text box found on the
     * prices tab
     * @return
     */
    public WebElement getCostTextBox() {
        return locateWebElement("CostTextbox");
    }

    /**
     * Get the price text box found on the
     * prices tab
     * @return
     */
    public WebElement getPriceTextBox() {
        return locateWebElement("PriceTextBox");
    }

    /**
     * Get the tax class drop down found on
     * the prices tab
     * @return
     */
    public WebElement getTaxClassDropDown() {
        return locateWebElement("TaxClassDropDown");
    }

    /**
     * Get the Images page link
     * @return
     */
    public WebElement getImagesPageLink() {
        return locateWebElement("ImagesPage");
    }

    /**
     * Get the Inventory page link
     * @return
     */
    public WebElement getInventoryPageLink() {
        return locateWebElement("InventoryPage");
    }

    /**
     * Get the Managed stock check box on the
     * inventory tab. May be called "Use Config Settings"
     * @return
     */
    public WebElement getManageStockCheckBox() {
        return locateWebElement("ManageStockCheckBox");
    }

    /**
     * Get the Managed stock drop down found
     * on the Inventory tab
     * @return
     */
    public WebElement getManageStockDropDown() {
        return locateWebElement("ManageStockDropDown");
    }

    /**
     * Get the Available Stock drop down found
     * on the Inventory tab
     * @return
     */
    public WebElement getStockAvailDropDown() {
        return locateWebElement("StockAvailabilityDropDown");
    }

    /**
     * Get the Inventory Qty text box found on
     * the inventory tab
     * @return
     */
    public WebElement getInventoryQtyTextBox() {
        return locateWebElement("InventoryQtyTextBox");
    }

    /**
     * Get the WebSites page link
     * @return
     */
    public WebElement getWebSitePageLink() {
        return locateWebElement("WebSitePage");
    }

    /**
     * Get the Main Website check box found
     * on the inventory tab
     * @return
     */
    public WebElement getMainWebSiteCheckBox() {
        return locateWebElement("MainWebSiteCheckBox");
    }

    /**
     * Get the Categories page link
     * @return
     */
    public WebElement getCategoriesPageLink() {
        return locateWebElement("CategoriesPage");
    }

    /**
     * Get the Product category tree found on the
     * categories page. Note: This returns the entire
     * tree. Can be used for the 'wait' condition.
     * @return
     */
    public WebElement getProductCategoryTree() {
        return locateWebElement("ProductCategoryTree");
    }

    /**
     * Get the individual list of elements found within
     * the product category tree. i.e. National, New York,
     * Chicago, Miami, etc.
     * @return
     */
    public List<WebElement> getProductCategories() {
        return locateWebElements("ProductCategories");
    }

    /**
     * Get reporting page link
     * @return
     */
    public WebElement getReportingPageLink() {
        return locateWebElement("ReportingPage");
    }

    /**
     * Get the Rev Share text box found on the
     * reporting tab
     * @return
     */
    public WebElement getRevShareTextBox() {
        return locateWebElement("RevShareTextBox");
    }

    /**
     * Get Size Page link
     * @return
     */
    public WebElement getSizePageLink() {
        return locateWebElement("SizePage");
    }

    /**
     * Get the Size drop down found on the
     * size tab.
     * @return
     */
    public WebElement getSizeDropDown() {
        return locateWebElement("SizeDropDown");
    }

    /**
     * Get the Downloadable page
     * @return
     */
    public WebElement getDownloadablePageLink() {
        return locateWebElement("DownloadablePage");
    }

    /**
     * Get the Link item button found on the
     * downloadable tab
     * @return
     */
    public WebElement getLinkItemButton() {
        return locateWebElement("LinkItemButton");
    }

    /**
     * Get the Purchase type dropdown found on the
     * downloadable tab
     * @return
     */
    public WebElement getPurchaseTypeDropDown() {
        return locateWebElement("PurchaseTypeDropDown");
    }

    /**
     * Get the success message
     * @return
     */
    public WebElement getSuccessMessage() {
        return locateWebElement("SuccessMessage");
    }
}