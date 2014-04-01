package org.geno.com.helpers.mobile_perks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: gmodin
 * Date: 7/10/13
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class MobilePerksHomePage {

    private final WebDriver driver;
    public MobilePerksHeader header;

    public MobilePerksHomePage (WebDriver driver) {
        this.driver = driver;
        this.header = PageFactory.initElements(driver,MobilePerksHeader.class);
    }

    @FindBy(css=".item")
    private List<WebElement> perkItemList;

    @FindBy(css=".item>div>img")
    private List<WebElement> perkItems;

    @FindBy(css=".item>div>header>h2")
    private List<WebElement> perksH2HeaderText;

    @FindBy(css=".item>div>header>h3")
    private List<WebElement> perksH3HeaderText;

    @FindBy(css=".cta>span")
    private List<WebElement> getNowBlocks;

    public int getPerksCount() {
        return perkItemList.size();
    }

    public String getPerkH2Text(int index) {
        return perkItemList.get(index).findElement(By.cssSelector("div>header>h2")).getText().trim();
    }

    public String getPerkH3Text(int index) {
        return perkItemList.get(index).findElement(By.cssSelector("div>header>h3")).getText().trim();
    }

    public String getPerkImageLink(int index) {
        return perkItemList.get(index).findElement(By.cssSelector("div>img")).getAttribute("src");
    }

    public String getPerkImageAltText(int index) {
        return perkItemList.get(index).findElement(By.cssSelector("div>img")).getAttribute("alt");
    }

    public MobilePerksProductPage clickGetItNow(int index) {
        perkItemList.get(index).findElement(By.cssSelector(".cta>span")).click();
        return PageFactory.initElements(driver,MobilePerksProductPage.class);
    }

    public MobilePerksHomePage focusProduct(int index) {
        new Actions(driver).moveToElement(perksH2HeaderText.get(index)).perform();
        //perkItemList.get(index).click();
        return this;
    }

    public MobilePerksHomePage printPerkList() {
        for (WebElement headlineEl : perksH3HeaderText) {
            System.out.println(headlineEl.getText());
        }
        return this;
    }

    public MobilePerksHomePage printAvailablePerks() {
        for (int i = 0; i < getNowBlocks.size(); i++) {
            if (getNowBlocks.get(i).getText().contains("GET")) {
                System.out.println(perksH3HeaderText.get(i).getText());
            }
        }
        return this;
    }

    public MobilePerksHomePage printUnAvailablePerks() {
        for (int i = 0; i < getNowBlocks.size(); i++) {
            if (getNowBlocks.get(i).getText().contains("OUT")) {
                System.out.println(perksH3HeaderText.get(i).getText());
            }
        }
        return this;
    }

}
