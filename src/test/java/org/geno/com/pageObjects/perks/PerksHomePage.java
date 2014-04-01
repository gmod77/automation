package org.geno.com.pageObjects.perks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PerksHomePage {

    private final WebDriver driver;
    public PerksHeader header;
    public PerksFooter footer;

    public PerksHomePage (WebDriver driver) {
        this.driver = driver;
        this.header = PageFactory.initElements(driver,PerksHeader.class);
        this.footer = PageFactory.initElements(driver,PerksFooter.class);
    }

    @FindBy(css="#promotion-banner>a")
    private WebElement promotionalBanner;

    @FindBy(css=".block-inner")
    private List<WebElement> perkBlocks;

    @FindBy(css=".copy.align>p")
    private List<WebElement> perksSubjectText;

    @FindBy(css=".copy.align>h1>a")
    private List<WebElement> perksHeadLineText;

    @FindBy(css=".book-now")
    private List<WebElement> getNowBlocks;

    @FindBy(css=".block>a>img")
    private List<WebElement> footerAdBlocks;

    @FindBy(css=".right>ul>li>a")
    private List<WebElement> footerLinks;

    @FindBy(css=".copyright>p")
    private WebElement copyrightText;

    @FindBy(css="#footer>.container>.perks>ul>li>a")
    private List<WebElement> footerPerkEditionLinks;

    public PerksHomePage printPerkList() {
        for (WebElement headlineEl : perksHeadLineText) {
            System.out.println(headlineEl.getText());
        }
        return this;
    }

    public PerksHomePage printAvailablePerks() {
        for (int i = 0; i < getNowBlocks.size(); i++) {
            if (getNowBlocks.get(i).getText().contains("GET")) {
                System.out.println(perksHeadLineText.get(i).getText());
            }
        }
        return this;
    }

    public PerksHomePage printUnAvailablePerks() {
        for (int i = 0; i < getNowBlocks.size(); i++) {
            if (getNowBlocks.get(i).getText().contains("OUT")) {
                System.out.println(perksHeadLineText.get(i).getText());
            }
        }
        return this;
    }

}
