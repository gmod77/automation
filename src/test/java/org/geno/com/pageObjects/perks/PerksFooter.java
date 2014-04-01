package org.geno.com.pageObjects.perks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PerksFooter {

    private final WebDriver driver;

    public PerksFooter(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css=".block>a>img")
    private List<WebElement> footerAdBlocks;

    @FindBy(css=".left>img")
    private WebElement UDFooterLogo;

    @FindBy(css=".right>ul>li>a")
    private List<WebElement> footerLinks;

    @FindBy(css=".copyright>p")
    private WebElement copyrightText;

    @FindBy(css="#footer>.container>.perks>ul>li>a")
    private List<WebElement> footerPerkEditionLinks;

    public PerksFooter getClickAdBlock(int index) {
        footerAdBlocks.get(index).click();
        return this;
    }

    public String getAdBlockAltText(int index) {
        return footerAdBlocks.get(index).getAttribute("alt");
    }

    public int getAdBlockCount() {
        return footerAdBlocks.size();
    }

    public Boolean udLogoIsDisplayed() {
        return UDFooterLogo.isDisplayed();
    }

    public String getCopyrightText() {
        return copyrightText.getText();
    }

    public String getFooterLinkName(int index) {
        if (index < 0 || index > 13) {
            throw new IllegalArgumentException("Index must be between 1 and 13");
        }

        return footerLinks.get(index - 1).getText();
    }

    public <T> T clickFooterLink(int index, Class<T> pageObj) {
        if (index < 0 || index > 13) {
            throw new IllegalArgumentException("Index must be between 1 and 13");
        }

        switch (index) {

            // About Us
            case 1: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver, PerksFooter.class));

            // Archives
            case 2: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // My Account
            case 3: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // Contact
            case 4: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // Jobs
            case 5: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // Advertise
            case 6: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // Tips
            case 7: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // Help
            case 8: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // Email Issues
            case 9: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // Privacy Policy
            case 10: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // User Agreement
            case 11: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // Unsubscribe
            case 12: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            // Editorial Policy
            case 13: footerLinks.get(index - 1).click();
                return pageObj.cast(PageFactory.initElements(driver,PerksFooter.class));

            default: return (T) this;
        }
    }

    public String getPerkEditionLinkName(int index) {
        if (index < 0 || index > 8) {
            throw new IllegalArgumentException("Index must be between 1 and 8");
        }
        return footerLinks.get(index - 1).getText();
    }

    public PerksHomePage clickPerkEditionLink(int index) {
        if (index < 0 || index > 8) {
            throw new IllegalArgumentException("Index must be between 1 and 8");
        }

        switch (index) {

            // National
            case 1: footerPerkEditionLinks.get(index - 1).click();
                return PageFactory.initElements(driver, PerksHomePage.class);

            // New York
            case 2: footerPerkEditionLinks.get(index - 1).click();
                return PageFactory.initElements(driver, PerksHomePage.class);

            // Chicago
            case 3: footerPerkEditionLinks.get(index - 1).click();
                return PageFactory.initElements(driver, PerksHomePage.class);

            // Miami
            case 4: footerPerkEditionLinks.get(index - 1).click();
                return PageFactory.initElements(driver, PerksHomePage.class);

            // Boston
            case 5: footerPerkEditionLinks.get(index - 1).click();
                return PageFactory.initElements(driver, PerksHomePage.class);

            // Washington DC
            case 6: footerPerkEditionLinks.get(index - 1).click();
                return PageFactory.initElements(driver, PerksHomePage.class);

            // Los Angeles
            case 7: footerPerkEditionLinks.get(index - 1).click();
                return PageFactory.initElements(driver, PerksHomePage.class);

            // San Francisco
            case 8: footerPerkEditionLinks.get(index - 1).click();
                return PageFactory.initElements(driver, PerksHomePage.class);

            default: return PageFactory.initElements(driver, PerksHomePage.class);
        }
    }

}
