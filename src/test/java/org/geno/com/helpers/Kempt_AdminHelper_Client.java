package org.geno.com.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.geno.com.common.IHelper;

import java.util.List;

/**
 * This class contains all the methods required for the
 * UD Admin site.
 */
public class Kempt_AdminHelper_Client extends IHelper {

    public Kempt_AdminHelper_Client(WebDriver client) {
        super(client,"Kempt_Admin.xml");
    }

    public WebElement getPostTitleBox() {
        return locateWebElement("ArticleTitleBox");
    }

    public WebElement getTextTab() {
        return locateWebElement("TextTab");
    }

    public WebElement getPostMainCopyBox() {
        return locateWebElement("ArticleMainCopyBox");
    }

    public WebElement getLinkButton() {
        return locateWebElement("ArticleMainCopyLinkButton");
    }

    public WebElement getURLBox() {
        return locateWebElement("ArticleMainCopyLinkModalURLBox");
    }

    public WebElement getTitleBox() {
        return locateWebElement("ArticleMainCopyLinkModalTitleBox");
    }

    public WebElement getOLINWCheck() {
        return locateWebElement("ArticleMainCopyLinkModalOLINWCheck");
    }

    public WebElement getAddLinkButton() {
        return locateWebElement("ArticleMainCopyLinkModalAddLinkButton");
    }

    public WebElement getCategoryAcrossTheSeaSelect() {
        return locateWebElement("CategoryAcrossTheSeaSelect");
    }

    public WebElement getTagBox() {
        return locateWebElement("TagBox");
    }

    public WebElement getTagAddButton() {
        return locateWebElement("TagAddButton");
    }

    public WebElement getAddMediaButton() {
        return locateWebElement("AddMediaButton");
    }

    public WebElement getPublishButton() {
        return locateWebElement("PublishButton");
    }

    public List<WebElement> getImageThumbnail() {
        return locateWebElements("ImageThumbnail");
    }

    public WebElement getInsertIntoPostButton() {
        return locateWebElement("InsertIntoPostButton");
    }

}