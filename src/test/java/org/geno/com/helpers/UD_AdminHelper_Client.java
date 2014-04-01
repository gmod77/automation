package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.geno.com.common.IHelper;

/**
 * This class contains all the methods required for the
 * UD Admin site.
 */
public class UD_AdminHelper_Client extends IHelper {

    public UD_AdminHelper_Client(WebDriver client) {
        super(client,"UD_Admin.xml");
    }

    public WebElement getStatusDropdown() {
        return locateWebElement("Status");
    }

    public WebElement getArticleIsDedicatedCheck() {
        return locateWebElement("DedicatedArticleCheck");
    }

    public WebElement getArticleTemplateDropdown() {
        return locateWebElement("ArticleTemplateTypeDropdown");
    }

    public WebElement getLayoutModuleDropdown() {
        return locateWebElement("LayoutModuleDropdown");
    }

    public WebElement getLayoutWidthDropdown() {
        return locateWebElement("LayoutModuleWidthDropDown");
    }

    public WebElement getAdCampaignDropdown() {
        return locateWebElement("AdCampaignDropdown");
    }

    public WebElement getAuthorDropdown() {
        return locateWebElement("AuthorDropdown");
    }

    public WebElement getEmailFromBox() {
        return locateWebElement("FromEmailDisplay");
    }

    public WebElement getEmailSegmentDropdown() {
        return locateWebElement("EmailSegmentDropdown");
    }

    public WebElement getArticleTitleBox() {
        return locateWebElement("ArticleTitleBox");
    }
    public WebElement getArticleBusinessSubjectBox() {
        return locateWebElement("ArticleBusinessSubjectBox");
    }

    public WebElement getArticleSubHeaderBox() {
        return locateWebElement("ArticleSubHeaderBox");
    }

    public WebElement getArticleEmailSubjectBox() {
        return locateWebElement("ArticleEmailSubjectBox");
    }

    public WebElement getArticleBlurbBox() {
        return locateWebElement("ArticleBlurbBox");
    }

    public WebElement getArticleIphoneBlurbBox() {
        return locateWebElement("ArticleIphoneBlurbBox");
    }

    public WebElement getArticleTwitterBlurbBox() {
        return locateWebElement("ArticleTwitterBlurbBox");
    }

    public WebElement getArticleLegalLineBox() {
        return locateWebElement("ArticleLegalLineBox");
    }

    public WebElement getArticleKeywordsBox() {
        return locateWebElement("ArticleKeywordsBox");
    }

    public WebElement getArticleBusinessTypeDropdown() {
        return locateWebElement("ArticleBusinessTypeDropdown");
    }

    public WebElement getBusinessSpecialtyBox() {
        return locateWebElement("ArticleBusinessSpecialtyBox");
    }

    public WebElement getSaveButton() {
        return locateWebElement("SaveButton");
    }

    // Note: Below element shares css selector between edit and image edit pages
    public WebElement getEditArticleSuccessConfirmationText() {
        return locateWebElement("SuccessConfirmationText");
    }

    public WebElement getArticleImageNameBox() {
        return locateWebElement("ArticleImageNameBox");
    }

    public WebElement getArticleImageIdBox() {
        return locateWebElement("ArticleImageIdBox");
    }

    public WebElement getArticleImagePositionDropdown() {
        return locateWebElement("ArticleImagePositionDropdown");
    }

    public WebElement getSaveAndAddImageButton() {
        return locateWebElement("SaveAndAddImage");
    }

    public WebElement getSelectLeftModuleDropdown() {
        return locateWebElement("SelectLeftModuleDropdown");
    }

    public WebElement getSelectRightModuleDropdown() {
        return locateWebElement("SelectRightModuleDropdown");
    }

    public WebElement getSelectCenterModuleDropdown() {
        return locateWebElement("SelectCenterModuleDropdown");
    }

    public WebElement getArticlePhotoCreditBox() {
        return locateWebElement("ArticlePhotoCreditBox");
    }

    public WebElement getArticleFeatureBox() {
        return locateWebElement("ArticleFeatureBox");
    }

    /**
     * Use this to get the requested dropdown when creating the
     * weekender article. There are 3 dropdowns to choose from:
     * 1,2, and 3.
     * @param day Enter 1,2, or 3.
     * @return WebElement
     */
    public WebElement getMultiArticleDayDropdown(int day) {
        WebElement el;
        switch(day) {
            case 1: el = locateWebElement("SelectDay1DropDown");
                    break;
            case 2: el = locateWebElement("SelectDay2DropDown");
                    break;
            case 3: el = locateWebElement("SelectDay3DropDown");
                    break;
            case 4: el = locateWebElement("SelectDay4DropDown");
                    break;
            case 5: el = locateWebElement("SelectDay5DropDown");
                    break;
            case 6: el = locateWebElement("SelectDay6DropDown");
                    break;
            default: el = null;
                    break;
        }
        return el;
    }

    public WebElement getMultiArticleHeaderBox(int day) {
        WebElement el;
        switch(day) {
            case 1: el = locateWebElement("Day1HeaderBox");
                break;
            case 2: el = locateWebElement("Day2HeaderBox");
                break;
            case 3: el = locateWebElement("Day3HeaderBox");
                break;
            case 4: el = locateWebElement("Day4HeaderBox");
                break;
            case 5: el = locateWebElement("Day5HeaderBox");
                break;
            case 6: el = locateWebElement("Day6HeaderBox");
                break;
            default: el = null;
                break;
        }
        return el;
    }

    public WebElement getMultiArticleSubHeaderBox(int day) {
        WebElement el;
        switch(day) {
            case 1: el = locateWebElement("Day1SubHeaderBox");
                break;
            case 2: el = locateWebElement("Day2SubHeaderBox");
                break;
            case 3: el = locateWebElement("Day3SubHeaderBox");
                break;
            case 4: el = locateWebElement("Day4SubHeaderBox");
                break;
            case 5: el = locateWebElement("Day5SubHeaderBox");
                break;
            case 6: el = locateWebElement("Day6SubHeaderBox");
                break;
            default: el = null;
                break;
        }
        return el;
    }

    public WebElement getMultiArticleSubheaderURLBox(int day) {
        WebElement el;
        switch(day) {
            case 1: el = locateWebElement("Day1SubheaderURLBox");
                break;
            case 2: el = locateWebElement("Day2SubheaderURLBox");
                break;
            case 3: el = locateWebElement("Day3SubheaderURLBox");
                break;
            case 4: el = locateWebElement("Day4SubheaderURLBox");
                break;
            case 5: el = locateWebElement("Day5SubheaderURLBox");
                break;
            case 6: el = locateWebElement("Day6SubheaderURLBox");
                break;
            default: el = null;
                break;
        }
        return el;
    }

    public WebElement getMultiArticleImageFileBox(int day) {
        WebElement el;
        switch(day) {
            case 1: el = locateWebElement("Day1ImageFileBox");
                break;
            case 2: el = locateWebElement("Day2ImageFileBox");
                break;
            case 3: el = locateWebElement("Day3ImageFileBox");
                break;
            case 4: el = locateWebElement("Day4ImageFileBox");
                break;
            case 5: el = locateWebElement("Day5ImageFileBox");
                break;
            case 6: el = locateWebElement("Day6ImageFileBox");
                break;
            default: el = null;
                break;
        }
        return el;
    }

    public WebElement getMultiArticleImageURLCheckBox(int day) {
        WebElement el;
        switch(day) {
            case 1: el = locateWebElement("Day1ImageURLCheckBox");
                break;
            case 2: el = locateWebElement("Day2ImageURLCheckBox");
                break;
            case 3: el = locateWebElement("Day3ImageURLCheckBox");
                break;
            case 4: el = locateWebElement("Day4ImageURLCheckBox");
                break;
            case 5: el = locateWebElement("Day5ImageURLCheckBox");
                break;
            case 6: el = locateWebElement("Day6ImageURLCheckBox");
                break;
            default: el = null;
                break;
        }
        return el;
    }

    public WebElement getMultiArticleImageURLBox(int day) {
        WebElement el;
        switch(day) {
            case 1: el = locateWebElement("Day1ImageURLBox");
                break;
            case 2: el = locateWebElement("Day2ImageURLBox");
                break;
            case 3: el = locateWebElement("Day3ImageURLBox");
                break;
            case 4: el = locateWebElement("Day4ImageURLBox");
                break;
            case 5: el = locateWebElement("Day5ImageURLBox");
                break;
            case 6: el = locateWebElement("Day6ImageURLBox");
                break;
            default: el = null;
                break;
        }
        return el;
    }

    public WebElement getMultiArticleAltTextBox(int day) {
        WebElement el;
        switch(day) {
            case 1: el = locateWebElement("Day1AltTextBox");
                break;
            case 2: el = locateWebElement("Day2AltTextBox");
                break;
            case 3: el = locateWebElement("Day3AltTextBox");
                break;
            case 4: el = locateWebElement("Day4AltTextBox");
                break;
            case 5: el = locateWebElement("Day5AltTextBox");
                break;
            case 6: el = locateWebElement("Day6AltTextBox");
                break;
            default: el = null;
                break;
        }
        return el;
    }
    public void enterArticleImage(String articleID, String imagePath, String positionName) {
        getArticleImageNameBox().sendKeys(imagePath);

        getArticleImageIdBox().sendKeys(articleID);

        Select dropdown = new Select(getArticleImagePositionDropdown());
        dropdown.selectByVisibleText(positionName);

        getSaveAndAddImageButton().click();

    }

    // Below methods are for Testing and Sending Email
    public WebElement getMailingSendToBox() {
        return locateWebElement("MailingSendToBox");
    }

    public WebElement getSendTestMailingButton() {
        return locateWebElement("SendTestMailingButton");
    }

    public WebElement getSendMailingButton() {
        return locateWebElement("SendMailingButton");
    }

    // Member Source creation methods
    public WebElement getMemberSourceNameBox() {
        return locateWebElement("MemberSourceNameBox");
    }

    public WebElement getMemberSourceSaveButton() {
        return locateWebElement("MemberSourceSaveButton");
    }
    
    // Background Takeover elements
    public WebElement getBGCampaignDropDown() {
        return locateWebElement("BGCampaignDropDown");
    }

    public WebElement getBGTColorTextBox() {
        return locateWebElement("BGColorTextBox");
    }
    
    public WebElement getBGLeftImageTxtBox() {
        return locateWebElement("BGLeftImageTextBox");
    }
    
    public WebElement getBGLeftImgWidthTextBox() {
        return locateWebElement("BGLeftWidthTextBox");
    }
    
    public WebElement getBGLeftImgHeightTextBox() {
        return locateWebElement("BGLeftHeightTextBox");
    }
    
    public WebElement getBGLeftAltTextBox() {
        return locateWebElement("BGLeftAltTextBox");
    }

    public WebElement getBGRightImageTxtBox() {
        return locateWebElement("BGRightImageTextBox");
    }

    public WebElement getBGRightImgWidthTextBox() {
        return locateWebElement("BGRightWidthTextBox");
    }

    public WebElement getBGRightImgHeightTextBox() {
        return locateWebElement("BGRightHeightTextBox");
    }

    public WebElement getBGRightAltTextBox() {
        return locateWebElement("BGRightAltTextBox");
    }

    public WebElement getBGSaveButton() {
        return locateWebElement("BGSaveButton");
    }

    public WebElement getBGSaveConfirmText() {
        return locateWebElement("BGSaveConfirmationText");
    }

}