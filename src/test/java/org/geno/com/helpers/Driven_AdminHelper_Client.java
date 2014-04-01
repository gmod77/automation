package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.geno.com.common.IHelper;

/**
 * This class contains all the methods required for the
 * UD Admin site.
 */
public class Driven_AdminHelper_Client extends IHelper {

    public Driven_AdminHelper_Client(WebDriver client) {
        super(client,"Driven_Admin.xml");
    }

    public WebElement getPostTitleBox() {
        return locateWebElement("ArticleTitleBox");
    }
    public WebElement getPostMainCopyBox() {
        return locateWebElement("ArticleMainCopyBox");
    }
    public WebElement getPostShortCopyBox() {
        return locateWebElement("ArticleShortCopyBox");
    }
    public WebElement getCategoryAcceleratorSelect() {
        return locateWebElement("CategoryAcceleratorSelect");
    }
    public WebElement getLeftBoxTitleBox() {
        return locateWebElement("LeftBoxTitleBox");
    }
    public WebElement getLeftBoxImageBox() {
        return locateWebElement("LeftBoxImageBox");
    }

    public WebElement getSelectFromMediaLeftLink() {
        return locateWebElement("SelectFromMediaLeftLink");
    }
    public WebElement getSelectFromMediaRightLink() {
        return locateWebElement("SelectFromMediaRightLink");
    }
    public WebElement getMediaLibraryLink() {
        return locateWebElement("MediaLibraryLink");
    }
    public WebElement getMLShowLink() {
        return locateWebElement("MLShowLink");
    }
    public WebElement getMLSelectImageLinkButton() {
        return locateWebElement("MLSelectImageLinkButton");
    }

    public WebElement getLeftBoxPhotoCreditsTitleBox() {
        return locateWebElement("LeftBoxPhotoCreditsTitleBox");
    }
    public WebElement getLeftBoxPhotoCreditsURLBox() {
        return locateWebElement("LeftBoxPhotoCreditsURLBox");
    }
    public WebElement getMiddleTextBox() {
        return locateWebElement("MiddleTextBox");
    }
    public WebElement getRightBoxTitleBox() {
        return locateWebElement("RightBoxTitleBox");
    }
    public WebElement getRightBoxImageBox() {
        return locateWebElement("RightBoxImageBox");
    }
    public WebElement getRightBoxPhotoCreditsBox() {
        return locateWebElement("RightBoxPhotoCreditsBox");
    }
    public WebElement getRightBoxPhotoCreditsURL() {
        return locateWebElement("RightBoxPhotoCreditsURL");
    }
    public WebElement getSetFeaturedImageLink() {
        return locateWebElement("SetFeaturedImageLink");
    }

    public WebElement getTemplateDropdown() {
        return locateWebElement("TemplateDropdown");
    }

    public WebElement getSaveDraftButton() {
        return locateWebElement("SaveDraftButton");
    }

    public WebElement getPublishButton() {
        return locateWebElement("PublishButton");
    }

    public WebElement getSetFeaturedImageButton() {
        return locateWebElement("SetFeaturedImageButton");
    }

    public WebElement getImageThumbnail() {
        return locateWebElement("ImageThumbnail");
    }

}